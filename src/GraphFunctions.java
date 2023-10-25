import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Random;
import java.util.Scanner;

public class GraphFunctions {   // библиотека графов

    private static Random random = new Random();
    private static Scanner scanner;

    public static void printGraph(Graph graph) {
        if (graph == null) {
            System.out.println("Графа нет");
            return;
        }
        System.out.println("\nГраф");
        System.out.println("Количество вершин - " + graph.vertexCount());
        System.out.println("Количество ребер - " + graph.edgeCount());
        if (graph instanceof Digraph) {
            for (int i = 0; i < graph.vertexCount(); i++) {
                for (int j = 0; j < graph.vertexCount(); j++) {
                    if (graph.isAdj(i, j)) {
                        System.out.println(i + " -> " + j);
                    }
                }
            }
        } else {
            for (int i = 0; i < graph.vertexCount(); i++) {
                for (int j = i; j < graph.vertexCount(); j++) {
                    if (graph.isAdj(i, j)) {
                        System.out.println(i + " - " + j);
                    }
                }
            }
        }
    }

    public static Graph createRandomGraph(int maxVertex) {
        MatrixGraph graph = null;
        int ver = random.nextInt(2, maxVertex + 2);
        int edge = 0;
        if (ver == 2) {
            edge = 1;
            graph = new MatrixGraph(ver);
            graph.addEdge(0, 1);
        } else {
            edge = random.nextInt(ver - 1, ver * (ver - 1) / 2);
            graph = new MatrixGraph(ver);
            int count = 0;
            while (edge >= count) {
                int a = random.nextInt(0, ver);
                int b = random.nextInt(0, ver);
                if (!graph.isAdj(a, b) && !graph.isAdj(b, a) && a != b) {
                    graph.addEdge(b, a);
                    graph.addEdge(a, b);
                    count++;
                }
            }
        }
        return graph;
    }

    public static Digraph createRandomDigraph(int maxVertex) {
        MatrixDigraph digraph = null;
        int ver = random.nextInt(2, maxVertex);
        int edge = 0;
        if (ver == 2) {
            edge = 1;
            digraph = new MatrixDigraph(ver);
            digraph.addEdge(0, 1);
        } else {
            edge = random.nextInt(ver - 1, ver * (ver - 1) / 2);
            digraph = new MatrixDigraph(ver);
            int count = 0;
            while (edge >= count) {
                int a = random.nextInt(0, ver);
                int b = random.nextInt(0, ver);
                if (!digraph.isAdj(a, b) && a != b) {
                    digraph.addEdge(a, b);
                    count++;
                }
            }
        }
        return digraph;
    }

    public static void clear(Graph graph) {
        graph = null;
    }

    public static Graph readGraphFromFile(String fileName) throws Exception {
        try {
            scanner = new Scanner(new FileInputStream(fileName + ".txt"));
        } catch (Exception e) {
            System.out.println("Файла " + fileName + " не существует!!");
            return null;
        }
        String line = "";
        int vertexCount = Integer.parseInt(scanner.nextLine());
        int edgeCount = Integer.parseInt(scanner.nextLine());
        Graph graph = new MatrixGraph(vertexCount);
        int v1, v2;
        int count = 0;
        while (scanner.hasNext()) {
            if (count >= edgeCount) {
                break;
            }
            line = scanner.nextLine();
            if (line.matches("\\d+-\\d+")) {
                v1 = Integer.parseInt(line.substring(0, line.indexOf("-")).trim());
                v2 = Integer.parseInt(line.substring(line.indexOf("-") + 1).trim());
                graph.addEdge(v1, v2);
                count++;
            } else {
                throw new Exception("Неправильно составлена строка \"" + line + "\" для ввода ребра!");
            }
        }
        return graph;
    }

    public static Digraph readDigraphFromFile(String fileName) throws Exception {
        try {
            scanner = new Scanner(new File(fileName + ".txt"));
        } catch (Exception e) {
            System.out.println("Файла " + fileName + " не существует!!");
            return null;
        }
        String line = "";
        int vertexCount = Integer.parseInt(scanner.nextLine());
        int edgeCount = Integer.parseInt(scanner.nextLine());
        Digraph graph = new MatrixDigraph(vertexCount);
        int v1, v2;
        int count = 0;
        while (scanner.hasNext()) {
            if (count >= edgeCount) {
                break;
            }
            line = scanner.nextLine();
            if (line.matches("\\d+->\\d+")) {
                v1 = Integer.parseInt(line.substring(0, line.indexOf("-")).trim());
                v2 = Integer.parseInt(line.substring(line.indexOf(">") + 1).trim());
                graph.addEdge(v1, v2);
                count++;
            } else {
                throw new Exception("Неправильно составлена строка \"" + line + "\" для ввода ребра!");
            }
        }
        return graph;
    }

    public static void writeToFile(Graph graph, String filename) throws FileNotFoundException {
        PrintStream out = new PrintStream(filename + ".txt");
        out.println(graph.vertexCount());
        out.println(graph.edgeCount());
        if (graph instanceof Digraph) {
            for (int v1 = 0; v1 < graph.vertexCount(); v1++) {
                for (int v2 : graph.adjacencies(v1)) {
                    out.printf("%s->%s\n", v1, v2);
                }
            }
            out.close();
            return;
        }
        for (int v1 = 0; v1 < graph.vertexCount(); v1++) {
            for (int v2 = v1; v2 < graph.vertexCount(); v2++) {
                if (graph.isAdj(v1, v2)) {
                    out.printf("%s-%s\n", v1, v2);
                }
            }
        }
        out.close();
    }
}
