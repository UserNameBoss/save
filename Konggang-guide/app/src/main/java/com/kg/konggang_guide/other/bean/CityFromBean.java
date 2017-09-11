package com.kg.konggang_guide.other.bean;

import java.util.List;

/**
 * @author wuwang
 * @Description
 * @email 1558183202@qq.com
 * @date 2017/8/10
 */

public class CityFromBean {


    /**
     * data : [{"id":1,"cityName":"深圳","numbers":1,"isOpen":1,"createTime":1501759127000},{"id":2,"cityName":"长沙","numbers":1,"isOpen":1,"createTime":1501759171000}]
     * code : 200
     * msg : 城市列表
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
         * cityName : 深圳
         * numbers : 1
         * isOpen : 1
         * createTime : 1501759127000
         */

        public int id;
        public String cityName;
        public int numbers;
        public int isOpen;
        public long createTime;
    }
}
