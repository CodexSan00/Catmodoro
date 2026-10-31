package main.java.com.codexsan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PomodoroApp extends JFrame {

    private JLabel timeLabel;
    private JButton startButton;
    private Timer timer;

    private int timeLeftInSeconds = 1500;
    private boolean isRunning = false;
    private boolean isWorkSession = true;

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

        //Timer configuration
        timer = new Timer(1000, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                if(timeLeftInSeconds > 0){
                    timeLeftInSeconds--;
                    updateTimeDisplay();
                } else {
                    switchSession();
                }
            }
        });

        //Start timer configuration
        startButton.addActionListener(e -> {
            if(!isRunning){
                timer.start();
                isRunning = true;
                startButton.setText("Pausar");
                startButton.setBackground(new Color(180, 80, 80));
            } else {
                timer.stop();
                isRunning = false;
                startButton.setText("Reanudar");
                startButton.setBackground(new Color(46, 139, 87));
            }
        });

        mainPanel.add(timeLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        mainPanel.add(startButton);

        add(mainPanel);
    }

    private void switchSession(){
        isWorkSession = !isWorkSession;

        if(isWorkSession){
            timeLeftInSeconds = 1500;
            timeLabel.setForeground(new Color(255, 179, 0));
            startButton.setText("Stop focus");
        } else {
            timeLeftInSeconds = 300;
            timeLabel.setForeground(new Color(100, 200, 255));
            startButton.setText("Stop break");
        }
        updateTimeDisplay();
    }

    private void updateTimeDisplay(){
        int minutes = timeLeftInSeconds / 60;
        int seconds = timeLeftInSeconds % 60;
        timeLabel.setText(String.format("%02d:%02d", minutes, seconds));
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> {
            new PomodoroApp().setVisible(true);
        });
    }
}
