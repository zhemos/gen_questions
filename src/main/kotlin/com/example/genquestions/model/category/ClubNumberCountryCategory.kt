package com.example.genquestions.model.category

import com.example.genquestions.generate.CountriesController.findCountry
import com.example.genquestions.model.*
import com.example.genquestions.model.question.Question
import com.example.genquestions.util.toCellString
import com.example.genquestions.util.toPicture
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Workbook

class ClubNumberCountryCategory(
    private val countries: List<Country>,
    workbook: Workbook,
) : Category(workbook) {

    override val mySheet: MySheet get() = MySheet.ClubNumberCountry
    override val type: String get() = "cnc"
    override val translateTitles: Map<Language, String> get() = mapOf(
        Language.Russian to "Угадайте футболиста"
    )

    override fun getInfo(row: Row): String {
        val country = countries.findCountry(row.getCell(4).toString())
        val info = Info(
            club = row.getCell(2).toString().toPicture(),
            number = row.getCell(3).toCellString(),
            nationality = country.iso,
        )
        return mapper.writeValueAsString(info)
    }

    override fun getData(row: Row, language: Language): Question.Data {
        return Question.Data(
            title = translateTitles[language] ?: "",
            correct = row.getCell(1).toCellString(),
            incorrect = emptyList(),
        )
    }

    data class Info(val club: String, val number: String, val nationality: String)
}