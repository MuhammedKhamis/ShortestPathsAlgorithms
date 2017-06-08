package classes;

import java.io.File;
import java.util.ArrayList;
import java.util.PriorityQueue;

import interfaces.IGraph;
import util.Edge;
import util.Pair;

public class Graph implements IGraph {

    private ArrayList<ArrayList<Pair<Integer, Integer>>> adjList;
    private ArrayList<Edge> edges;

    public Graph() {
        // TODO Auto-generated constructor stub
        adjList = new ArrayList<>();
        edges = new ArrayList<>();
    }

    @Override
    public void readGraph(File file) {
        // TODO Auto-generated method stub
    }

    @Override
    public int size() {
        // TODO Auto-generated method stub
        return adjList.size() + edges.size();
    }

    @Override
    public ArrayList<Integer> getVertices() {
        // TODO Auto-generated method stub
        ArrayList<Integer> vertices = new ArrayList<>();
        for (int i = 0; i < adjList.size(); i++) {
            vertices.add(i);
        }

        return vertices;
    }

    @Override
    public ArrayList<Integer> getNeighbors(int v) {
        // TODO Auto-generated method stub
        ArrayList<Integer> neighbors = new ArrayList<>();
        for (int i = 0; i < adjList.get(v).size(); i++) {
            neighbors.add(adjList.get(v).get(i).getFirst());
        }
        return neighbors;
    }

    @Override
    public void runDijkstra(int src, int[] distances) {
        // TODO Auto-generated method stub
        distances = initializeDist();
        distances[src] = 0;
        PriorityQueue<Pair<Integer, Integer>> pq = new PriorityQueue<>();
        pq.add(new Pair<Integer, Integer>(distances[src], src));
        while (!pq.isEmpty()) {
            Pair<Integer, Integer> node = pq.remove();
            int srcNode = node.getSecond();
            int weight = node.getFirst();
            for (int i = 0; i < adjList.get(srcNode).size(); i++) {
                if (distances[adjList.get(srcNode).get(i).getFirst()] != Integer.MAX_VALUE
                        && distances[adjList.get(srcNode).get(i).getFirst()] > weight
                                + adjList.get(srcNode).get(i).getSecond()) {
                    distances[adjList.get(srcNode).get(i).getFirst()] = weight
                            + adjList.get(srcNode).get(i).getSecond();
                    pq.add(new Pair<Integer, Integer>(distances[adjList.get(srcNode).get(i).getFirst()],
                            adjList.get(srcNode).get(i).getFirst()));
                }
            }
        }

    }

    @Override
    public ArrayList<Integer> getDijkstraProcessedOrder() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean runBellmanFord(int src, int[] distances) {
        // TODO Auto-generated method stub
        distances = initializeDist();
        distances[src] = 0;
        for (int i = 0; i < distances.length - 1; i++) {
            processEdges(distances);
        }
        return processEdges(distances);
    }

    private int[] initializeDist() {
        int[] distances = new int[adjList.size()];
        for (int i = 0; i < distances.length; i++) {
            distances[i] = Integer.MAX_VALUE;
        }
        return distances;
    }

    private boolean processEdges(int[] distances) {
        boolean flag = true;
        for (int j = 0; j < edges.size(); j++) {
            int srcNode = edges.get(j).getSrc();
            int distNode = edges.get(j).getDist();
            int weight = edges.get(j).getWeight();
            if (distances[distNode] != Integer.MAX_VALUE && distances[distNode] > weight + distances[srcNode]) {
                distances[distNode] = weight + distances[srcNode];
                flag = false;
            }
        }
        return flag;
    }

}
