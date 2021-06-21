import javax.swing.*;

import java.awt.*; 
import java.util.ArrayList;


public class BoardPanel extends JPanel {
  
  
  private ArrayList<NodePanel> nodePanels;   // Array zur Aufnahme der Kreisformpanels
  private int[] shortestPath;
 
  public BoardPanel() {
    nodePanels = new ArrayList<NodePanel>();
    shortestPath = new int[0];
  }
  
  // Methode zum Hinzuf�gen von Kreisformen
  public void addNode(Color color, int posX, int posY, NodeListener listener) {
    nodePanels.add(new NodePanel(color, posX, posY, listener));
    repaint();
  }

  public void refreshBoard(){
      this.removeAll();
      nodePanels.removeAll(nodePanels);
      shortestPath = new int[0];
      repaint();
  }
  
  public void refreshShortestPath(int[] path){
    shortestPath = path;
    repaint();
  }

  // Paint-Methode zum Zeichnen der Grafik, wird bei �nderungen durch Java 
  // selbst aufgerufen, erweitert die entsprechende Methode der Oberklasse:
  public void paintComponent(Graphics g) {
    
    super.paintComponent(g);                // Aufruf der �bergeordneten Methode
  
    Graphics2D g2d = (Graphics2D) g;

    for (NodePanel NodePanel : nodePanels) {
      this.add(NodePanel);
    }

    for (int i = 0; i < shortestPath.length; i++) {

      int nextIndex = (i+1) % shortestPath.length;

      g2d.setStroke(new BasicStroke(4.0f));

      int x1 = nodePanels.get(shortestPath[i]).getX()+10;
      int y1 = nodePanels.get(shortestPath[i]).getY()+10;

      int x2 = nodePanels.get(shortestPath[nextIndex]).getX()+10;
      int y2 = nodePanels.get(shortestPath[nextIndex]).getY()+10;

      g2d.drawLine(x1, y1, x2, y2);
    }
    

  }
  // Ende Methoden
  
} // end of ImagePanel

