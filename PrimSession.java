package _0_Algorithms.Graph_Traversal_Algorithms.Prim;
import _0_DataStructures.Graph.UndirectedGraph.GraphNode;
import _0_DataStructures.Graph.UndirectedGraph;


public class PrimSession {

    public static UndirectedGraph generateExampleGraph(){
        UndirectedGraph g = new UndirectedGraph();

        GraphNode n0 = g.addGraphNode(); // 0
        GraphNode n1 = g.addGraphNode(); // 1
        GraphNode n2 = g.addGraphNode(); // 2
        GraphNode n3 = g.addGraphNode(); // 3
        GraphNode n4 = g.addGraphNode(); // 4
        GraphNode n5 = g.addGraphNode(); // 5
        GraphNode n6 = g.addGraphNode(); // 6


        // 0 edges
        n0.addEdge(n1, 2);
        n0.addEdge(n2, 1);

        // 1 edges
        n1.addEdge( n0, 2);
        n1.addEdge( n2, 5);
        n1.addEdge( n3, 11);
        n1.addEdge( n4, 3);

        // 2 edges
        n2.addEdge(n0, 1);
        n2.addEdge(n1, 5);
        n2.addEdge(n4, 1);
        n2.addEdge(n5, 15);

        // 3 edges
        n3.addEdge(n1, 11);
        n3.addEdge(n4, 2);
        n3.addEdge(n6, 1);

        // 4 edges
        n4.addEdge(n1, 3);
        n4.addEdge(n2, 1);
        n4.addEdge(n3, 2);
        n4.addEdge(n5,4);
        n4.addEdge(n6, 3);

        // 5 edges
        n5.addEdge( n2, 15);
        n5.addEdge( n4, 4);
        n5.addEdge( n6, 1);

        // 6 edges
        n6.addEdge( n3, 1);
        n6.addEdge( n4, 3);
        n6.addEdge( n5, 1);


        g.printGraph();
        return g;
    }




    public static void main(String[] args) {

        PrimAlgorithm foo = new PrimAlgorithm();
        UndirectedGraph graph1 = generateExampleGraph();
        foo.prim(graph1);
        System.out.println();









    }


}
