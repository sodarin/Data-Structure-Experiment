package parking;


import fileUtil.FileOperate;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by ZhangHaodong on 2017/9/7.
 */
public class ParkingLot {

    private static Stack<Car> parkCars = new Stack<Car>();
    private static Queue<Car> waitCars = new LinkedList<Car>();
    private static Map<String, Integer> plateNumbers = new HashMap<String, Integer>();
    private static Stack<Car> tempCars = new Stack<Car>();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd: HH:mm:ss");
    StringBuilder content = new StringBuilder();

    public ParkingLot(){}

    public void mainMenu(){
        StringBuilder sb = new StringBuilder();
        sb.append("1.车辆停放登记\n");
        sb.append("2.车辆离开登记\n");
        sb.append("3.检查所有停放车辆\n");
        sb.append("4.查询历史记录\n");
        sb.append("5.退出\n");
        Scanner sc = new Scanner(System.in);
        System.out.println(sb.toString());
        String in = sc.nextLine();
        switch (in){
            case "1":
                System.out.println("输入停放车辆的车牌");
                String plateNumber = sc.nextLine();
                this.parkCar(plateNumber);
                this.mainMenu();
                break;
            case "2":
                System.out.println("输入离开车辆的车牌");
                plateNumber = sc.nextLine();
                this.leavingCar(plateNumber);
                this.mainMenu();
                break;
            case "3":
                this.printAllParkingCars();
                this.mainMenu();
                break;
            case "4":
                this.readRecord();
                this.mainMenu();
                break;
            case "5":
                return;
            default:
                System.out.println("请重新输入！");
                this.mainMenu();
                break;
        }
    }

    public boolean isParkingAll(){
        if(this.parkCars.size() == 5)
            return true;
        else
            return false;
    }

    public boolean isWaitEmpty(){
        if(this.waitCars.isEmpty())
            return true;
        else
            return false;
    }

    public String parkCar(String plateNumber){
        String str = "";
        if(this.isParkingAll()){
            this.waitCars.add(new Car(plateNumber));
            System.out.println("车牌号："+plateNumber+"进入便道等待\n");
            str = "0";
        }else{
            Car car = new Car(plateNumber, new Date());
            if(this.plateNumbers.get(car.getPlateNumber())==null){
                this.parkCars.add(car);
                str = sdf.format(car.getParkTime());
                str += "\n"+car.getPlateNumber()+"进入停车场";
                System.out.println(car.getPlateNumber()+"进入停车场");
                System.out.println();
                this.plateNumbers.put(car.getPlateNumber(), 1);
                content.append(sdf.format(car.getParkTime())+"\n");
                content.append(car.getPlateNumber()+"进入停车场"+"\n");
                content.append("\n");
                FileOperate.writeParkingRecord(content.toString());
            }else{
                str = "-1";
                System.out.println("车牌号重复！");
            }
        }
        return str;
    }

    public String leavingCar(String plateNumber) {
        String str = "";
        while(!this.parkCars.isEmpty()&&!this.parkCars.peek().getPlateNumber().equals(plateNumber)){
            this.tempCars.add(this.parkCars.pop());
        }
        if(this.parkCars.isEmpty()){
            while(!this.tempCars.isEmpty()){
                this.parkCars.add(this.tempCars.pop());
            }
            str = "-1";
            return str;
        }else{
            Car leaveCar = this.parkCars.pop();
            this.plateNumbers.remove(leaveCar.getPlateNumber());
            leaveCar.setLeaveTime(new Date());
            str = leaveCar.getCost();
            System.out.println(leaveCar.getCost());
            System.out.println();
            content.append(sdf.format(leaveCar.getLeaveTime())+"\n");
            content.append(leaveCar.getCost()+"\n");
            FileOperate.writeParkingRecord(content.toString());
            while(!this.tempCars.isEmpty()){
                this.parkCars.add(this.tempCars.pop());
            }
            if(!isWaitEmpty()){
                Car car = this.waitCars.poll();
                car.setParkTime(new Date());
                str += "";
                if(this.plateNumbers.get(car.getPlateNumber())==null){
                    this.parkCars.add(car);
                    str += car.getParkTime()+"\n"+car.getPlateNumber()+"进入停车场";
                    System.out.println(car.getPlateNumber()+"进入停车场");
                    System.out.println();
                    this.plateNumbers.put(car.getPlateNumber(),1);
                    content.append(sdf.format(car.getParkTime())+"\n");
                    content.append(car.getPlateNumber()+"进入停车场"+"\n");
                    FileOperate.writeParkingRecord(content.toString());
                }else{
                    str += "-1";
                    System.out.println("车牌号重复");
                }
            }
            return str;
        }
    }

    public String printAllParkingCars() {
        String str = "";
        for(Car car: this.parkCars){
            str += car.getPlateNumber()+ ":"+car.getParkTime()+"\n";
            System.out.println("车牌号："+car.getPlateNumber());
            System.out.println("进入时间："+sdf.format(new Date()));
        }
        return str;
    }

    public void readRecord() {
        FileOperate.readParkingRecord();
        System.out.println();
        System.out.println("输入0退出");
        Scanner sc = new Scanner(System.in);
        int read = sc.nextInt();
        sc.nextLine();
        if(read == 0)
            return;
    }
}
