package fitnest.auth_service.web

import fitnest.auth_service.entities.Interest
import fitnest.auth_service.entities.User
import fitnest.auth_service.services.IUserService
import fitnest.auth_service.controllers.UserController
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import java.util.*

internal class UserControllerTest {

    @InjectMocks
    private lateinit var userController: UserController

    @Mock
    private lateinit var userService: IUserService

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun testAddUser() {
        val user = User()
        user.id = 1L

        Mockito.`when`(userService.addUser(user)).thenReturn(user)

        val result = userController.addUser(user)

        Assertions.assertEquals(HttpStatus.OK, result.statusCode)
        Assertions.assertEquals(user, result.body)
    }

    @Test
    fun testGetUserById() {
        val user = User()
        user.id = 1L

        Mockito.`when`(userService.getUser(1L)).thenReturn(Optional.of(user))

        val result = userController.getUser(1L)

        Assertions.assertEquals(HttpStatus.OK, result.statusCode)
        Assertions.assertEquals(1L, result.body?.id)
    }

    @Test
    fun testGetUserById_NotFound() {
        Mockito.`when`(userService.getUser(2L)).thenReturn(Optional.empty())

        val result = userController.getUser(2L)

        Assertions.assertEquals(HttpStatus.NOT_FOUND, result.statusCode)
    }

    @Test
    fun testGetUserInterests() {
        val userId = 1L
        val interests = listOf(Interest.HIKE ,Interest.SWIM)

        Mockito.`when`(userService.getUserInterests(userId)).thenReturn(interests)

        val result = userController.getUserInterests(userId)

        Assertions.assertEquals(HttpStatus.OK, result.statusCode)
        Assertions.assertEquals(interests, result.body)
    }
}
