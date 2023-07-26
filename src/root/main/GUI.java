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

    String displayedText = "";

    String typedPass;
    String message;

    public GUI() {

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

    }


    @Override
    public void actionPerformed(ActionEvent e) {


    }
    @Override
    public void keyTyped(KeyEvent e) {


    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
