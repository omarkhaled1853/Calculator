package com.omarKhaled.calculator.calculator;

import org.springframework.stereotype.Service;

@Service
public class CalculatorService implements CalculatorInterface{
    public CalculatorService() {
    }

    @Override
    public String add(double op1, double op2) {
        return String.valueOf(op1 + op2);
    }

    @Override
    public String sub(double op1, double op2) {
        return String.valueOf(op1 - op2);
    }

    @Override
    public String mult(double op1, double op2) {
        return String.valueOf(op1 * op2);
    }

    @Override
    public String divide(double op1, double op2) {
        return op2 != 0 ? String.valueOf(op1 / op2) : "ERROR";
    }

    @Override
    public String mod(double op1, double op2) {
        return op2 != 0 ? String.valueOf(op1 % op2) : "ERROR";
    }

    @Override
    public String sqrt(double op) {
        return op >= 0 ? String.valueOf(Math.sqrt(op)) : "ERROR";
    }

    @Override
    public String square(double op) {
        return String.valueOf(Math.pow(op, 2));
    }

    @Override
    public String inverse(double op) {
        return op != 0 ? String.valueOf(1 / op) : "ERROR";
    }
}
