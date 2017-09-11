package com.kg.konggang_guide.other.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * @author wuwang
 * @Description
 * @email 1558183202@qq.com
 * @date 2017/8/25
 */

@DatabaseTable(tableName = "kg_address_from")
public class AddressFromBean {

    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(columnName = "user_id")
    private String userId;
    @DatabaseField(columnName = "address")
    private String address;
    @DatabaseField(columnName = "airId")
    private String airId;
    @DatabaseField(columnName = "cityName")
    private String cityName;
    @DatabaseField(columnName = "cityId")
    private int cityId;

    public AddressFromBean() {
    }

    public AddressFromBean(String userId, String name, String cityName, int cityId, double latitude, double longitude) {
    }

    public AddressFromBean(String userId, String address, String airId, String cityName, int cityId) {
        this.userId = userId;
        this.address = address;
        this.airId = airId;
        this.cityName = cityName;
        this.cityId = cityId;
    }

    public int getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public String getAirId() {
        return airId;
    }

    public String getCityName() {
        return cityName;
    }

    public int getCityId() {
        return cityId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAirId(String airId) {
        this.airId = airId;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }
}
