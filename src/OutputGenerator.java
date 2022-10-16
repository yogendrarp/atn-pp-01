public class OutputGenerator {
    float density;
    long zOpt;
    int[][] allSourceShortestPathMatrix;
    int[][] edgeWeightsCosts;
    int[][] trafficDemands;

    public OutputGenerator(float density, long zOpt, int[][] allSourceShortestPathMatrix, int[][] edgeWeightsCosts, int[][] trafficDemands) {
        this.density = density;
        this.zOpt = zOpt;
        this.allSourceShortestPathMatrix = allSourceShortestPathMatrix;
        this.edgeWeightsCosts = edgeWeightsCosts;
        this.trafficDemands = trafficDemands;
    }

    public void displayOutput() {
        System.out.println();
        System.out.println("Computed Edge Weight Costs Matrix is below");
        Utility.printMatrix(edgeWeightsCosts);
        System.out.printf("\n\n\n");

        System.out.println("Computed Traffic Demand Matrix is below");
        Utility.printMatrix(trafficDemands);
        System.out.printf("\n\n\n");

        System.out.println("All source shortestpath Matrix is below");
        Utility.printMatrix(allSourceShortestPathMatrix);
        System.out.printf("\n\n\n");

        linkGraphGenerator();

        System.out.printf("The optimum multicommodity flow is %d\n", zOpt);
        System.out.printf("The density of the network is %f\n", density);

    }

    private void linkGraphGenerator() {
        int nodeCount = allSourceShortestPathMatrix.length;
        int[][] linkGraph = new int[nodeCount][nodeCount];
        for (int i = 0; i < nodeCount; i++) {
            for (int j = 0; j < nodeCount; j++) {
                if (i != j & (allSourceShortestPathMatrix[i][j] == 0 || (allSourceShortestPathMatrix[i][j] != 1 & allSourceShortestPathMatrix[i][j] != 100))) {
                    linkGraph[i][j] = 0;
                } else if (i != j) {
                    linkGraph[i][j] = 1;
                }
            }
        }
        System.out.println("Generated link graph is below");
        Utility.printMatrix(linkGraph);
        System.out.printf("\n\n\n");
    }
}
