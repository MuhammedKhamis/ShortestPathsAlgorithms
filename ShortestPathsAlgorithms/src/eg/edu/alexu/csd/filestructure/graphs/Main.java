package eg.edu.alexu.csd.filestructure.graphs;

import java.io.File;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File test = new File("Test.txt");
		GraphM graph = new GraphM();
		graph.readGraph(test);
		int[] distance= new int [graph.getVertices().size()];
		System.out.println(graph.runBellmanFord(0, distance));;
		for(int i=0;i<distance.length;i++){
			System.out.println(distance[i]);
		}
	}

}
