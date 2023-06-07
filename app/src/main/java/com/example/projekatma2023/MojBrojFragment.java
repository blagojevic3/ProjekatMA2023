package com.example.projekatma2023;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import org.w3c.dom.Text;

import java.util.Random;



public class MojBrojFragment extends Fragment implements View.OnClickListener {

    private TextView showNumberField;
    private TextView solutionText;
    private TextView solutionPlayerText;

    private Button stopNumberButton;
    private Button num1;
    private Button num2;
    private Button num3;
    private Button num4;
    private Button num5;
    private Button num6;
    private Button plusButton;
    private Button minusButton;
    private Button multiplyButton;
    private Button divideButton;
    private Button openBracketButton;
    private Button closeBracketButton;
    private Button resetButton;
    private Button calculateButton;

    private int stopButtonClickCount = 0;
    private boolean canAppendNumbers = true;
    private boolean canAppendOperations = false;
    private boolean isNumberButtonClicked = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_moj_broj, container, false);

        stopNumberButton = view.findViewById(R.id.stopNumberButton);
        showNumberField = view.findViewById(R.id.showNumberField);
        num1 = view.findViewById(R.id.num1);
        num2 = view.findViewById(R.id.num2);
        num3 = view.findViewById(R.id.num3);
        num4 = view.findViewById(R.id.num4);
        num5 = view.findViewById(R.id.num5);
        num6 = view.findViewById(R.id.num6);
        plusButton = view.findViewById(R.id.plusButton);
        minusButton = view.findViewById(R.id.minusButton);
        multiplyButton = view.findViewById(R.id.multiplyButton);
        divideButton = view.findViewById(R.id.divideButton);
        openBracketButton = view.findViewById(R.id.openBracketButton);
        closeBracketButton = view.findViewById(R.id.closeBracketButton);
        resetButton = view.findViewById(R.id.resetButton);
        calculateButton = view.findViewById(R.id.calculateButton);
        solutionText = view.findViewById(R.id.solutionText);
        solutionPlayerText = view.findViewById(R.id.solutionPlayerText);

        stopNumberButton.setOnClickListener(this);
        num1.setOnClickListener(this);
        num2.setOnClickListener(this);
        num3.setOnClickListener(this);
        num4.setOnClickListener(this);
        num5.setOnClickListener(this);
        num6.setOnClickListener(this);
        plusButton.setOnClickListener(this);
        minusButton.setOnClickListener(this);
        multiplyButton.setOnClickListener(this);
        divideButton.setOnClickListener(this);
        openBracketButton.setOnClickListener(this);
        closeBracketButton.setOnClickListener(this);
        resetButton.setOnClickListener(this);
        calculateButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.stopNumberButton:
                handleStopNumberButtonClick();
                break;
            case R.id.num1:
            case R.id.num2:
            case R.id.num3:
            case R.id.num4:
            case R.id.num5:
            case R.id.num6:
                handleNumberButtonClick((Button) v);
                break;
            case R.id.plusButton:
            case R.id.minusButton:
            case R.id.multiplyButton:
            case R.id.divideButton:
            case R.id.openBracketButton:
            case R.id.closeBracketButton:
                handleOperationButtonClick((Button) v);
                break;
            case R.id.resetButton:
                handleResetButtonClick();
                break;
            case R.id.calculateButton:
                handleCalculateButtonClick();
                break;
        }
    }

    private void handleStopNumberButtonClick() {
        stopButtonClickCount++;

        if (stopButtonClickCount == 1) {
            int randomNumber = generateRandomNumber(1, 999);
            showNumberField.setText(String.valueOf(randomNumber));
        } else if (stopButtonClickCount <= 5) {
            int randomNumber = generateRandomNumber(1, 9);
            Button button = getNumberButton(stopButtonClickCount);
            button.setText(String.valueOf(randomNumber));
        } else if (stopButtonClickCount == 6) {
            int randomNumber = generateRandomNumberFromGroup(10, 15, 20);
            num5.setText(String.valueOf(randomNumber));
        } else if (stopButtonClickCount == 7) {
            int randomNumber = generateRandomNumberFromGroup(25, 50, 75, 100);
            num6.setText(String.valueOf(randomNumber));
        }

        // Reset number button click status
        isNumberButtonClicked = false;
    }

    private int generateRandomNumber(int min, int max) {
        return new Random().nextInt(max - min + 1) + min;
    }

    private int generateRandomNumberFromGroup(int... numbers) {
        int randomIndex = new Random().nextInt(numbers.length);
        return numbers[randomIndex];
    }

    private Button getNumberButton(int clickCount) {
        switch (clickCount) {
            case 2:
                return num1;
            case 3:
                return num2;
            case 4:
                return num3;
            case 5:
                return num4;
            default:
                throw new IllegalArgumentException("Invalid click count for number button");
        }
    }

    private void handleNumberButtonClick(Button button) {
        if (!isNumberButtonClicked && canAppendNumbers) {
            String number = button.getText().toString();
            solutionText.append(number);
            canAppendNumbers = false;
            canAppendOperations = true;
            isNumberButtonClicked = true;
            button.setEnabled(false);
        }
    }

    private void handleOperationButtonClick(Button button) {
        if (canAppendOperations) {
            String operation = button.getText().toString();
            if (solutionText.length() > 0) {
                String lastCharacter = solutionText.getText().toString().substring(solutionText.length() - 1);
                if (!isOperation(lastCharacter) && !operation.equals(lastCharacter)) {
                    solutionText.append(operation);
                    canAppendNumbers = true;
                    canAppendOperations = false;
                    isNumberButtonClicked = false;
                }
            }
        }
    }

    private boolean isOperation(String character) {
        return character.equals("+") || character.equals("-") || character.equals("*")
                || character.equals("/") || character.equals("(") || character.equals(")");
    }

    private void handleResetButtonClick() {
        solutionText.setText("");
        solutionPlayerText.setText("");
        canAppendNumbers = true;
        canAppendOperations = false;
        isNumberButtonClicked = false;

        num1.setEnabled(true);
        num2.setEnabled(true);
        num3.setEnabled(true);
        num4.setEnabled(true);
        num5.setEnabled(true);
        num6.setEnabled(true);
    }

    private void handleCalculateButtonClick() {
        String expression = solutionText.getText().toString();
        try {
            double result = evaluateExpression(expression);
            solutionPlayerText.setText(String.valueOf(result));
        } catch (IllegalArgumentException e) {
            Toast.makeText(getContext(), "Invalid expression", Toast.LENGTH_SHORT).show();
        }
    }

    private double evaluateExpression(String expression) {
        String[] tokens = expression.split("(?<=\\d)(?=\\D)|(?<=\\D)(?=\\d)");

        if (tokens.length % 2 == 0) {
            throw new IllegalArgumentException("Invalid expression");
        }

        double result = Double.parseDouble(tokens[0]);

        for (int i = 1; i < tokens.length; i += 2) {
            String operator = tokens[i];
            double operand = Double.parseDouble(tokens[i + 1]);

            switch (operator) {
                case "+":
                    result += operand;
                    break;
                case "-":
                    result -= operand;
                    break;
                case "*":
                    result *= operand;
                    break;
                case "/":
                    if (operand == 0) {
                        throw new IllegalArgumentException("Division by zero");
                    }
                    result /= operand;
                    break;
                default:
                    throw new IllegalArgumentException("Invalid operator: " + operator);
            }
        }

        return result;
    }
}