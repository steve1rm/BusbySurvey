package me.androidbox.domain.authorization.usecases.imp

import me.androidbox.domain.authorization.models.AttributesModel
import me.androidbox.domain.authorization.models.DataModel
import me.androidbox.domain.authorization.models.LoginRequestModel
import me.androidbox.domain.authorization.models.LoginResponseModel
import me.androidbox.domain.repository.AuthorizationRepository
import org.junit.Test
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import java.util.UUID

class LoginUseCaseImpTest {
    private val authorizationRepository: AuthorizationRepository = mock(AuthorizationRepository::class.java)

    @Test
    fun `should login user`() = runTest {
        // Arrange
        val loginRequestModel = LoginRequestModel(
            clientId = UUID.randomUUID().toString(),
            clientSecret = UUID.randomUUID().toString(),
            email = UUID.randomUUID().toString(),
            grantType = UUID.randomUUID().toString(),
            password = UUID.randomUUID().toString()
        )

        val loginResponseModel = LoginResponseModel(
            data = DataModel(
                attributes = AttributesModel(
                    accessToken = UUID.randomUUID().toString(),
                    createdAt = 0,
                    expiresIn = 0,
                    refreshToken = UUID.randomUUID().toString(),
                    tokenType = UUID.randomUUID().toString()
                )
            )
        )

        whenever(authorizationRepository.fetchTokenAuthorization()).thenReturn(authorizationInfo)
        val fetchTokenAuthorizationUseCase = FetchTokenAuthorizationUseCaseImp(authorizationRepository)

        // Act
        val result = fetchTokenAuthorizationUseCase.execute()

        // Assert
        verify(authorizationRepository).fetchTokenAuthorization()
        assertThat(result?.accessToken).isEqualTo(authorizationInfo.accessToken)
        assertThat(result?.refreshToken).isEqualTo(authorizationInfo.refreshToken)
    }

}