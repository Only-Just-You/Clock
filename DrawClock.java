package org.example.clock;

import java.awt.*;
import java.util.Calendar;

public class DrawClock {
    public void drawSelfClock(Clock clock, Graphics g) {
        Calendar cal = Calendar.getInstance();

        int hour = cal.get(Calendar.HOUR);
        int minute = cal.get(Calendar.MINUTE);
        int second = cal.get(Calendar.SECOND);

        int width = clock.getWidth();
        int height = clock.getHeight();

        int small = Math.min(width, height);
        int diameter = (int) (small * 0.8);
        int radius = diameter / 2;
        Point center = new Point(width / 2, height / 2);

        int secondLength = (int) (radius * 0.8);
        int minuteLength = (int) (secondLength * 0.8);
        int hourLength = (int) (minuteLength * 0.8);

        int secondX = center.x + (int) (secondLength * Math.sin(second * 2 * Math.PI / 60.0));
        int secondY = center.y - (int) (secondLength * Math.cos(second * 2 * Math.PI / 60.0));

        int minuteX = center.x + (int) (minuteLength * Math.sin(minute * 2 * Math.PI / 60.0));
        int minuteY = center.y - (int) (minuteLength * Math.cos(minute * 2 * Math.PI / 60.0));

        int hourX = center.x + (int) (hourLength * Math.sin((minute / 60.0 + hour) * Math.PI / 6.0));
        int hourY = center.y - (int) (hourLength * Math.cos((minute / 60.0 + hour) * Math.PI / 6.0));

        Graphics2D g2d = (Graphics2D) g;

        g.drawOval(center.x - radius, center.y - radius, diameter, diameter);

        for(int i=0; i < 60; i++){
            int x2 = center.x + (int) (radius * Math.sin(i * 2 * Math.PI / 60.0));
            int y2 = center.y - (int) (radius * Math.cos(i * 2 * Math.PI / 60.0));

            if(i % 5 == 0){
                int x1 = center.x + (int) ((secondLength + 1) * Math.sin(i * 2 * Math.PI / 60.0));
                int y1 = center.y - (int) ((secondLength + 1) * Math.cos(i * 2 * Math.PI / 60.0));

                g2d.setStroke(new BasicStroke(2.5f));
                g2d.drawLine(x1, y1, x2, y2);

                int sj = i / 5;
                if(sj == 0){
                    sj = 12;
                }
                g2d.drawString(String.valueOf(sj), x2-6, y2+4);
            }else {
                int x1 = center.x + (int) ((secondLength + 10) * Math.sin(i * 2 * Math.PI / 60.0));
                int y1 = center.y - (int) ((secondLength + 10) * Math.cos(i * 2 * Math.PI / 60.0));

                g2d.setStroke(new BasicStroke(0.8f));
                g2d.drawLine(x1, y1, x2, y2);
            }
        }
        g2d.setColor(Color.RED);
        g2d.setStroke(new BasicStroke(3.0f));
        g2d.drawLine(center.x, center.y, hourX, hourY);

        g2d.setColor(Color.BLUE);
        g2d.setStroke(new BasicStroke(1.5f));
        g2d.drawLine(center.x, center.y, minuteX, minuteY);

        g2d.setColor(Color.MAGENTA);
        g2d.setStroke(new BasicStroke(1.0f));
        g2d.drawLine(center.x, center.y, secondX, secondY);
    }
}
