package parking;

import java.util.Date;

/**
 * Created by ZhangHaodong on 2017/9/7.
 */
public class Car {
    private String plateNumber;
    private Date parkTime;
    private Date LeaveTime;

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public Date getParkTime() {
        return parkTime;
    }

    public void setParkTime(Date parkTime) {
        this.parkTime = parkTime;
    }

    public Date getLeaveTime() {
        return LeaveTime;
    }

    public void setLeaveTime(Date leaveTime) {
        LeaveTime = leaveTime;
    }

    public Car(String plateNumber) {
        this.setPlateNumber(plateNumber);
    }

    public Car(String plateNumber, Date parkTime) {
        this.setPlateNumber(plateNumber);
        this.setParkTime(parkTime);
    }

    public String getCost() {
        long exp = this.getLeaveTime().getTime() - this.getParkTime().getTime();
        long days = exp / (1000*60*60*24);
        long hours = (exp - days * (24*60*60*1000)) / (1000*60*60);
        long minutes = (exp-days*(24*60*60*1000)-hours*(60*60*1000))/(1000*60);
        int cost = 0;
        if(days>0)
            cost = 20;
        else if(hours>3)
            cost = 10;
        else
            cost = 5;
        String str = "车牌号："+this.getPlateNumber()+"离开了停车场\n逗留了"+days+"天"+hours+"小时"+minutes+"分\n";
        str += "需缴费"+cost+"元";
        return str;
    }
}
