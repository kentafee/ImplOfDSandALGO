package sxs178130;

import java.io.File;
import java.util.Scanner;




public class Diameter {

// find diameter method returns diameter by using bfs twice
    public static int findDiameter(Graph g) {
        int src =1;
        BFSOO b = BFSOO.breadthFirstSearch(g, src);
        Graph.Vertex maxVertex = null;

        int maxDist = 0;
        for (Graph.Vertex v : b.g)
        {
            if(b.getDistance(v)>maxDist)
            {
                maxDist = b.getDistance(v);
                maxVertex = v;
            }
        }

        b = BFSOO.breadthFirstSearch(g, maxVertex);

        int diameter = 0;
        for (Graph.Vertex v : b.g)
        {
            if(b.getDistance(v)>diameter)
            {
                diameter = b.getDistance(v);
            }
        }

        return diameter;
    }



    public static void main(String[] args) throws Exception {
        String string = "7 6   1 2 1  1 6 1  1 3 1  7 1 1  1 4 1   1 5 1 1";
        Scanner in;
        // If there is a command line argument, use it as file from which
        // input is read, otherwise use input from string.
        in = args.length > 0 ? new Scanner(new File(args[0])) : new Scanner(string);
        // Read graph from input
        Graph g = Graph.readGraph(in,false);
        int s = in.nextInt();



        g.printGraph(false);

        System.out.println(" Diameter:---------------------->>  "+Diameter.findDiameter(g));
    }
}
