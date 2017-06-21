package classes;

public class Vertex {

    private int val;
    private int distance;
    private Vertex predecessor;
    
    public Vertex(int val) {
        this.val = val;
        this.distance = Integer.MAX_VALUE - 10000;
        this.predecessor = this;
    }
    
    public void setdistance(int distance) {
        this.distance = distance;
    }
    
    public int getdistance() {
        return distance;
    }
    
    public int getVal() {
        return val;
    }
    
    public void setPredecessor(Vertex v) {
        predecessor = v;
    }
    
    public Vertex getPredecessor() {
        return predecessor;
    }
}
