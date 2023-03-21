//package assign07;
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Random;
//import java.util.Scanner;
//
//public class TimingTest {
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//
//        System.out.print("Enter the starting size of the graph (N): ");
//        int startSize = scanner.nextInt();
//
//        System.out.print("Enter the increment for the graph size: ");
//        int increment = scanner.nextInt();
//
//        System.out.print("Enter the number of iterations for each graph size: ");
//        int iterations = scanner.nextInt();
//
//        System.out.print("Enter the output CSV file name: ");
//        String outputFileName = scanner.next();
//
//        try (FileWriter writer = new FileWriter(outputFileName)) {
//            writer.write("GraphSize,areConnectedTime,shortestPathTime,sortTime\n");
//
//            for (int n = startSize; n <= startSize + increment * iterations; n += increment) {
//                long areConnectedTime = 0;
//                long shortestPathTime = 0;
//                long sortTime = 0;
//
//                for (int i = 0; i < iterations; i++) {
//                    File dotFile = generateDotFile(n, 2 * n);
//                    ArrayList<String> vertices = new ArrayList<>();
//                    ArrayList<Edge> edges = new ArrayList<>();
//                    GraphUtility.buildListsFromDot(dotFile.getAbsolutePath(), vertices, edges);
//                    Graph graph = new Graph(vertices, edges);
//
//                    // Time areConnected
//                    long startTime = System.nanoTime();
//                    GraphUtility.areConnected(graph, vertices.get(0), vertices.get(vertices.size() - 1));
//                    long endTime = System.nanoTime();
//                    areConnectedTime += endTime - startTime;
//
//                    // Time shortestPath
//                    startTime = System.nanoTime();
//                    GraphUtility.shortestPath(graph, vertices.get(0), vertices.get(vertices.size() - 1), 0);
//                    endTime = System.nanoTime();
//                    shortestPathTime += endTime - startTime;
//
//                    // Time sort
//                    List<Edge> edgeList = new ArrayList<>(graph.getEdges());
//                    startTime = System.nanoTime();
//                    GraphUtility.sort(edgeList);
//                    endTime = System.nanoTime();
//                    sortTime += endTime - startTime;
//                }
//
//                writer.write(n + "," + (areConnectedTime / iterations) + "," + (shortestPathTime / iterations) + "," + (sortTime / iterations) + "\n");
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private static File generateDotFile(int vertices, int edges) throws IOException {
//        File tempFile = File.createTempFile("tempDotFile", ".dot");
//        try (FileWriter writer = new FileWriter(tempFile)) {
//            writer.write("graph G {\n");
//
//            // Generate random edges
//            Random random = new Random();
//            for (int i = 0; i < edges; i++) {
//                int startNode = random.nextInt(vertices);
//                int endNode = random.nextInt(vertices);
//                writer.write("  " + startNode + " -- " + endNode + ";\n");
//            }
//
//            writer.write("}\n");
//        }
//        return tempFile;
//    }
//}
//
