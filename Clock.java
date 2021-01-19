package org.example.clock;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Clock extends JPanel implements Runnable {

    private JLabel j1;
    private SimpleDateFormat dateFormat;

    public Clock(){
        j1 = new JLabel();
        j1.setHorizontalAlignment(JLabel.CENTER);

        dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        new Thread(this).start();

        this.setLayout(new BorderLayout());
        this.add(j1, BorderLayout.SOUTH);
    }

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            j1.setText(dateFormat.format(new Date()));
            repaint();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        DrawClock ts = new DrawClock();
        ts.drawSelfClock(this, g);
    }

    public static void main(String[] args) {
        JFrame jf = new JFrame("指针时钟");

        jf.getContentPane().add(new Clock(), BorderLayout.CENTER);
        jf.setBounds(300, 300, 300, 300);
        jf.setLocation(350,350);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
