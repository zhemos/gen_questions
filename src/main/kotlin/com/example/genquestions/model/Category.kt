package com.example.genquestions.model

import com.example.genquestions.model.category.*
import com.example.genquestions.model.question.Question
import com.example.genquestions.util.toCellString
import com.example.genquestions.util.toCountryCode
import com.example.genquestions.util.toPicture
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Workbook

abstract class Category(workbook: Workbook) {

    abstract val mySheet: MySheet
    abstract val type: String
    abstract val translateTitles: Map<Language, String>

    private val languages: List<Language> = Language.values().toList()
    private val sheet = workbook.getSheet(mySheet.name)
    protected val mapper = ObjectMapper()

    abstract fun getInfo(row: Row): String
    abstract fun getData(row: Row, language: Language): Question.Data

    fun getQuestions(count: Int): List<Question> {
        var index = 0
        val questions = mutableListOf<Question>()
        repeat(count) {
            val row = sheet.getRow(index++)
            val question = getQuestion(row)
            questions.add(question)
        }
        return questions
    }

    private fun getQuestion(row: Row): Question {
        return Question(
            type = type,
            info = getInfo(row),
            data = languages.associate { it.code to getData(row, it) },
        )
    }

    companion object {
        fun build(workbook: Workbook, name: String, countries: List<Country>, topic: Topic): Category {
            return when (name) {
                MySheet.Clubs.name -> ClubsCategory(topic, workbook)
                MySheet.Recently.name -> RecentlyCategory(workbook)
                MySheet.Common.name -> CommonCategory(workbook)
                MySheet.Cups.name -> CupsCategory(workbook)
                MySheet.NationalTeam.name -> NationalTeamCategory(workbook)
                MySheet.Nationality.name -> NationalityCategory(countries, topic, workbook)
                MySheet.Career.name -> CareerCategory(workbook)
                MySheet.ClubNumberCountry.name -> ClubNumberCountryCategory(countries, workbook)
                MySheet.Photo.name -> PhotoCategory(workbook)
                else -> error("unknown category name")
            }
        }
    }
}