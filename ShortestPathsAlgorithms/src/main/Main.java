package main;

import java.io.File;

import classes.Graph;
import interfaces.IGraph;

public class Main {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        File file = new File("C:\\Users\\Muhammed\\git\\ShortestPathsAlgorithms\\ShortestPathsAlgorithms\\input.txt");
        IGraph g = new Graph();
        g.readGraph(file);
        int[] dist = new int[g.size()];
        boolean res = g.runBellmanFord(0, dist);
        System.out.println(res);
        printArray(dist);
        dist = new int[g.size()];
        printArray(dist);
        g.runDijkstra(0, dist);
        printArray(dist);

    }

    private static void printArray(int[] arr) {
        for (int e : arr) {
            System.out.print(e + " ");
        }
        System.out.println();
    }

}
