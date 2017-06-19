package classes;

import java.util.Comparator;

public class VertexCompare implements Comparator<Vertex> {

    @Override
    public int compare(Vertex o1, Vertex o2) {
        if(o1.getdistance() < o2.getdistance())
            return -1;
        else if (o1.getdistance() > o2.getdistance())
            return 1;
        else
            return 0;
    }

}
