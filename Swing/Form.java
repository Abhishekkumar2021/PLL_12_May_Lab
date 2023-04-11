/*
    Form is a JFrame. It should have the following components:
    a. Name which will be a “JLabel". Corresponding to it, there shall be a box for the user 
    to write. The box will be a “JTextField.”
    b. DOB, which should also be a “JLabel”. Corresponding to the label, we shall have a 
    “JTextField.” 
    c. Gender, which is also a “JLabel.” Corresponding to the label, there shall be two 
    “JRadioButton,” namely Male and Female. 
    d. Email, which should also be a “JLabel”. Corresponding to the label, we shall have a 
    “JTextField.”.
    e. Password, which should also be a “JLabel”. Corresponding to the label, we shall 
    have a “JTextField.”
    f. Mobile No., which should also be a “JLabel”. Corresponding to the label, we shall 
    have a “JTextField.” 
    g. Desired Branch, which should also be a “JLabel”. Corresponding to the label, we 
    shall have a drop-down menu “JCombobox” of 5 branches: CSE, ME, EEE, ET, and 
    CE.
    h. There should be three more “JLabels,” Marks in Maths, Marks in Physics, and 
    Marks in Chemistry. Corresponding to each label, a text field should be present, 
    which is a “JTextField.” Along with a small Calculate percentage “JButton." Once 
    the calculate button is clicked, it should calculate the percentage and show it in the 
    text field corresponding to the next label, “Percentage.” The percentage is calculated 
    for physics, chemistry, and math and subsequently displayed. 
    Note: Maximum Marks is 100. No need for error checking here.
    i. Percentage is a Jlabel with a “JTextField” corresponding to it, whose value should 
    NOT be written by the User; instead, the value should be fetched as mentioned in 
    point(h). 
    j. Finally, there should be a “JButton,” namely, Register, which, on clicking, must 
    display a Message Registration Successful. For this, use "JOptionPane."
    And on clicking Register, the following should be shown. 
    k. The form should reload, with all values flushed, to enter new values. This should be a 
    continuous process until someone closes the JFrame.
    l. Connect the User Interface to a database. Now for simplicity of the assignment, you 
    may take a simple txt file (however, possibilities are endless, you may connect a 
    MySQL Database or MS Excel as well, but for this assignment let’s keep things 
    simple) and store all the details of a registered user to that txt file in the following 
    format. Name that file as “Database.txt”. Each line must have all the details of a 
    student separated by ‘|’.
*/

package Lab.Swing;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;


