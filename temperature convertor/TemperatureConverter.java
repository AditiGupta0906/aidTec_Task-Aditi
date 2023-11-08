import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import necessary Java Swing classes and event handling classes.

public class TemperatureConverter extends JFrame {
    //  a new class named TemperatureConverter that extends JFrame,
    //  indicating that it is a graphical user interface (GUI) application.

    private JTextField inputField;
    private JLabel resultLabel;
    private JComboBox<String> unitSelector;
    private JButton convertButton;
    private JButton clearButton;
    // components of the GUI, such as text fields, labels, a combo box, and buttons.

    public TemperatureConverter() {
        setTitle("Temperature Converter");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
    }
    // The constructor initializes the JFrame with a title, size, default close operation, and location.
    //  It then calls the initComponents method to set up the GUI components.

    private void initComponents() {
        JPanel panel = new JPanel();
        inputField = new JTextField(10);
        resultLabel = new JLabel("Result: ");
        unitSelector = new JComboBox<>(new String[]{"Celsius", "Fahrenheit", "Kelvin"});
        convertButton = new JButton("Convert");
        clearButton = new JButton("Clear");
        // The initComponents method creates the GUI components, such as text fields, labels, combo boxes, and buttons.

        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertTemperature();
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });
        // Action listeners are added to the "Convert" and "Clear" buttons to respond to user actions.

        panel.add(new JLabel("Enter Temperature: "));
        panel.add(inputField);
        panel.add(new JLabel("Select Unit: "));
        panel.add(unitSelector);
        panel.add(convertButton);
        panel.add(clearButton);
        panel.add(resultLabel);

        add(panel);
    }
    // The GUI components are added to a panel, and the panel is added to the JFrame.

    private void convertTemperature() {
        try {
            double inputTemperature = Double.parseDouble(inputField.getText());
            String selectedUnit = (String) unitSelector.getSelectedItem();
            double result = 0;

            switch (selectedUnit) {
                case "Celsius":
                    result = inputTemperature;
                    break;
                case "Fahrenheit":
                    result = (inputTemperature - 32) * 5 / 9;
                    break;
                case "Kelvin":
                    result = inputTemperature + 273.15;
                    break;
            }

            resultLabel.setText("Result: " + result + " " + selectedUnit);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid number.");
        }
    }
    // The convertTemperature method is responsible for converting the temperature based on the user input and updating the result label. 
    // It uses a try-catch block to handle NumberFormatException in case the user enters invalid input.

    private void clearFields() {
        inputField.setText("");
        resultLabel.setText("Result: ");
    }
    // The clearFields method is used to clear the input field and result label when the "Clear" button is clicked.

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TemperatureConverter().setVisible(true);
            }
        });
    }
}
// The main method starts the application by creating an instance of TemperatureConverter and making it visible on the screen. 
// The invokeLater method is used to ensure that the Swing components are created and modified on the event dispatch thread.