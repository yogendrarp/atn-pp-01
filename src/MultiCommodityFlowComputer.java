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
        System.out.println();
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
        long zOpt = optimumMinFlow();
        float density = networkDensity();
        System.out.printf("The optimum multicommodity flow is %d\n", zOpt);
        System.out.printf("The density of the network is %f\n", density);

    }

    private long optimumMinFlow() {
        long zOpt = 0;
        for (int i = 0; i < nodeCount; i++) {
            for (int j = 0; j < nodeCount; j++) {
                //Doesn't matter as the distance is zero anyway, but a calculation is reduced
                if (i != j) {
                    zOpt += (long) trafficDemands[i][j] * allSourceShortestPathMatrix[i][j];
                }
            }
        }
        return zOpt;
    }

    private float networkDensity() {
        long count = 0;
        //We take a count of unused links and then use it compute the network density
        for (int i = 0; i < nodeCount; i++) {
            for (int j = 0; j < nodeCount; j++) {
                if (i != j & (allSourceShortestPathMatrix[i][j] == 0 || (allSourceShortestPathMatrix[i][j] != 1 & allSourceShortestPathMatrix[i][j] != 100))) {
                    count++;
                }
            }
        }
        long links = (long) nodeCount * (nodeCount - 1);
        return (float) (links - count) / (links);
    }

    private void floydWarshallShortestPath() {
        allSourceShortestPathMatrix = new int[nodeCount][nodeCount];
        for (int i = 0; i < nodeCount; i++) {
            //the shortest-path is initialized to graph, by definition of floyd warshall algorithm
            //it's the subgraph.
            for (int j = 0; j < nodeCount; j++) {
                allSourceShortestPathMatrix[i][j] = edgeWeightsCosts[i][j];
            }
        }

        for (int k = 0; k < nodeCount; k++) {
            for (int i = 0; i < nodeCount; i++) {
                for (int j = 0; j < nodeCount; j++) {
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
