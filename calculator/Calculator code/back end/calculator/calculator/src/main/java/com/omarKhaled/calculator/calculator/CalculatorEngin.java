package com.omarKhaled.calculator.calculator;

public class CalculatorEngin implements CalculatorEnginInterface{
    private double op1, op2;
    private String operator;

    public void handler(String operation){
        String [] s = operation.split(" ");
        op1 = Double.parseDouble(s[0]);
        operator = s[1];
        if (s.length == 3) {
            op2 = Double.parseDouble(s[2]);
        }
    }
    public String getValue(){
        String output = null;
        CalculatorService calculatorService = new CalculatorService();
        switch (operator) {
            case "+" -> output = calculatorService.add(op1, op2);
            case "-" -> output = calculatorService.sub(op1, op2);
            case "*" -> output = calculatorService.mult(op1, op2);
            case "÷" -> output = calculatorService.divide(op1, op2);
            case "mod" -> output = calculatorService.mod(op1, op2);
            case "sqrt" -> output = calculatorService.sqrt(op1);
            case "square" -> output = calculatorService.square(op1);
            case "inverse" -> output = calculatorService.inverse(op1);
            default -> {
            }
        }
        return output;
    }
}
