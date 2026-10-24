package main.java.com.codexsan;

import javax.swing.*;
import java.awt.*;
public class PomodoroApp extends JFrame {

    private JLabel timeLabel;
    private JButton startButton;

    public PomodoroApp(){
        setTitle("Catmodoro");
        setSize(350, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);

        //Main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(24, 24, 37)); //Dark mode
        mainPanel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

        //Timer label
        timeLabel = new JLabel("25:00", JLabel.CENTER);
        timeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        timeLabel.setFont(new Font("Monospaced", Font.BOLD, 48));
        timeLabel.setForeground(new Color(255, 179, 0));

        //Start button
        startButton = new JButton("Iniciar Foco");
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        startButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        startButton.setBackground(new Color(46, 139, 87));
        startButton.setForeground(Color.WHITE);
        startButton.setFocusPainted(false);
        startButton.setBorderPainted(false);
        startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        startButton.addActionListener(e -> {
            timeLabel.setForeground(new Color(120, 220, 120));
            startButton.setText("Probando...");
        });

        mainPanel.add(timeLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        mainPanel.add(startButton);

        add(mainPanel);
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> {
            new PomodoroApp().setVisible(true);
        });
    }
}
