package com.kg.konggang_guide.other.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/12/14
 */
public class SplashBean {

    /**
     * data : [{"id":21,"title":"出行启动页","picture":"http://39.108.51.176:8080//konggang//file//506593480793815932.png","url":"","type":2,"status":1,"createTime":1506593485000,"position":null}]
     * code : 200
     * msg : 广告列表
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
         * id : 21
         * title : 出行启动页
         * picture : http://39.108.51.176:8080//konggang//file//506593480793815932.png
         * url :
         * type : 2
         * status : 1
         * createTime : 1506593485000
         * position : null
         */

        public int id;
        public String title;
        public String picture;
        public String url;
        public int type;
        public int status;
        public long createTime;
        public Object position;

        public int getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public String getPicture() {
            return picture;
        }

        public String getUrl() {
            return url;
        }

        public int getType() {
            return type;
        }

        public int getStatus() {
            return status;
        }

        public long getCreateTime() {
            return createTime;
        }

        public Object getPosition() {
            return position;
        }
    }
}
