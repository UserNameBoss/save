package com.kg.konggang_guide.other.bean;

import java.util.List;

/**
 * @author wuwang
 * @Description
 * @email 1558183202@qq.com
 * @date 2017/8/7
 */

public class DriverListBean {


    /**
     * data : [{"id":1,"models":"宝马X5","uId":4,"licensePlate":"粤B25684","terminalBuilding":"宝安机场F1航站楼","parkPosition":"第1停车位","status":3,"driverName":null}]
     * code : 200
     * msg : 司机列表
     * totalPage : null
     * totalRecord : null
     * pageNo : null
     */

    public int code;
    public String msg;
    public Object totalPage;
    public Object totalRecord;
    public Object pageNo;
    public List<DataEntity> data;

    public static class DataEntity {
        /**
         * id : 1
         * models : 宝马X5
         * uId : 4
         * licensePlate : 粤B25684
         * terminalBuilding : 宝安机场F1航站楼
         * parkPosition : 第1停车位
         * status : 3
         * driverName : null
         */

        public int id;
        public String models;
        public int uId;
        public String licensePlate;
        public String terminalBuilding;
        public String parkPosition;
        public int status;
        public String driverName;
        public String picture;
        public String driverPhone;
    }
}
