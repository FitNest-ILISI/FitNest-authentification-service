package fitnest.auth_service.web

import fitnest.auth_service.entities.User
import fitnest.auth_service.services.UserService
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
    private val userController: UserController? = null

    @Mock
    private val userService: UserService? = null

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun testGetUserById() {
        val user = User()
        user.id = 1L

        Mockito.`when`(userService!!.findUserById(1L)).thenReturn(ResponseEntity.of(Optional.of(user)))

        val result = userController!!.getUserById(1L)

        Assertions.assertEquals(HttpStatus.OK, result.statusCode)
        Assertions.assertEquals(1L, result.body.id)
    }

    @Test
    fun testGetUserByEmail() {
        val user = User()
        user.email = "test@example.com"

        Mockito.`when`(userService!!.findUserByEmail("test@example.com")).thenReturn(ResponseEntity.of(Optional.of(user)))

        val result = userController!!.getUserByEmail("test@example.com")

        Assertions.assertEquals(HttpStatus.OK, result.statusCode)
        Assertions.assertEquals("test@example.com", result.body.email)
    }

    @Test
    fun testGetAllUsers() {
        val users: MutableList<User> = ArrayList()
        users.add(User())
        users.add(User())

        Mockito.`when`(userService!!.retrieveAllUsers()).thenReturn(ResponseEntity.ok(users))

        val result = userController!!.allUsers

        Assertions.assertEquals(HttpStatus.OK, result.statusCode)
        Assertions.assertEquals(2, result.body.size)
    }
}