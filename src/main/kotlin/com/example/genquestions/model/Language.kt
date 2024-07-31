package com.example.genquestions.model

enum class Language {
    Russian {
        override val code: String get() = "ru"
    };

    abstract val code: String
}