public class Form extends JFrame {
    public Form() {
        super("Registration Desk");
        setSize(500, 750);
        setLayout(null);
        setLocationRelativeTo(null);

        // Name label
        JLabel nameLabel = new JLabel("Name");
        nameLabel.setBounds(50, 50, 150, 30);
        add(nameLabel);

        // Name field
        JTextField nameField = new JTextField();
        nameField.setBounds(200, 50, 200, 30);
        add(nameField);

        // DOB label
        JLabel dobLabel = new JLabel("DOB");
        dobLabel.setBounds(50, 100, 150, 30);
        add(dobLabel);

        // DOB field
        JTextField dobField = new JTextField();
        dobField.setBounds(200, 100, 200, 30);
        add(dobField);

        // Gender label
        JLabel genderLabel = new JLabel("Gender");
        genderLabel.setBounds(50, 150, 150, 30);
        add(genderLabel);

        // Gender male button
        JRadioButton maleButton = new JRadioButton("Male");
        maleButton.setBounds(200, 150, 100, 30);
        add(maleButton);

        // Gender female button
        JRadioButton femaleButton = new JRadioButton("Female");
        femaleButton.setBounds(300, 150, 100, 30);
        add(femaleButton);

        // Handle the radio buttons i.e at a time only one button can be selected
        ButtonGroup group = new ButtonGroup();
        group.add(maleButton);
        group.add(femaleButton);

        // Email label
        JLabel emailLabel = new JLabel("Email");
        emailLabel.setBounds(50, 200, 150, 30);
        add(emailLabel);

        // Email field
        JTextField emailField = new JTextField();
        emailField.setBounds(200, 200, 200, 30);
        add(emailField);

        // Password label
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(50, 250, 150, 30);
        add(passwordLabel);

        // Password field
        JTextField passwordField = new JTextField();
        passwordField.setBounds(200, 250, 200, 30);
        add(passwordField);

        // Mobile label
        JLabel mobileLabel = new JLabel("Mobile");
        mobileLabel.setBounds(50, 300, 150, 30);
        add(mobileLabel);

        // Mobile field
        JTextField mobileField = new JTextField();
        mobileField.setBounds(200, 300, 200, 30);
        add(mobileField);

        // Branch label
        JLabel branchLabel = new JLabel("Branch");
        branchLabel.setBounds(50, 350, 150, 30);
        add(branchLabel);

        // Branch JCombobox
        String[] branches = { "CSE", "ME", "EEE", "ET", "CE" };
        JComboBox<String> branchBox = new JComboBox<String>(branches);
        branchBox.setBounds(200, 350, 200, 30);
        add(branchBox);

        // Math label
        JLabel mathLabel = new JLabel("Math");
        mathLabel.setBounds(50, 400, 150, 30);
        add(mathLabel);

        // Math field
        JTextField mathField = new JTextField();
        mathField.setBounds(200, 400, 200, 30);
        add(mathField);

        // Physics label
        JLabel physicsLabel = new JLabel("Physics");
        physicsLabel.setBounds(50, 450, 150, 30);
        add(physicsLabel);

        // Physics field
        JTextField physicsField = new JTextField();
        physicsField.setBounds(200, 450, 200, 30);
        add(physicsField);

        // Chemistry label
        JLabel chemistryLabel = new JLabel("Chemistry");
        chemistryLabel.setBounds(50, 500, 150, 30);
        add(chemistryLabel);

        // Chemistry field
        JTextField chemistryField = new JTextField();
        chemistryField.setBounds(200, 500, 200, 30);
        add(chemistryField);

        // Percentage label
        JLabel percentageLabel = new JLabel("Percentage");
        percentageLabel.setBounds(50, 550, 150, 30);
        add(percentageLabel);

        // Percentage field
        JTextField percentageField = new JTextField();
        percentageField.setBounds(200, 550, 200, 30);
        add(percentageField);

        // Calculate button
        JButton calculateButton = new JButton("Calculate Percentage");
        calculateButton.setBounds(50, 600, 350, 30);
        add(calculateButton);

        // Register button
        JButton registerButton = new JButton("Register");
        registerButton.setBounds(50, 650, 350, 30);
        add(registerButton);

        // Event handlers
        calculateButton.addActionListener(e -> {
            // Calculate percentage
            float math = Float.parseFloat(mathField.getText());
            float physics = Float.parseFloat(physicsField.getText());
            float chemistry = Float.parseFloat(chemistryField.getText());
            float percentage = (math + physics + chemistry) / 3;
            percentageField.setText(String.valueOf(percentage));
        });

        registerButton.addActionListener(e->{
            // open a file to write the data
            File file = new File("./PLL_AS6/Database.txt");

            // Check if the file exists
            if(!file.exists()){
                try{
                    file.createNewFile();
                }
                catch(Exception ex){
                    System.out.println("Error: " + ex.getMessage());
                }
            }

            try{
                // First count how many lines are there in the file
                int count = 0;
                Scanner scanner = new Scanner(file);
                while(scanner.hasNextLine()){
                    scanner.nextLine();
                    count++;
                }
                scanner.close();

                // Now open the file in append mode
                FileWriter writer = new FileWriter(file, true);

                // Write the data
                writer.write((count+1) + " | " + nameField.getText() + " | " + dobField.getText() + " | " + (maleButton.isSelected() ? "Male" : "Female") + " | " + emailField.getText() + " | " + passwordField.getText().replaceAll(".", "*") + " | " + mobileField.getText() + " | " + branchBox.getSelectedItem() + " | " + mathField.getText() + " | " + physicsField.getText() + " | " + chemistryField.getText() + " | " + percentageField.getText() + " |" + System.lineSeparator());

                // Close the file
                writer.close();
            }
            catch(Exception ex){
                System.out.println("Error writing to file - " + ex.getMessage());
            }

            // open a new window to show the message & close the current window
            JOptionPane.showMessageDialog(null, "Registration Successful!", "Message", JOptionPane.INFORMATION_MESSAGE);
            nameField.setText("");
            dobField.setText("");
            emailField.setText("");
            passwordField.setText("");
            mobileField.setText("");
            branchBox.setSelectedIndex(0);
            mathField.setText("");  
            physicsField.setText("");
            chemistryField.setText("");
            percentageField.setText("");
            group.clearSelection();
        });
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Form();
    }
}