package com.example.genquestions.generate

import com.example.genquestions.model.MySheet
import com.example.genquestions.model.Question
import com.example.genquestions.model.TypeOfQuestions
import com.example.genquestions.model.languages
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.File
import java.io.FileInputStream

private const val TITLE_LEVEL = "уровень"

object GenerateQuestions {

    suspend fun generate(
        input: String,
        output: String,
    ) = withContext(Dispatchers.IO) {
        println(input)
        val inputFile = File(input)
        val fis = FileInputStream(inputFile)
        val workbook = XSSFWorkbook(fis)
        val sheet = workbook.getSheetAt(MySheet.Result.index)
        var field = ""
        var index = 0
        var level = 1
        while (true) {
            val row = sheet.getRow(++index)
            field = row.getCell(1).toString()
            if (field == TITLE_LEVEL) {
                println("level -> ${++level}")
                continue
            }
            if (field.isEmpty()) continue
            val question = generateQuestion(workbook, field, row)
            println("-> $index----------")
            println("-> $question")
            if (level == 51) break
        }
//        val outputFile = FileWriter("$output/1.json")
//        outputFile.write("{{{}}}")
//        outputFile.close()
    }

    private fun generateQuestion(workbook: Workbook, field: String, row: Row): Question {
        val type = TypeOfQuestions.find(workbook, field)
        return Question(
            type = type.absoluteName,
            info = type.getInfo(row),
            default = type.getDefaultData(row),
            translate = languages.associateWith { type.getData(it, row) },
        )
    }
}