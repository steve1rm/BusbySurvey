package me.androidbox.data.repository

import me.androidbox.data.BuildConfig
import me.androidbox.data.authorization.RegisterUserDto
import me.androidbox.data.authorization.UserDto
import me.androidbox.data.local.AuthorizationLocalDataSource
import me.androidbox.data.mappers.toAuthorizationInfo
import me.androidbox.data.mappers.toErrorResponseModel
import me.androidbox.data.mappers.toLoginResponseModel
import me.androidbox.data.mappers.toResetPasswordModel
import me.androidbox.data.models.AuthorizationInfoSerializable
import me.androidbox.data.remote.AuthorizationRemoteDataSource
import me.androidbox.domain.CheckResult
import me.androidbox.domain.DataError
import me.androidbox.domain.authorization.AuthorizationInfo
import me.androidbox.domain.authorization.models.ErrorResponseModel
import me.androidbox.domain.authorization.models.LoginRequestModel
import me.androidbox.domain.authorization.models.LoginResponseModel
import me.androidbox.domain.authorization.models.RegisterUserModel
import me.androidbox.domain.authorization.models.ResetPasswordModel
import me.androidbox.domain.repository.AuthorizationRepository

class AuthorizationRepositoryImp(
    private val authorizationRemoteDataSource: AuthorizationRemoteDataSource,
    private val authorizationLocalDataSource: AuthorizationLocalDataSource
) : AuthorizationRepository {
    override suspend fun register(registerUserUserModel: RegisterUserModel): CheckResult<Unit, DataError.Network, ErrorResponseModel> {
        val registerUser = RegisterUserDto(
            user = UserDto(
                email = registerUserUserModel.email,
                name = registerUserUserModel.name,
                password = registerUserUserModel.password,
                passwordConfirmation = registerUserUserModel.passwordConfirmation
            ),
            clientSecret = BuildConfig.CLIENT_SECRET,
            clientId = BuildConfig.CLIENT_KEY
        )

        val response = authorizationRemoteDataSource.registerUser(registerUser)
        return when(response) {
            is CheckResult.Failure -> {
                CheckResult.Failure(response.exceptionError, response.responseError!!.toErrorResponseModel())
            }
            is CheckResult.Success -> {
               CheckResult.Success(data = response.data)
            }
        }
    }

    override suspend fun login(loginRequestModel: LoginRequestModel): CheckResult<LoginResponseModel, DataError.Network, ErrorResponseModel> {
        return when(val apiResponse = authorizationRemoteDataSource.loginUser(loginRequestModel)) {
            is CheckResult.Success -> {
                val authorizationInfoSerializable = AuthorizationInfoSerializable(
                    accessToken = apiResponse.data.data.attributes.accessToken,
                    refreshToken = apiResponse.data.data.attributes.refreshToken)

                authorizationLocalDataSource.set(authorizationInfoSerializable.toAuthorizationInfo())
                CheckResult.Success(apiResponse.data.toLoginResponseModel())
            }
            is CheckResult.Failure -> {
                if(apiResponse.responseError != null) {
                    /** Should not happen so safe to use the !! */
                    CheckResult.Failure(responseError = apiResponse.responseError!!.toErrorResponseModel())
                }
                else {
                    CheckResult.Failure(exceptionError = apiResponse.exceptionError)
                }
            }
        }
    }

    override suspend fun resetPassword(email: String): CheckResult<ResetPasswordModel, DataError.Network, ErrorResponseModel> {
        return when(val response = authorizationRemoteDataSource.resetPassword(email)) {
            is CheckResult.Success -> {
                CheckResult.Success(response.data.toResetPasswordModel())
            }
            is CheckResult.Failure -> {
                CheckResult.Failure(exceptionError = response.exceptionError, responseError = response.responseError!!.toErrorResponseModel())
            }
        }
    }

    override suspend fun fetchTokenAuthorization(): AuthorizationInfo? {
        return authorizationLocalDataSource.get()
    }

    override suspend fun setTokenAuthorization(authorizationInfo: AuthorizationInfo?) {
        return authorizationLocalDataSource.set(authorizationInfo)
    }

    override suspend fun logout(): CheckResult<Unit, DataError.Network, ErrorResponseModel> {
        return when(val response = authorizationRemoteDataSource.logoutUser()) {
            is CheckResult.Success -> {
                CheckResult.Success(data = response.data)
            }
            is CheckResult.Failure -> {
                CheckResult.Failure(exceptionError = response.exceptionError, responseError = response.responseError!!.toErrorResponseModel())
            }
        }
    }
}