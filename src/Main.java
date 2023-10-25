import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws Exception {  //проверка работы методов
        Scanner scan = new Scanner(System.in);
        int num;
        MatrixGraph graph = null;
        MatrixDigraph digraph = null;
        do {
            System.out.println("1. Создать граф\n2. Вывести граф\n3. Прочитать граф из файла\n4. Записать граф в файл\n" +
                    "5. Рандомный граф\n6. Рандомный диграф\n7. Удалить граф\n8. Выход ");
            System.out.print("\nВыбирите пункт - ");
            num = scan.nextInt();
            switch (num) {
                case 1:
                    System.out.println("1. Граф\n2. Диграф");
                    int a, b, v, r, x;
                    x = scan.nextInt();
                    if (x == 1) {
                        System.out.print("Число вершин - ");
                        v = scan.nextInt();
                        graph = new MatrixGraph(v);
                        if (v > 1) {
                            System.out.print("Число ребер - ");
                            r = scan.nextInt();
                            for (int i = 0; i < r; i++) {
                                System.out.print("a - ");
                                a = scan.nextInt();
                                System.out.print("b - ");
                                b = scan.nextInt();
                                graph.addEdge(a, b);
                            }
                        }
                    }
                    break;
                case 2:
                    System.out.println("1.Граф\n" + "2.Диграф");
                    int y = scan.nextInt();
                    if (y == 1) {
                        GraphFunctions.printGraph(graph);
                        System.out.println();
                    } else {
                        GraphFunctions.printGraph(digraph);
                        System.out.println();
                    }
                    break;
                case 3:
                    System.out.println("1.Граф\n" + "2.Диграф");
                    int o = scan.nextInt();
                    scan.nextLine();
                    System.out.print("Введите имя файла - ");
                    String str = scan.nextLine();
                    if (o == 1) {
                        GraphFunctions.clear(graph);
                        graph = (MatrixGraph) GraphFunctions.readGraphFromFile(str);
                        GraphFunctions.printGraph(graph);
                    } else {
                        GraphFunctions.clear(digraph);
                        digraph = (MatrixDigraph) GraphFunctions.readDigraphFromFile(str);
                        GraphFunctions.printGraph(digraph);
                    }
                    System.out.println();
                    break;
                case 4:
                    System.out.println("1.Граф\n" + "2.Диграф");
                    int l = scan.nextInt();
                    scan.nextLine();
                    System.out.print("Введите имя файла - ");
                    String str1 = scan.nextLine();
                    if (l == 1) {
                        GraphFunctions.clear(graph);
                        GraphFunctions.writeToFile(graph, str1);
                    } else {
                        GraphFunctions.clear(digraph);
                        GraphFunctions.writeToFile(digraph, str1);
                    }
                    System.out.println();
                    break;
                case 5:
                    GraphFunctions.clear(graph);
                    System.out.print("Введите максимальную вершину - ");
                    int q = scan.nextInt();
                    graph = (MatrixGraph) GraphFunctions.createRandomGraph(q);
                    System.out.println("Содан рандомный граф!");
                    break;
                case 6:
                    GraphFunctions.clear(graph);
                    System.out.print("Введите максимальную вершину - ");
                    int p = scan.nextInt();
                    digraph = (MatrixDigraph) GraphFunctions.createRandomDigraph(p);
                    System.out.println("Содан рандомный диграф!");
                    break;
                case 7:
                    GraphFunctions.clear(graph);
                    break;
            }
        } while (num != 8);
    }
}