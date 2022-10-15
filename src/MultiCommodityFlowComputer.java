public class MultiCommodityFlowComputer {
    int k;
    int nodeCount;
    int[][] edgeWeightsCosts;
    int[][] trafficDemands;
    int[][] allSourceShortestPathMatrix;

    public MultiCommodityFlowComputer(int k, int nodeCount, int[][] edgeWeightsCosts, int[][] trafficDemands) {
        this.k = k;
        this.nodeCount = nodeCount;
        this.edgeWeightsCosts = edgeWeightsCosts;
        this.trafficDemands = trafficDemands;
    }

    public void compute() {
        System.out.println("Computed Edge Weight Costs Matrix is below");
        Utility.printMatrix(edgeWeightsCosts);
        System.out.printf("\n\n\n");

        System.out.println("Computed Traffic Demand Matrix is below");
        Utility.printMatrix(trafficDemands);
        System.out.printf("\n\n\n");

        floydWarshallShortestPath();
        System.out.println("All source shortestpath Matrix is below");
        Utility.printMatrix(allSourceShortestPathMatrix);
        System.out.printf("\n\n\n");
        computeAndPrintMultiCommodityFlow();
    }

    private void computeAndPrintMultiCommodityFlow() {
        long zOpt = 0;
        for (int i = 0; i < nodeCount; i++) {
            for (int j = 0; j < nodeCount; j++) {
                //Doesn't matter as the distance is zero anyway, but a calculation is reduced
                if (i != j) {
                    zOpt+=trafficDemands[]
                }
            }
        }
    }

    private void floydWarshallShortestPath() {
        allSourceShortestPathMatrix = new int[nodeCount][nodeCount];
        for (int i = 0; i < nodeCount; i++) {
            for (int j = 0; j < nodeCount; j++) {
                //the shortest-path is initialized to graph, by definition of floyd warshall algorithm
                //it's the subgraph.
                allSourceShortestPathMatrix[i][j] = edgeWeightsCosts[i][j];
            }
        }

        for (int i = 0; i < nodeCount; i++) {
            for (int j = 0; j < nodeCount; j++) {
                for (int k = 0; k < nodeCount; k++) {
                    if (allSourceShortestPathMatrix[i][k] + allSourceShortestPathMatrix[k][j] <
                            allSourceShortestPathMatrix[i][j]) {
                        allSourceShortestPathMatrix[i][j] = allSourceShortestPathMatrix[i][k] +
                                allSourceShortestPathMatrix[k][j];
                    }
                }
            }
        }
    }
}
