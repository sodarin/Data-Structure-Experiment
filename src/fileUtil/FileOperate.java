package fileUtil;

import algorithm.Algorithm;
import edge.Edge;
import vertex.Vertex;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * Created by ZhangHaodong on 2017/8/31.
 */
public class FileOperate {

    public static ArrayList<Vertex> vertexCollection = new ArrayList<Vertex>();
    public static ArrayList<Edge> edgeCollection  =new ArrayList<Edge>();

    /**
     * 将景点信息保存到文件里，内容包括景点名称，景点简介，景点人气以及相邻景点，每条信息以空格分隔
     * @param fileName 保存景点信息的文件名
     * @throws Exception
     */
    public static void writeVertexFile(String fileName) throws Exception{
        File file = new File(fileName);
        if(!file.exists()){
            file.createNewFile();
        }
        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);
        StringBuilder sb = new StringBuilder();
        for(int index = 0; index < vertexCollection.size(); index++){
            sb.append(vertexCollection.get(index).getName()+" "+vertexCollection.get(index).getIntroduction()+" "+vertexCollection.get(index).getPopulation()+" "+vertexCollection.get(index).getToilet()+" "+vertexCollection.get(index).getRestArea()+" ");
            sb.append("\r\n");

        }
        bw.write(sb.toString());
        bw.close();
    }

    /**
     * 将道路信息保存在文件里，内容包括起始景点，终止景点，道路长度，每条信息以空格隔开
     * @param fileName 保存道路信息的文件名
     */
    public static void writeEdgeFile(String fileName){
        File file = new File(fileName);
            try {
                if(!file.exists()) {
                    file.createNewFile();
                }
                FileWriter fw = new FileWriter(file.getAbsoluteFile());
                BufferedWriter bw = new BufferedWriter(fw);
                StringBuilder sb = new StringBuilder();
                for(int index = 0; index < edgeCollection.size(); index++){
                    sb.append(edgeCollection.get(index).getFromVertex()+"-----"+edgeCollection.get(index).getToVertex()+"-----"+edgeCollection.get(index).getValue());
                    sb.append("\r\n");

                }
                bw.write(sb.toString());
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }


    }

    public static Vertex returnVertex(String name) {
        Vertex vertex=null;
        try {
            BufferedReader br = new BufferedReader(new FileReader("D:\\JavaWebDesign\\TourismSystem\\Data-Structure-Experiment\\vertex.txt"));
            String s;
            while((s=br.readLine())!=null){
                if(s.split(" ")[0].equals(name)) {
                    String desc = s.split(" ")[1];
                    int population = Integer.parseInt(s.split(" ")[2]);
                    String hasToilet = s.split(" ")[3];
                    String hasRestArea = s.split(" ")[4];
                    vertex = new Vertex(name, desc, population, hasToilet, hasRestArea);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return vertex;

    }

    public static ArrayList<Vertex> readVertex(){
        ArrayList<Vertex> vertices = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("D:\\JavaWebDesign\\TourismSystem\\Data-Structure-Experiment\\vertex.txt"));
            String s;
            while((s=br.readLine())!=null){
                String name = s.split(" ")[0];
                String desc = s.split(" ")[1];
                int population = Integer.parseInt(s.split(" ")[2]);
                String hasToilet = s.split(" ")[3];
                String hasRestArea = s.split(" ")[4];
                Vertex vertex = new Vertex(name, desc, population, hasToilet, hasRestArea);
                vertices.add(vertex);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return vertices;
    }

    public static ArrayList<Edge> readEdge() {
        ArrayList<Edge> edges = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("D:\\JavaWebDesign\\TourismSystem\\Data-Structure-Experiment\\edge.txt"));
            String s;
            while ((s=br.readLine())!=null){
                Vertex fromVertex = returnVertex(s.split("-----")[0]);
                Vertex toVertex = returnVertex(s.split("-----")[1]);
                int value = Integer.parseInt(s.split("-----")[2]);
                Edge edge = new Edge(fromVertex, toVertex, value);
                edges.add(edge);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return edges;
    }

    /**
     * 向vertex.txt追加新添景点的内容
     * @param vertex 传入要添加的景点
     * @throws Exception
     */
    public static void appendVertex(Vertex vertex) throws Exception{
        BufferedReader br = new BufferedReader(new FileReader("D:\\JavaWebDesign\\TourismSystem\\Data-Structure-Experiment\\vertex.txt"));
        String s;
        while((s=br.readLine())!=null){
            if(s.split(" ")[0].equals(vertex.getName())){
                System.out.println("该景点已经存在！");
                return;
            }
        }//判断景点是否已经存在
        FileWriter fw = new FileWriter("D:\\JavaWebDesign\\TourismSystem\\Data-Structure-Experiment\\vertex.txt", true);
        String content = vertex.getName()+" "+vertex.getIntroduction()+" "+vertex.getPopulation()+" "+vertex.getToilet()+" "+vertex.getRestArea()+"\r\n";
        fw.write(content);//追加到景点文件的末尾
        if(fw!=null){
            fw.close();
        }
        System.out.println("添加成功!");

    }

    /**
     * 向edge.txt追加新添道路的内容
     * @param edge 传入要添加的道路
     */
    public static void appendEdge(Edge edge){
        try {
            BufferedReader br = new BufferedReader(new FileReader("D:\\JavaWebDesign\\TourismSystem\\Data-Structure-Experiment\\edge.txt"));
            String s;
            while((s=br.readLine())!=null){
                if((s.split("-----")[0].equals(edge.getFromVertex().getName())&&s.split("-----")[1].equals(edge.getToVertex().getName()))||(s.split("-----")[0].equals(edge.getToVertex().getName())&&s.split("-----")[1].equals(edge.getFromVertex().getName()))){
                    System.out.println("该路线已经存在！");
                    return;
                }
            }
            FileWriter fw = new FileWriter("D:\\JavaWebDesign\\TourismSystem\\Data-Structure-Experiment\\edge.txt", true);
            String content = edge.getFromVertex().getName()+"-----"+edge.getToVertex().getName()+"-----"+edge.getValue()+"\r\n";
            fw.write(content);
            if(fw!=null)
                fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("添加成功!");
    }



    public static void removeVertex(String vertexDesc){
        boolean flag = false;
        try {
            File file = new File("D:\\JavaWebDesign\\TourismSystem\\Data-Structure-Experiment\\vertex.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String str = null;
            List list = new ArrayList();
            while((str = br.readLine())!=null){
                if(str.split(" ")[0].equals(vertexDesc)) {
                    flag = true;
                    continue;
                }
                list.add(str);
            }
           if(flag){
               BufferedWriter bw = new BufferedWriter(new FileWriter(file));
               for (int i = 0; i < list.size(); i++){
                   bw.write(list.get(i).toString());
                   bw.newLine();
               }
               bw.flush();
               bw.close();
               removeAllEdge(vertexDesc);
           }else{
               System.out.println("该景点不存在!");
           }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void removeEdge(String fromVertex, String toVertex){
        try{
            boolean flag = false;
            File file = new File("D:\\JavaWebDesign\\TourismSystem\\Data-Structure-Experiment\\edge.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String str = null;
            List list = new ArrayList();
            while((str = br.readLine()) != null){
                if((str.split("-----")[0].equals(fromVertex)&&str.split("-----")[1].equals(toVertex))||(str.split(" ")[0].equals(toVertex)&&str.split(" ")[1].equals(fromVertex))){
                    flag = true;
                    continue;
                }
                list.add(str);
            }
            if(flag){
                BufferedWriter bw = new BufferedWriter(new FileWriter(file));
                for(int i = 0; i < list.size(); i++){
                    bw.write(list.get(i).toString());
                    bw.newLine();
                }
                bw.flush();
                bw.close();
                System.out.println("删除成功!");
            }else{
                System.out.println("该路线不存在!");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void removeAllEdge(String name){
        try{
            boolean flag = false;
            File file = new File("D:\\JavaWebDesign\\TourismSystem\\Data-Structure-Experiment\\edge.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String str = null;
            List list = new ArrayList();
            while((str = br.readLine()) != null){
                if((str.split("-----")[0].equals(name))||str.split("-----")[1].equals(name)){
                    flag = true;
                    continue;
                }
                list.add(str);
            }
            if(flag){
                BufferedWriter bw = new BufferedWriter(new FileWriter(file));
                for(int i = 0; i < list.size(); i++){
                    bw.write(list.get(i).toString());
                    bw.newLine();
                }
                bw.flush();
                bw.close();
                System.out.println("删除成功!");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static String readVertexInfo(String vertexDesc) throws Exception{
        File file = new File("D:\\JavaWebDesign\\TourismSystem\\Data-Structure-Experiment\\vertex.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String s = null;
        StringBuilder sb = new StringBuilder();
        String result;
        while((s = br.readLine())!=null){
            if(s.contains(vertexDesc)){
                sb.append("景区名字："+s.split(" ")[0]+"\n");
                sb.append("景区描述："+s.split(" ")[1]+"\n");
                sb.append("景区人气："+s.split(" ")[2]+"\n");
                sb.append("是否有厕所："+s.split(" ")[3]+"\n");
                sb.append("是否有休息区："+s.split(" ")[4]+"\n");
                sb.append("\n");
            }
        }
        return (result = sb.toString()).equals("")? "没有找到匹配结果": result;
    }


    public static void writeInfo(String content){
        File file = new File("D:\\JavaWebDesign\\TourismSystem\\Data-Structure-Experiment\\info.txt");
        try {
            if(!file.exists()){
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file, true);
            fw.write(content);
            if(fw!=null)
                fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("编辑成功!");
    }

    public static void returnInfo() {
        File file = new File("D:\\JavaWebDesign\\TourismSystem\\Data-Structure-Experiment\\info.txt");
        String s;
        List<String> info = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            while((s = br.readLine())!=null){
                info.add(s);
            }
            for(int i = info.size()-1; i >= 0; i--){
                System.out.println(info.get(i));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void sortByPopulation(){
        ArrayList<Vertex> sortingVertex = new ArrayList<>();
        String s;
        StringBuilder sb = new StringBuilder();
        try {
            File file = new File("D:\\JavaWebDesign\\TourismSystem\\Data-Structure-Experiment\\vertex.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            while((s=br.readLine())!=null){
                String name = s.split(" ")[0];
                String desc = s.split(" ")[1];
                int population = Integer.parseInt(s.split(" ")[2]);
                String hasToilet = s.split(" ")[3];
                String hasRestArea = s.split(" ")[4];
                Vertex vertex = new Vertex(name, desc, population, hasToilet, hasRestArea);
                sortingVertex.add(vertex);
            }
            Algorithm.quickSort(sortingVertex, 0, sortingVertex.size()-1, "population");
            for(int i = sortingVertex.size() - 1; i >= 0; i--){
                sb.append("景区名字："+sortingVertex.get(i).getName()+"\n");
                sb.append("景区人气值："+sortingVertex.get(i).getPopulation()+"\n");
                sb.append("景区介绍："+sortingVertex.get(i).getIntroduction()+"\n");
                sb.append("是否有厕所："+sortingVertex.get(i).getToilet()+"\n");
                sb.append("是否有休息区："+sortingVertex.get(i).getRestArea()+"\n");
                sb.append("\n");
            }
            System.out.println(sb.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void sortByVertex(){
        ArrayList<Vertex> sortingVertex = new ArrayList<>();
        ArrayList<Edge> sortingEdge = new ArrayList<>();
        String s;
        StringBuilder sb = new StringBuilder();
        try{
            File file = new File("D:\\JavaWebDesign\\TourismSystem\\Data-Structure-Experiment\\vertex.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            while((s=br.readLine())!=null){
                String name = s.split(" ")[0];
                String desc = s.split(" ")[1];
                int population = Integer.parseInt(s.split(" ")[2]);
                String hasToilet = s.split(" ")[3];
                String hasRestArea = s.split(" ")[4];
                Vertex vertex = new Vertex(name, desc, population, hasToilet, hasRestArea);
                sortingVertex.add(vertex);
            }
            File file1 = new File("D:\\JavaWebDesign\\TourismSystem\\Data-Structure-Experiment\\edge.txt");
            br = new BufferedReader(new FileReader(file1));
            while((s = br.readLine())!=null){
                Vertex fromVertex = returnVertex(s.split("-----")[0]);
                Vertex toVertex = returnVertex(s.split("-----")[1]);
                int value = Integer.parseInt(s.split("-----")[2]);
                Edge edge = new Edge(fromVertex, toVertex, value);
                sortingEdge.add(edge);
            }
            for(int i = 0; i < sortingVertex.size(); i++){
                for(int j = 0; j < sortingEdge.size(); j++){
                    if(sortingEdge.get(j).getFromVertex().getName().equals(sortingVertex.get(i).getName())){
                        sortingVertex.get(i).addNumOfNode();
                        sortingVertex.get(i).getNearByVertex().put(sortingEdge.get(j).getToVertex(), sortingEdge.get(j).getValue());
                    }
                    if(sortingEdge.get(j).getToVertex().getName().equals(sortingVertex.get(i).getName())){
                        sortingVertex.get(i).addNumOfNode();
                        sortingVertex.get(i).getNearByVertex().put(sortingEdge.get(j).getFromVertex(), sortingEdge.get(j).getValue());
                    }
                }
            }
            Algorithm.quickSort(sortingVertex, 0, sortingVertex.size()-1, "vertex");
            for(int i = sortingVertex.size() - 1; i >= 0; i--){
                sb.append("景区名字："+sortingVertex.get(i).getName()+"\n");
                sb.append("景区人气值："+sortingVertex.get(i).getPopulation()+"\n");
                sb.append("景区介绍："+sortingVertex.get(i).getIntroduction()+"\n");
                sb.append("是否有厕所："+sortingVertex.get(i).getToilet()+"\n");
                sb.append("是否有休息区："+sortingVertex.get(i).getRestArea()+"\n");
                sb.append("景区岔路数："+sortingVertex.get(i).getNumOfNode()+"\n");
                sb.append("相邻景点：");
                for(Map.Entry<Vertex, Integer> entry: sortingVertex.get(i).getNearByVertex().entrySet()){
                    sb.append(entry.getKey().getName()+" 距离："+entry.getValue()+" ");
                }
                sb.append("\n\n");
            }
            System.out.println(sb.toString());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void writeParkingRecord(String content){
        File file = new File("D:\\JavaWebDesign\\TourismSystem\\Data-Structure-Experiment\\parking.txt");
            try {
                if(!file.exists())
                    file.createNewFile();
                FileWriter fw = new FileWriter(file, true);
                fw.write(content);
                if(fw!=null)
                    fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    public static void readParkingRecord(){
        File file = new File("D:\\JavaWebDesign\\TourismSystem\\Data-Structure-Experiment\\parking.txt");
        StringBuilder sb = new StringBuilder();
        String s;
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            while((s=br.readLine())!=null){
                sb.append(s);
                sb.append("\n");
            }
            sb.append("\n");
            System.out.println(sb.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void returnHasToiletVertex() {
        ArrayList<Vertex> vertices = readVertex();
        ArrayList<Vertex> tempVertex = new ArrayList<Vertex>();
        for(int i = 0; i < vertices.size(); i++){
            if(vertices.get(i).isHasToilet())
                tempVertex.add(vertices.get(i));
        }
        for(int i = 0; i < tempVertex.size(); i++) {
            System.out.println(tempVertex.get(i).getName());
            System.out.println(tempVertex.get(i).getIntroduction());
            System.out.println();
        }
    }

    public static void returnHasRestAreaVertex() {
        ArrayList<Vertex> vertices = readVertex();
        ArrayList<Vertex> tempVertex = new ArrayList<Vertex>();
        for(int i = 0; i < vertices.size(); i++){
            if(vertices.get(i).isHasRestArea())
                tempVertex.add(vertices.get(i));
        }
        for(int i = 0; i < tempVertex.size(); i++) {
            System.out.println(tempVertex.get(i).getName());
            System.out.println(tempVertex.get(i).getIntroduction());
            System.out.println();
        }
    }


}
