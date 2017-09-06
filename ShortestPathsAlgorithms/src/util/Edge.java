package util;

public class Edge {

    private Integer src, dist, weight;

    public Edge(Integer src, Integer dist, Integer weight) {
        // TODO Auto-generated constructor stub
        super();
        this.dist = dist;
        this.weight = weight;
        this.src = src;
    }
    public Integer getSrc() {
        return src;
    }

    public Integer getDist() {
        return dist;
    }

    public Integer getWeight() {
        return weight;
    }

}
