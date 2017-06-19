package classes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

import interfaces.IGraph;

public class Graph implements IGraph {

    private int numOfVert;
    private int numOfEdges;
    private int[][] adjMatrix;
    private Edge[] edgesArray;
    private Vertex[] vertices;
    private ArrayList<Integer> set;
    private int maxNum = Integer.MAX_VALUE;
    
    @Override
    public void readGraph(File file) {
        FileReader fileReader;
        try {
            fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            String[] firstLine = line.split(" ");
            numOfVert = Integer.parseInt(firstLine[0]);
            numOfEdges = Integer.parseInt(firstLine[1]);
            adjMatrix = new int[numOfVert][numOfVert];
            edgesArray = new Edge[numOfEdges];
            vertices = new Vertex[numOfVert];
            for(int i = 0; i < numOfVert; i++)
                Arrays.fill(adjMatrix[i], maxNum);
            int counter = 0;
            while ((line = bufferedReader.readLine()) != null) {
                String[] edges = line.split(" ");
                int first = Integer.parseInt(edges[0]);
                int second = Integer.parseInt(edges[1]);
                int weight = Integer.parseInt(edges[2]);
                adjMatrix[first][second] = weight;
                if(vertices[first] == null)
                    vertices[first] = new Vertex(first);
                if(vertices[second] == null)
                    vertices[second] = new Vertex(second);
                Edge edge = new Edge(vertices[first], vertices[second], weight);
                edgesArray[counter] = edge;
                counter++;
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public int size() {
        // TODO Auto-generated method stub
        return numOfVert;
    }

    @Override
    public ArrayList<Integer> getVertices() {
        ArrayList<Integer> vert = new ArrayList<>();
        for(int i = 0; i < numOfVert; i++) {
            vert.add(i);
        }
        return vert;
    }

    @Override
    public ArrayList<Integer> getNeighbors(int v) {
        ArrayList<Integer> neigh = new ArrayList<>();
        for(int i = 0; i < numOfVert; i++) {
            if(adjMatrix[v][i] != maxNum) {
                neigh.add(i);
            }
        }
        return neigh;
    }

    @Override
    public void runDijkstra(int src, int[] distances) {
        vertices[src].setdistance(0);
        set = new ArrayList<>();
        Comparator<Vertex> comp = new VertexCompare();
        PriorityQueue<Vertex> queue = new PriorityQueue<Vertex>(comp);
        for(int i = 0; i < numOfVert; i++)
            queue.add(vertices[i]);
        while (!queue.isEmpty()) {
            Vertex u = queue.remove();
            set.add(u.getVal());
            for(int j = 0; j < numOfVert; j++) {
                if (adjMatrix[u.getVal()][vertices[j].getVal()] != maxNum) {
                    relax(u,vertices[j],adjMatrix[u.getVal()][vertices[j].getVal()], distances);
                    if(queue.remove(vertices[j]))
                        queue.add(vertices[j]);
                }
            }
        }
    }

    private void relax(Vertex u, Vertex v, int w, int[] distances) {
        if (v.getdistance() > u.getdistance() + w) {
            v.setdistance(u.getdistance() + w);
            distances[v.getVal()] = u.getdistance() + w;
            v.setPredecessor(u);
        }
    }

    @Override
    public ArrayList<Integer> getDijkstraProcessedOrder() {
        return set;
    }

    @Override
    public boolean runBellmanFord(int src, int[] distances) {
        vertices[src].setdistance(0); 
        for (int i = 0; i < numOfVert - 1; i++) {
            for(Edge e : edgesArray) {
                relax(e,distances);
            }
        }
        for (Edge e : edgesArray) {
            if (e.terminals()[1].getdistance() > e.terminals()[0].getdistance() + e.getWeight()) {
                return false;
            }
        }
        return true;
    }

    private void relax(Edge e, int[] distances) {
        Vertex[] terminals = e.terminals();
        if (terminals[1].getdistance() > terminals[0].getdistance() + e.getWeight()) {
            e.terminals()[1].setdistance(terminals[0].getdistance() + e.getWeight());
            distances[e.terminals()[1].getVal()] = terminals[0].getdistance() + e.getWeight();
            e.predecessor();
        }
    }
    
    public void printDistances() {
        for(Vertex v : vertices)
            System.out.print(v.getdistance() + " ");
    }

    public static void main(String[] args) {
        Graph g = new Graph();
        g.readGraph(new File("dijkstra_2.txt"));
        int[] a = new int[g.size()];
        //System.out.println(g.runBellmanFord(0, a));
        g.runDijkstra(0, a);
        ArrayList<Integer> sorted = g.getDijkstraProcessedOrder();
        g.printDistances();
        System.out.println();
        for(int i : sorted)
            System.out.print(i + " ");
    }
}
