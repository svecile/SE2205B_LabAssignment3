
public class Assignment3<V, E> {

    //variable to keep track of max flow through network
    public int maxFlow = 0;
    //parent array
    public int[] parent;

    public int breadthFirstPathSearch(Graph FN, int s, int d) {

        //create visited node array and initialise parent array
        int[] visitedNodes = new int[FN.numVertices()];
        parent = new int[FN.numVertices()];

        //set all to 0 meaning unvisited
        for (int i = 0; i < FN.numVertices(); i++)
            visitedNodes[i] = 0;

        //initialise all parents to -1
        for (int i = 0; i < parent.length; i++)
            parent[i] = -1;

        //get starting vertex
        Vertex<V> start = FN.getVertex(s);

        //initialise queue and enqueue first node
        LinkedListQueue<Vertex<V>> llQ = new LinkedListQueue<>();
        llQ.enqueue(start);

        Vertex<V> v;

        while (!llQ.isEmpty()) {

            v = llQ.dequeue();

            //check to see if node has been visited
            if (visitedNodes[v.getLabel()] == 0) {
                //mark node as visited
                visitedNodes[v.getLabel()] = 1;
                Iterable<Edge<E>> iterEdges = FN.outgoingEdges(v);

                //for each edge adjacent to vertex v check if it has been visited and if more flow can be added
                for (Edge<E> e : iterEdges) {

                    if (visitedNodes[v.getLabel()] == 0 && e.flow < e.flowCap) {

                        //set v as the parent of the vertexes at the end of the edges then enqueue them
                        parent[FN.opposite(v, e).getLabel()] = v.getLabel();
                        llQ.enqueue(FN.opposite(v, e));
                    }
                }
            }
        }
        //check if the d was visited, if it was return 1 if it wasnt then no path was found return 0
        if (visitedNodes[d] == 1)
            return 1;

        return 0;
    }

    public void maximizeFlowNetwork(Graph FN, int s, int t) {

        //create residual graph
        Graph resGraph = FN;

        while (breadthFirstPathSearch(resGraph, s, t) == 1) {

            //set path flow to a large value so it can be compared to a lower value
            int maxPathFlow = Integer.MAX_VALUE;

            //for loop loops through parent array following shortest path found by the bfs
            //looking for the maximum flow that can be pushed through the augmented path
            for (int j = t; j != s; j = parent[j]) {

                int i = parent[j];

                //find maximum possible flow through augmented path by checking each edges flowCap-flow and comparing it to the max value
                maxPathFlow = Math.min(maxPathFlow, (resGraph.getEdge(FN.getVertex(i), FN.getVertex(j)).flowCap - resGraph.getEdge(FN.getVertex(i), FN.getVertex(j)).flow));
            }

            //update residual graph capacities and reverse edges along path to account for back flow
            for (int j = t; j != s; j = parent[j]) {

                int i = parent[j];
                //update main graph flow
                FN.getEdge(FN.getVertex(i), FN.getVertex(j)).flow += maxPathFlow;
                //subtract the path flow from residual capacity of residual graph
                resGraph.getEdge(FN.getVertex(i), FN.getVertex(j)).flowCap -= maxPathFlow;
                //account for backflow by adding residual capacity to reverse edge
                resGraph.getEdge(FN.getVertex(j), FN.getVertex(i)).flowCap += maxPathFlow;
            }
            //update maximum flow through the network
            maxFlow += maxPathFlow;
        }
    }
}
