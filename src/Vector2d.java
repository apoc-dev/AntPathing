public class Vector2d {
    
    public int x,y;

    Vector2d(int x, int y){
        this.x = x;
        this.y = y;
    }

    public double getLenght(){
        return Math.sqrt(x*x + y*y);
    }

    public static Vector2d substract(Vector2d v1, Vector2d v2){
        return new Vector2d(v1.x - v2.x, v1.y-v2.y);
    }

    public static Vector2d createDirectional(Vector2d v1, Vector2d v2){
        return substract(v2, v1);
    }

}
