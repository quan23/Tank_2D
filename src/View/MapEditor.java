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
        setTitle("Checkbox Matrix 21x21");
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
        JButton selectRowButton = new JButton("Select Row");
        selectRowButton.addActionListener(e -> selectRow(rowField.getText(), true));

        JTextField colField = new JTextField(2); // Nhập số cột
        JButton selectColButton = new JButton("Select Column");
        selectColButton.addActionListener(e -> selectColumn(colField.getText(), true));

        // Panel chứa các nút
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(newButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(new JLabel("Row:"));
        buttonPanel.add(rowField);
        buttonPanel.add(selectRowButton);
        buttonPanel.add(new JLabel("Column:"));
        buttonPanel.add(colField);
        buttonPanel.add(selectColButton);

        add(matrixPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem openMenuItem = new JMenuItem("Open");
        openMenuItem.addActionListener(e -> openFile());
        fileMenu.add(openMenuItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);

        loadSavedFiles();

        //setSize(800, 800);
        setLocationRelativeTo(null);
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
            JOptionPane.showMessageDialog(this, "Lỗi khi tải danh sách file: " + e.getMessage());
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
            currentFile = JOptionPane.showInputDialog(this, "Nhập tên file:", "Save As", JOptionPane.PLAIN_MESSAGE);
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
            JOptionPane.showMessageDialog(this, "Đã lưu trạng thái vào file " + currentFile);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Lỗi khi lưu file: " + ex.getMessage());
        }
    }

    private void openFile() {
        String[] filesArray = savedFiles.toArray(new String[0]);
        if (filesArray.length == 0) {
            JOptionPane.showMessageDialog(this, "Không có file nào để mở.");
            return;
        }

        String fileName = (String) JOptionPane.showInputDialog(this, "Chọn file để mở:", "Open File",
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
            JOptionPane.showMessageDialog(this, "Đã mở file " + fileName);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Lỗi khi mở file: " + ex.getMessage());
        }
    }

    // Phương thức chọn/bỏ chọn tất cả các checkbox trong hàng
    private void selectRow(String rowText, boolean select) {
        try {
            int row = Integer.parseInt(rowText);
            if (row >= 0 && row < SIZE) {
                for (int j = 0; j < SIZE; j++) {
                    checkboxes[row][j].setSelected(select);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Hàng không hợp lệ!");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Hãy nhập số hợp lệ cho hàng!");
        }
    }

    // Phương thức chọn/bỏ chọn tất cả các checkbox trong cột
    private void selectColumn(String colText, boolean select) {
        try {
            int col = Integer.parseInt(colText);
            if (col >= 0 && col < SIZE) {
                for (int i = 0; i < SIZE; i++) {
                    checkboxes[i][col].setSelected(select);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Cột không hợp lệ!");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Hãy nhập số hợp lệ cho cột!");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MapEditor matrix = new MapEditor();
            matrix.setVisible(true);
        });
    }
}
