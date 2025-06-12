import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

class StudentGradeTracker extends JFrame {

    public JTextField nameField;
    public JTextField gradeField;
    public JTextArea outputArea;
    public double[] grades = new double[100];
    public int gradeCount = 0;

    public StudentGradeTracker() {
        setLayout(new BorderLayout());

        // Input panel
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(2, 2));
        inputPanel.add(new JLabel("Student Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Grade:"));
        gradeField = new JTextField();
        inputPanel.add(gradeField);
        add(inputPanel, BorderLayout.NORTH);

        // Button panel
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add Grade");
        addButton.addActionListener(new AddGradeListener());
        buttonPanel.add(addButton);
        JButton computeButton = new JButton("Compute Statistics");
        computeButton.addActionListener(new ComputeStatisticsListener());
        buttonPanel.add(computeButton);
        JButton clearButton = new JButton("Clear Grades");
        clearButton.addActionListener(new ClearGradesListener());
        buttonPanel.add(clearButton);
        add(buttonPanel, BorderLayout.CENTER);

        // Output panel
        outputArea = new JTextArea(10, 20);
        outputArea.setEditable(false);
        add(new JScrollPane(outputArea), BorderLayout.SOUTH);

        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public class AddGradeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                double grade = Double.parseDouble(gradeField.getText());
                if (grade >= 0 && grade <= 100) {
                    grades[gradeCount] = grade;
                    gradeCount++;
                    gradeField.setText("");
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid grade. Please enter a grade between 0 and 100.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a number.");
            }
        }
    }
public class ComputeStatisticsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (gradeCount == 0) {
                JOptionPane.showMessageDialog(null, "No grades entered.");
                return;
            }

            double sum = 0;
            double highest = grades[0];
            double lowest = grades[0];

            for (int i = 0; i < gradeCount; i++) {
                sum += grades[i];
                if (grades[i] > highest) {
                    highest = grades[i];
                }
                if (grades[i] < lowest) {
                    lowest = grades[i];
                }
            }

            double average = sum / gradeCount;

            DecimalFormat df = new DecimalFormat("#.00");

            StringBuilder output = new StringBuilder();
            output.append("Student Name: ").append(nameField.getText()).append("\n");
            output.append("Average Grade: ").append(df.format(average)).append("\n");
            output.append("Highest Grade: ").append(df.format(highest)).append("\n");
            output.append("Lowest Grade: ").append(df.format(lowest));

            outputArea.setText(output.toString());
        }
    }

    public class ClearGradesListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            gradeCount = 0;
            nameField.setText("");
            gradeField.setText("");
            outputArea.setText("");
        }
    }

    public static void main(String[] args) {
        new StudentGradeTracker();
    }
}