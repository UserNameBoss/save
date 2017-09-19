package com.kg.konggang_guide.personal;

import android.text.TextUtils;

import com.kg.konggang_guide.AppSet;
import com.kg.konggang_guide.other.utils.ShareUtils;

/**
 * @author wuwang
 * @Description
 * @email 1558183202@qq.com
 * @date 2017/8/7
 */

public class AppState {


    private static AppState appState;
    private String id;
    private String userAccount;
    private String name;
    private String telephone;
    private String password;
    private String identity;
    private String picture;
    private String cityId;



    private boolean isLogin;


    public static AppState getInstance() {
        if (appState == null) {
            synchronized (AppState.class) {
                if (appState == null) {
                    appState = new AppState();
                }
            }
        }
        return appState;
    }

    public boolean isLogin(){
        return ShareUtils.getInstance().getFlag(AppSet.FLAG_ISLOGIN,false);
    }

    public void setLogin(boolean login) {
        this.isLogin = login;
        ShareUtils.getInstance().setFlag(AppSet.FLAG_ISLOGIN, login);
    }

    public void setUserId(String userId){
        ShareUtils.getInstance().setCache(AppSet.FLAR_USERID,userId);
    }
    public String getUserId(){
        return ShareUtils.getInstance().getCache(AppSet.FLAR_USERID);
    }

    public void setUserPhone(String phone){
        ShareUtils.getInstance().setCache(AppSet.FLAG_USERPHONE,phone);
    }

    public String getUserPhone(){
        return ShareUtils.getInstance().getCache(AppSet.FLAG_USERPHONE);
    }

    public void setPassword(String password){
        ShareUtils.getInstance().setCache(AppSet.FLAG_PWD,password);
    }

    public String getPassword(){
        return ShareUtils.getInstance().getCache(AppSet.FLAG_PWD);
    }

    public void setName(String name){
        ShareUtils.getInstance().setCache(AppSet.FLAG_USERNAME,name);
    }

    public String getName(){
        return ShareUtils.getInstance().getCache(AppSet.FLAG_USERNAME);
    }

    public void setAccount(String account){
        ShareUtils.getInstance().setCache(AppSet.FLAG_ACCOUNT,account);
    }

    public String getAccount(){
        return ShareUtils.getInstance().getCache(AppSet.FLAG_ACCOUNT);
    }

    public void setIdentity(String identity){
        ShareUtils.getInstance().setCache(AppSet.FLAG_IDENTITY,identity);
    }

    public String getIdentity(){
        return ShareUtils.getInstance().getCache(AppSet.FLAG_IDENTITY);
    }

    public void setPicture(String picture){
        ShareUtils.getInstance().setCache(AppSet.FLAG_PICTURE,picture);
    }

    public String getPicture(){
        return  ShareUtils.getInstance().getCache(AppSet.FLAG_PICTURE);
    }

    public void setSex(int sex){
        if(sex==0) {
            ShareUtils.getInstance().setCache(AppSet.FLAG_SEX,"男");
        }else{
            ShareUtils.getInstance().setCache(AppSet.FLAG_SEX,"女");
        }
    }

    public String getSex(){
        return ShareUtils.getInstance().getCache(AppSet.FLAG_SEX);
    }
    public void setOlCityCode(int cityCode){
        ShareUtils.getInstance().setCache(AppSet.OLCITYCODE,cityCode);
    }
    public int getOlCityCode(){
        return ShareUtils.getInstance().getCache(AppSet.OLCITYCODE,0);
    }

    public void setOlCity(String city){
        ShareUtils.getInstance().setCache(AppSet.OLCITY,city);
    }
    public String getOlCity(){
        return ShareUtils.getInstance().getCache(AppSet.OLCITY);
    }
    public void setLocationCity(String city){
        if(!TextUtils.isEmpty(city)) {
            ShareUtils.getInstance().setCache(AppSet.LOCATION_CITY, city);
        }
    }

    public String getLocaitonCity(){
        return ShareUtils.getInstance().getCache(AppSet.LOCATION_CITY);
    }

    public void setCityId(String cityId){
        if(!TextUtils.isEmpty(cityId)) {
            ShareUtils.getInstance().setCache(AppSet.FLAG_CITYID, cityId);
        }
    }
    public String getCityId(){
        return ShareUtils.getInstance().getCache(AppSet.FLAG_CITYID);
    }

    public void setAirId(String airId){
        if(!TextUtils.isEmpty(cityId)) {
            ShareUtils.getInstance().setCache(AppSet.FLAG_CITYID, cityId);
        }
    }

    public String getAirId(){
        return ShareUtils.getInstance().getCache(AppSet.FLAG_AIRID);
    }
}
