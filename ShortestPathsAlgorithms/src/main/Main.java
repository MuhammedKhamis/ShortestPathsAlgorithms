package main;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;

import classes.Graph;
import interfaces.IGraph;

public class Main {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String[] bellman = {"belman_ford_1", "belman_ford_2", "belman_ford_3"};
        String[] dijstra = {"dijkstra_1", "dijkstra_2", "dijkstra_3"};
        IGraph g = new Graph();
        int[] dist;
        boolean res = false;
        for (String e : bellman) {
            File file = new File(e + ".txt");
            g.readGraph(file);
            dist = new int[g.size()];
            res = g.runBellmanFord(0, dist);
            writeBellman(e + "_output.txt", dist, !res);
        }
        for (String e : dijstra) {
            File file = new File(e + ".txt");
            g.readGraph(file);
            dist = new int[g.size()];
            g.runDijkstra(0, dist);
            writeDijkstra(e + "_output.txt", dist, g.getDijkstraProcessedOrder());
        }
    }

    private static void writeBellman(String name, int[] dist, boolean res) {
        try {
            PrintWriter writer = new PrintWriter(name, "UTF-8");
            writer.println(res);
            for (int e : dist) {
                writer.print(e + " ");
            }
            writer.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static void writeDijkstra(String name, int[] dist, ArrayList<Integer> order) {
        try {
            PrintWriter writer = new PrintWriter(name, "UTF-8");
            for (int e : dist) {
                writer.print(e + " ");
            }
            writer.println();
            for (int e : order) {
                writer.print(e + " ");
            }
            writer.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
