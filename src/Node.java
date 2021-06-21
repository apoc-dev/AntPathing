public class Node {
    
    public int x,y;
    public Vector2d vector;
    Node(int x, int y){
        this.x = x;
        this.y = y;
        this.vector = new Vector2d(this.x, this.y);
    }

}
