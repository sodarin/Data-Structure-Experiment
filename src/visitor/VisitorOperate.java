package visitor;


import fileUtil.FileOperate;

import java.io.File;
import java.util.Scanner;

/**
 * Created by ZhangHaodong on 2017/9/4.
 */
public class VisitorOperate {


    public static void searchSpot(){
        String result, flag;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println();
            System.out.println("*********************");
            System.out.println("*1.查询公厕地点     *");
            System.out.println("*2.查询休息区地点   *");
            System.out.println("*3.关键词搜索       *");
            System.out.println("*********************");
            int choice = sc.nextInt();
            sc.nextLine();
            if(choice == 3) {
                System.out.println("请输入查询内容：");
                String search = sc.nextLine();
                try {
                    result = FileOperate.readVertexInfo(search);
                    System.out.println(result);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else if(choice == 2){
                FileOperate.returnHasRestAreaVertex();
            }else if(choice == 1) {
                FileOperate.returnHasToiletVertex();
            }else{
                System.out.println("输入错误！");
            }
            System.out.println("是否继续查询（Y/N):");
            flag = sc.nextLine();
        }while(flag.equals("y")||flag.toUpperCase().equals("Y"));

    }

    public static void sortSpot(){
        Scanner sc = new Scanner(System.in);
        String flag, choice;
        StringBuilder sb = new StringBuilder();
        sb.append("1.按人气排序\n");
        sb.append("2.按岔路数排序\n");
        do{
            System.out.println(sb.toString());
            choice = sc.nextLine();
            if(choice.equals("1")){
                FileOperate.sortByPopulation();
            }else if(choice.equals(("2"))){
                FileOperate.sortByVertex();
            }else{
                System.out.println("无效的输入!");
            }
            System.out.println("是否继续查询（Y/N）：");
            flag = sc.nextLine();
        }while(flag.equals("y")||flag.toUpperCase().equals("Y"));
    }


    public static void readInfo() {
        FileOperate.returnInfo();
        System.out.println("输入0返回主菜单");
        Scanner sc = new Scanner(System.in);
        int back = sc.nextInt();
        sc.nextLine();
        if(back == 0){
            return;
        }
    }

}
