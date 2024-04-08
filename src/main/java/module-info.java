module com.gabkt.gdc {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires spring.web;
    requires com.fasterxml.jackson.annotation;
    requires java.desktop;

    opens com.gabkt.gdc.controllers to javafx.fxml;
    opens com.gabkt.gdc.model to com.fasterxml.jackson.databind, javafx.base;
    exports com.gabkt.gdc.model to com.fasterxml.jackson.databind;
    opens com.gabkt.gdc to javafx.fxml;
    exports com.gabkt.gdc;
}