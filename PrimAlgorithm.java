package _0_Algorithms.Graph_Traversal_Algorithms.Prim;
import _0_DataStructures.Graph.UndirectedGraph;
import _0_DataStructures.Graph.UndirectedGraph.GraphNode;
import _0_DataStructures.Graph.UndirectedGraph;

import java.util.ArrayList;
import java.util.LinkedHashMap;


public class PrimAlgorithm  {

    public UndirectedGraph primGraph;
    public ArrayList<PrimNode> primNodeList = new ArrayList<>();
    public Fringe fringe;
    public PrimNode currentNode;






    public void initAlgorithm(UndirectedGraph graph){
        this.primGraph = graph;
    }


    public class PrimNode  {

        public int number;

        public int incumbentDistTo;
        public Integer edgeTo;

        public boolean isMarked; // set to 0 by default

        public LinkedHashMap<PrimNode, ArrayList<Integer>> connections;

        public int[] pathToArr;


        public void markNode(){
            this.isMarked = true;
        }

        public void updateHighlightedNode(int highlightedEdgeWeight){

            this.incumbentDistTo = highlightedEdgeWeight;
            this.edgeTo = currentNode.number;
            this.pathToArr[this.number] = highlightedEdgeWeight;


            Integer fringeEntryIndex = null;
            for (Fringe.Node node : fringe.keys){
                if (node.value != null && node.number == this.number){
                    fringeEntryIndex = node.index;
                }
            }
            Fringe.Node highlightedNodeFringeEntry = fringe.getNode(fringeEntryIndex);
            highlightedNodeFringeEntry.value = highlightedEdgeWeight;

            //highlightedNodeFringeEntry.updateNodeAttr();
            highlightedNodeFringeEntry.swim();

        }
    }










    public void highlightNodes() {

        for (PrimNode highlightedNode : currentNode.connections.keySet()) {

            if (!highlightedNode.isMarked) {
                int highlightedEdgeWeight = currentNode.connections.get(highlightedNode).get(0);

                if (highlightedEdgeWeight < highlightedNode.incumbentDistTo) {
                    highlightedNode.updateHighlightedNode(highlightedEdgeWeight);


                }
            }
        }

        visitNextNode();

    }












    public void visitNextNode(){

        PrimNode nextNode = this.primNodeList.get(this.fringe.getSmallestNodeNumber());

        fringe.removeSmallest();
        nextNode.markNode();

        this.currentNode = nextNode;


    }






    public void initPrimNodes(){

        for (GraphNode gNode : this.primGraph.graphNodeList) {

            PrimNode dNode = new PrimNode();

            dNode.number = gNode.number;
            dNode.incumbentDistTo = 999999999;

            if (dNode.number == 0){
                dNode.isMarked = true;
                dNode.incumbentDistTo = 0;
                dNode.edgeTo = null;
            }

            this.primNodeList.add(dNode);
        }

        this.currentNode = primNodeList.get(0);



        for (PrimNode node : primNodeList){
            node.pathToArr = new int[primNodeList.size()];
        }

    }



    public void initPrimNodeConnections(){

        for (GraphNode gNode : this.primGraph.graphNodeList){
            PrimNode dNode = primNodeList.get(gNode.number);
            dNode.connections = new LinkedHashMap<>();

            for (GraphNode neighborGNode : gNode.connections.keySet()) {
                PrimNode neighborDNode = primNodeList.get(neighborGNode.number);
                dNode.connections.put(neighborDNode, gNode.connections.get(neighborGNode));
            }
        }
    }


    public void initFringe(){

        this.fringe = new Fringe();

        for (PrimNode dNode : primNodeList){
            if (dNode.number != 0){
                this.fringe.addNode(dNode);
            }
        }
    }



    public int calculateTotalDist(int nodeNum, int cumDist){

        PrimNode node = this.primNodeList.get(nodeNum);

        if (node.edgeTo == null){
            return cumDist;
        }

        else {
            int pathTo = node.pathToArr[nodeNum];
            return calculateTotalDist(node.edgeTo, cumDist) + pathTo;
        }
    }









    public void prim(UndirectedGraph graph){

        initAlgorithm(graph);

        initPrimNodes();
        initPrimNodeConnections();

        initFringe();


        while (fringe.nodeCount != 0){
            highlightNodes();
        }


        for (PrimNode node : this.primNodeList){
            System.out.println(node.edgeTo);
        }




    }












}


