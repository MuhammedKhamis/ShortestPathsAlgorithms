package eg.edu.alexu.csd.filestructure.graphs;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class GraphM  implements IGraph{
    private  int [][]adjMatrix ;
    private  int [][]edges ;
    private ArrayList<Integer>neighbors  = new ArrayList<>();
    private ArrayList<Integer>	vertices;
	private int vertexNum;
	private int edgeNum;
	private File Bellman ;
	private File Dijkstra;
	private PrintWriter p;
	@Override
	public void readGraph(File file) {
		// TODO Auto-generated method stub
		try {
			Scanner in = new Scanner(file);
			 vertexNum = in.nextInt();
			 edgeNum=in .nextInt();
			adjMatrix =new int [vertexNum][vertexNum];
			edges=new int [edgeNum][3];
			vertices= new ArrayList<>();
            Arrays.fill(adjMatrix, Integer.MAX_VALUE);
			for(int i=0;i<edgeNum;i++){
				int from = in.nextInt();
				int to = in.nextInt();
				int weight=in.nextInt();
				adjMatrix[from][to]=weight;
				edges[i][0]=from;
				edges[i][1]=to;
				edges[i][2]=weight;
				p = new PrintWriter(new FileOutputStream(file.getName()+"_output"));

			}
			in.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return vertices.size();
	}

	@Override
	public ArrayList<Integer> getVertices() {
		// TODO Auto-generated method stub
		for(int i=0;i<vertexNum;i++){
			vertices.add(i);
		}
		return vertices;
	}

	@Override
	public ArrayList<Integer> getNeighbors(int v) {
		// TODO Auto-generated method stub
		for(int i=0;i<vertexNum;i++){
			if(adjMatrix[v][i]!=Integer.MAX_VALUE);
				neighbors.add(i);
				
		}
		return neighbors;
	}

	@Override
	public void runDijkstra(int src, int[] distances) {
		// TODO Auto-generated method stub
		for(int i=0;i<vertexNum;i++){
			distances[i]=Integer.MAX_VALUE;
		}
		distances[src]=0;
	}

	@Override
	public ArrayList<Integer> getDijkstraProcessedOrder() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean runBellmanFord(int src, int[] distances) {
		// TODO Auto-generated method stub
		for(int i=0;i<vertexNum;i++){
			distances[i]=Integer.MAX_VALUE;
		}
		distances[src]=0;
		for(int i=0;i<vertexNum;i++){
			for(int j=0;j<edgeNum;j++){
				if(distances[edges[j][1]]>distances[edges[j][0]]+edges[j][2]){
					distances[edges[j][1]]=distances[edges[j][0]]+edges[j][2];
				}
			}
		}
		for(int j=0;j<edgeNum;j++){
			if(distances[edges[j][1]]>distances[edges[j][0]]+edges[j][2]){
				p.println("False");
				return false; 
			}
		}
		p.println("True");
		for(int i=0;i<distances.length;i++){
			p.print(distances+" ");
		}
		
		return true;
	}

}
