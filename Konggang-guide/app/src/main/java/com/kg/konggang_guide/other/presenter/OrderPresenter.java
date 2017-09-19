package com.kg.konggang_guide.other.presenter;

import android.app.Activity;

import com.kg.konggang_guide.AppConstants;
import com.kg.konggang_guide.other.base.CpBasePresenter;
import com.kg.konggang_guide.other.base.IView;
import com.kg.konggang_guide.other.base.OnBaseDataListener;
import com.kg.konggang_guide.other.bean.CityFromBean;
import com.kg.konggang_guide.other.bean.SearchAddressBean;
import com.kg.konggang_guide.other.module.OrderModule;
import com.kg.konggang_guide.other.view.IOrderView;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author wuwang
 * @Description
 * @email 1558183202@qq.com
 * @date 2017/8/7
 */

public class OrderPresenter extends CpBasePresenter {

    private OrderModule orderModule;
    private IOrderView iOrderView;
    private Activity activity;
    public OrderPresenter(IView iView) {
        super(iView);
        activity= (Activity) iView;
        this.orderModule=new OrderModule((Activity) iView);
        this.iOrderView= (IOrderView) iView;
    }

    public void getPrice(){
        orderModule.requestServerDataOne(new OnBaseDataListener<String>() {
            @Override
            public void onNewData(String data) {
                try {
                    JSONObject jsonObject=new JSONObject(data);
                    if(jsonObject.getInt(AppConstants.CODE)==AppConstants.SUCCESS_CODE){
                        JSONObject jsonObject1=jsonObject.getJSONObject(AppConstants.DATA);
                        iOrderView.setOrderId(jsonObject1.getString("orderId"),jsonObject1.getInt("type"));
                        iOrderView.setPrice(jsonObject1.getString("price"));
                    }else{
                        iOrderView.setOrderId("",-1);
                        iOrderView.showToask(jsonObject.getString(AppConstants.MSG));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(String code) {
                iOrderView.setOrderId("",-1);
                iOrderView.showToask(code);
            }
        },iOrderView.getGuideId(),iOrderView.getMileage(),iOrderView.getTime(),iOrderView.getIsOut(),iOrderView.getOutMile(),iOrderView.getCityCode());
    }


    //获取上车城市
    public void getCity() {
        orderModule.getCityModule(new OnBaseDataListener<CityFromBean>() {
            @Override
            public void onNewData(CityFromBean data) {
                if (!activity.isDestroyed()) {
                    if (data.code == AppConstants.SUCCESS_CODE) {
                        iOrderView.setCityFrom(data);
                    } else {
                        iOrderView.showToask(data.msg);
                    }
                }
            }

            @Override
            public void onError(String code) {
                if (!activity.isDestroyed()) {
                    iOrderView.showToask(code);
                }
            }
        });
    }

    public void getAddress(){
        orderModule.getSearchAddress(new OnBaseDataListener<SearchAddressBean>() {
            @Override
            public void onNewData(SearchAddressBean data) {
                if(!activity.isDestroyed()) {
                    if (data.code == AppConstants.SUCCESS_CODE) {
                        iOrderView.setAddress(data);
                    } else {

                    }
                }
            }

            @Override
            public void onError(String code) {
            }
        },iOrderView.getCityCode());
    }


    //确认下单
    public void confirmOrder(){
        orderModule.requestServerDataTwo(new OnBaseDataListener<String>() {
           @Override
           public void onNewData(String data) {
               try {
                   if(!activity.isDestroyed()) {
                       JSONObject jsonObject = new JSONObject(data);
                       if (jsonObject.getInt(AppConstants.CODE) == AppConstants.SUCCESS_CODE) {
                           iOrderView.isOrderSuccess();
                           //有未支付的订单
                       } else {
                           iOrderView.showToask(jsonObject.getString(AppConstants.MSG));
                       }
                   }
               } catch (JSONException e) {
                   e.printStackTrace();
               }
           }

           @Override
           public void onError(String code) {
               if(!activity.isDestroyed()) {
                   iOrderView.showToask(code);
               }
           }
       },iOrderView.getOrderId(),iOrderView.getUserId(),iOrderView.getDepartureLocation(),iOrderView.getArrivedLocation(),
                iOrderView.getName(),iOrderView.getTelephone(),iOrderView.getIsSend(),iOrderView.getCarType(),
                iOrderView.getEstimatePrice(),iOrderView.getCity(),iOrderView.getAirport(),iOrderView.getTerminalBuilding(),
                iOrderView.getOTime(),iOrderView.getArriedTime(),iOrderView.getDeparturePosition(),
                iOrderView.getArrivedPosition(),iOrderView.getArriredTime(),iOrderView.getTime(),iOrderView.getMileage(),
                iOrderView.getGuideId(),iOrderView.getCityCode(),iOrderView.getAirId());
    }
}
