package com.example.genquestions.model.category

import com.example.genquestions.model.Category
import com.example.genquestions.model.Language
import com.example.genquestions.model.MySheet
import com.example.genquestions.model.question.Question
import com.example.genquestions.util.toCellString
import com.example.genquestions.util.toPicture
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Workbook

class CareerCategory(workbook: Workbook) : Category(workbook) {
    override val mySheet: MySheet get() = MySheet.Career
    override val type: String get() = "career"
    override val translateTitles: Map<Language, String> get() = mapOf(
        Language.Russian to "Угадайте игрока по его карьере"
    )

    override fun getInfo(row: Row): String {
        val clubs = mutableListOf<String>()
        var index = 2
        while (true) {
            val club = row.getCell(index++)?.toString()?.toPicture() ?: break
            clubs.add(club)
        }
        return clubs.toString()
    }

    override fun getData(row: Row, language: Language): Question.Data {
        return Question.Data(
            title = translateTitles[language] ?: "",
            correct = row.getCell(1).toCellString(),
            incorrect = emptyList(),
        )
    }
}