module com.calculator.calculator {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    opens com.calculator.calculator to javafx.fxml;
    exports com.calculator.calculator;
}