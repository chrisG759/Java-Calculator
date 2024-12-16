package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class calcControl {

    @FXML
    private Button addBut;

    @FXML
    private Button divideBut;

    @FXML
    private Button eightBut;

    @FXML
    private Button fiveBut;
    
    @FXML
    private Button clearBut;

    @FXML
    private Button fourBut;

    @FXML 
    private Button equalsBut;

    @FXML
    private Button minusBut;

    @FXML
    private Button multiplyBut;

    @FXML
    private Button nineBut;

    @FXML
    private Button oneBut;

    @FXML
    private Button periodBut;

    @FXML
    private TextArea resultBox;

    @FXML
    private Button sevenBut;

    @FXML
    private Button sixBut;

    @FXML
    private Button threeBut;

    @FXML
    private Button twoBut;

    @FXML
    private Button zeroBut;

    // Add clicked number to the result box
    @FXML
    private void handleNumber(ActionEvent event) {
        String buttonText = ((Button) event.getSource()).getText();
        resultBox.appendText(buttonText);
    }

    // Evaluate the expression
    public String evaluateExpression(String expression) {
        expression = expression.replaceAll(" ", "");  // Remove spaces
        String[] tokens = expression.split("(?=[-+*/])|(?<=[-+*/])"); // Split by operators, keeping them

        // Handle case for empty expression or invalid input
        if (tokens.length == 0) {
            return "Error";
        }

        // Initialize result with the first number
        double result = 0;
        try {
            result = Double.parseDouble(tokens[0]);
        } catch (NumberFormatException e) {
            return "Error";  // Return error if the first token is not a number
        }

        // Iterate through the rest of the expression
        for (int i = 1; i < tokens.length; i += 2) {
            String operator = tokens[i];
            double operand = 0;
            try {
                operand = Double.parseDouble(tokens[i + 1]);
            } catch (NumberFormatException e) {
                return "Error";  // Return error if the operand is not a number
            }

            // Perform the operation based on the operator
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
                        return "Error: Div by 0";  // Handle division by zero
                    }
                    result /= operand;
                    break;
                default:
                    return "Error: Invalid operator";  // Handle invalid operator
            }
        }

        return String.valueOf(result);
    }

    // Solve equation when equals button is pressed
    @FXML
    private void equationSolver(ActionEvent event) {
        String answerText = resultBox.getText();
        String result = evaluateExpression(answerText);
        resultBox.setText(result);  // Display result in the text area
    }
    
    @FXML
    private void clearResults(ActionEvent event) {
    	resultBox.clear();;
    }
}
