import java.awt.event.*;



public class NodeListener implements MouseListener {
  
  // Anfang Attribute
  private AntPathController app;   // zur Referenz auf das Controller-Objekt
  // Ende Attribute
  

  // Konstruktor, �bergeben wird die Referenz auf das Controller-Objekt  
  public NodeListener(Object controller) {
    this.app = (AntPathController) controller;
  }
  
  // Implementierung der Event-Reaktionen, ben�tigt wird nur mouseClicked:
  @Override 
  public void mouseClicked(MouseEvent e) {
    if(e.getButton() == MouseEvent.BUTTON3){
      NodePanel pnl = (NodePanel) e.getSource();
      int nodeX = (int) pnl.getLocation().getX();
      int nodeY = (int) pnl.getLocation().getY();
  
      app.removeNode(nodeX, nodeY);
    }     
    
  } 
  
  @Override 
  public void mousePressed(MouseEvent e) { 
    
  } 
  
  @Override 
  public void mouseReleased(MouseEvent e) { 
    
  } 
  
  @Override 
  public void mouseEntered(MouseEvent e) { 

  } 
  
  @Override 
  public void mouseExited(MouseEvent e) { 
    
  }
  // Ende Methoden
   
}

