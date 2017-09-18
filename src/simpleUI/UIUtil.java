package simpleUI;

import admin.AdminOperate;
import parking.ParkingLot;

import java.util.Scanner;

/**
 * Created by ZhangHaodong on 2017/9/1.
 */
public class UIUtil {

    public static void menu(){
        StringBuilder sb = new StringBuilder();
        sb.append("=================================\n");
        sb.append("|                               |\n");
        sb.append("|          景区管理系统         |\n");
        sb.append("|                               |\n");
        sb.append("=================================\n");
        sb.append("1.管理员登录\n");
        sb.append("2.输出景区景点分布图\n");
        sb.append("3.景点查找\n");
        sb.append("4.热门景点排序\n");
        sb.append("5.输出导游线路图\n");
        sb.append("6.查询景点最短路径\n");
        sb.append("7.查询通知通告\n");
        System.out.print(sb.toString());
    }



    public static void adminUI() throws Exception{
        boolean flag = true;
        StringBuilder sb = new StringBuilder();
        sb.append("=================================\n");
        sb.append("|                               |\n");
        sb.append("|          景区管理系统         |\n");
        sb.append("|                               |\n");
        sb.append("=================================\n");
        sb.append("1.景点更新\n");
        sb.append("2.景点删除\n");
        sb.append("3.道路更新\n");
        sb.append("4.道路删除\n");
        sb.append("5.发布通知通告\n");
        sb.append("6.停车场车辆进出信息记录\n");
        sb.append("7.退出登录\n");

        while(flag) {
            System.out.print(sb.toString());
            Scanner sc = new Scanner(System.in);
            int opr = sc.nextInt();
            sc.nextLine();
            switch (opr) {
                case 1:
                    AdminOperate.insertVertex();
                    break;
                case 2:
                    AdminOperate.removeVertex();
                    break;
                case 3:
                    AdminOperate.insertEdge();
                    break;
                case 4:
                    AdminOperate.removeEdge();
                    break;
                case 5:
                    AdminOperate.sendInfo();
                    break;
                case 6:
                    ParkingLot parkingLot = new ParkingLot();
                    parkingLot.mainMenu();
                    break;
                case 7:
                    flag = false;
                    menu();
                    break;
                default:
                    System.out.println("无效的操作！");
                    break;
            }
        }
    }
}
