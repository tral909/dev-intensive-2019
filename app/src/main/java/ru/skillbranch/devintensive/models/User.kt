package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.utils.Utils
import java.util.Date


data class User(
    val id: String,
    var firstName: String?,
    var lastName: String?,
    var avatar: String?,
    var rating: Int = 0,
    var respect: Int = 0,
    var lastVisit: Date? = null,
    var isOnline: Boolean = false
) {
    constructor(id: String, firstName: String?, lastName: String?) : this(id, firstName, lastName, null)

    constructor(id: String) : this(id, "John", "Doe")

    init {
        println(
            "It's Alive!!!\n${
            if (lastName === "Doe")
                "His name is $firstName $lastName"
            else
                "And his name is $firstName $lastName!!!"
            }\n"
        )
    }

    fun printMe() = println("""
            id $id
            firstName $firstName
            lastName $lastName
            avatar $avatar
            rating $rating
            respect $respect
            lastVisit $lastVisit
            isOnline $isOnline
        """.trimIndent())

    companion object Factory {

        private var lastId: Int = -1

        fun makeUser(fullName: String?): User {
            lastId++

            val (firstName, lastName) = Utils.parseFullName(fullName)

            return User("$lastId", firstName, lastName)
        }
    }

    class Builder {
        private var id: String? = null
        private var firstName: String? = null
        private var lastName: String? = null
        private var avatar: String? = null
        private var rating: Int = 0
        private var respect: Int = 0
        private var lastVisit: Date? = null
        private var isOnline: Boolean = false

        fun id(id: String): Builder {
            this.id = id
            return this
        }
        fun firstName(firstName: String): Builder {
            this.firstName = firstName
            return this
        }
        fun lastName(lastName: String): Builder {
            this.lastName = lastName
            return this
        }
        fun avatar(avatar: String): Builder {
            this.avatar = avatar
            return this
        }
        fun rating(rating: Int): Builder {
            this.rating = rating
            return this
        }
        fun respect(respect: Int): Builder {
            this.respect = respect
            return this
        }
        fun lastVisit(lastVisit: Date): Builder {
            this.lastVisit = lastVisit
            return this
        }
        fun isOnline(isOnline: Boolean): Builder {
            this.isOnline = isOnline
            return this
        }
        fun build() = User(
            id ?: (++lastId).toString(),
            firstName,
            lastName,
            avatar,
            rating,
            respect,
            lastVisit,
            isOnline
        )
    }
}