package com.kg.konggang_guide.other.bean;

import java.util.List;

/**
 * @author wuwang
 * @Description
 * @email 1558183202@qq.com
 * @date 2017/8/8
 */

public class AddressBean {


    /**
     * data : [{"id":1,"cityId":1,"airportName":"宝安国际机场","airportAddress":"深圳市宝安大道","upTime":1501759656000,"terminalBuilding":"T1航站楼,T2航站楼,T3航站楼","fileName":"fewfew","fileUrl":"fefewd","reservationName":"dsfvsf","reservationUrl":"sddsfcsd","telephone":"75523456789","createTime":1501759618000}]
     * code : 200
     * msg : 机场列表
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
         * cityId : 1
         * airportName : 宝安国际机场
         * airportAddress : 深圳市宝安大道
         * upTime : 1501759656000
         * terminalBuilding : T1航站楼,T2航站楼,T3航站楼
         * fileName : fewfew
         * fileUrl : fefewd
         * reservationName : dsfvsf
         * reservationUrl : sddsfcsd
         * telephone : 75523456789
         * createTime : 1501759618000
         */

        public int id;
        public int cityId;
        public String airportName;
        public String airportAddress;
        public long upTime;
        public String terminalBuilding;
        public String fileName;
        public String fileUrl;
        public String reservationName;
        public String reservationUrl;
        public String telephone;
        public long createTime;
    }
}
