import admin.AdminOperate;
import algorithm.HamiltonCircuit;
import algorithm.MST;
import algorithm.Path;
import edge.Edge;
import fileUtil.FileOperate;
import graph.Graph;
import simpleUI.UIUtil;
import vertex.Vertex;
import visitor.VisitorOperate;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        boolean flag = true;

        ArrayList<Vertex> vertexCollection = new ArrayList<>();
        ArrayList<Edge> edgeCollection = new ArrayList<>();
        Graph graph;

        Scanner sc = new Scanner(System.in);
        while(flag){
            UIUtil.menu();
            int in = sc.nextInt();
            sc.nextLine();
            switch (in) {
                case 1:
                    AdminOperate.adminLogin();
                    break;
                case 2:
                    vertexCollection = FileOperate.readVertex();
                    edgeCollection =FileOperate.readEdge();
                    graph = new Graph(vertexCollection, edgeCollection);
                    graph.outputGraph();
                    break;
                case 3:
                    VisitorOperate.searchSpot();
                    break;
                case 4:
                    VisitorOperate.sortSpot();
                    break;
                case 5:
                    int i = 0;
                    boolean exist = false;
                    boolean toExist = false;
                    int j = 0;
                    vertexCollection = FileOperate.readVertex();
                    edgeCollection = FileOperate.readEdge();
                    graph = new Graph(vertexCollection, edgeCollection);
                    Scanner scan = new Scanner(System.in);
                    String y;
                    HamiltonCircuit hamilton = new HamiltonCircuit(graph.getVertices(),graph.getAdjMatrix());
                    do {
                        if (!hamilton.getHamiltonCircuit()) {
                            MST mst = new MST(edgeCollection, graph.getVertices());
                            System.out.println("请手动输入起点：");
                            String name = scan.nextLine();
                            System.out.println("请手动输入终点：");
                            String toName = scan.nextLine();
                            for (i = 0; i < vertexCollection.size(); i++) {
                                if (vertexCollection.get(i).getName().equals(name)) {
                                    exist = true;
                                    break;
                                }
                            }
                            for(j = 0; j < vertexCollection.size(); j++){
                                if(vertexCollection.get(j).getName().equals(toName)){
                                    toExist = true;
                                    break;
                                }
                            }
                            if (!exist||!toExist) {
                                System.out.println("该景点不存在！");
                            } else {
                                mst.print(graph.getVertices().get(i), graph.getVertices().get(j));
                                exist = false;
                                toExist = false;
                            }
                        }
                        System.out.println("是否继续查询（Y/N):");
                        y = scan.nextLine();
                    }while(y.equals("y")||y.toUpperCase().equals("Y"));
                    break;
                case 6:
                    String back;
  //                  path.shortestPath();
                    do {
                        vertexCollection = FileOperate.readVertex();
                        edgeCollection = FileOperate.readEdge();
                        graph = new Graph(vertexCollection, edgeCollection);
                        Path path = new Path(graph.getVertices(), graph.getmGraph());
                        System.out.print("请输入查询距离的两个景点名称:\n1.");
                        Scanner scanner = new Scanner(System.in);
                        String str1 = scanner.nextLine();
                        System.out.print("2.");
                        String str2 = scanner.nextLine();
                        path.returnShortestPath(str1, str2);
                        System.out.println("是否继续查询？（Y/N)");
                        back = scanner.nextLine();
                    } while (back.equals("y")||back.toUpperCase().equals("Y"));
                    break;
                case 7:
                    VisitorOperate.readInfo();
                    break;
                default:
                    System.out.println("无效的操作！");
                    break;
            }
        }



    }

}
