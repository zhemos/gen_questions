package com.example.genquestions.model

import com.example.genquestions.util.*
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Workbook

data class Question(
    val type: String,
    val info: String,
    val default: Data,
    val translate: Map<String, Data>,
) {

    data class Data(
        val title: String,
        val correct: String,
        val incorrect: List<String>,
    )

    data class Translate(
        val data: Map<String, Data>,
    )
}

sealed class TypeOfQuestions(protected val workbook: Workbook) {
    abstract val name: String
    abstract val absoluteName: String
    abstract fun getInfo(row: Row): String
    abstract fun getDefaultData(row: Row): Question.Data
    abstract fun getData(lang: String, row: Row): Question.Data

    class Logo(workbook: Workbook) : TypeOfQuestions(workbook) {
        override val name: String get() = "лого"
        override val absoluteName: String get() = "logo"
        override fun getInfo(row: Row): String {
            return "${row.getCell(3).toString().lowercase()}_hide$IMAGE_FORMAT".normalize()
        }
        override fun getDefaultData(row: Row): Question.Data {
            return Question.Data(
                title = "Which club's logo is shown in the photo?",
                correct = row.getCell(3).toCellString(),
                incorrect = listOf(
                    row.getCell(4).toCellString(), row.getCell(5).toCellString(), row.getCell(6).toCellString(),
                ),
            )
        }
        override fun getData(lang: String, row: Row): Question.Data {
            return if (lang == "ru") {
                Question.Data(
                    title = "Логотип какого клуба изображен на фото?",
                    correct = "",
                    incorrect = emptyList(),
                )
            } else {
                Question.Data(
                    title = "",
                    correct = "",
                    incorrect = emptyList(),
                )
            }
        }
    }

    class Stadium(workbook: Workbook) : TypeOfQuestions(workbook) {
        override val name: String get() = "стадион"
        override val absoluteName: String get() = "stadium"
        override fun getInfo(row: Row): String {
            return ""
        }
        override fun getDefaultData(row: Row): Question.Data {
            return Question.Data(
                title = "",
                correct = "",
                incorrect = emptyList(),
            )
        }
        override fun getData(lang: String, row: Row): Question.Data {
            return if (lang == "ru") {
                Question.Data(
                    title = "Какой стадион является домашней ареной футбольного клуба ...?",
                    correct = "",
                    incorrect = emptyList(),
                )
            } else {
                Question.Data(
                    title = "",
                    correct = "",
                    incorrect = emptyList(),
                )
            }
        }
    }

    class City(workbook: Workbook) : TypeOfQuestions(workbook) {
        override val name: String get() = "город"
        override val absoluteName: String get() = "city"
        override fun getInfo(row: Row): String {
            return ""
        }
        override fun getDefaultData(row: Row): Question.Data {
            return Question.Data(
                title = "",
                correct = "",
                incorrect = emptyList(),
            )
        }
        override fun getData(lang: String, row: Row): Question.Data {
            return if (lang == "ru") {
                Question.Data(
                    title = "Из какого города футбольный клуб ...?",
                    correct = "",
                    incorrect = emptyList(),
                )
            } else {
                Question.Data(
                    title = "",
                    correct = "",
                    incorrect = emptyList(),
                )
            }
        }
    }

    class Common(workbook: Workbook) : TypeOfQuestions(workbook) {
        override val name: String get() = "общие"
        override val absoluteName: String get() = "common"
        override fun getInfo(row: Row) = ""
        override fun getDefaultData(row: Row): Question.Data {
            val index = row.getCell(1).toString().toNumber()
            val rowCommon = workbook.getSheetAt(MySheet.Common.index).getRow(index - 1)
            return Question.Data(
                title = rowCommon?.getCell(0)?.toString() ?: "",
                correct = rowCommon.getCell(1).toCellString(),
                incorrect = listOf(
                    rowCommon.getCell(2).toCellString(), rowCommon.getCell(3).toCellString(), rowCommon.getCell(4).toCellString(),
                ),
            )
        }
        override fun getData(lang: String, row: Row): Question.Data {
            return Question.Data(
                title = "",
                correct = "",
                incorrect = emptyList(),
            )
        }
    }

    class FiveYears(workbook: Workbook) : TypeOfQuestions(workbook) {
        override val name: String get() = "пятилетка"
        override val absoluteName: String get() = "5years"
        override fun getInfo(row: Row): String {
            return ""
        }
        override fun getDefaultData(row: Row): Question.Data {
            return Question.Data(
                title = "",
                correct = "",
                incorrect = emptyList(),
            )
        }
        override fun getData(lang: String, row: Row): Question.Data {
            return Question.Data(
                title = "",
                correct = "",
                incorrect = emptyList(),
            )
        }
    }

