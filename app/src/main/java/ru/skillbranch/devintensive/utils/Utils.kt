package ru.skillbranch.devintensive.utils

object Utils {

    fun parseFullName(fullName: String?): Pair<String?, String?> {
        //val parts: List<String>? = fullName?.trim()?.replace(Regex(" +"), " ")?.split(" ")
        val parts: List<String>? = fullName?.trim()?.split(" ")

        val firstName = parts?.getOrNull(0)
        val lastName = parts?.getOrNull(1)

        return firstName to lastName
    }

    fun transliteration(payload: String, divider: String = " "): String {
        return "IMPLEMENT trans!"
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        return "IMPLEMENT initials!"
    }
}