package com.example.genquestions

import javafx.application.Application
import javafx.application.Platform
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.stage.Stage

class HelloApplication : Application() {

    override fun start(stage: Stage) {
        HelloApplication.stage = stage
        val fxmlLoader = FXMLLoader(HelloApplication::class.java.getResource("hello-view.fxml"))
        val scene = Scene(fxmlLoader.load(), 320.0, 240.0)
        stage.title = "Question parser!"
        stage.scene = scene
        stage.show()
    }

    companion object {
        lateinit var stage: Stage
    }
}

fun main() {
    TestJSON.init()
    Application.launch(HelloApplication::class.java)
}