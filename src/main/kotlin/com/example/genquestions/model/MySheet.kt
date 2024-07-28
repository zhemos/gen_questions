package com.example.genquestions.model

sealed interface MySheet {
    val index: Int
    val name: String

    object Result : MySheet {
        override val index: Int get() = 0
        override val name: String get() = "Итог"
    }

    object Clubs : MySheet {
        override val index: Int get() = 1
        override val name: String get() = "Клубы"
    }

    object Recently : MySheet {
        override val index: Int get() = 2
        override val name: String get() = "Пятилетка"
    }

    object Common : MySheet {
        override val index: Int get() = 3
        override val name: String get() = "Общие"
    }

    object Cups : MySheet {
        override val index: Int get() = 4
        override val name: String get() = "Еврокубки"
    }

    object NationalTeam : MySheet {
        override val index: Int get() = 5
        override val name: String get() = "Сборная"
    }

    object Nationality : MySheet {
        override val index: Int get() = 6
        override val name: String get() = "Национальность"
    }

    object Career : MySheet {
        override val index: Int get() = 7
        override val name: String get() = "Карьера"
    }

    object ClubNumberCountry : MySheet {
        override val index: Int get() = 8
        override val name: String get() = "Клуб,номер,cтрана"
    }

    object Photo : MySheet {
        override val index: Int get() = 9
        override val name: String get() = "Фото"
    }
}