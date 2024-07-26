module com.example.genquestions {
    requires javafx.controls;
    requires javafx.fxml;
    requires kotlin.stdlib;
    requires org.eclipse.collections.api;
    requires org.eclipse.collections.impl;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.datatype.jsr310;
    requires jackson.datatype.eclipse.collections;

    requires org.kordamp.bootstrapfx.core;
    requires com.fasterxml.jackson.module.kotlin;
    requires kotlinx.coroutines.core;
    requires org.apache.poi.poi;
    requires org.apache.poi.ooxml;

    opens com.example.genquestions to javafx.fxml;
    exports com.example.genquestions;
    exports com.example.genquestions.model;
    exports com.example.genquestions.model.category;
}