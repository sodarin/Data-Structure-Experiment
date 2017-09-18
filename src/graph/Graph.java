package graph;

import edge.Edge;
import vertex.Vertex;

import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.Scanner;

/**
 * Created by ZhangHaodong on 2017/9/8.
 */
public class Graph {
    private int[][] mGraph;
    private int[][] adjMatrix;
    private ArrayList<Edge> edges;
    private ArrayList<Vertex> vertices;

    public ArrayList<Vertex> getVertices(){
        return this.vertices;
    }
    public int[][] getmGraph(){
        return this.mGraph;
    }
    public int[][] getAdjMatrix() {return this.adjMatrix;}

    public Graph(ArrayList<Vertex> vertices, ArrayList<Edge> edges){
        this.mGraph = new int[edges.size()+1][edges.size()+1];
        this.edges = edges;
        this.vertices = vertices;
        this.adjMatrix = new int[vertices.size()][vertices.size()];
        createGraph(edges);
    }

    public void createGraph(ArrayList<Edge> edges) {
        for (Edge edge : edges) {
            this.vertices.get(this.vertices.indexOf(edge.getFromVertex())).addNearByVertex(edge.getToVertex(), edge.getValue());
            this.vertices.get(this.vertices.indexOf(edge.getToVertex())).addNearByVertex(edge.getFromVertex(), edge.getValue());

            this.mGraph[this.vertices.indexOf(edge.getFromVertex())+1][this.vertices.indexOf(edge.getToVertex())+1] = edge.getValue();
            this.mGraph[this.vertices.indexOf(edge.getToVertex())+1][this.vertices.indexOf(edge.getFromVertex())+1] = edge.getValue();

            this.adjMatrix[this.vertices.indexOf(edge.getFromVertex())][this.vertices.indexOf(edge.getToVertex())] = 1;
            this.adjMatrix[this.vertices.indexOf(edge.getToVertex())][this.vertices.indexOf(edge.getFromVertex())] = 1;
        }
        for(int i = 1; i <= edges.size(); i++){
            for(int j = 1; j <= edges.size(); j++){
                if(i!=j && this.mGraph[i][j] == 0){
                    this.mGraph[i][j] = 32767;
                }
            }
        }
        for(int i = 0; i < vertices.size(); i++){
            for(int j = 0; j < vertices.size(); j++){
                if(this.adjMatrix[i][j] == 0)
                    this.adjMatrix[i][j] = -1;
            }
        }
    }

    public void outputGraph() {
        System.out.printf("\t                   ");
        for(Vertex vertex: vertices){
            System.out.printf("%-22s", vertex.getName());
        }
        System.out.println();
        for(int i = 1; i <= vertices.size(); i++){
            System.out.printf("%-20s", vertices.get(i-1).getName());
            System.out.printf(Pattern.compile("[\\u4e00-\\u9fa5][\\u4e00-\\u9fa5]").matcher(vertices.get(i-1).getName()).matches()?" ":"");
            for(int j = 1; j <= vertices.size(); j++){
                System.out.printf("%-25d", this.mGraph[i][j]);
            }
            System.out.println();
        }
        System.out.println("输入0返回主菜单");
        Scanner sc = new Scanner(System.in);
        int i = sc.nextInt();
        sc.nextLine();
        if(i == 0)
            return;
    }

}
