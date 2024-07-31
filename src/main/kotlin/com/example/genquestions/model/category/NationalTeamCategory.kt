package com.example.genquestions.model.category

import com.example.genquestions.model.Category
import com.example.genquestions.model.Language
import com.example.genquestions.model.MySheet
import com.example.genquestions.model.question.Question
import com.example.genquestions.util.toCellString
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Workbook

class NationalTeamCategory(workbook: Workbook) : Category(workbook) {
    override val mySheet: MySheet get() = MySheet.NationalTeam
    override val type: String get() = "nt"
    override val translateTitles: Map<Language, String> get() = mapOf()

    override fun getInfo(row: Row) = ""

    override fun getData(row: Row, language: Language): Question.Data {
        val correct: String
        val incorrect: List<String>
        if (row.rowNum < 3) {
            correct = row.getCell(1).toCellString()
            incorrect = listOf(
                row.getCell(2).toCellString(),
                row.getCell(3).toCellString(),
                row.getCell(4).toCellString(),
            )
        } else {
            val stage = Stage.build(row.getCell(1).toString())
            correct = stage.text[language] ?: ""
            incorrect = stage.incorrect.map { it.text[language] ?: "" }
        }
        return Question.Data(
            title = row.getCell(0).toString(),
            correct = correct,
            incorrect = incorrect,
        )
    }

    sealed interface Stage {
        val text: Map<Language, String>
        val incorrect: List<Stage>

        object NoParticipated : Stage {
            override val text: Map<Language, String> get() = mapOf(
                Language.Russian to "Не участвовали"
            )
            override val incorrect: List<Stage> get() = listOf(
                NoQualification, Group, OneOfEight,
            )
        }

        object NoQualification : Stage {
            override val text: Map<Language, String> get() = mapOf(
                Language.Russian to "Не прошли квалификацию"
            )
            override val incorrect: List<Stage> get() = listOf(
                NoParticipated, Group, OneOfEight,
            )
        }
        object Group : Stage {
            override val text: Map<Language, String> get() = mapOf(
                Language.Russian to "Групповой этап"
            )
            override val incorrect: List<Stage> get() = listOf(
                NoQualification, OneOfEight, QuarterFinal,
            )
        }
        object OneOfEight : Stage {
            override val text: Map<Language, String> get() = mapOf(
                Language.Russian to "1/8 финала"
            )
            override val incorrect: List<Stage> get() = listOf(
                Group, QuarterFinal, SecondPlace,
            )
        }
        object QuarterFinal : Stage {
            override val text: Map<Language, String> get() = mapOf(
                Language.Russian to "Четвертьфинал"
            )
            override val incorrect: List<Stage> get() = listOf(
                Group, OneOfEight, Champion,
            )
        }
        object Semifinal : Stage {
            override val text: Map<Language, String> get() = mapOf(
                Language.Russian to "Полуфинал"
            )
            override val incorrect: List<Stage> get() = listOf(
                QuarterFinal, SecondPlace, Champion,
            )
        }
        object FourthPlace : Stage {
            override val text: Map<Language, String> get() = mapOf(
                Language.Russian to "4-е место"
            )
            override val incorrect: List<Stage> get() = listOf(
                QuarterFinal, ThirdPlace, SecondPlace,
            )
        }
        object ThirdPlace : Stage {
            override val text: Map<Language, String> get() = mapOf(
                Language.Russian to "3-е место"
            )
            override val incorrect: List<Stage> get() = listOf(
                FourthPlace, SecondPlace, Champion,
            )
        }
        object SecondPlace : Stage {
            override val text: Map<Language, String> get() = mapOf(
                Language.Russian to "2-е место"
            )
            override val incorrect: List<Stage> get() = listOf(
                OneOfEight, QuarterFinal, Champion,
            )
        }
        object Champion : Stage {
            override val text: Map<Language, String> get() = mapOf(
                Language.Russian to "Чемпион"
            )
            override val incorrect: List<Stage> get() = listOf(
                OneOfEight, QuarterFinal, SecondPlace,
            )
        }

        companion object {
            fun build(title: String): Stage = when (title.lowercase()) {
                "не участвовали" -> NoParticipated
                "не прошли квалификацию" -> NoQualification
                "групповой этап" -> Group
                "1/8 финала" -> OneOfEight
                "четвертьфинал" -> QuarterFinal
                "полуфинал" -> Semifinal
                "4-е место" -> FourthPlace
                "3-е место" -> ThirdPlace
                "2-е место" -> SecondPlace
                "чемпион" -> Champion
                else -> error("$title не найден")
            }
        }
    }
}