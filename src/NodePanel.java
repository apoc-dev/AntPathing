import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

public class NodePanel extends JPanel{
    
    public Shape circle;
    public NodeListener listener;
    public Color color;
    public NodePanel(Color color, int x, int y, NodeListener listener) {
        this.color = color;
        Dimension size = new Dimension(20, 20);   // Festlegung der Paneldimension
        setPreferredSize(size);                  // Festlegung der bevorzugten Gr��e
        setMinimumSize(size);                    // Festlegung der Mindestgr��e
        setMaximumSize(size);                    // Festlegung der Maximalgr��e
        setSize(size);                           // Festlegung der Gr��e
        setLayout(null);                         // Kein Layout-Manager
        setOpaque(false);                        // Hintergrund transparent
        setLocation(x, y);                       // Festlegung der Position
        addMouseListener(listener);              // Listener-Objekt registrieren
        circle = new Ellipse2D.Double(0,0,20,20);  // Erstellung der Kreisform
    }

    public void paintComponent(Graphics g) {
    
        super.paintComponent(g);                         // Aufruf der �bergeordneten Methode
        
        Graphics2D g2d = (Graphics2D) g.create();        // Erstellung eines 2D-Grafik-Objekts
        
        g2d.setColor(color);                             // Farbe der Form festlegen
        g2d.fill(circle);                                // Kreisform zeichnen
        

        
        //g2d.dispose();
      }
}
