package com.example.genquestions.model

sealed interface MySheet {
    val index: Int

    object Result : MySheet {
        override val index: Int get() = 0
    }

    object FiveYears : MySheet {
        override val index: Int get() = 1
    }

    object Common : MySheet {
        override val index: Int get() = 2
    }

    object Cups : MySheet {
        override val index: Int get() = 3
    }

    object Country : MySheet {
        override val index: Int get() = 4
    }

    object Nationality : MySheet {
        override val index: Int get() = 5
    }

    object Career : MySheet {
        override val index: Int get() = 6
    }

    object Club : MySheet {
        override val index: Int get() = 7
    }

    object Photo : MySheet {
        override val index: Int get() = 8
    }
}