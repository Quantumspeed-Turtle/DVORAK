package main;

import FileHandling.FileHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

public class GUI extends JFrame implements ActionListener, KeyListener {

    JButton button;
    Timer timer;
    JLabel label;

    int typed = 0;
    int count = 0;

    double start;
    double end;
    double elapsed;

    boolean running;
    boolean ended;

    final int SCREEN_X;
    final int SCREEN_Y;
    final int DELAY = 100;

    private final FileHandler fileHandler;
    String displayedText = "";

    String typedPass;
    String message;

    public GUI() {

        fileHandler = new FileHandler();

        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.SCREEN_X = 600;
        this.SCREEN_Y = 500;
        this.setSize(SCREEN_X, SCREEN_Y);
        this.setVisible(true);
        this.setLocationRelativeTo(null);

        button = new JButton("Begin");
        button.setFont(new Font("Arial", Font.BOLD, 30));
        button.setForeground(Color.BLACK);
        button.addActionListener(this);
        //button.setFocusable(false);

        label = new JLabel();
        label.setText("Press Begin");
        label.setFont(new Font("Arial", Font.BOLD, 20));
        label.setVisible(true);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setBackground(Color.WHITE);

        this.add(button, BorderLayout.SOUTH);
        this.add(label, BorderLayout.CENTER);
        this.getContentPane().setBackground(Color.WHITE);
        this.addKeyListener(this);
        this.setFocusable(true);
        this.setResizable(false);
        this.setTitle("DVORAK TRAINING CAMP");
        this.revalidate();


    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        draw(g);
    }

    public void draw(Graphics g) {

        g.setFont(new Font("Arial", Font.PLAIN, 25));

        g.setFont(new Font("MV Boli", Font.BOLD, 25));
        FontMetrics metrics = g.getFontMetrics(g.getFont());
        int lineHeight = g.getFont().getSize() * 2; // Adjust this value to set the line spacing

        if (running) {
            int yPosition = g.getFont().getSize() * 5; // Adjust 5 to set the initial line position
            int startIndex = 0;

            while (startIndex < displayedText.length()) {
                int endIndex = Math.min(startIndex + 50, displayedText.length());
                String line = displayedText.substring(startIndex, endIndex);
                g.drawString(line, g.getFont().getSize(), yPosition);
                yPosition += lineHeight;
                startIndex = endIndex;
            }

            // Displaying correctly typed passage in GREEN
            g.setColor(Color.GREEN);
            yPosition = g.getFont().getSize() * 5; // Reset the yPosition to the initial line position
            startIndex = 0;

            while (startIndex < typedPass.length()) {
                int endIndex = Math.min(startIndex + 50, typedPass.length());
                String line = typedPass.substring(startIndex, endIndex);
                g.drawString(line, g.getFont().getSize(), yPosition);
                yPosition += lineHeight;
                startIndex = endIndex;
            }

            // The typing process is now completed, so set running to false
            running = false;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==button){
            label.setText("");

            displayedText = fileHandler.getText();
            timer = new Timer(DELAY, this);
            timer.start();
            running = true;
            ended = false;


            typedPass = "";
            message = "";


            typed = 0;
            count = 0;

            if(running)
                repaint();
            if(ended)
                repaint();
        }

    }
    @Override
    public void keyTyped(KeyEvent e) {

        if(displayedText.length() > 1) {

            char[] c = displayedText.toCharArray();
            if(typed<200) {
                running=true;
                if (e.getKeyChar() == c[typed]) {

                    typedPass = typedPass+c[typed];
                    typed++;
                    count++;

                }
            }


        }

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
