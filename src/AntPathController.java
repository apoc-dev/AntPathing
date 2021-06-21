import java.awt.*;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AntPathController {

    private JFrame frame;
    private JPanel framePanel;
    private JPanel controlPanel;
    private JPanel infoPanel;
    private JPanel drawPanel;
    private JButton resetButton;
    private JButton solveButton;

    private Board board;
    private BoardPanel boardPanel;

    private JLabel runtime;
    private JLabel estimatedTime;
    private JLabel possiblePaths;
    private JLabel checkedPaths;

    private int checkedSolutions;

    Thread thread;

    private long runtimeMilis;
    
    public AntPathController(){

        frame = new JFrame();
        framePanel = new JPanel(new BorderLayout());
        
        controlPanel = new JPanel(new BorderLayout());
        controlPanel.setBackground(Color.LIGHT_GRAY);
        infoPanel = new JPanel(new GridLayout());
        controlPanel.add(infoPanel, BorderLayout.CENTER);
        controlPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        drawPanel = new JPanel(new BorderLayout());
        drawPanel.setBackground(Color.PINK);
        drawPanel.setBorder(BorderFactory.createLineBorder(Color.black));

        runtime = new JLabel("runtime");
        estimatedTime = new JLabel("estimated");
        possiblePaths = new JLabel("possible");
        checkedPaths = new JLabel("checked");

        infoPanel.add(runtime);
        infoPanel.add(estimatedTime);
        infoPanel.add(possiblePaths);
        infoPanel.add(checkedPaths);


        solveButton = new JButton("Solve");
        controlPanel.add(solveButton, BorderLayout.EAST);

        resetButton = new JButton("Reset");
        controlPanel.add(resetButton, BorderLayout.WEST);

        resetButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                
                
                setupBoard();

                
            }
        });

        solveButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {   
                thread = new Thread() {
                    public void run() {
                        solveButton.setEnabled(false);
                        boardPanel.refreshShortestPath(board.getSolution());
                        solveButton.setEnabled(true);
                    }
                };
                thread.start();
                runtimeMilis = System.currentTimeMillis();
                
                
            }
        });

        //frame setup
        frame.setSize(1016,565);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        framePanel.add(controlPanel, BorderLayout.NORTH);
        framePanel.add(drawPanel, BorderLayout.CENTER);
        frame.add(framePanel);
        frame.setVisible(true);

    }

    public void setupBoard(){
        
        try {
            drawPanel.removeAll();
        } catch (Exception e) {
            
        }

        try {
            thread.stop();
        } catch (Exception exception) {
            //TODO: handle exception
        }
        
        this.boardPanel = new BoardPanel();
        this.board = new Board(new Node(50,50), this);
        boardPanel.addMouseListener(new BoardListener(this));
        this.boardPanel.setLayout(new BorderLayout());
        drawPanel.add(boardPanel, BorderLayout.CENTER);
        
        checkedSolutions = 0;
        
        drawPanel.revalidate();
        drawPanel.repaint();

        solveButton.setEnabled(true);

        drawBoard();
    }

    public void drawBoard(){

        try {
            thread.stop();
        } catch (Exception exception) {
            //TODO: handle exception
        }

        boardPanel.refreshBoard();
        checkedSolutions = 0;
        resetGUI();
        for (Node node : board.nodes) {
            boardPanel.addNode(Color.PINK, node.x, node.y, new NodeListener(this));
        }
    }

    public void refreshBoard(int[] path){
        boardPanel.refreshShortestPath(path);
    }

    public void addNodeToBoard(int x, int y){
        this.board.addNode(new Node(x, y));
        setPossiblePaths(board.getPossiblePaths());
        drawBoard();
    }

    public void removeNode(int x, int y){
        this.board.removeNode(board.searchNode(x, y));
        setPossiblePaths(board.getPossiblePaths());
        drawBoard();
    }

    public static void main(String[] args) throws Exception {
        new AntPathController();
    }

    public void setRuntime(){
        runtimeMilis = (System.currentTimeMillis() - runtimeMilis);
        setEstimated();
        this.runtime.setText("Runtime: " + Long.toString(runtimeMilis));
    }

    public void setEstimated(){
        if(checkedSolutions > 0){
            double estimate = board.getPossiblePaths() / checkedSolutions * runtimeMilis;
            this.estimatedTime.setText("Estimated: " + Double.toString(estimate) );
        }
    }

    public void setCheckedPaths(){
        
        
    }



    public void setPossiblePaths(long value){
        this.possiblePaths.setText("Possible: " + Long.toString(value));
    }

    public void resetGUI(){
        this.checkedPaths.setText("Checked: " + "0");
        this.estimatedTime.setText("Estimated: " + "0");
        this.runtime.setText("Runtime: " + "0");
    }
}
