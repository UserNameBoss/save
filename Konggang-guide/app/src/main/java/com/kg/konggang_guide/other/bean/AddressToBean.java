package com.kg.konggang_guide.other.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * @author wuwang
 * @Description
 * @email 1558183202@qq.com
 * @date 2017/8/25
 */

@DatabaseTable(tableName = "kg_address_to")
public class AddressToBean {

    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(columnName = "user_id")
    private String userId;
    @DatabaseField(columnName = "name")
    private String name;
    @DatabaseField(columnName = "city")
    private String city;
    @DatabaseField(columnName = "cityId")
    private int cityId;
    @DatabaseField(columnName = "latitude")
    private double latitude;
    @DatabaseField(columnName = "longitude")
    private double longitude;

    public AddressToBean() {
    }

    public AddressToBean(int id, String userId, String name, String city, int cityId, double latitude, double longitude) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.city = city;
        this.cityId = cityId;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public int getCityId() {
        return cityId;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

}