    class Cups(workbook: Workbook) : TypeOfQuestions(workbook) {
        override val name: String get() = "еврокубки"
        override val absoluteName: String get() = "cups"
        override fun getInfo(row: Row): String {
            return ""
        }
        override fun getDefaultData(row: Row): Question.Data {
            val index = row.getCell(1).toString().toNumber()
            val rowCups = workbook.getSheetAt(MySheet.Cups.index).getRow(index - 1)
            return Question.Data(
                title = rowCups?.getCell(0)?.toString() ?: "",
                correct = rowCups.getCell(1).toCellString(),
                incorrect = listOf(
                    rowCups.getCell(2).toCellString(), rowCups.getCell(3).toCellString(), rowCups.getCell(4).toCellString(),
                ),
            )
        }
        override fun getData(lang: String, row: Row): Question.Data {
            return Question.Data(
                title = "",
                correct = "",
                incorrect = emptyList(),
            )
        }
    }

    class Country(workbook: Workbook) : TypeOfQuestions(workbook) {
        override val name: String get() = "сборная"
        override val absoluteName: String get() = "country"
        override fun getInfo(row: Row): String {
            return ""
        }
        override fun getDefaultData(row: Row): Question.Data {
            val index = row.getCell(1).toString().toNumber()
            val rowCups = workbook.getSheetAt(MySheet.Country.index).getRow(index - 1)
            return Question.Data(
                title = rowCups?.getCell(0)?.toString() ?: "",
                correct = rowCups.getCell(1).toCellString(),
                incorrect = listOf(
                    rowCups.getCell(2).toCellString(), rowCups.getCell(3).toCellString(), rowCups.getCell(4).toCellString(),
                ),
            )
        }
        override fun getData(lang: String, row: Row): Question.Data {
            return Question.Data(
                title = "",
                correct = "",
                incorrect = emptyList(),
            )
        }
    }

    class Nationality(workbook: Workbook) : TypeOfQuestions(workbook) {
        override val name: String get() = "нац"
        override val absoluteName: String get() = "nationality"
        override fun getInfo(row: Row): String {
            val index = row.getCell(1).toString().toNumber()
            val rowNat = workbook.getSheetAt(MySheet.Nationality.index).getRow(index - 1)
            return rowNat.getCell(1)?.toString()?.toPicture() ?: ""
        }
        override fun getDefaultData(row: Row): Question.Data {
            val index = row.getCell(1).toString().toNumber()
            val rowNat = workbook.getSheetAt(MySheet.Nationality.index).getRow(index - 1)
            return Question.Data(
                title = "What is the nationality of this player?",
                correct = rowNat.getCell(2).toCellString(),
                incorrect = listOf(
                    rowNat.getCell(3).toCellString(), rowNat.getCell(4).toCellString(), rowNat.getCell(5).toCellString(),
                ),
            )
        }
        override fun getData(lang: String, row: Row): Question.Data {
            return if (lang == "ru") {
                Question.Data(
                    title = "Какая национальность у этого игрока?",
                    correct = "",
                    incorrect = emptyList(),
                )
            } else {
                Question.Data(
                    title = "",
                    correct = "",
                    incorrect = emptyList(),
                )
            }
        }
    }

    class Career(workbook: Workbook) : TypeOfQuestions(workbook) {
        override val name: String get() = "карьера"
        override val absoluteName: String get() = "career"
        override fun getInfo(row: Row): String {
            val index = row.getCell(1).toString().toNumber()
            val rowCareer = workbook.getSheetAt(MySheet.Career.index).getRow(index - 1)
            var clubIndex = 5
            val career = mutableListOf<String>()
            while (true) {
                val cell = rowCareer.getCell(clubIndex++)?.toString() ?: break
                career.add(cell.toPicture())
            }
            return career.toString()
        }
        override fun getDefaultData(row: Row): Question.Data {
            val index = row.getCell(1).toString().toNumber()
            val rowCareer = workbook.getSheetAt(MySheet.Career.index).getRow(index - 1)
            return Question.Data(
                title = "Guess the player based on his career",
                correct = rowCareer.getCell(1).toCellString(),
                incorrect = listOf(
                    rowCareer.getCell(2).toCellString(), rowCareer.getCell(3).toCellString(), rowCareer.getCell(4).toCellString(),
                ),
            )
        }
        override fun getData(lang: String, row: Row): Question.Data {
            return if (lang == "ru") {
                Question.Data(
                    title = "Угадайте игрока по его карьере",
                    correct = "",
                    incorrect = emptyList(),
                )
            } else {
                Question.Data(
                    title = "",
                    correct = "",
                    incorrect = emptyList(),
                )
            }
        }
    }

