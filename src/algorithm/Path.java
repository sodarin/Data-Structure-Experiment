package algorithm;

import vertex.Vertex;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by ZhangHaodong on 2017/9/9.
 */
public class Path {
    private ArrayList<Vertex> vertices = new ArrayList<Vertex>();
    private int[][] distance;
    private Vertex[][] preVertex;
    private int[][] graph;
    private int[][] path;
    private int[][] dist;
    Vertex notConnectVertex;

    public Path(ArrayList<Vertex> vertices, int[][] graph){
        this.vertices = vertices;
        this.distance = new int[this.vertices.size()+1][this.vertices.size()+1];
        this.preVertex = new Vertex[this.vertices.size()+1][this.vertices.size()+1];
        this.graph = graph;
        this.path = new int[this.vertices.size()+1][this.vertices.size()+1];
        this.dist = new int[this.vertices.size()+1][this.vertices.size()+1];
    }

    public boolean shortestPath() {
        int max = 32767;
        int min = 32767;
        int[] flag = new int[this.vertices.size()+1];
        boolean notConnect = false;
        for(int i = 0; i < this.vertices.size(); i++){
            if(this.vertices.get(i).getNumOfNode() == 0){
                notConnect = true;
                notConnectVertex = this.vertices.get(i);
                this.vertices.remove(notConnectVertex);
            }
        }
            for (int i = 0; i < this.vertices.size(); i++) {
                //迪杰斯特拉算法
                Vertex startVertex = this.vertices.get(i);
                flag = new int[this.vertices.size() + 1];
                for (int j = 1; j <= this.vertices.size(); j++) {
                    this.distance[this.vertices.indexOf(startVertex) + 1][j] = 32767;
                }
                for (Vertex vertex : startVertex.getNearByVertex().keySet()) {
                    this.distance[this.vertices.indexOf(startVertex) + 1][this.vertices.indexOf(vertex) + 1] = startVertex.getNearByVertex().get(vertex);
                }
                //起点
                flag[this.vertices.indexOf(startVertex) + 1] = 1;
                this.distance[this.vertices.indexOf(startVertex) + 1][this.vertices.indexOf(startVertex) + 1] = 0;
                //距离最小的点
                for (int j = 1; j < this.vertices.size(); j++) {
                    min = 32767;
                    Vertex nextVertex = null;
                    for (int l = 1; l <= this.vertices.size(); l++) {
                        if (flag[l] == 0 && this.distance[this.vertices.indexOf(startVertex) + 1][l] < min) {
                            min = this.distance[this.vertices.indexOf(startVertex) + 1][l];
                            nextVertex = this.vertices.get(l - 1);
                        }
                    }
                    flag[this.vertices.indexOf(nextVertex) + 1] = 1;

                    for (Vertex vertex : nextVertex.getNearByVertex().keySet()) {
                        int vMin = nextVertex.getNearByVertex().get(vertex) + this.distance[this.vertices.indexOf(startVertex) + 1][this.vertices.indexOf(nextVertex) + 1];
                        if (flag[this.vertices.indexOf(vertex) + 1] == 0 && vMin < this.distance[this.vertices.indexOf(startVertex) + 1][this.vertices.indexOf(vertex) + 1]) {
                            this.distance[this.vertices.indexOf(startVertex) + 1][this.vertices.indexOf(vertex) + 1] = vMin;
                            this.preVertex[this.vertices.indexOf(startVertex) + 1][this.vertices.indexOf(vertex) + 1] = nextVertex;
                        }
                    }
                }
            }
        return notConnect;
    }

    public String findShortestPath(String from, String to){
        int j = 0;
        int i = 0;
        StringBuilder sb = new StringBuilder();
        Stack<String> path = new Stack<String>();
        for(Vertex vertex: this.vertices){
            if(i == 0 && vertex.getName().equals(from)){
                i = this.vertices.indexOf(vertex)+1;
            }
            if(j == 0 && vertex.getName().equals(to)){
                j = this.vertices.indexOf(vertex)+1;
            }
        }
        if(i == 0|| j == 0){
            System.out.println("未查找到相关景点信息");
        }else{
            path.push(to);
            //搜索前置数组，查找根节点
            for(int m = i, n = j; this.preVertex[m][n]!=null; n = this.vertices.indexOf(this.preVertex[m][n])+1){
                path.push(this.preVertex[m][n].getName());
            }
            path.push(from);

            for(String str = path.pop(), str2 = path.pop();; str = str2, str2 = path.pop()){;
                sb.append(str+" ");
                if(path.isEmpty())
                    break;
            }

        }
        return sb.toString();
    }

    public void returnShortestPath(String from, String to){
        int j = 0;
        int i = 0;
        Stack<String> path = new Stack<String>();

        if(shortestPath()){
            if(from.equals(notConnectVertex.getName())||to.equals(notConnectVertex.getName())){
                System.out.println(notConnectVertex.getName()+"道路尚未修建好");
                return;
            }
        }

        for(Vertex vertex: this.vertices){
            if(i == 0 && vertex.getName().equals(from)){
                i = this.vertices.indexOf(vertex)+1;
            }
            if(j == 0 && vertex.getName().equals(to)){
                j = this.vertices.indexOf(vertex)+1;
            }
        }
        if(i == 0|| j == 0){
            System.out.println("未查找到相关景点信息");
        }else{
            path.push(to);
            //搜索前置数组，查找根节点
            for(int m = i, n = j; this.preVertex[m][n]!=null; n = this.vertices.indexOf(this.preVertex[m][n])+1){
                path.push(this.preVertex[m][n].getName());
            }
            path.push(from);

            for(String str = path.pop(), str2 = path.pop();; str = str2, str2 = path.pop()){
                System.out.println(str+"-----"+str2);
                if(path.isEmpty())
                    break;
            }
            System.out.println();
            System.out.println(from+"-----"+to+":"+this.distance[i][j]);
        }
    }
}
