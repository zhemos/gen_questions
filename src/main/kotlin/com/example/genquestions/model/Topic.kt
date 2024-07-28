package com.example.genquestions.model

enum class Topic {
    England {
        override val title: String get() = "Англия"
        override val isCountry: Boolean get() = true
        override val iso: String get() = "gb-eng"
    },
    Spain {
        override val title: String get() = "Испания"
        override val isCountry: Boolean get() = true
        override val iso: String get() = "es"
    },
    WorldCup {
        override val title: String get() = "ЧМ"
        override val isCountry: Boolean get() = false
        override val iso: String get() = ""
    };

    abstract val title: String
    abstract val isCountry: Boolean
    abstract val iso: String
}

fun String.findTopic(): Topic {
    return when(this) {
        Topic.England.title -> Topic.England
        Topic.Spain.title -> Topic.Spain
        Topic.WorldCup.title -> Topic.WorldCup
        else -> error("$this не известная тема")
    }
}
