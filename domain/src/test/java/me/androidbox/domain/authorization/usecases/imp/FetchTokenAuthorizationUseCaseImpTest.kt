package me.androidbox.domain.authorization.usecases.imp

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest
import me.androidbox.domain.authorization.AuthorizationInfo
import me.androidbox.domain.repository.AuthorizationRepository
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import java.util.UUID


class FetchTokenAuthorizationUseCaseImpTest {

    private val authorizationRepository: AuthorizationRepository = mock(AuthorizationRepository::class.java)

    @Test
    fun `should fetch token`() = runTest{
        // Arrange
        val authorizationInfo = AuthorizationInfo(
            accessToken = UUID.randomUUID().toString(),
            refreshToken = UUID.randomUUID().toString()
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