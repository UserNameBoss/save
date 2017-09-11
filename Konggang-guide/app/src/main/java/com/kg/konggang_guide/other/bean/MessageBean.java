package com.kg.konggang_guide.other.bean;

import java.util.List;

/**
 * @author wuwang
 * @Description
 * @email 1558183202@qq.com
 * @date 2017/8/8
 */

public class MessageBean {


    /**
     * data : [{"id":13,"uId":5,"title":"预约接机","content":"胜多负少","publishTime":1502350344000,"orderId":"502173784736833566","isRead":1,"status":1,"guideId":5,"createTime":1502350344000,"type":null,"items":{"id":87,"orderId":"502173784736833566","uId":6,"onTime":null,"arriedTime":null,"departureLocation":"深圳宝安国际机场信息大楼停车场","arrivedLocation":"深圳航空漏2","departurePosition":null,"arrivedPosition":null,"city":"深圳","airport":"宝安国际机场","terminalBuilding":"第三航站楼","name":"自己乘车","telephone":"15712129220","orderName":null,"orderPhone":null,"isSend":0,"carType":1,"estimatePrice":335.56,"type":1,"totalPrice":336.23,"createTime":1502173797000,"flightNumber":null,"flightArrived":null,"upTime":null,"status":12,"payStatus":0,"commentStatus":0,"dirberId":14,"guideId":5,"isDeleted":1,"mileage":null,"payType":0,"orderChannel":1,"serviceStartTime":null,"serviceEndTime":null,"driverPhone":null,"carNum":null,"guidePhone":null,"times":null},"guide":{"id":5,"userAccount":"0","name":"hh","sex":1,"telephone":"18565860212","picture":"http://120.25.237.198:8810/images/KGZCIMG/1.png","identity":2,"models":null,"remark":0,"licensePlate":null}},{"id":14,"uId":5,"title":"改派订单","content":"个股","publishTime":1502350883000,"orderId":"12233","isRead":1,"status":1,"guideId":5,"createTime":1502350883000,"type":1,"items":{"id":3,"orderId":"12233","uId":2,"onTime":null,"arriedTime":null,"departureLocation":"深圳北站","arrivedLocation":"深圳南山地铁站","departurePosition":null,"arrivedPosition":null,"city":"深圳","airport":"宝安国际机场","terminalBuilding":"第三航站楼","name":"测试","telephone":"15198230725","orderName":null,"orderPhone":null,"isSend":0,"carType":1,"estimatePrice":10,"type":1,"totalPrice":20,"createTime":1501064052000,"flightNumber":"g231","flightArrived":1501064052000,"upTime":1501064052000,"status":4,"payStatus":1,"commentStatus":0,"dirberId":4,"guideId":5,"isDeleted":0,"mileage":100,"payType":1,"orderChannel":1,"serviceStartTime":1502173129000,"serviceEndTime":1502176739000,"driverPhone":null,"carNum":null,"guidePhone":null,"times":null},"guide":{"id":5,"userAccount":"0","name":"hh","sex":1,"telephone":"18565860212","picture":"http://120.25.237.198:8810/images/KGZCIMG/1.png","identity":2,"models":null,"remark":0,"licensePlate":null}}]
     * code : 200
     * msg : 消息列表
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
         * id : 13
         * uId : 5
         * title : 预约接机
         * content : 胜多负少
         * publishTime : 1502350344000
         * orderId : 502173784736833566
         * isRead : 1
         * status : 1
         * guideId : 5
         * createTime : 1502350344000
         * type : null
         * items : {"id":87,"orderId":"502173784736833566","uId":6,"onTime":null,"arriedTime":null,"departureLocation":"深圳宝安国际机场信息大楼停车场","arrivedLocation":"深圳航空漏2","departurePosition":null,"arrivedPosition":null,"city":"深圳","airport":"宝安国际机场","terminalBuilding":"第三航站楼","name":"自己乘车","telephone":"15712129220","orderName":null,"orderPhone":null,"isSend":0,"carType":1,"estimatePrice":335.56,"type":1,"totalPrice":336.23,"createTime":1502173797000,"flightNumber":null,"flightArrived":null,"upTime":null,"status":12,"payStatus":0,"commentStatus":0,"dirberId":14,"guideId":5,"isDeleted":1,"mileage":null,"payType":0,"orderChannel":1,"serviceStartTime":null,"serviceEndTime":null,"driverPhone":null,"carNum":null,"guidePhone":null,"times":null}
         * guide : {"id":5,"userAccount":"0","name":"hh","sex":1,"telephone":"18565860212","picture":"http://120.25.237.198:8810/images/KGZCIMG/1.png","identity":2,"models":null,"remark":0,"licensePlate":null}
         */

        public int id;
        public int uId;
        public String title;
        public String content;
        public long publishTime;
        public String orderId;
        public int isRead;
        public int status;
        public int guideId;
        public long createTime;
        public int type;
        public ItemsEntity items;
        public GuideEntity guide;

        public static class ItemsEntity {
            /**
             * id : 87
             * orderId : 502173784736833566
             * uId : 6
             * onTime : null
             * arriedTime : null
             * departureLocation : 深圳宝安国际机场信息大楼停车场
             * arrivedLocation : 深圳航空漏2
             * departurePosition : null
             * arrivedPosition : null
             * city : 深圳
             * airport : 宝安国际机场
             * terminalBuilding : 第三航站楼
             * name : 自己乘车
             * telephone : 15712129220
             * orderName : null
             * orderPhone : null
             * isSend : 0
             * carType : 1
             * estimatePrice : 335.56
             * type : 1
             * totalPrice : 336.23
             * createTime : 1502173797000
             * flightNumber : null
             * flightArrived : null
             * upTime : null
             * status : 12
             * payStatus : 0
             * commentStatus : 0
             * dirberId : 14
             * guideId : 5
             * isDeleted : 1
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
            public int uId;
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
            public Object orderName;
            public Object orderPhone;
            public int isSend;
            public int carType;
            public float estimatePrice;
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
            public Object mileage;
            public int payType;
            public int orderChannel;
            public String serviceStartTime;
            public String serviceEndTime;
            public String driverPhone;
            public int carNum;
            public String guidePhone;
            public String times;
        }

        public static class GuideEntity {
            /**
             * id : 5
             * userAccount : 0
             * name : hh
             * sex : 1
             * telephone : 18565860212
             * picture : http://120.25.237.198:8810/images/KGZCIMG/1.png
             * identity : 2
             * models : null
             * remark : 0.0
             * licensePlate : null
             */

            public int id;
            public String userAccount;
            public String name;
            public int sex;
            public String telephone;
            public String picture;
            public int identity;
            public String models;
            public double remark;
            public String licensePlate;
        }
    }
}
