package com.example.genquestions.model.category

import com.example.genquestions.model.Category
import com.example.genquestions.model.Language
import com.example.genquestions.model.MySheet
import com.example.genquestions.model.question.Question
import com.example.genquestions.util.toCellString
import com.example.genquestions.util.toPicture
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Workbook

class PhotoCategory(workbook: Workbook) : Category(workbook) {
    override val mySheet: MySheet get() = MySheet.Photo
    override val type: String get() = "photo"
    override val translateTitles: Map<Language, String> get() = mapOf(
        Language.Russian to "Кто изображён на фото?"
    )

    override fun getInfo(row: Row): String {
        return row.getCell(1).toString().toPicture()
    }

    override fun getData(row: Row, language: Language): Question.Data {
        return Question.Data(
            title = translateTitles[language] ?: "",
            correct = row.getCell(1).toCellString(),
            incorrect = emptyList(),
        )
    }
}