package com.example.genquestions.model

sealed interface MySheet {
    val index: Int
    val name: String

    object Result : MySheet {
        override val index: Int get() = 0
        override val name: String get() = ""
    }

    object FiveYears : MySheet {
        override val index: Int get() = 1
        override val name: String get() = ""
    }

    object Common : MySheet {
        override val index: Int get() = 2
        override val name: String get() = ""
    }

    object Cups : MySheet {
        override val index: Int get() = 3
        override val name: String get() = ""
    }

    object Country : MySheet {
        override val index: Int get() = 4
        override val name: String get() = ""
    }

    object Nationality : MySheet {
        override val index: Int get() = 5
        override val name: String get() = ""
    }

    object Career : MySheet {
        override val index: Int get() = 6
        override val name: String get() = ""
    }

    object ClubNumberCountry : MySheet {
        override val index: Int get() = 7
        override val name: String get() = "Клуб,номер,cтрана"
    }

    object Photo : MySheet {
        override val index: Int get() = 8
        override val name: String get() = "Фото"
    }
}