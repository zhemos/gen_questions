package com.example.genquestions.generate

import com.example.genquestions.model.Country
import com.example.genquestions.model.Language
import com.example.genquestions.model.Topic
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import java.io.File

object CountriesController {
    fun read(): List<Country> {
        val mapper = ObjectMapper()
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
        val path = javaClass.getResource("/countries.json")?.file
        val file = File(path ?: "")
        println(file)
        val node = mapper.readTree(file)
        return node.map {
            val names = Language.values().associate { lang ->
                lang.code to it[lang.code].asText()
            }
            Country(
                iso = it["alpha2"].asText(),
                names = names,
                similar = it["similar"].map { iso -> iso.asText() }
            )
        }
    }

    fun List<Country>.findCountry(ruName: String): Country {
        return find {
            it.names["ru"] == ruName
        } ?: error("Страна: $ruName не найдена")
    }

    fun Country.getIncorrectCountries(countries: List<Country>, topic: Topic, language: Language): List<String> {
        if (similar.size <= 3) error("${names[language.code]} нет похожих")
        return similar.filter { it != topic.iso }.take(3).map { iso ->
            countries.find { it.iso == iso }?.names?.get(language.code) ?: error("${names[language.code]} error")
        }
    }
}