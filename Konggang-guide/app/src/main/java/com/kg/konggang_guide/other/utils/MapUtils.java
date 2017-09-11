package com.kg.konggang_guide.other.utils;

import android.content.Context;

import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.model.LatLng;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.geocoder.GeocodeAddress;
import com.amap.api.services.geocoder.GeocodeQuery;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeAddress;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.amap.api.services.help.Inputtips;
import com.amap.api.services.help.InputtipsQuery;
import com.amap.api.services.route.BusRouteResult;
import com.amap.api.services.route.DrivePath;
import com.amap.api.services.route.DriveRouteResult;
import com.amap.api.services.route.RideRouteResult;
import com.amap.api.services.route.RouteSearch;
import com.amap.api.services.route.WalkRouteResult;

import java.util.List;

/**
 * @author wuwang
 * @Description
 * @email 1558183202@qq.com
 * @date 2017/8/7
 */

public class MapUtils {

    //出发地和目的地的坐标
    private LatLonPoint latLonPointFrom,latLonPointTo;
    private Context context;
    private RouteSearch routeSearch;
    private GeocodeSearch geocodeSearch;

    //预算的总距离
    private float tollDistance;
    //预算的总时间
    private String time;
    private String toCity;
    private String fromCity;
    //是否出城：0没有出城，1是出城
    private int isOut;
    private LatLng latLngFrom;
    //两个城市的中心距离
    private float distance;

    public OnResultData onResultData;

    public interface OnResultData{
        void setResult(float tollDistance,String time,int isOut,float distance);
    }


    public MapUtils(Context context) {
        this.context = context;
    }

    public void getPriceInfo(LatLonPoint latLonPointFrom, LatLonPoint latLonPointTo,OnResultData onResultData){
        this.latLonPointFrom = latLonPointFrom;
        this.latLonPointTo= latLonPointTo;
        this.onResultData=onResultData;
        getAddressInfo();
    }

    public void getAddressInfo(){
        routeSearch=new RouteSearch(context);
        routeSearch.setRouteSearchListener(new RouteSearch.OnRouteSearchListener() {
            @Override
            public void onBusRouteSearched(BusRouteResult busRouteResult, int i) {

            }

            @Override
            public void onDriveRouteSearched(DriveRouteResult driveRouteResult, int i) {
                if(i==1000){
                    List<DrivePath> listPath=driveRouteResult.getPaths();
                    if(listPath!=null&&listPath.size()>0){
                        DrivePath drivePath=listPath.get(0);
                        tollDistance=drivePath.getDistance()/1000;
                        time=drivePath.getDuration()/60+"";
                        getFromCityInfo();
                    }
                }
            }

            @Override
            public void onWalkRouteSearched(WalkRouteResult walkRouteResult, int i) {

            }

            @Override
            public void onRideRouteSearched(RideRouteResult rideRouteResult, int i) {

            }
        });

        RouteSearch.FromAndTo fromAndTo=new RouteSearch.FromAndTo(latLonPointFrom,latLonPointTo);
        RouteSearch.DriveRouteQuery query = new RouteSearch.DriveRouteQuery(fromAndTo,RouteSearch.DRIVING_MULTI_STRATEGY_FASTEST_SHORTEST,null, null, "");
        routeSearch.calculateDriveRouteAsyn(query);
    }


