package classes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import interfaces.IGraph;

public class Graph implements IGraph {

    private int numOfVert;
    private int numOfEdges;
    private int[][] adjMatrix;
    private int[][] edgesArray;
    
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
            edgesArray = new int[numOfEdges][3];
            Arrays.fill(adjMatrix, Integer.MAX_VALUE);
            int counter = 0;
            while ((line = bufferedReader.readLine()) != null) {
                String[] edges = line.split(" ");
                adjMatrix[Integer.parseInt(edges[0])][Integer.parseInt(edges[1])] = Integer.parseInt(edges[2]);
                edgesArray[counter][0] = Integer.parseInt(edges[0]);
                edgesArray[counter][1] = Integer.parseInt(edges[1]);
                edgesArray[counter][2] = Integer.parseInt(edges[2]);
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
        return 0;
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
            if(adjMatrix[v][i] != Integer.MAX_VALUE) {
                neigh.add(i);
            }
        }
        return neigh;
    }

    @Override
    public void runDijkstra(int src, int[] distances) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public ArrayList<Integer> getDijkstraProcessedOrder() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean runBellmanFord(int src, int[] distances) {
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[src] = 0;
        for(int i = 0; i < numOfVert - 1; i++) {
            for(int j = 0; j < numOfEdges; j++) {
                if(distances[edgesArray[j][0]] + edgesArray[j][2] < edgesArray[j][1]) {
                    distances[edgesArray[j][1]] = distances[edgesArray[j][0]] + edgesArray[j][2];
                }
            }
        }
        //loop one more time if any change happen then there is a loop
        for(int j = 0; j < numOfEdges; j++) {
            if(distances[edgesArray[j][0]] + edgesArray[j][2] < edgesArray[j][1]) {
                return false;
            }
        }
        return true;
    }

}
