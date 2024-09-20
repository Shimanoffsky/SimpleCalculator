package com.calculator.calculator;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.text.Font;

public class CalculatorApp extends Application {

    private Label display = new Label();
    private StringBuilder currentInput = new StringBuilder();
    private double num1 = 0;
    private String operator = "";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("JavaFX Calculator");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(10);
        grid.setHgap(10);

        display.setFont(new Font("Arial", 24));
        display.setPrefHeight(50);
        display.setPrefWidth(240);
        display.setStyle("-fx-border-color: black;");
        grid.add(display, 0, 0, 4, 1);

        String[][] buttons = {
                {"7", "8", "9", "/"},
                {"4", "5", "6", "*"},
                {"1", "2", "3", "-"},
                {"0", "C", "=", "+"}
        };

        for (int row = 0; row < buttons.length; row++) {
            for (int col = 0; col < buttons[row].length; col++) {
                String text = buttons[row][col];
                Button button = new Button(text);
                button.setPrefSize(60, 60);
                button.setOnAction(e -> handleButtonClick(text));
                grid.add(button, col, row + 1);
            }
        }

        Scene scene = new Scene(grid, 250, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void handleButtonClick(String text) {
        switch (text) {
            case "C":
                currentInput.setLength(0);
                display.setText("");
                num1 = 0;
                operator = "";
                break;
            case "=":
                calculate();
                break;
            case "+": case "-": case "*": case "/":
                if (operator.isEmpty() && currentInput.length() > 0) {
                    num1 = Double.parseDouble(currentInput.toString());
                    operator = text;
                    currentInput.setLength(0);
                }
                break;
            default:
                currentInput.append(text);
                display.setText(currentInput.toString());
                break;
        }
    }

    private void calculate() {
        if (!operator.isEmpty() && currentInput.length() > 0) {
            double num2 = Double.parseDouble(currentInput.toString());
            double result = 0;
            switch (operator) {
                case "+": result = num1 + num2; break;
                case "-": result = num1 - num2; break;
                case "*": result = num1 * num2; break;
                case "/": result = num1 / num2; break;
            }
            display.setText(String.valueOf(result));
            operator = "";
            currentInput.setLength(0);
        }
    }
}

