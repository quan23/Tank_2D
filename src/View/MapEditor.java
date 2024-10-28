package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class MapEditor extends JFrame {
    private static final int MATRIX_SIZE = 21;
    private JCheckBox[][] checkboxes = new JCheckBox[MATRIX_SIZE][MATRIX_SIZE];

    public MapEditor() {
        // Set up the frame
        setTitle("21x21 Checkbox Matrix");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 650);
        setLocationRelativeTo(null);

        // Set up the panel with GridLayout
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(MATRIX_SIZE, MATRIX_SIZE));

        // Add checkboxes to the panel and store in 2D array
        for (int i = 0; i < MATRIX_SIZE; i++) {
            for (int j = 0; j < MATRIX_SIZE; j++) {
                checkboxes[i][j] = new JCheckBox();
                panel.add(checkboxes[i][j]);
            }
        }

        // Create a "Done" button
        JButton doneButton = new JButton("Done");
        doneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveCheckboxStatesToFile();
                System.exit(0); // Exit the program
            }
        });

        // Add the panel and button to the frame
        add(panel, BorderLayout.CENTER);
        add(doneButton, BorderLayout.SOUTH);
    }

    private void saveCheckboxStatesToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("checkbox_states.txt"))) {
            for (int i = 0; i < MATRIX_SIZE; i++) {
                for (int j = 0; j < MATRIX_SIZE; j++) {
                    // Write "1" for checked, "0" for unchecked, separated by spaces
                    writer.write(checkboxes[i][j].isSelected() ? "0" : "1");
                    if (j < MATRIX_SIZE - 1) writer.write(" ");
                }
                writer.newLine();
            }
            System.out.println("Checkbox states saved to checkbox_states.txt");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MapEditor matrix = new MapEditor();
            matrix.setVisible(true);
        });
    }
}
