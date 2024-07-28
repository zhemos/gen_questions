package com.example.genquestions.model.category

import com.example.genquestions.model.Category
import com.example.genquestions.model.Language
import com.example.genquestions.model.MySheet
import com.example.genquestions.model.question.Question
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Workbook

class RecentlyCategory(workbook: Workbook) : Category(workbook) {
    override val mySheet: MySheet get() = MySheet.Recently
    override val type: String get() = "recently"
    override val translateTitles: Map<Language, String> get() = mapOf()

    override fun getInfo(row: Row) = ""

    override fun getData(row: Row, language: Language): Question.Data {
        return Question.Data(
            title = row.getCell(0).toString(),
            correct = row.getCell(1).toString(),
            incorrect = listOf(
                row.getCell(2).toString(),
                row.getCell(3).toString(),
                row.getCell(4).toString(),
            ),
        )
    }
}