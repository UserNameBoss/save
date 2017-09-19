package com.kg.konggang_guide.other.bean;

/**
 * @author wuwang
 * @Description
 * @email 1558183202@qq.com
 * @date 2017/8/7
 */

public class LoginBean {


    /**
     * data : {"id":5,"userAccount":"0","name":"hh","telephone":"18565860212","password":null,"sex":1,"picture":"http://120.25.237.198:8810//konggang//file//201708011682028.jpg","email":"157647900@qq.m","identity":2,"billingLimit":10,"account":null,"score":null,"dracc":null}
     * code : 200
     * msg : 登录成功
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
         * id : 5
         * userAccount : 0
         * name : hh
         * telephone : 18565860212
         * password : null
         * sex : 1
         * picture : http://120.25.237.198:8810//konggang//file//201708011682028.jpg
         * email : 157647900@qq.m
         * identity : 2
         * billingLimit : 10
         * account : null
         * score : null
         * dracc : null
         */

        public int id;
        public String userAccount;
        public String name;
        public String telephone;
        public Object password;
        public int sex;
        public String picture;
        public String email;
        public int identity;
        public int billingLimit;
        public Object account;
        public Object score;
        public Object dracc;
        public String cityId;
        public String airId;
    }
}
