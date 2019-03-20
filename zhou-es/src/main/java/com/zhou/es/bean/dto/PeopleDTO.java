package com.zhou.es.bean.dto;


import com.zhou.es.bean.People;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.GeoPointField;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

import java.io.Serializable;

public class PeopleDTO extends People {

   private Object geoDistance;

    public Object getGeoDistance() {
        return geoDistance;
    }

    public void setGeoDistance(Object geoDistance) {
        this.geoDistance = geoDistance;
    }
}
