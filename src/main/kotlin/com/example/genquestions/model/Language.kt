package com.example.genquestions.model

sealed class Language {
    abstract val code: String
}

object Russian : Language() {
    override val code: String get() = "ru"
}