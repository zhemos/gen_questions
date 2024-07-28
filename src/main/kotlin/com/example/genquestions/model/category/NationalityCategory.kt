package com.example.genquestions.model.category

import com.example.genquestions.generate.CountriesController.findCountry
import com.example.genquestions.generate.CountriesController.getIncorrectCountries
import com.example.genquestions.model.*
import com.example.genquestions.model.question.Question
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Workbook

class NationalityCategory(
    private val countries: List<Country>,
    private val topic: Topic,
    workbook: Workbook,
) : Category(workbook) {
    override val mySheet: MySheet get() = MySheet.Nationality
    override val type: String get() = "nationality"
    override val translateTitles: Map<Language, String> get() = mapOf(
        Language.Russian to "Какая национальность у этого игрока?"
    )

    override fun getInfo(row: Row) = ""

    override fun getData(row: Row, language: Language): Question.Data {
        val country = countries.findCountry(row.getCell(2).toString())
        return Question.Data(
            title = translateTitles[language] ?: "",
            correct = country.names[language.code] ?: "",
            incorrect = country.getIncorrectCountries(countries, topic, language),
        )
    }
}