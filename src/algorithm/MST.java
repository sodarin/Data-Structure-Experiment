package algorithm;

import edge.Edge;
import fileUtil.FileOperate;
import graph.Graph;
import vertex.Vertex;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by ZhangHaodong on 2017/9/10.
 */
public class MST {
    private ArrayList<Edge> arcNodes = new ArrayList<Edge>();
    private ArrayList<Vertex> vNodes = new ArrayList<Vertex>();
    private LinkedList<Vertex> visited = new LinkedList<Vertex>();
    private int weight;
    Vertex notConnectVertex;

    ArrayList<Edge> mstEdge = new ArrayList<>();

    public MST(ArrayList<Edge> arcNodes,ArrayList<Vertex> vNodes){
        this.arcNodes = arcNodes;
        this.vNodes = vNodes;
    }
    //克鲁斯卡尔算法
    public String kruskal(){
        ArrayList<Edge> sortArcNodes = new ArrayList<Edge>(this.arcNodes.size());
        //对边集进行深拷贝
        ArrayList<Edge> arcNodes = new ArrayList<Edge>(this.arcNodes.size());
        for(Edge arcNode : this.arcNodes){
            arcNodes.add(arcNode.clone());
        }
        //对边集进行升序排序，结果保存在sortArcNodes中
        for(int i = 0;i<this.arcNodes.size();++i){
            Edge arcNode = null;
            int value = 32767;
            int minNumber = -1;
            for(int j = 0;j<arcNodes.size();++j){
                if(arcNodes.get(j).getValue() < value){
                    arcNode = arcNodes.get(j);
                    value = arcNodes.get(j).getValue();
                    minNumber = j;
                }
            }
            arcNodes.remove(minNumber);
            sortArcNodes.add(arcNode.clone());
        }

        int weight = 0;
        int[] vEnd = new int[this.vNodes.size()+1];
        for(Edge arcNode : sortArcNodes){
            int i = this.vNodes.indexOf(arcNode.getFromVertex())+1;
            int j = this.vNodes.indexOf(arcNode.getToVertex())+1;
            //获得两个边的终点
            int m = getEnd(vEnd, i);
            int n = getEnd(vEnd, j);
            //判断是否会构成回路
            if(m!=n){
                weight += arcNode.getValue();
                vEnd[m] = n;
                arcNodes.add(arcNode);
            }
        }
        String string="Kruskal:"+weight;
        for(Edge arcNode : arcNodes){
            string += " " + arcNode.getFromVertex().getName()+"--"+arcNode.getToVertex().getName()+":"+arcNode.getValue();
        }
        return string;
    }

    private int getEnd(int[] vEnd,int i){
        while(vEnd[i]!=0)
            i = vEnd[i];
        return i;
    }
    //普里姆算法
    public boolean prim(Vertex vNode){

        ArrayList<Vertex> tempVertex = new ArrayList<Vertex>();

        int sumWeight = 0;
        int[] weight = new int[this.vNodes.size()+1];
        int[] preVNodeNumber = new int[this.vNodes.size()+1];
        ArrayList<Edge> resArcNodes = new ArrayList<Edge>();
        boolean notConnect = false;
        //初始化距离及所有点的前序点
        for(int i = 0; i < this.vNodes.size(); i++){
            if(vNodes.get(i).getNumOfNode() == 0){
                notConnect = true;
                notConnectVertex = vNodes.get(i);
                this.vNodes.remove(vNodes.get(i));
                break;
            }
        }
            for (int i = 1; i <= this.vNodes.size(); ++i) {
                weight[i] = 32767;
                preVNodeNumber[i] = this.vNodes.indexOf(vNode) + 1;
            }
            //为权重赋值
            for (Vertex tempVNode : vNode.getNearByVertex().keySet()) {
                weight[this.vNodes.indexOf(tempVNode) + 1] = vNode.getNearByVertex().get(tempVNode);
            }
            //权重为0代表此点已被加入点集
            weight[this.vNodes.indexOf(vNode) + 1] = 0;

            int flag = 0;
            Vertex preVNode = vNode;
            for (int i = 1; i < this.vNodes.size(); ++i) {
                int min = 32767;
                Edge arcNode = null;
                //选出权重最小的点并加入点集
                for (int j = 1; j <= this.vNodes.size(); ++j) {
                    if (weight[j] != 0 && weight[j] < min) {
                        preVNode = this.vNodes.get(preVNodeNumber[j] - 1);
                        arcNode = new Edge(preVNode, this.vNodes.get(j - 1), weight[j]);
                        min = weight[j];
                        flag = j;

                    }
                }

                sumWeight += min;
                resArcNodes.add(arcNode);
                weight[flag] = 0;
                //更新权重
                for (Vertex tempVNode : this.vNodes.get(flag - 1).getNearByVertex().keySet()) {
                    if (weight[this.vNodes.indexOf(tempVNode) + 1] != 0 && this.vNodes.get(flag - 1).getNearByVertex().get(tempVNode) < weight[this.vNodes.indexOf(tempVNode) + 1]) {
                        weight[this.vNodes.indexOf(tempVNode) + 1] = this.vNodes.get(flag - 1).getNearByVertex().get(tempVNode);
                        preVNodeNumber[this.vNodes.indexOf(tempVNode) + 1] = flag;
                    }
                }
            }
            String string = "Prim:" + sumWeight;
            for (Edge arcNode : resArcNodes) {
                string += " " + arcNode.getFromVertex().getName() + "--" + arcNode.getToVertex().getName() + ":" + arcNode.getValue();
                mstEdge.add(new Edge(arcNode.getFromVertex(), arcNode.getToVertex(), arcNode.getValue()));
            }

        return notConnect;
    }

