package classes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.PriorityQueue;

import interfaces.IGraph;
import util.Edge;
import util.Pair;

public class Graph implements IGraph {

    private ArrayList<ArrayList<Pair<Integer, Integer>>> adjList;
    private ArrayList<Edge> edges;
    private ArrayList<Integer> processedOrder;
    private boolean[] visited;

    public Graph() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void readGraph(File file) {
        // TODO Auto-generated method stub
        FileReader reader;
        try {
            reader = new FileReader(file);
            BufferedReader bf = new BufferedReader(reader);
            String line = null;
            // Reading the number of Edges and Nodes
            while (bf.ready()) {
                line = bf.readLine();
                break;
            }
            int nodes, edges;
            String[] inputs = line.split(" ");
            nodes = Integer.valueOf(inputs[0].trim());
            edges = Integer.valueOf(inputs[1].trim());
            initialize();
            fillAdjList(nodes);
            // read start end cost
            while (bf.ready()) {
                line = bf.readLine();
                inputs = line.split(" ");
                int src = Integer.valueOf(inputs[0].trim());
                int dist = Integer.valueOf(inputs[1].trim());
                int cost = Integer.valueOf(inputs[2].trim());
                this.edges.add(new Edge(src, dist, cost));
                this.adjList.get(src).add(new Pair<Integer, Integer>(dist, cost));
            }
            bf.close();
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int size() {
        // TODO Auto-generated method stub
        return adjList.size();
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
        distances = initializeDist(distances);
        visited = new boolean[distances.length];
        distances[src] = 0;
        PriorityQueue<Pair<Integer, Integer>> pq = new PriorityQueue<>();
        pq.add(new Pair<Integer, Integer>(distances[src], src));
        while (!pq.isEmpty()) {
            Pair<Integer, Integer> node = pq.remove();
            int srcNode = node.getSecond();
            int weight = node.getFirst();
            if (!visited[srcNode]) {
                processedOrder.add(srcNode);
                visited[srcNode] = true;
            }
            for (int i = 0; i < adjList.get(srcNode).size(); i++) {
                if (distances[adjList.get(srcNode).get(i).getFirst()] > weight
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
        return processedOrder;
    }

    @Override
    public boolean runBellmanFord(int src, int[] distances) {
        // TODO Auto-generated method stub
        distances = initializeDist(distances);
        distances[src] = 0;
        for (int i = 0; i < distances.length - 1; i++) {
            boolean res = processEdges(distances);
            print(String.valueOf(res));
        }
        return processEdges(distances.clone());
    }

    private int[] initializeDist(int[] distances) {
        for (int i = 0; i < distances.length; i++) {
            distances[i] = Integer.MAX_VALUE;
        }
        return distances;
    }

    private void initialize() {
        adjList = new ArrayList<>();
        edges = new ArrayList<>();
        processedOrder = new ArrayList<>();
    }

    private boolean processEdges(int[] distances) {
        boolean flag = true;
        for (int j = 0; j < edges.size(); j++) {
            int srcNode = edges.get(j).getSrc();
            int distNode = edges.get(j).getDist();
            int weight = edges.get(j).getWeight();
            if (distances[srcNode] != Integer.MAX_VALUE && distances[distNode] > weight + distances[srcNode]) {
                distances[distNode] = weight + distances[srcNode];
                flag = false;
            }
        }
        return flag;
    }

    // fill the adjlist with the number of nodes
    private void fillAdjList(int n) {
        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<Pair<Integer, Integer>>());
        }
    }

    // used for Debugging
    private void print(String messege) {
        // System.out.println(messege);
    }

}
