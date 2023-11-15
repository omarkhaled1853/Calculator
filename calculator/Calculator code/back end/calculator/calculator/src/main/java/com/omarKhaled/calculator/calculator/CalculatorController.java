package com.omarKhaled.calculator.calculator;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping
@CrossOrigin
public class CalculatorController {
    private CalculatorEnginInterface calculatorEnginInterface;

    public CalculatorController() {
        this.calculatorEnginInterface = new CalculatorEngin();
    }

    @GetMapping("/{operation}")
    public String getValue(@PathVariable String operation){
        calculatorEnginInterface.handler(operation);
        return calculatorEnginInterface.getValue();
    }
}
