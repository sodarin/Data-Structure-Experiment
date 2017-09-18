package admin;

import edge.Edge;
import fileUtil.FileOperate;
import simpleUI.UIUtil;
import vertex.Vertex;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * Created by ZhangHaodong on 2017/9/1.
 */
public class AdminOperate {
    public static void adminLogin() throws Exception {
        Scanner login = new Scanner(System.in);
        System.out.println("请输入用户名：");
        String username = login.nextLine();
        System.out.println("请输入密码：");
        String password = login.nextLine();
        if (username.equals("admin") && password.equals("123456")) {
            UIUtil.adminUI();
        } else {
            System.out.println("用户名或密码错误！");
        }
    }

    public static void insertVertex() throws Exception {
        Scanner insert = new Scanner(System.in);
        String flag;
        do {
            System.out.println("请输入景点名称：");
            String name = insert.nextLine();
            System.out.println("请输入景点描述：");
            String desc = insert.nextLine();
            System.out.println("请输入景点人气值：");
            int population = insert.nextInt();
            insert.nextLine();
            System.out.println("是否有厕所：（Y/N)");
            String hasToilet = insert.nextLine();
            System.out.println("是否有休息区：(Y/N)");
            String hasRestArea = insert.nextLine();
            Vertex vertex = new Vertex(name, desc, population, hasToilet, hasRestArea);
            FileOperate.appendVertex(vertex);
            System.out.println("是否继续添加（Y/N）：");
            flag = insert.nextLine();
        }while(flag.equals("y")||flag.toUpperCase().equals("Y"));
    }

    public static void removeVertex(){
        Scanner remove = new Scanner(System.in);
        String flag;
        do {
            System.out.println("请输入景点名称：");
            String name = remove.nextLine();
            FileOperate.removeVertex(name);
            System.out.println("是否继续删除（Y/N）：");
            flag = remove.nextLine();
        }while(flag.equals("y")||flag.toUpperCase().equals("Y"));
    }

    public static void insertEdge(){
        Scanner insert = new Scanner(System.in);
        String flag;
        do {
            System.out.println("请输入景点名称：");
            String fromVertex = insert.nextLine();
            System.out.println("请输入相邻景点名称：");
            String toVertex = insert.nextLine();
            System.out.println("请输入道路长度：");
            int value = insert.nextInt();
            insert.nextLine();
            Vertex from = FileOperate.returnVertex(fromVertex);
            Vertex to = FileOperate.returnVertex(toVertex);
            Edge edge = new Edge(from, to, value);
            FileOperate.appendEdge(edge);
            System.out.println("是否继续添加（Y/N）：");
            flag = insert.nextLine();
        }while(flag.equals("y")||flag.toUpperCase().equals("Y"));
    }

    public static void removeEdge(){
        Scanner remove = new Scanner(System.in);
        String flag;
        do {
            System.out.println("请输入景点名称：");
            String fromVertex = remove.nextLine();
            System.out.println("请输入相邻景点名称：");
            String toVertex = remove.nextLine();
            FileOperate.removeEdge(fromVertex, toVertex);
            System.out.println("是否继续删除（Y/N）：");
            flag = remove.nextLine();
        }while(flag.equals("y")||flag.toUpperCase().equals("Y"));
    }

    public static void sendInfo(){
        Scanner sc = new Scanner(System.in);
        String flag;
        do {
            StringBuilder sb = new StringBuilder();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            System.out.println("当前时间："+df.format(new Date()));
            System.out.println("请输入内容：");
            String content = sc.nextLine();
            sb.append("\r\n");
            sb.append(content+"\n");
            sb.append(df.format(new Date())+"\n");

            FileOperate.writeInfo(sb.toString());
            System.out.println("是否继续编辑（Y/N）：");
            flag = sc.nextLine();
        }while(flag.equals("y")||flag.toUpperCase().equals("Y"));
    }

}
