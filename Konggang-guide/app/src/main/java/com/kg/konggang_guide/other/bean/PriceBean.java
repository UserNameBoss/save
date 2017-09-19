package com.kg.konggang_guide.other.bean;

import java.util.List;

/**
 * @author wuwang
 * @Description
 * @email 1558183202@qq.com
 * @date 2017/8/9
 */

public class PriceBean {


    /**
     * data : [{"id":7,"orderId":"501039713606253111","uId":2,"mileage":16,"mileageMoney":0,"time":45,"timeFee":null,"startFee":0,"travelFee":0,"cityFee":0,"eveFee":0,"mileageCharge":2.5,"timeCharge":6,"packageFee":66,"serveiceCharge":0,"otherFee":null,"totalFee":null,"type":1},{"id":8,"orderId":"501039713606253111","uId":2,"mileage":16,"mileageMoney":41.6,"time":45,"timeFee":18,"startFee":13,"travelFee":1.2,"cityFee":0,"eveFee":0,"mileageCharge":0,"timeCharge":null,"packageFee":0,"serveiceCharge":0,"otherFee":null,"totalFee":73.8,"type":0}]
     * code : 200
     * msg : 获取预估价详情成功
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
         * id : 7
         * orderId : 501039713606253111
         * uId : 2
         * mileage : 16
         * mileageMoney : 0
         * time : 45
         * timeFee : null
         * startFee : 0
         * travelFee : 0
         * cityFee : 0
         * eveFee : 0
         * mileageCharge : 2.5
         * timeCharge : 6
         * packageFee : 66
         * serveiceCharge : 0
         * otherFee : null
         * totalFee : null
         * type : 1
         */

        public int id;
        public String orderId;
        public int uId;
        public float mileage;
        public float mileageMoney;
        public float time;
        public float timeFee;
        public float startFee;
        public float travelFee;
        public float cityFee;
        public float eveFee;
        public float mileageCharge;
        public float timeCharge;
        public float packageFee;
        public float serveiceCharge;
        public float otherFee;
        public float totalFee;
        public float totalMoney;
        public int type;
        public int calType;
        public float mile;
        public float pacTime;
        public float packages;
        public float overMile;
        public float overTime;
    }
}
