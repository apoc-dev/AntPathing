import java.awt.event.*;




public class BoardListener implements MouseListener {
  
  // Anfang Attribute
  private AntPathController controller;   // zur Referenz auf das Controller-Objekt
  // Ende Attribute
  

  // Konstruktor, �bergeben wird die Referenz auf das Controller-Objekt  
  public BoardListener(Object controller) {
    this.controller = (AntPathController) controller;
  }
  
  // Implementierung der Event-Reaktionen, ben�tigt wird nur mouseClicked:
  @Override 
  public void mouseClicked(MouseEvent e) {

  } 
  
  @Override 
  public void mousePressed(MouseEvent e) { 
    if(e.getButton() == MouseEvent.BUTTON1){

      controller.addNodeToBoard(e.getX(), e.getY());
    }     
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