    public LinkedList<Vertex> preOrder(ArrayList<Edge> edges){
        boolean isFrom=true;
        boolean isTo=true;
        for(int i = 0; i < edges.size(); i++){
            for(int j = 0; j < visited.size(); j++) {
                if (edges.get(i).getFromVertex().getName().equals(visited.get(j).getName()))
                    isFrom = false;
                if(edges.get(i).getToVertex().getName().equals(visited.get(j).getName()))
                    isTo = false;
            }
            if(isFrom){
                visited.add(edges.get(i).getFromVertex());
            }
            if(isTo){
                visited.add(edges.get(i).getToVertex());
            }
            isTo = true;
            isFrom = true;
        }
        visited.add(edges.get(0).getFromVertex());
        System.out.println();
        return visited;
    }

    public LinkedList<Vertex> diffPreOrder(ArrayList<Edge> edges, Vertex toVertex){
        boolean isFrom=true;
        boolean isTo=true;
        for(int i = 0; i < edges.size(); i++){
            for(int j = 0; j < visited.size(); j++) {
                if (edges.get(i).getFromVertex().getName().equals(visited.get(j).getName()))
                    isFrom = false;
                if(edges.get(i).getToVertex().getName().equals(visited.get(j).getName()))
                    isTo = false;
            }
            if(isFrom){
                visited.add(edges.get(i).getFromVertex());
            }
            if(isTo){
                visited.add(edges.get(i).getToVertex());
            }
            isTo = true;
            isFrom = true;
        }
        for(int i = 0; i < this.vNodes.size(); i++){
            if(!visited.get(visited.size()-1).getName().equals(toVertex.getName())&&this.vNodes.get(i).getName().equals(toVertex.getName())){
                visited.add(toVertex);
                break;
            }
        }
        System.out.println();
        return visited;
    }

    public void print(Vertex v, Vertex toVertex) {
        int content = 0;
        for(int i = 0; i < this.vNodes.size(); i++){
            if(v.getName().equals(this.vNodes.get(i).getName())&&this.vNodes.get(i).getNumOfNode() == 0){
                System.out.println(v.getName()+"没有道路连通，不能作为起点！");
                return;
            }
            if(toVertex.getName().equals(this.vNodes.get(i).getName())&&this.vNodes.get(i).getNumOfNode()==0){
                System.out.println(toVertex.getName()+"没有道路连通，不能作为终点！");
            }
        }
        if(prim(v)){
            System.out.println(notConnectVertex.getName()+"尚未有道路连通，其他景点路线如下：");
        }
        if(v.getName().equals(toVertex.getName())) {
            this.visited = preOrder(mstEdge);
            this.weight = goPath();
            for (int i = 0; i < this.visited.size() - 1; i++) {
                System.out.print(this.visited.get(i).getName() + "-----");
            }
            System.out.print(this.visited.get(visited.size() - 1).getName());
            System.out.println();
            for (Vertex vertex : this.visited.get(visited.size() - 1).getNearByVertex().keySet()) {
                if (vertex.getName().equals(this.visited.get(visited.size() - 2).getName()))
                    content = this.visited.get(visited.size() - 1).getNearByVertex().get(vertex);
            }
            weight += content;
            System.out.println("总路长：" + weight);
        }else{
            this.visited = diffPreOrder(mstEdge, toVertex);
            this.weight = goPath();
            for (int i = 0; i < this.visited.size() - 1; i++) {
                System.out.print(this.visited.get(i).getName() + "-----");
            }
            System.out.print(this.visited.get(visited.size() - 1).getName());
            System.out.println();
            for (Vertex vertex : this.visited.get(visited.size() - 1).getNearByVertex().keySet()) {
                if (vertex.getName().equals(this.visited.get(visited.size() - 2).getName()))
                    content = this.visited.get(visited.size() - 1).getNearByVertex().get(vertex);
            }
            weight += content;
            System.out.println("总路长：" + weight);
        }
    }

    public int goPath(){
        boolean flag = false;
        ArrayList<Edge> edges = FileOperate.readEdge();
        Graph graph = new Graph(vNodes, edges);
        Path path = new Path(graph.getVertices(), graph.getmGraph());
        path.shortestPath();
        int weight = 0;
        int i = 0;
        for(i = 0; i < this.visited.size()-1; i++){
            for(Vertex vertex: this.visited.get(i).getNearByVertex().keySet()){
                if(vertex.getName().equals(this.visited.get(i+1).getName())) {
                    weight += this.visited.get(i).getNearByVertex().get(vertex);
                    flag = true;
                    break;
                }
            }
            if(!flag){
                int k=0;
                String str = path.findShortestPath(this.visited.get(i).getName(), this.visited.get(i+1).getName());
                String[] content = str.split(" ");
                for(int j = 0; j < content.length-1; j++){
                    for(k = 0; k < vNodes.size(); k++){
                        if(vNodes.get(k).getName().equals(content[j+1]))
                            break;
                    }
                    this.visited.add(i+1+j, vNodes.get(k));
                }
                i--;
            }
            flag = false;
        }
        return weight;
    }

}
