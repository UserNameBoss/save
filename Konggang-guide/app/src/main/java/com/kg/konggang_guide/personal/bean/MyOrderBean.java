package com.kg.konggang_guide.personal.bean;

import java.util.List;

/**
 * @author wuwang
 * @Description
 * @email 1558183202@qq.com
 * @date 2017/8/9
 */

public class MyOrderBean {


    /**
     * data : {"list1":[{"number":2,"month":7},{"number":1,"month":8}],"list2":[{"number":1,"month":7}]}
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
        public List<List1Entity> list1;
        public List<List2Entity> list2;

        public static class List1Entity {
            /**
             * number : 2
             * month : 7
             */

            public int number;
            public int month;
        }

        public static class List2Entity {
            /**
             * number : 1
             * month : 7
             */

            public int number;
            public int month;
        }
    }
}
