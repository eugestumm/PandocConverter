import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class MarkdownConverter extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JButton pdfButton, pptxButton;
    private JLabel infoLabel;

    public MarkdownConverter() {
        super("Euge Stumm&ChatGPT's MD Converter");

        // Create main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(mainPanel);

        // Create information label
        infoLabel = new JLabel("This program converts Markdown files to PDF and PPTX.");
        infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(infoLabel, BorderLayout.NORTH);

        // Create button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        // Create PDF button
        pdfButton = new JButton("Convert to PDF");
        pdfButton.setPreferredSize(new Dimension(150, 50));
        pdfButton.addActionListener(this);
        buttonPanel.add(pdfButton);

        // Create PPTX button
        pptxButton = new JButton("Convert to PPTX");
        pptxButton.setPreferredSize(new Dimension(150, 50));
        pptxButton.addActionListener(this);
        buttonPanel.add(pptxButton);

        // Set window properties
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 150);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == pdfButton) {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                String command = "pandoc -s \"" + selectedFile.getAbsolutePath() + "\" -o \"" + selectedFile.getParent() + "\\" + selectedFile.getName().replace(".md", ".pdf") + "\"";
                try {
                    Process process = Runtime.getRuntime().exec(command);
                    process.waitFor();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        } else if (e.getSource() == pptxButton) {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                String command = "pandoc -s \"" + selectedFile.getAbsolutePath() + "\" -o \"" + selectedFile.getParent() + "\\" + selectedFile.getName().replace(".md", ".pptx") + "\" --slide-level=2";
                try {
                    Process process = Runtime.getRuntime().exec(command);
                    process.waitFor();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        new MarkdownConverter();
    }
}
