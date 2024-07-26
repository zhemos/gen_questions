package com.example.genquestions

import com.example.genquestions.generate.GenerateQuestions
import javafx.fxml.FXML
import javafx.scene.control.Alert
import javafx.scene.control.Button
import javafx.scene.control.ProgressBar
import javafx.scene.control.TextField
import javafx.stage.FileChooser
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext


class HelloController {
    @FXML
    private lateinit var textFieldInput: TextField
    @FXML
    private lateinit var textFieldOutput: TextField
    @FXML
    private lateinit var progressBar: ProgressBar
    @FXML
    private lateinit var btnFile: Button
    @FXML
    private lateinit var btnGenerate: Button
    @FXML
    private lateinit var btnGetQuestions: Button

    private val fileChooser = FileChooser()
//    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
//        println("Handle $exception in CoroutineExceptionHandler")
//        println("Handle ${exception.message} in CoroutineExceptionHandler")
//    }
//    private val scope = CoroutineScope(Job() + coroutineExceptionHandler + Dispatchers.Default)

    init {
        fileChooser.extensionFilters.addAll(
            FileChooser.ExtensionFilter("excel", "*.xlsx"),
        )
    }

    @FXML
    private fun onChoiceFileButtonClick() {
        val file = fileChooser.showOpenDialog(HelloApplication.stage)
        textFieldInput.text = file.toString()
    }

    @FXML
    private fun onGenerateButtonClick() {
        if (textFieldInput.text.isEmpty()) {
            Alert(Alert.AlertType.ERROR).show()
            return
        }
        disableElements(true)
        try {
            GenerateQuestions.generateQuestions(textFieldInput.text)
        } catch (e: Exception) {
            println(e.message)
            Alert(Alert.AlertType.ERROR, e.message).show()
        }
        finally {
            disableElements(false)
        }
    }

    @FXML
    private fun onGetQuestionsButtonClick() {
        if (textFieldInput.text.isEmpty() || textFieldOutput.text.isEmpty()) {
            Alert(Alert.AlertType.ERROR).show()
            return
        }
        disableElements(true)
        try {
            GenerateQuestions.generate(textFieldInput.text, textFieldOutput.text)
        } catch (e: Exception) {
            Alert(Alert.AlertType.ERROR, e.message).show()
        }
        finally {
            disableElements(false)
        }
    }

    private fun disableElements(isDisable: Boolean) {
        textFieldInput.isDisable = isDisable
        textFieldOutput.isDisable = isDisable
        btnFile.isDisable = isDisable
        btnGenerate.isDisable = isDisable
        btnGetQuestions.isDisable = isDisable
        progressBar.isVisible = isDisable
    }
}