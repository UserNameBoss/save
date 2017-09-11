package com.kg.konggang_guide.other.bean;

import java.util.List;

/**
 * @author wuwang
 * @Description
 * @email 1558183202@qq.com
 * @date 2017/8/9
 */

public class OrderBean {


    /**
     * data : {"pageNum":1,"pageSize":10,"size":1,"startRow":1,"endRow":1,"total":1,"pages":1,"list":[{"id":247,"orderId":null,"uId":null,"onTime":null,"arriedTime":null,"departureLocation":null,"arrivedLocation":null,"departurePosition":null,"arrivedPosition":null,"city":null,"airport":null,"terminalBuilding":null,"name":null,"telephone":null,"orderName":null,"orderPhone":null,"isSend":0,"carType":null,"estimatePrice":0,"type":null,"totalPrice":0,"createTime":null,"flightNumber":null,"flightArrived":null,"upTime":null,"status":1,"payStatus":0,"commentStatus":0,"dirberId":0,"guideId":0,"isDeleted":0,"mileage":null,"payType":0,"orderChannel":1,"serviceStartTime":null,"serviceEndTime":null,"driverPhone":null,"carNum":null,"guidePhone":null,"times":null}],"prePage":0,"nextPage":0,"isFirstPage":true,"isLastPage":true,"hasPreviousPage":false,"hasNextPage":false,"navigatePages":8,"navigatepageNums":[1],"navigateFirstPage":1,"navigateLastPage":1,"lastPage":1,"firstPage":1}
     * code : 200
     * msg : 订单列表
     * totalPage : null
     * totalRecord : null
     * pageNo : null
     */

    public DataEntity data;
    public int code;
    public String msg;
    public Object totalPage;
    public Object totalRecord;
    public Object pageNo;

    public static class DataEntity {
        /**
         * pageNum : 1
         * pageSize : 10
         * size : 1
         * startRow : 1
         * endRow : 1
         * total : 1
         * pages : 1
         * list : [{"id":247,"orderId":null,"uId":null,"onTime":null,"arriedTime":null,"departureLocation":null,"arrivedLocation":null,"departurePosition":null,"arrivedPosition":null,"city":null,"airport":null,"terminalBuilding":null,"name":null,"telephone":null,"orderName":null,"orderPhone":null,"isSend":0,"carType":null,"estimatePrice":0,"type":null,"totalPrice":0,"createTime":null,"flightNumber":null,"flightArrived":null,"upTime":null,"status":1,"payStatus":0,"commentStatus":0,"dirberId":0,"guideId":0,"isDeleted":0,"mileage":null,"payType":0,"orderChannel":1,"serviceStartTime":null,"serviceEndTime":null,"driverPhone":null,"carNum":null,"guidePhone":null,"times":null}]
         * prePage : 0
         * nextPage : 0
         * isFirstPage : true
         * isLastPage : true
         * hasPreviousPage : false
         * hasNextPage : false
         * navigatePages : 8
         * navigatepageNums : [1]
         * navigateFirstPage : 1
         * navigateLastPage : 1
         * lastPage : 1
         * firstPage : 1
         */

        public int pageNum;
        public int pageSize;
        public int size;
        public int startRow;
        public int endRow;
        public int total;
        public int pages;
        public int prePage;
        public int nextPage;
        public boolean isFirstPage;
        public boolean isLastPage;
        public boolean hasPreviousPage;
        public boolean hasNextPage;
        public int navigatePages;
        public int navigateFirstPage;
        public int navigateLastPage;
        public int lastPage;
        public int firstPage;
        public List<ListEntity> list;
        public List<Integer> navigatepageNums;

        public static class ListEntity {
            /**
             * id : 247
             * orderId : null
             * uId : null
             * onTime : null
             * arriedTime : null
             * departureLocation : null
             * arrivedLocation : null
             * departurePosition : null
             * arrivedPosition : null
             * city : null
             * airport : null
             * terminalBuilding : null
             * name : null
             * telephone : null
             * orderName : null
             * orderPhone : null
             * isSend : 0
             * carType : null
             * estimatePrice : 0.0
             * type : null
             * totalPrice : 0.0
             * createTime : null
             * flightNumber : null
             * flightArrived : null
             * upTime : null
             * status : 1
             * payStatus : 0
             * commentStatus : 0
             * dirberId : 0
             * guideId : 0
             * isDeleted : 0
             * mileage : null
             * payType : 0
             * orderChannel : 1
             * serviceStartTime : null
             * serviceEndTime : null
             * driverPhone : null
             * carNum : null
             * guidePhone : null
             * times : null
             */

            public int id;
            public String orderId;
            public String uId;
            public long onTime;
            public String arriedTime;
            public String departureLocation;
            public String arrivedLocation;
            public String departurePosition;
            public String arrivedPosition;
            public String city;
            public String airport;
            public String terminalBuilding;
            public String name;
            public String telephone;
            public String orderName;
            public String orderPhone;
            public int isSend;
            public Object carType;
            public double estimatePrice;
            public int type;
            public float totalPrice;
            public String createTime;
            public String flightNumber;
            public String flightArrived;
            public long upTime;
            public int status;
            public int payStatus;
            public int commentStatus;
            public int dirberId;
            public int guideId;
            public int isDeleted;
            public float mileage;
            public int payType;
            public int orderChannel;
            public String serviceStartTime;
            public String serviceEndTime;
            public String driverPhone;
            public String carNum;
            public String guidePhone;
            public String times;
        }
    }
}
