package com.example.genquestions.model.category

import com.example.genquestions.model.Category
import com.example.genquestions.model.Language
import com.example.genquestions.model.MySheet
import com.example.genquestions.model.Topic
import com.example.genquestions.model.question.Question
import com.example.genquestions.util.toPicture
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Workbook

class ClubsCategory(
    private val topic: Topic,
    workbook: Workbook,
) : Category(workbook) {
    override val mySheet: MySheet get() = MySheet.Clubs
    override val type: String get() = "clubs"
    override val translateTitles: Map<Language, String> get() = mapOf()

    override fun getInfo(row: Row): String {
        val type = Type.build(row.getCell(0).toString())
        val info = Info(
            type = type.value,
            image = "${topic.iso}/${type.getImage(row)}",
        )
        return mapper.writeValueAsString(info)
    }

    override fun getData(row: Row, language: Language): Question.Data {
        val type = Type.build(row.getCell(0).toString())
        val incorrect = when (type) {
            Type.Logo -> emptyList()
            else -> listOf(
                row.getCell(4).toString(),
                row.getCell(5).toString(),
                row.getCell(6).toString(),
            )
        }
        return Question.Data(
            title = type.title[language] ?: "",
            correct = row.getCell(3).toString(),
            incorrect = incorrect,
        )
    }

    data class Info(val type: String, val image: String)

    sealed interface Type {
        val value: String
        val title: Map<Language, String>
        fun getImage(row: Row): String

        object Logo : Type {
            override val value: String get() = "logo"
            override val title: Map<Language, String> get() = mapOf(
                Language.Russian to "Логотип какого клуба изображен?"
            )
            override fun getImage(row: Row) = row.getCell(2).toString().toPicture("_hide")
        }

        object City : Type {
            override val value: String get() = "city"
            override val title: Map<Language, String> get() = mapOf(
                Language.Russian to "Из какого города этот футбольный клуб?"
            )
            override fun getImage(row: Row) = row.getCell(2).toString().toPicture()
        }

        object Stadium : Type {
            override val value: String get() = "stadium"
            override val title: Map<Language, String> get() = mapOf(
                Language.Russian to "Какой стадион является домашней ареной этого футбольного клуба?"
            )
            override fun getImage(row: Row) = row.getCell(2).toString().toPicture()
        }

        companion object {
            fun build(type: String) : Type {
                return when (type.lowercase()) {
                    "лого" -> Logo
                    "город" -> City
                    "стадион" -> Stadium
                    else -> error("$type не определен")
                }
            }
        }
    }
}