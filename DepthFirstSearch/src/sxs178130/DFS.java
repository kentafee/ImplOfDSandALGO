/** Starter code for SP8
 *  @author
 */

// change to your netid
package sxs178130;

import sxs178130.Graph.Vertex;
import sxs178130.Graph.Edge;
import sxs178130.Graph.GraphAlgorithm;
import sxs178130.Graph.Factory;
import sxs178130.Graph.Timer;

import java.io.File;
import java.util.List;
import java.util.LinkedList;
import java.util.Scanner;

public class DFS extends GraphAlgorithm<DFS.DFSVertex> {
    public static class DFSVertex implements Factory {
        int cno;
        public DFSVertex(Vertex u) {
        }
        public DFSVertex make(Vertex u) { return new DFSVertex(u); }
    }

    public DFS(Graph g) {
        super(g, new DFSVertex(null));
    }

    public static DFS depthFirstSearch(Graph g) {


        return null;
    }

    // Member function to find topological order
    public List<Vertex> topologicalOrder1() {
        return null;
    }

    // Find the number of connected components of the graph g by running dfs.
    // Enter the component number of each vertex u in u.cno.
    // Note that the graph g is available as a class field via GraphAlgorithm.
    public int connectedComponents() {
        return 0;
    }

    // After running the onnected components algorithm, the component no of each vertex can be queried.
    public int cno(Vertex u) {
        return get(u).cno;
    }

    // Find topological oder of a DAG using DFS. Returns null if g is not a DAG.
    public static List<Vertex> topologicalOrder1(Graph g) {
        DFS d = new DFS(g);
        return d.topologicalOrder1();
    }

    // Find topological oder of a DAG using the second algorithm. Returns null if g is not a DAG.
    public static List<Vertex> topologicalOrder2(Graph g) {
        return null;
    }

    public static void main(String[] args) throws Exception {
        String string = "7 8   1 2 2   1 3 3   2 4 5   3 4 4   4 5 1   5 1 7   6 7 1   7 6 1 0";
        Scanner in;
        // If there is a command line argument, use it as file from which
        // input is read, otherwise use input from string.
        in = args.length > 0 ? new Scanner(new File(args[0])) : new Scanner(string);

        // Read graph from input
        Graph g = Graph.readGraph(in);
        g.printGraph(false);

        DFS d = new DFS(g);
        int numcc = d.connectedComponents();
        System.out.println("Number of components: " + numcc + "\nu\tcno");
        for(Vertex u: g) {
            System.out.println(u + "\t" + d.cno(u));
        }
    }
}