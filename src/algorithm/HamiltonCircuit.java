package algorithm;

import vertex.Vertex;

import java.util.ArrayList;

/**
 * Created by ZhangHaodong on 2017/9/10.
 */
public class HamiltonCircuit {

    private ArrayList<Vertex> vertex;
    private int[][] adjMatrix;
    int flag = 0;
    ArrayList<Vertex> noEdgeVertex = new ArrayList<>();

    public HamiltonCircuit(ArrayList<Vertex> vertex, int[][] adjMatrix){
        this.adjMatrix = adjMatrix;
        this.vertex = vertex;
        deleteEdge();
    }

    public void deleteEdge() {
        int index;
        for(int i = 0; i < this.vertex.size(); i++) {
            if(this.vertex.get(i).getNumOfNode() == 1){
                for(Vertex near: this.vertex.get(i).getNearByVertex().keySet()) {
                    index = this.vertex.indexOf(near);
                    int[][] temp = adjMatrix;
                    adjMatrix = new int[adjMatrix.length-1][adjMatrix.length-1];
                    for(int j = 0; j < i; j++){
                        for(int k = 0; k < i; k++) {
                            adjMatrix[j][k] = temp[j][k];
                        }
                    }
                    for(int j = i; j < adjMatrix.length; j++){
                        for(int k = i; k < adjMatrix.length; k++){
                            adjMatrix[j][k] = temp[j+1][k+1];
                        }
                    }
                }
                noEdgeVertex.add(this.vertex.get(i));
                flag++;
            }
        }
    }

    public boolean getHamiltonCircuit(){
        boolean[] visited = new boolean[adjMatrix.length];
        int[] path = new int[adjMatrix.length];
        for(int i = 0; i < adjMatrix.length; i++){
            visited[i] = false;
            path[i] = -1;
        }
        visited[0] = true;
        path[0] = 0;
        return dfs(adjMatrix, path, visited, 1);
    }

    public boolean dfs(int[][] adjMatrix, int[] path, boolean[] visited, int step) {
        int weight = 0;
        if(step == adjMatrix.length){//步长和景点个数一样的时候准备往控制台打印
            if(adjMatrix[path[step-1]][0] == 1){//如果倒数第二步可达起点则继续打印
                for(int i = 0; i < path.length-1; i++){
                    System.out.print(vertex.get(path[i]).getName()+"--->");
                    for(int j = 0; j < flag; j++) {
                        for(Vertex noEdgeVertex: noEdgeVertex.get(j).getNearByVertex().keySet()){
                            if(noEdgeVertex.getName().equals(vertex.get(path[i]).getName())){
                                System.out.print(this.noEdgeVertex.get(j).getName()+"--->"+vertex.get(path[i]).getName()+"--->");
                                weight += this.noEdgeVertex.get(j).getNearByVertex().get(noEdgeVertex) * 2;
                            }
                        }
                    }
                    for(Vertex vertex: this.vertex.get(path[i]).getNearByVertex().keySet()){
                        if(vertex.getName().equals(this.vertex.get(path[i+1]).getName())){
                            weight += this.vertex.get(path[i]).getNearByVertex().get(vertex);
                        }
                    }
                }
                System.out.print(vertex.get(path[path.length-1]).getName()+"--->");
                for(Vertex vertex: this.vertex.get(path[path.length-1]).getNearByVertex().keySet()){
                    if (vertex.getName().equals(this.vertex.get(0).getName())){
                        weight += this.vertex.get(path[path.length-1]).getNearByVertex().get(vertex);
                    }
                }
                System.out.print(vertex.get(0).getName());
                System.out.println();
                System.out.println("回路长度："+weight);
                return true;
            }
            return false;
        }else{//当步长未达到景点个数时，重复这一步骤
            for(int i = 0; i < adjMatrix.length; i++) {
                if(!visited[i] &&adjMatrix[path[step-1]][i] ==1){//表示未经过并可达的景点
                    visited[i] = true;
                    path[step] = i;
                    if(dfs(adjMatrix, path, visited, step+1))//迭代，如果可以一路走到底，返回true，如果走到死路返回false
                        return true;
                    else{//返回false时退回到上一步，并把visited和path重置
                        visited[i] = false;
                        path[step] = -1;

                    }
                }

            }
        }
        return false;
    }
}
