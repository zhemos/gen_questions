package com.example.genquestions.generate

import com.example.genquestions.model.*
import com.example.genquestions.model.question.Question
import com.example.genquestions.util.toCellString
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.File
import java.io.FileInputStream
import kotlin.jvm.Throws

private const val TITLE_LEVEL = "уровень"

object GenerateQuestions {

    fun generate(
        input: String,
        output: String,
    ) {
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

    @Throws
    fun generateQuestions(input: String) {
        println(input)
        val inputFile = File(input)
        val fis = FileInputStream(inputFile)
        val workbook = XSSFWorkbook(fis)
        val sheet = workbook.getSheetAt(MySheet.Result.index)
        val categoriesOfCount = mutableMapOf<String, Int>()
        repeat(9) {
            val row = sheet.getRow(it)
            val cellCategoryName = row.getCell(0).toCellString()
            val cellCount = row.getCell(1).toCellString()
            categoriesOfCount[cellCategoryName] = cellCount.toInt()
        }
        val questions = ArrayList<Question>(categoriesOfCount.values.sum())
        val category = Category.build(workbook, "Клуб,номер,cтрана")
        category.getQuestions(51).forEach { questions.add(it) }
        //categoriesOfCount.toList().sortedByDescending { (_, value) -> value }.forEachIndexed { index, (categoryName, count) ->
        //    val category = Category.build(workbook, categoryName)
        //    if (index == 0) {
        //        category.getQuestions(count).forEach { questions.add(it) }
        //    } else {
        //        val currentIndex = questions.size / count
        //        println(categoryName)
        //        println("${questions.size} $count $currentIndex")
        //        repeat(count) {
        //            //questions.add(((currentIndex + 1) * (it + 1)) - 1, "$categoryName${it + 1}")//4 8 12
        //        }
        //    }
        //}
        println(questions)
        println(questions.size)
//        val cell = row.createCell(0)
//        val fos = FileOutputStream(inputFile)
//        workbook.write(fos)
//        workbook.close()
//        fis.close()
//        fos.close()
    }

    private fun generateQuestion(workbook: Workbook, field: String, row: Row): QuestionOld {
        val type = TypeOfQuestions.find(workbook, field)
        return QuestionOld(
            type = type.absoluteName,
            info = type.getInfo(row),
            default = type.getDefaultData(row),
            translate = languages.associateWith { type.getData(it, row) },
        )
    }
}