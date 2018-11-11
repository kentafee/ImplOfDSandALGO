/** Starter code for SP8
 *  @author
 */

// change to your netid
package sxs178130;

import rbk.Graph;
import rbk.Graph.Vertex;
import rbk.Graph.Edge;
import rbk.Graph.GraphAlgorithm;
import rbk.Graph.Factory;
import rbk.Graph.Timer;

import java.io.File;
import java.util.*;

public class DFS extends GraphAlgorithm<DFS.DFSVertex> {
    LinkedList<Vertex> finishList=new LinkedList<>();
    boolean containsCycle;
    int topNum;
    int noOfC;
    public static class DFSVertex implements Factory {

        public boolean seen;
        public Object parent;
        public boolean inCallStack;
        public int top;
        private int cno;


        public DFSVertex(Vertex u) {
            seen=false;
            parent=null;
            inCallStack=false;
            top=0;
            cno =0;
        }
        public DFSVertex make(Vertex u) { return new DFSVertex(u); }
    }

    public DFS(Graph g) {
        super(g, new DFSVertex(null));
        topNum=g.size();
        noOfC =0;
    }


    //calls dfs for each node in g if it is not seen before
    public void dfs() {

        for(Vertex u: g) {
            get(u).seen = false;
            get(u).parent = null;
            get(u).inCallStack=false;
            get(u).top=0;
        }
        for (Vertex u:g){
            if(!get(u).seen){
                dfsVisit(u);

            }
        }
    }

    public void dfs(Iterable<Vertex> iterableGraph) {

        this.finishList.clear();
        this.noOfC=0;
        Iterator<Vertex> itr = iterableGraph.iterator();

        while (itr.hasNext()) {
            Vertex v = itr.next();
            get(v).seen = false;
            get(v).parent = null;
            get(v).inCallStack = false;
            get(v).top = 0;
            get(v).cno = 0;
        }

        for (Vertex u : iterableGraph) {
            if (!get(u).seen) {
                noOfC++;
                get(u).cno = noOfC;
                dfsVisit(u);
            }
        }
    }


    //helper method which calls dfs for each neighboring vertex of u and checks whether the graph has cycle or not

    private void dfsVisit(Vertex u){

        get(u).seen=true;
        get(u).inCallStack=true;
        for(Edge e: g.incident(u)) {
            if(!containsCycle){
                if(get(u).inCallStack){
                    containsCycle=true;
                }
            }
            Vertex v = e.otherEnd(u);
            if (!get(v).seen) {
                get(v).parent = u;
                get(v).cno = get(u).cno;
                dfsVisit(v);
            }
        }
        get(u).top=topNum--;
        finishList.addFirst(u);
        get(u).inCallStack=false;
    }









    //calls dfs
    public static DFS depthFirstSearch(Graph g) {

        DFS d = new DFS(g);
        d.dfs();
        return d;
    }

    // Member function to find topological order
    public List<Vertex> topologicalOrder1() {

        if(!g.isDirected()){
            return null;
        }
        DFS d=depthFirstSearch(g);
        if(d.containsCycle){
            return null;
        }
        return d.finishList;
    }

    // Find topological oder of a DAG using DFS. Returns null if g is not a DAG.
    public static List<Vertex> topologicalOrder1(Graph g) {
        DFS d = new DFS(g);
        return d.topologicalOrder1();
    }


    public static DFS stronglyConnectedComponents(Graph g)
    { return null; }

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
        Graph g = Graph.readDirectedGraph(in);
        g.printGraph(false);


        DFS d = new DFS(g);
        d.dfs(g);
        List<Vertex> finishList= new LinkedList<>();

        if(d.finishList!=null){
            for (Vertex v:d.finishList){
                finishList.add(v);
            }
        }
        else {
            System.out.println("Not a DAG");
        }

        System.out.println("Comp No."+d.noOfC);
        g.reverseGraph();
        d.dfs(finishList);
        g.reverseGraph();


        if(d.finishList!=null){
            for (Vertex v:d.finishList){
                System.out.println(v);
            }
        }
        else {
            System.out.println("Not a DAG");
        }
        System.out.println("Comp No."+d.noOfC);


    }
}