package com.example.genquestions.model.question

data class Question(
    val type: String,
    val info: String,
    val data: Map<String, Data>,
) {

    data class Data(
        val title: String,
        val correct: String,
        val incorrect: List<String>,
    )
}

fun emptyQuestion() = Question(
    type = "default type",
    info = "",
    data = mapOf(),
)

/***
 * 1. Клубы
 * - лого W
 * - город V
 * - стадион V
 * 2. Пятилетка V
 * 3. Общие V
 * 4. Еврокубки V
 * 5. Сборная V
 * 6. Нац V
 * 7. Карьера W
 * 8. Клуб,номер,страна W
 * 9. Фото W
 */
