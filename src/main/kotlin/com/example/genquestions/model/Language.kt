package com.example.genquestions.model

enum class Language {
    Russian {
        override val code: String get() = "ru"
    },
    English {
        override val code: String get() = "en"
    };

    abstract val code: String
}