    public void getToCityInfo() {
        geocodeSearch=new GeocodeSearch(context);
        RegeocodeQuery query = new RegeocodeQuery(latLonPointTo,200,GeocodeSearch.AMAP);
        geocodeSearch.setOnGeocodeSearchListener(new GeocodeSearch.OnGeocodeSearchListener() {
            @Override
            public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {
                if(i==1000){
                    RegeocodeAddress regeocodeAddress = regeocodeResult.getRegeocodeAddress();
                    toCity = regeocodeAddress.getCity();
                    if(fromCity.equals(toCity)){
                        //没有出城
                        isOut=0;
                        onResultData.setResult(tollDistance,time,isOut,distance);
                    }else{
                        //出城了
                        isOut=1;
                        GeocodeQuery queryFrom = new GeocodeQuery(fromCity,fromCity);
                        geocodeSearch.setOnGeocodeSearchListener(new GeocodeSearch.OnGeocodeSearchListener() {
                            @Override
                            public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {

                            }

                            @Override
                            public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {
                                List<GeocodeAddress> list=geocodeResult.getGeocodeAddressList();
                                if(list!=null&&list.size()>0){
                                    GeocodeAddress geocodeAddress=list.get(0);
                                    LatLonPoint latLonPoint=geocodeAddress.getLatLonPoint();
                                    final LatLng latLngTo=new LatLng(latLonPoint.getLatitude(),latLonPoint.getLongitude());
                                    GeocodeSearch geocodeSearch=new GeocodeSearch(context);
                                    GeocodeQuery query = new GeocodeQuery(toCity,toCity);
                                    getCityPoint(geocodeSearch, query, new GeocodeSearch.OnGeocodeSearchListener() {
                                        @Override
                                        public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {

                                        }

                                        @Override
                                        public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {
                                            if (i == 1000) {
                                                List<GeocodeAddress> list = geocodeResult.getGeocodeAddressList();
                                                if (list != null && list.size() > 0) {
                                                    GeocodeAddress geocodeAddress = list.get(0);
                                                    LatLonPoint latLonPoint = geocodeAddress.getLatLonPoint();
                                                    latLngFrom = new LatLng(latLonPoint.getLatitude(), latLonPoint.getLongitude());
                                                    distance = AMapUtils.calculateLineDistance(latLngTo, latLngFrom) / 1000;
                                                    onResultData.setResult(tollDistance,time,isOut,distance);
                                                }
                                            }
                                        }
                                    });
                                }
                            }
                        });
                        geocodeSearch.getFromLocationNameAsyn(queryFrom);

                    }
                }
            }

            @Override
            public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {

            }
        });
        geocodeSearch.getFromLocationAsyn(query);
    }


    //出发点的城市信息
    public void getFromCityInfo(){
        geocodeSearch=new GeocodeSearch(context);
        RegeocodeQuery query = new RegeocodeQuery(latLonPointTo,200,GeocodeSearch.AMAP);
        geocodeSearch.setOnGeocodeSearchListener(new GeocodeSearch.OnGeocodeSearchListener() {
            @Override
            public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {
                if(i==1000){
                    RegeocodeAddress regeocodeAddress = regeocodeResult.getRegeocodeAddress();
                    fromCity = regeocodeAddress.getCity();
                    getToCityInfo();
                }
            }

            @Override
            public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {

            }
        });
        geocodeSearch.getFromLocationAsyn(query);
    }



    //将坐标转换成地址信息
    public void GetgeocoderSearch(GeocodeSearch geocodeSearch, RegeocodeQuery query, GeocodeSearch.OnGeocodeSearchListener onGeocodeSearchListener){
        geocodeSearch.setOnGeocodeSearchListener(onGeocodeSearchListener);
        geocodeSearch.getFromLocationAsyn(query);
    }

    //得到城市的坐标
    public void getCityPoint(GeocodeSearch geocodeSearch, GeocodeQuery query, GeocodeSearch.OnGeocodeSearchListener onGeocodeSearchListener){
        geocodeSearch.setOnGeocodeSearchListener(onGeocodeSearchListener);
        geocodeSearch.getFromLocationNameAsyn(query);
    }


    //搜索地址
    public void searchAddress(String name,String city,Inputtips.InputtipsListener inputtipsListener){
        InputtipsQuery inputquery = new InputtipsQuery(name,city);
        inputquery.setCityLimit(true);
        Inputtips inputTips = new Inputtips(context,inputquery);
        inputTips.setInputtipsListener(inputtipsListener);
        inputTips.requestInputtipsAsyn();
    }
}
