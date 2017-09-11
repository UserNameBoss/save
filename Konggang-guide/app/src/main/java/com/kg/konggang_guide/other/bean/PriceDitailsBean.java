package com.kg.konggang_guide.other.bean;

import java.util.List;

/**
 * @author wuwang
 * @Description
 * @email 1558183202@qq.com
 * @date 2017/8/9
 */

public class PriceDitailsBean {


    /**
     * data : [{"id":4,"ruleName":"深普","city":1,"cityName":"深圳","serviceType":"1,2","server":null,"carType":1,"packagePrice":null,"packages":null,"commonTime":null,"commonMileage":null,"mileagePrice":2.3,"milePrice":null,"timePrice":2.3,"time":null,"overMileage":null,"overMilePrice":null,"overTime":null,"overTimePrice":null,"farAwayPrice":2.3,"farPrice":null,"outTownPrice":2.3,"outPrice":null,"nightPrice":2.3,"night":null,"startPrice":15,"start":null,"type":2}]
     * code : 200
     * msg : 计价规则详情获取成功
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
         * id : 4
         * ruleName : 深普
         * city : 1
         * cityName : 深圳
         * serviceType : 1,2
         * server : null
         * carType : 1
         * packagePrice : null
         * packages : null
         * commonTime : null
         * commonMileage : null
         * mileagePrice : 2.3
         * milePrice : null
         * timePrice : 2.3
         * time : null
         * overMileage : null
         * overMilePrice : null
         * overTime : null
         * overTimePrice : null
         * farAwayPrice : 2.3
         * farPrice : null
         * outTownPrice : 2.3
         * outPrice : null
         * nightPrice : 2.3
         * night : null
         * startPrice : 15.0
         * start : null
         * type : 2
         */

        public int id;
        public String ruleName;
        public int city;
        public String cityName;
        public String serviceType;
        public Object server;
        public int carType;
        public Object packagePrice;
        public Object packages;
        public String commonTime;
        public Object commonMileage;
        public double mileagePrice;
        public Object milePrice;
        public double timePrice;
        public Object time;
        public Object overMileage;
        public Object overMilePrice;
        public Object overTime;
        public Object overTimePrice;
        public double farAwayPrice;
        public Object farPrice;
        public double outTownPrice;
        public Object outPrice;
        public double nightPrice;
        public Object night;
        public double startPrice;
        public Object start;
        public int type;
    }
}
