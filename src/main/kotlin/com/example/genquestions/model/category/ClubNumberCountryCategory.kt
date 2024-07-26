package com.example.genquestions.model.category

import com.example.genquestions.model.*
import com.example.genquestions.model.question.Question
import com.example.genquestions.util.toCellString
import com.example.genquestions.util.toCountryCode
import com.example.genquestions.util.toPicture
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Workbook

class ClubNumberCountryCategory(workbook: Workbook) : Category(workbook) {

    override val mySheet: MySheet get() = MySheet.ClubNumberCountry
    override val type: String get() = "cnc"
    override val translateTitles: Map<Language, String> get() = mapOf(
        Russian to "Угадайте футболиста"
    )

    override fun getInfo(row: Row): String {
        val info = Info(
            club = row.getCell(2).toString().toPicture(),
            number = row.getCell(3).toCellString(),
            nationality = row.getCell(4).toString().toCountryCode(),
        )
        try {
            mapper.writeValueAsString(info)
        } catch (e: Exception) {
            println(e.message)
        }
        return mapper.writeValueAsString(info)
    }

    override fun getData(row: Row, language: Language): Question.Data {
        return Question.Data(
            title = translateTitles[language] ?: "",
            correct = row.getCell(1).toString(),
            incorrect = emptyList(),
        )
    }

    data class Info(val club: String, val number: String, val nationality: String)
}