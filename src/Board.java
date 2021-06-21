import java.util.ArrayList;


public class Board {
    
    public Node master;
    public ArrayList<Node> nodes;

    int[] shortest;
    int shortestDistance;

    AntPathController controller;

    Board(Node master, AntPathController controller){
        this.master = master;
        nodes = new ArrayList<>();
        nodes.add(master);
        shortest = new int[nodes.size()];
        shortestDistance = Integer.MAX_VALUE;
        this.controller = controller;
    }

    public void addNode(Node node){
        //TODO: implement securiy feature so no nodes can overlap
        this.nodes.add(node);

        

    }

    public void removeNode(Node node){
        this.nodes.remove(node);
        System.out.println("removed: " + node.x + ", " + node.y);
    }

    public Node searchNode(int x, int y){
        for (Node node : nodes) {
            if(node.x == x && node.y == y){
                return node;
            }
        }
        System.out.println("No node found");
        return new Node(-1,-1);
    }




    // Possible Paths

    public long getPossiblePaths(){

        // +1 for master node
        int n = nodes.size();
        
        return factorial(n -1) / 2;
    }


    public int[] getSolution(){
        shortestDistance = Integer.MAX_VALUE;
        
        int[] a = new int[nodes.size()];
        for (int i = 0; i < a.length; i++) {
            a[i] = i;
        }
        generatePaths(a, nodes.size()-1);

        return this.shortest;
    }


    public void evaluate(int[] path){
        if(path[0] < path[path.length -2]){

            

            int distance = 0;

            for (int i = 0; i < path.length; i++) {
                int nextIndex = (i+1) % path.length;
                distance += Vector2d.createDirectional(nodes.get(path[i]).vector, nodes.get(path[nextIndex]).vector).getLenght();
                
            }
            
            

            if(distance < this.shortestDistance){
                this.shortest = new int[path.length];
                for (int i = 0; i < path.length; i++) {
                    this.shortest[i] = path[i];
                }

                this.shortestDistance = distance;

                controller.refreshBoard(shortest);
                
            }
        }

    }



    public void generatePaths(int[] a, int n){

        if ( n == 1){
            if(a.length > 2){
                evaluate(a);

            }
            
        }else{

            for (int i = 0; i < n; i++) {
                generatePaths(a, n-1);

                if(n%2 == 0){
                    int temp = a[i];
                    a[i] = a[n -1 ];
                    a[n -1] = temp;
                }else{
                    int temp = a[0];
                    a[0] = a[n -1 ];
                    a[n -1] = temp;
                }

            }

        }
    }

    public static long factorial(long n){
        if (n <= 1 ) return 1;
        else return n * factorial(n -1);
    }

}
