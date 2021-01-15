package ru.skillbranch.devintensive

import org.junit.Assert.assertEquals
import org.junit.Test
import ru.skillbranch.devintensive.extensions.*
import ru.skillbranch.devintensive.models.*
import ru.skillbranch.devintensive.utils.Utils
import java.util.Date

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    // normal tests for hometask_2 with asserts
    @Test
    fun test_utils_parseFullName() {
        val (firstName, lastName) = Utils.parseFullName(null)
        assertEquals("null null", "$firstName $lastName")
        val (firstName2, lastName2) = Utils.parseFullName("")
        assertEquals("null null", "$firstName2 $lastName2")
        val (firstName3, lastName3) = Utils.parseFullName(" ")
        assertEquals("null null", "$firstName3 $lastName3")
        val (firstName4, lastName4) = Utils.parseFullName("John")
        assertEquals("John null", "$firstName4 $lastName4")
    }

    @Test
    fun test_toInitials() {
        assertEquals("JD", Utils.toInitials("john", "doe"))
        assertEquals("J", Utils.toInitials("John", null))
        assertEquals(null, Utils.toInitials(null, null))
        assertEquals(null, Utils.toInitials(" ", ""))
    }

    @Test
    fun test_transliteration() {
        assertEquals("Zhenya Stereotipov", Utils.transliteration("Женя Стереотипов"))
        assertEquals("Amazing_Petr", Utils.transliteration("Amazing Петр", "_"))
    }

    @Test
    fun test_builder() {
        val user = User.Builder()
            .id("1")
            .firstName("Roman")
            .lastName("Egorov")
            .avatar("No")
            .rating(10)
            .respect(9)
            .lastVisit(Date())
            .isOnline(true)
            .build()

        user.printMe()
    }

    @Test
    fun test_timeUnits_plurals() {
        assertEquals("1 секунду", TimeUnits.SECOND.plural(1))
        assertEquals("4 минуты", TimeUnits.MINUTE.plural(4))
        assertEquals("19 часов", TimeUnits.HOUR.plural(19))
        assertEquals("222 дня", TimeUnits.DAY.plural(222))
    }

    @Test
    fun test_string_truncate() {
        assertEquals("Bender Bending R...",
            "Bender Bending Rodriguez — дословно «Сгибальщик Сгибающий Родригес»".truncate())
        assertEquals("Bender Bending...",
            "Bender Bending Rodriguez — дословно «Сгибальщик Сгибающий Родригес»".truncate(15))
        assertEquals("A", "A     ".truncate(3))
    }

    @Test
    fun test_string_stripHtml() {
        assertEquals("Образовательное IT-сообщество Skill Branch",
            "<p class=\"title\">Образовательное IT-сообщество Skill Branch</p>".stripHtml())
        assertEquals("Образовательное IT-сообщество Skill Branch",
            "<p>Образовательное       IT-сообщество Skill Branch</p>".stripHtml())
    }

    @Test
    fun test_date_humanizeDiff() {
        assertEquals("2 часа назад", Date().add(-2, TimeUnits.HOUR).humanizeDiff())
        assertEquals("5 дней назад", Date().add(-5, TimeUnits.DAY).humanizeDiff())
        assertEquals("через 2 минуты", Date().add(2, TimeUnits.MINUTE).humanizeDiff())
        assertEquals("через 7 дней", Date().add(7, TimeUnits.DAY).humanizeDiff())
        assertEquals("более года назад", Date().add(-400, TimeUnits.DAY).humanizeDiff())
        assertEquals("более чем через год", Date().add(400, TimeUnits.DAY).humanizeDiff())
    }

    // methods without asserts - written by teacher at practical lesson

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
        when (imgMessage) {
            is TextMessage -> println("this is text message")
            is ImageMessage -> println("this is image msg")
        }

        println(textMessage.formatMessage())
        println(imgMessage.formatMessage())
    }
}