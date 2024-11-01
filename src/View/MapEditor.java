package View;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.border.EmptyBorder;

public class MapEditor extends JFrame {

    private static final int SIZE = 21;
    private JCheckBox[][] checkboxes = new JCheckBox[SIZE][SIZE];
    private List<String> savedFiles;
    private String currentFile = null;

    public MapEditor() {
        setTitle("Map Editor 21x21");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Tạo giao diện checkbox
        JPanel matrixPanel = new JPanel(new GridLayout(SIZE, SIZE));
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                checkboxes[i][j] = createCustomCheckbox(); // Sử dụng checkbox tùy chỉnh
                matrixPanel.add(checkboxes[i][j]);
            }
        }

        // Tạo các nút "New", "Save" và "Select Row/Column"
        JButton newButton = new JButton("New");
        newButton.addActionListener(e -> createNewFile());

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> saveToFile());

        JTextField rowField = new JTextField(2); // Nhập số hàng
        rowField.setMaximumSize(new Dimension(300, 20));
        JButton selectRowButton = new JButton("Select Row");
        selectRowButton.addActionListener(e -> selectAll(true, rowField.getText(), true));

        JTextField colField = new JTextField(2); // Nhập số cột
        colField.setMaximumSize(new Dimension(300, 20));
        JButton selectColButton = new JButton("Select Column");
        selectColButton.addActionListener(e -> selectAll(false, colField.getText(), true));

        // Panel chứa các nút
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        buttonPanel.add(newButton);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(saveButton);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(new JLabel("Row:"));
        buttonPanel.add(rowField);
        buttonPanel.add(selectRowButton);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(new JLabel("Column:"));
        buttonPanel.add(colField);
        buttonPanel.add(selectColButton);

        add(matrixPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.EAST);

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem openMenuItem = new JMenuItem("Open");
        openMenuItem.addActionListener(e -> openFile());
        fileMenu.add(openMenuItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);

        loadSavedFiles();

        
        pack();
    }

    private JCheckBox createCustomCheckbox() {
        JCheckBox checkBox = new JCheckBox();
        // Tải hình ảnh cho checkbox
        ImageIcon icon = new ImageIcon(new ImageIcon("pathway.jpg").getImage().getScaledInstance(32, 32, Image.SCALE_DEFAULT)); // Hình ảnh cho trạng thái chưa chọn
        ImageIcon selectedIcon = new ImageIcon(new ImageIcon("Minecraft-Bricks.jpg").getImage().getScaledInstance(32, 32, Image.SCALE_DEFAULT)); // Hình ảnh cho trạng thái đã chọn

        checkBox.setIcon(icon);
        checkBox.setSelectedIcon(selectedIcon);
        checkBox.setBorderPainted(false); // Tắt viền để chỉ hiển thị hình ảnh
        checkBox.setContentAreaFilled(false); // Tắt nền
        checkBox.setFocusPainted(false); // Tắt viền khi có focus
        checkBox.setBorder(new EmptyBorder(0, 0, 0, 0)); // Giảm khoảng cách giữa các checkbox
        checkBox.setPreferredSize(new Dimension(32, 32)); // Điều chỉnh kích thước checkbox
        return checkBox;
    }

    private void loadSavedFiles() {
        try {
            savedFiles = Files.list(Paths.get("."))
                    .filter(Files::isRegularFile)
                    .map(path -> path.getFileName().toString())
                    .filter(name -> name.endsWith(".txt"))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            savedFiles = new ArrayList<>();
            JOptionPane.showMessageDialog(this, "Error loading file list: " + e.getMessage());
        }
    }

    private void createNewFile() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                checkboxes[i][j].setSelected(false);
            }
        }
        currentFile = null;
    }

    private void saveToFile() {
        if (currentFile == null) {
            currentFile = JOptionPane.showInputDialog(this, "Enter file name:", "Save As", JOptionPane.PLAIN_MESSAGE);
            if (currentFile == null || currentFile.isEmpty()) {
                return;
            }
            currentFile = currentFile.endsWith(".txt") ? currentFile : currentFile + ".txt";
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(currentFile))) {
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    writer.write(checkboxes[i][j].isSelected() ? "0" : "1");
                    if (j < SIZE - 1) {
                        writer.write(" ");
                    }
                }
                writer.newLine();
            }
            if (!savedFiles.contains(currentFile)) {
                savedFiles.add(currentFile);
            }
            JOptionPane.showMessageDialog(this, "Save State to File " + currentFile);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error saving file " + ex.getMessage());
        }
    }

    private void openFile() {
        String[] filesArray = savedFiles.toArray(String[]::new);
        if (filesArray.length == 0) {
            JOptionPane.showMessageDialog(this, "No file available to open.");
            return;
        }

        String fileName = (String) JOptionPane.showInputDialog(this, "Choose a file to open.:", "Open File",
                JOptionPane.PLAIN_MESSAGE, null, filesArray, filesArray[0]);
        if (fileName != null) {
            loadFromFile(fileName);
            currentFile = fileName;
        }
    }

    private void loadFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            int row = 0;
            while ((line = reader.readLine()) != null && row < SIZE) {
                String[] values = line.split(" ");
                for (int col = 0; col < SIZE && col < values.length; col++) {
                    checkboxes[row][col].setSelected("0".equals(values[col]));
                }
                row++;
            }
            JOptionPane.showMessageDialog(this, "File opened. " + fileName);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error when opening file: " + ex.getMessage());
        }
    }

    // Phương thức chọn/bỏ chọn tất cả các checkbox trong hàng
    private void selectAll(boolean isRow, String indexText, boolean select) {
    try {
        int index = Integer.parseInt(indexText);
        if (index >= 0 && index < SIZE) {
            if (isRow) {
                for (int j = 0; j < SIZE; j++) checkboxes[index][j].setSelected(select);
            } else {
                for (int i = 0; i < SIZE; i++) checkboxes[i][index].setSelected(select);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Invalid value!");
        }
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "Please enter a valid number!");
    }
}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MapEditor matrix = new MapEditor();
            matrix.setVisible(true);
        });
    }
}
