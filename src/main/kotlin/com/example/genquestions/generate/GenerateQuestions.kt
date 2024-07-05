package com.example.genquestions.generate

import com.example.genquestions.model.MySheet
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.File
import java.io.FileInputStream
import java.io.FileWriter

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
        val a = sheet.getRow(1)
        println("-> $a")
//        val outputFile = FileWriter("$output/1.json")
//        outputFile.write("{{{}}}")
//        outputFile.close()
    }
}