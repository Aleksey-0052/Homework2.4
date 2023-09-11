package ru.skypro.calculator.controller;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.calculator.service.CalculatorService;

@RestController
@RequestMapping("/calculator")

public class CalculatorController {
    public final CalculatorService calculatorService;

    public CalculatorController(CalculatorService calculatorService) {

        this.calculatorService = calculatorService;
    }

    @GetMapping
    public String showGreetings() {

        return "Добро пожаловать в калькулятор";
    }
    @GetMapping("/plus")
    public String sum(@RequestParam int num1, int num2) {
        int result = calculatorService.sum(num1, num2);
        return createRs(num1, num2, '+', result);
    }

    @GetMapping("/minus")
    public String subtract(@RequestParam int num1, int num2) {
        int result = calculatorService.subtract(num1, num2);
        return createRs(num1, num2, '-', result);
    }

    @GetMapping("/multiply")
    public String multiply(@RequestParam int num1, int num2) {
        int result = calculatorService.multiply(num1, num2);
        return createRs(num1, num2, '*', result);
    }

    @GetMapping("/divide")
    public String divide(@RequestParam int num1, int num2) {
        if (num2 != 0) {
            int result = calculatorService.divide(num1, num2);
            return createRs(num1, num2, '/', result);
        } else {
            return "Второй аргумент 0. Деление на 0 невозможно.";
        }

    }

    private String createRs(int num1, int num2, char action, int result) {
        return String.format("%d %c %d = %d",num1, action, num2, result);
    }
    
}
