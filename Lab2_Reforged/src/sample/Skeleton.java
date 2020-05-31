/*package sample;

import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Shape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;

public class Skeleton extends JPanel {
    private static int maxWidth;
    private static int maxHeight;

    public void draw_task1(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setBackground(Color.GREEN);
        g2d.clearRect(0, 0, maxWidth, maxHeight);
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHints(rh);

        g2d.setStroke(new BasicStroke(5, BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_BEVEL));

        g2d.setPaint(Color.yellow);
        double triangle_points[][] = {
                { 110.0, 165.0 },
                { 175.0, 20.0 },
                { 265.0, 165.0 }
        };
        GeneralPath triangle = new GeneralPath();
        triangle.moveTo(triangle_points[0][0], triangle_points[0][1]);
        for (int k = 1; k < triangle_points.length; k++)
            triangle.lineTo(triangle_points[k][0], triangle_points[k][1]);
        triangle.closePath();
        g2d.fill(triangle);
        //g2d.draw(triangle);

        g2d.setPaint(Color.RED);
        double line1_points[][] = {
                { 16.0, 20.0 },
                { 80.0, 215.0 },
                { 300.0, 215.0 },
                { 355.0, 20.0 }
        };
        GeneralPath line1 = new GeneralPath();
        line1.moveTo(line1_points[0][0], line1_points[0][1]);
        for (int k = 1; k < line1_points.length; k++)
            line1.lineTo(line1_points[k][0], line1_points[k][1]);
        g2d.draw(line1);

        g2d.setPaint(Color.BLUE);
        g2d.drawLine(65, 20, 115, 20);
        double line2_points[][] = {
                { 220, 20, },
                { 290, 20 },
                { 292, 18 }
        };
        GeneralPath line2 = new GeneralPath();
        line2.moveTo(line2_points[0][0], line2_points[0][1]);
        for (int k = 1; k < line2_points.length; k++)
            line2.lineTo(line2_points[k][0], line2_points[k][1]);
        g2d.draw(line2);
        GradientPaint fill = new GradientPaint(228,337,Color.blue,413,612, Color.YELLOW, true);
        g2d.setPaint(fill);
        g2d.fill(new Ellipse2D.Double(30,40,70,63));

        g2d.translate(400, 0);
        g2d.setStroke(new BasicStroke(5, BasicStroke.CAP_ROUND,
                BasicStroke.JOIN_BEVEL));
        g2d.draw(new Rectangle(0,0,500,400));
    }
    public void draw_task2(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        Color fur = new Color(12,24,50);
        g2d.setPaint(fur);
        Ellipse2D head = new Ellipse2D.Double(17.5,17.75,208.5,154.75);
        g2d.fill(head);
        double hair[][] = {
                { 101.25, 19.5 },
                { 130, 0.25 },
                { 130.25, 9.25 },
                { 152.25, 2.25 },
                { 145, 12 },
                { 160.5, 10 },
                { 148.5, 21.5 }
        };
        GradientPaint fill = new GradientPaint(129,0,Color.red,129,21, fur, false);
        g2d.setPaint(fill);
        GeneralPath hairs = new GeneralPath();
        hairs.moveTo(hair[0][0], hair[0][1]);
        for (int k = 1; k < hair.length; k++)
            hairs.lineTo(hair[k][0], hair[k][1]);
        g2d.fill(hairs);
        
    }
    public void paint(Graphics g) {
        draw_task1(g);

        draw_task2(g);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Hehe");
        // Визначаємо поведінку програми при закритті вікна (ЛКМ на "хрестик")
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Визначаємо розмір вікна
        frame.setSize(1080, 720);
        // Якщо позиція прив'язана до null, вікно з'явиться в центрі екрану
        frame.setLocationRelativeTo(null);
        // Забороняємо змінювати розміри вікна
        frame.add(new Skeleton());
        frame.setVisible(true);
        Dimension size = frame.getSize();
        Insets insets = frame.getInsets();
        maxWidth = size.width - insets.left - insets.right - 1;
        maxHeight = size.height - insets.top - insets.bottom - 1;

    }
}
*/ //I wanted to make a new picture but got insanely bored in the process
package sample;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.GeneralPath;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Skeleton extends JPanel  implements ActionListener {
    private static int maxWidth;
    private static int maxHeight;

    Timer timer;


    private double angle = 0;


    private double scale = 0.5;
    private double delta = 0.01;

    public Skeleton() {

        timer = new Timer(50, this);
        timer.start();
    }


    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2d = (Graphics2D) g;


        g2d.setBackground(Color.GREEN);
        g2d.clearRect(0, 0, maxWidth, maxHeight);


        BasicStroke bs1 = new BasicStroke(15, BasicStroke.CAP_ROUND,
                BasicStroke.JOIN_BEVEL);
        g2d.setStroke(bs1);
        g2d.drawRect(15, 15, maxWidth-30, maxHeight-30);

        g2d.translate( 150 , 150 );


        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);


        bs1 = new BasicStroke(5, BasicStroke.CAP_ROUND,
                BasicStroke.JOIN_BEVEL);
        g2d.setStroke(bs1);
        int xPoints[] = new int[] {100, 130, 220, 250};
        int yPoints[] = new int[] {100, 200, 200, 100};

        GradientPaint gp = new GradientPaint(5, 25,
                Color.YELLOW, 20, 2, Color.BLUE, true);
        double points[][] = {
                { 140, 180 }, { 210, 180 }, { 175, 105 }, { 140, 180 }
        };
        GeneralPath triangle = new GeneralPath();
        triangle.moveTo(points[0][0], points[0][1]);
        for (int k = 1; k < points.length; k++)
            triangle.lineTo(points[k][0], points[k][1]);
        triangle.closePath();


        g2d.rotate(angle, 220, 200);


        g2d.scale(scale, scale);


        g2d.setColor(Color.RED);
        g2d.drawPolyline(xPoints, yPoints, xPoints.length);

        g2d.setPaint(gp);
        g2d.fill(triangle);

        g2d.setColor(Color.BLUE);
        g2d.drawLine(120, 105, 150, 105);
        g2d.drawLine(200, 105, 230, 105);


    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("Lab 2");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 700);
        frame.setLocationRelativeTo(null); //centre
        frame.setResizable(false);
        frame.add(new Skeleton());
        frame.setVisible(true);

        Dimension size = frame.getSize();
        Insets insets = frame.getInsets();
        maxWidth = size.width - insets.left - insets.right - 1;
        maxHeight = size.height - insets.top - insets.bottom - 1;
    }

    public void actionPerformed(ActionEvent e) {
        if ( scale < 0.1 ) {
            delta = -delta;
        } else if (scale > 0.99) {
            delta = -delta;
        }

        scale += delta;
        angle += 0.05;

        repaint();
    }
}
