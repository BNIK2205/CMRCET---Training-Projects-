import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator {
    private JFrame frame;
    private JTextField textField;
    private double num1, num2, result;
    private String operator;

    public Calculator() {
        frame = new JFrame("Calculator");
        textField = new JTextField();

        textField.setBounds(30, 40, 280, 30);
        textField.setEditable(false);

        JButton[] numberButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(new NumberActionListener());
        }

        JButton addButton = new JButton("+");
        JButton subButton = new JButton("-");
        JButton mulButton = new JButton("*");
        JButton divButton = new JButton("/");
        JButton decButton = new JButton(".");
        JButton equButton = new JButton("=");
        JButton delButton = new JButton("Del");
        JButton clrButton = new JButton("Clr");

        addButton.addActionListener(new OperatorActionListener());
        subButton.addActionListener(new OperatorActionListener());
        mulButton.addActionListener(new OperatorActionListener());
        divButton.addActionListener(new OperatorActionListener());
        equButton.addActionListener(new EqualActionListener());
        clrButton.addActionListener(new ClearActionListener());
        delButton.addActionListener(new DeleteActionListener());
        decButton.addActionListener(new DecimalActionListener());

        numberButtons[1].setBounds(40, 100, 50, 40);
        numberButtons[2].setBounds(110, 100, 50, 40);
        numberButtons[3].setBounds(180, 100, 50, 40);
        numberButtons[4].setBounds(40, 170, 50, 40);
        numberButtons[5].setBounds(110, 170, 50, 40);
        numberButtons[6].setBounds(180, 170, 50, 40);
        numberButtons[7].setBounds(40, 240, 50, 40);
        numberButtons[8].setBounds(110, 240, 50, 40);
        numberButtons[9].setBounds(180, 240, 50, 40);
        numberButtons[0].setBounds(110, 310, 50, 40);

        addButton.setBounds(250, 100, 50, 40);
        subButton.setBounds(250, 170, 50, 40);
        mulButton.setBounds(250, 240, 50, 40);
        divButton.setBounds(250, 310, 50, 40);
        decButton.setBounds(40, 310, 50, 40);
        equButton.setBounds(180, 310, 50, 40);
        delButton.setBounds(40, 380, 120, 40);
        clrButton.setBounds(180, 380, 120, 40);

        frame.add(textField);
        frame.add(addButton);
        frame.add(subButton);
        frame.add(mulButton);
        frame.add(divButton);
        frame.add(decButton);
        frame.add(equButton);
        frame.add(delButton);
        frame.add(clrButton);

        for (int i = 0; i < 10; i++) {
            frame.add(numberButtons[i]);
        }

        frame.setLayout(null);
        frame.setSize(350, 500);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private class NumberActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            textField.setText(textField.getText() + command);
        }
    }

    private class OperatorActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            num1 = Double.parseDouble(textField.getText());
            operator = command;
            textField.setText("");
        }
    }

    private class EqualActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            num2 = Double.parseDouble(textField.getText());

            switch (operator) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    result = num1 / num2;
                    break;
            }

            textField.setText(String.valueOf(result));
            operator = null;
        }
    }

    private class ClearActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            textField.setText("");
        }
    }

    private class DeleteActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String text = textField.getText();
            if (text.length() > 0) {
                textField.setText(text.substring(0, text.length() - 1));
            }
        }
    }

    private class DecimalActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String text = textField.getText();
            if (!text.contains(".")) {
                textField.setText(text + ".");
            }
        }
    }

    public static void main(String[] args) {
        new Calculator();
    }
}
