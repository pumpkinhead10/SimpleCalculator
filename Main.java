import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] argv) {
        new CalculatorUI();
    }
}

class CalculatorUI extends JFrame {
    private final JTextField screen;
    CalculatorUI() {
        super();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        this.setSize(new Dimension(500, 600));
        this.getContentPane().setBackground(Color.BLACK);

        this.screen =  screen();
        buttons();

        this.setVisible(true);
    }

    // calculate math expressions

    private void evaluate() {
        String numbers = screen.getText();
        int result = 0;
        int currentNumber = 0;
        int lastNumber = 0;  // This stores the result of the last multiplication or division
        char operation = '+';  // Start with '+' to add the first number

        for (int i = 0; i < numbers.length(); i++) {
            char c = numbers.charAt(i);

            if (Character.isDigit(c)) {
                currentNumber = currentNumber * 10 + (c - '0');
            }

            // If the character is an operator, or it's the last character in the string
            if (!Character.isDigit(c) && c != ' ' || i == numbers.length() - 1) {
                switch (operation) {
                    case('+'): {
                        result += lastNumber;
                        lastNumber = currentNumber;
                        break;
                    }

                    case('-'): {
                        result += lastNumber;
                        lastNumber = -currentNumber;
                        break;
                    }

                    case('*'): {
                        lastNumber *= currentNumber;
                        break;
                    }

                    case('/'): {
                        lastNumber /= currentNumber;
                        break;
                    }

                    case('%'): {
                        lastNumber %= currentNumber;
                        break;
                    }

                    default: {
                        break;
                    }
                }
                operation = c;  // Update the operation to the current operator
                currentNumber = 0;  // Reset the current number
            }
        }

        // Add the last number to the result
        result += lastNumber;

        // check if number have decimal pr not
        String formattedResult = (result % 1 == 0) ? String.format("%d", (int) result) : String.format("%.2f", result);


        screen.setText(formattedResult);

    }


    private JTextField screen() {
        // number screen
        JTextField screen = new JTextField();

        screen.setForeground(Color.green);
        screen.setBackground(Color.black);
        screen.setFont(new Font("Consolas", Font.PLAIN, 24));
        screen.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.MAGENTA));

        screen.addActionListener(e -> evaluate());

        this.add(screen);
        return screen;
    }

    private void buttons() {
        // buttons
        JPanel buttons = new JPanel(new GridBagLayout());
        buttons.setBorder(null);
        buttons.setPreferredSize(new Dimension(500, 500));
        buttons.setBackground(Color.black);
        GridBagConstraints gbc = new GridBagConstraints();

        // grid styles
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        Font buttonFont = new Font("Consolas", Font.BOLD, 18);

        // row 0
        gbc.gridx = 0;
        gbc.gridy = 0;
        JButton bt1 = new JButton("1");
        buttons.add(bt1, gbc);

        gbc.gridx = 1;
        JButton bt2 = new JButton("2");
        buttons.add(bt2, gbc);


        gbc.gridx = 2;
        JButton bt3 = new JButton("3");
        buttons.add(bt3, gbc);

        gbc.gridx = 3;
        JButton bt_clear = new JButton("C");
        buttons.add(bt_clear, gbc);

        // row 1
        gbc.gridx = 0;
        gbc.gridy = 1;
        JButton bt4 = new JButton("4");
        buttons.add(bt4, gbc);

        gbc.gridx = 1;
        JButton bt5 = new JButton("5");
        buttons.add(bt5, gbc);


        gbc.gridx = 2;
        JButton bt6 = new JButton("6");
        buttons.add(bt6, gbc);

        gbc.gridx = 3;
        JButton bt_plus = new JButton("+");
        buttons.add(bt_plus, gbc);

        // row 2
        gbc.gridx = 0;
        gbc.gridy = 2;
        JButton bt7 = new JButton("7");
        buttons.add(bt7, gbc);

        gbc.gridx = 1;
        JButton bt8 = new JButton("8");
        buttons.add(bt8, gbc);


        gbc.gridx = 2;
        JButton bt9 = new JButton("9");
        buttons.add(bt9, gbc);

        gbc.gridx = 3;
        JButton bt_minus = new JButton("-");
        buttons.add(bt_minus, gbc);

        // row 3
        gbc.gridx = 0;
        gbc.gridy = 3;
        JButton bt_multiply = new JButton("*");
        buttons.add(bt_multiply, gbc);

        gbc.gridx = 1;
        JButton bt0 = new JButton("0");
        buttons.add(bt0, gbc);


        gbc.gridx = 2;
        JButton bt_divide = new JButton("/");
        buttons.add(bt_divide, gbc);


        gbc.gridx = 3;
        JButton bt_equal = new JButton("=");
        buttons.add(bt_equal, gbc);


        // styles
        List<JButton> btn_list = new ArrayList<>();
        btn_list.add(bt1);
        btn_list.add(bt2);
        btn_list.add(bt3);
        btn_list.add(bt4);
        btn_list.add(bt5);
        btn_list.add(bt6);
        btn_list.add(bt7);
        btn_list.add(bt8);
        btn_list.add(bt9);
        btn_list.add(bt0);
        btn_list.add(bt_clear);
        btn_list.add(bt_divide);
        btn_list.add(bt_multiply);
        btn_list.add(bt_equal);
        btn_list.add(bt_plus);
        btn_list.add(bt_minus);

        for(JButton btn: btn_list) {
            btn.setFont(buttonFont);
            btn.setBackground(Color.black);
            btn.setForeground(Color.gray);
            btn.addActionListener(e -> {
                if ("C".equals(btn.getText())) {
                    screen.setText(""); // Clear the text field
                } else if("=".equals(btn.getText())) {
                    screen.postActionEvent();
                } else {
                    screen.setText(screen.getText()+btn.getText()); // Set the text field value
                }
            });
        }

        this.add(buttons);
    }


}