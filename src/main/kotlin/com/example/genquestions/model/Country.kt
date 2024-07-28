package com.example.genquestions.model

data class Country(
    val iso: String,
    val names: Map<String, String>,
    val similar: List<String>,
)

//enum class CountryCategory {
//    England {
//        override val ruName: String get() = "Англия"
//        override val similar: List<Country> get() = listOf(
//
//        )
//    },
//    France {
//        override val ruName: String get() = "Франция"
//        override val similar: List<Country> get() = listOf()
//    };
//    abstract val ruName: String
//    abstract val similar: List<Country>
//}