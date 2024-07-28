package com.example.genquestions.util

import com.example.genquestions.model.IMAGE_FORMAT
import org.apache.poi.ss.usermodel.Cell
import java.text.Normalizer

fun String.toNumber(): Int {
    val result = Regex(pattern = "\\d+").find(input = this) ?: error("no numbers")
    return result.value.toInt()
}

fun Cell?.toCellString(): String {
    return try {
        this.toString().toDouble().toInt().toString()
    } catch (_: Exception) {
        this?.toString() ?: ""
    }
}

fun String.toPicture(suffix: String = ""): String {
    return "${this.lowercase().replace(" ", "_").normalize()}$suffix$IMAGE_FORMAT"
}

private val REGEX_UN_ACCENT = "\\p{InCombiningDiacriticalMarks}+".toRegex()
fun String.normalize(): String {
    val temp = Normalizer.normalize(this, Normalizer.Form.NFD)
    return REGEX_UN_ACCENT.replace(temp, "")
}

fun String.toCountryCode(): String {
    return this
}