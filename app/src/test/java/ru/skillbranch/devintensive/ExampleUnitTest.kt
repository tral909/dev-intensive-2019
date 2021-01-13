package ru.skillbranch.devintensive

import org.junit.Test

import org.junit.Assert.*
import ru.skillbranch.devintensive.extensions.TimeUnits
import ru.skillbranch.devintensive.extensions.add
import ru.skillbranch.devintensive.extensions.format
import ru.skillbranch.devintensive.extensions.toUserView
import ru.skillbranch.devintensive.models.*
import java.util.Date

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun test_instance() {
        val user = User("1")
        val user2 = User("2", "John", "Wick")
        val user3 = User("3", "John", "Silverhead", null, lastVisit = Date(), isOnline = true)

        user.printMe()
        user2.printMe()
        user3.printMe()

        println("$user $user2 $user3")
    }

    @Test
    fun test_factory() {
        val user3 = User.makeUser("John Wick")

        val user = user3.copy(id = "3", lastName = "Cena")
        print("$user3\n$user")
    }

    @Test
    fun test_decomposition() {
        val user = User.makeUser("John Wick")

        fun getUserInfo() = user

        val (id, firstName, lastName) = getUserInfo()
        println("$id $firstName $lastName")
    }

    @Test
    fun test_copy() {
        val user = User.makeUser("John Wick")
        val user1 = user.copy(lastName = "Ha", lastVisit = Date())
        val user2 = user.copy(lastVisit = Date().add(-2, TimeUnits.SECOND))
        val user3 = user.copy(lastName = "Cena", lastVisit = Date().add(2, TimeUnits.HOUR))

        println(
            """
            ${user.lastVisit?.format()}
            ${user1.lastVisit?.format()}
            ${user2.lastVisit?.format()}
            ${user3.lastVisit?.format()}
        """.trimIndent()
        )
    }

    @Test
    fun test_data_mapping() {
        val user = User.makeUser("Егоров Роман")
        println(user)

        val userView = user.toUserView()
        userView.printMe()
    }

    @Test
    fun test_abstract_factory() {
        val user = User.makeUser("Егоров Роман")
        val textMessage =
            BaseMessage.makeMessage(user, Chat("0"), payload = "any text message", type = "text")
        val imgMessage =
            BaseMessage.makeMessage(user, Chat("0"), payload = "any image message", type = "image")
        when(imgMessage) {
            is TextMessage -> println("this is text message")
            is ImageMessage -> println("this is image msg")
        }

        println(textMessage.formatMessage())
        println(imgMessage.formatMessage())
    }
}