package com.zhou.es.bean;


import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.GeoPointField;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

import java.io.Serializable;
import java.util.Map;

@Document(indexName = "zhou", type = "people")
public class People implements Serializable {

    @Id
    private Integer id;
    private String nickName;
    private String wsNo;
    private String sex;
//    private Location location;

    @GeoPointField
    private GeoPoint location;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getWsNo() {
        return wsNo;
    }

    public void setWsNo(String wsNo) {
        this.wsNo = wsNo;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

//    public Location getLocation() {
//        return location;
//    }
//
//    public void setLocation(Location location) {
//        this.location = location;
//    }

    public GeoPoint getLocation() {
        return location;
    }

    public void setLocation(GeoPoint location) {
        this.location = location;
    }



//    public static class Location{
//        double lat;
//        double lon;
//
//        public double getLat() {
//            return lat;
//        }
//
//        public void setLat(double lat) {
//            this.lat = lat;
//        }
//
//        public double getLon() {
//            return lon;
//        }
//
//        public void setLon(double lon) {
//            this.lon = lon;
//        }
//    }


}