    class Club(workbook: Workbook) : TypeOfQuestions(workbook) {

        private val mapper = ObjectMapper()
        data class Info(val club: String, val number: String, val nationality: String)

        override val name: String get() = "клуб,номер"
        override val absoluteName: String get() = "club"
        override fun getInfo(row: Row): String {
            val index = row.getCell(1).toString().toNumber()
            val rowClub = workbook.getSheetAt(MySheet.Club.index).getRow(index - 1)
            val info = Info(
                club = rowClub.getCell(2).toString().toPicture(),
                number = rowClub.getCell(3).toCellString(),
                nationality = rowClub.getCell(0).toString().toCountryCode(),
            )
            return mapper.writeValueAsString(info)
        }
        override fun getDefaultData(row: Row): Question.Data {
            val index = row.getCell(1).toString().toNumber()
            val rowClub = workbook.getSheetAt(MySheet.Club.index).getRow(index - 1)
            return Question.Data(
                title = "Guess the player",
                correct = rowClub.getCell(4).toCellString(),
                incorrect = listOf(
                    rowClub.getCell(5).toCellString(), rowClub.getCell(6).toCellString(), rowClub.getCell(7).toCellString(),
                ),
            )
        }
        override fun getData(lang: String, row: Row): Question.Data {
            return if (lang == "ru") {
                Question.Data(
                    title = "Угадайте игрока",
                    correct = "",
                    incorrect = emptyList(),
                )
            } else {
                Question.Data(
                    title = "",
                    correct = "",
                    incorrect = emptyList(),
                )
            }
        }
    }

    class Photo(workbook: Workbook) : TypeOfQuestions(workbook) {

        override val name: String get() = "фото"
        override val absoluteName: String get() = "photo"
        override fun getInfo(row: Row): String {
            val index = row.getCell(1).toString().toNumber()
            val rowPhoto = workbook.getSheetAt(MySheet.Photo.index).getRow(index - 1)
            return rowPhoto.getCell(2).toString().toPicture()
        }
        override fun getDefaultData(row: Row): Question.Data {
            val index = row.getCell(1).toString().toNumber()
            val rowPhoto = workbook.getSheetAt(MySheet.Photo.index).getRow(index - 1)
            return Question.Data(
                title = "Who is in the photo?",
                correct = rowPhoto.getCell(2).toCellString(),
                incorrect = listOf(
                    rowPhoto.getCell(3).toCellString(), rowPhoto.getCell(4).toCellString(), rowPhoto.getCell(5).toCellString(),
                ),
            )
        }
        override fun getData(lang: String, row: Row): Question.Data {
            return if (lang == "ru") {
                Question.Data(
                    title = "Кто изображен на фото?",
                    correct = "",
                    incorrect = emptyList(),
                )
            } else {
                Question.Data(
                    title = "",
                    correct = "",
                    incorrect = emptyList(),
                )
            }
        }
    }

    companion object {
        fun find(workbook: Workbook, field: String): TypeOfQuestions {
            val name = field.lowercase()
            return when {
                name.contains(Logo(workbook).name) -> Logo(workbook)
                name.contains(Stadium(workbook).name) -> Stadium(workbook)
                name.contains(City(workbook).name) -> City(workbook)
                name.contains(Common(workbook).name) -> Common(workbook)
                name.contains(FiveYears(workbook).name) -> FiveYears(workbook)
                name.contains(Cups(workbook).name) -> Cups(workbook)
                name.contains(Country(workbook).name) -> Country(workbook)
                name.contains(Nationality(workbook).name) -> Nationality(workbook)
                name.contains(Career(workbook).name) -> Career(workbook)
                name.contains(Club(workbook).name) -> Club(workbook)
                name.contains(Photo(workbook).name) -> Photo(workbook)
                else -> error("not found type of question")
            }
        }
    }
}

val languages = listOf("ru", "es", "pt", "br", "fr", "de", "nl", "it")
val IMAGE_FORMAT = ".png"