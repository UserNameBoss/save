package com.kg.konggang_guide;

/**
 * @author wuwang
 * @Description
 * @email 1558183202@qq.com
 * @date 2017/8/7
 */

public class Http {

    private Http() {
    }
    public static final String ROOT = "http://120.25.237.198:8810/api/";//正式服务地址
    //public static final String ROOT = "http://39.108.239.116:8080/api/";//客户服务地址

    //public static final String ROOT = "http://192.168.1.26:8810/api/";//正式服务地址

    /**
     * 启动页
     */
    public static final String STARTPAGE = ROOT+"home/getPicture";
    /**
     * 登录
     */
    public static final String LOGIN=ROOT+"guide/login";

    /**
     * 司机列表
     */
    public static final String DRIVERLIST=ROOT+"guide/driverList";

    /**
     * 改派
     */
    public static final String CHANGEGUIDE=ROOT+"guide/changeGuide";

    /**
     * 接单
     */
    public static final String GETORDERS=ROOT+"guide/getOrders";

    /**
     * 计算预估价
     */
    public static final String IMMEDIATELYCACULATEPRICE=ROOT+"home/caculatePrice";

    /**
     * 获取城市列表
     */
    public static final String CITYLIST=ROOT+"home/cityList";

    /**
     * 地址搜索
     */
    public static final String SEARCHADDRESS=ROOT+"home/searchAddress";

    /**
     * 消息中心
     */
    public static final String MESSAGECENTER=ROOT+"guide/messageCenter";

    /**
     * 我的订单
     */
    public static final String MYORDER=ROOT+"guide/myOrder";

    /**
     * 待接单 待服务列表
     */
    public static final String SERVICEORDER=ROOT+"guide/serviceOrder";

    /**
     * 获取验证码
     */
    public static final String LOGINCODE=ROOT+"home/loginCode";

    /**
     * 忘记密码
     */
    public static final String FORGETPASSWORD=ROOT+"driverHome/forgetPassword";

    /**
     * 派车
     */
    public static final String SENTCAER=ROOT+"guide/sentCaer";

    /**
     * 计价规则详情
     */
    public static final String CACULATEDETAIL=ROOT+"reservation/caculateDetail";


    /**
     * 价格预估
     */
    public static final String CACULATEPRICEDETAIL=ROOT+"reservation/caculatePriceDetail";

    /**
     * 修改密码
     */
    public static final String MODIFYPASSWORD=ROOT+"guide/modifyPassword";

    /**
     * 消息未读数量
     */
    public static final String MESSAGENOREAD=ROOT+"driverHome/messageNoRead";

    /**
     * 立即接机,确认下单
     */
    public static final String IMMEDIATELYORDER=ROOT+"guide/immediatelyOrder";


}
