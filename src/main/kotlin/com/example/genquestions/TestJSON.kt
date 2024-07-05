package com.example.genquestions

import com.fasterxml.jackson.databind.ObjectMapper

object TestJSON {

    fun init() {
        val person = Person("Jack", 30, Home("qwe", 12), mapOf("1" to Home("1", 1), "2" to Home("2", 2)))
        val mapper = ObjectMapper()
        val json = mapper.writeValueAsString(person)
        println(json)
    }
}

data class Person(
    val name: String,
    val age: Int,
    val home: Home,
    val last: Map<String, Home>
)

data class Home(
    val address: String,
    val number: Int,
)