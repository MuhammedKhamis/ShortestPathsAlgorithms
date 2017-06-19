package classes;

public class Edge {
    
    private Vertex[] terminals;
    private int weight;
    
    public Edge(Vertex first, Vertex second, int weight) {
        terminals = new Vertex[2];
        terminals[0] = first;
        terminals[1] = second;
        this.weight = weight;
    }
    
    public int getWeight() {
        return weight;
    }
    
    public Vertex[] terminals() {
        return terminals;
    }
    
    public void predecessor() {
        terminals[1].setPredecessor(terminals[0]);
    }

}
