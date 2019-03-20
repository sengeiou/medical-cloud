package com.zhou.es.service;

import com.google.gson.Gson;
import com.zhou.es.bean.People;
import com.zhou.es.repository.PeopleRepository;
import io.searchbox.client.JestClient;
import io.searchbox.core.SearchResult;
import org.apache.commons.lang.RandomStringUtils;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.common.unit.DistanceUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class PeopleService {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    JestClient jestClient;

    @Autowired
    PeopleRepository peopleRepository;

    Gson gson = new Gson();

    //东经
    private double myLon = 112.938815;
    //北纬
    private double myLat = 28.227878;

    private String myName = "Tom老师";

    public void recreateIndex() {

    }


    public void addDataToIndex(double myLat, double myLon, int total) {
        List<String> peopleList = new ArrayList<>();
        System.out.println("----------------");
        peopleRepository.deleteAll();
        List<IndexQuery> queries = new ArrayList<>();
        int counter = 0;
        for (int i = 0; i < total; i++) {

            People people = new People();
            people.setId(i + 1);
            people.setWsNo("WX_" + RandomStringUtils.randomAlphabetic(10) + i);
            people.setNickName("zhou" + i);
            people.setSex(i % 2 == 1 ? "男" : "女");
            people.setLocation(randomGeoPoint(myLat, myLon));
            System.out.println(":" + gson.toJson(people));


            IndexQuery indexQuery = new IndexQuery();
            indexQuery.setId(people.getId() + "");
            indexQuery.setObject(people);
            indexQuery.setIndexName("zhou");
            indexQuery.setType("people");

            queries.add(indexQuery);

            if (counter % 500 == 0) {
                elasticsearchTemplate.bulkIndex(queries);
                queries.clear();
                System.out.println("bulkIndex counter : " + counter);
            }
            counter++;
        }
        if (queries.size() > 0) {
            elasticsearchTemplate.bulkIndex(queries);
        }
        System.out.println("bulkIndex completed.");

    }

    /**
     * 初始化数据
     */
    public void initData() {
        int total = 100000;
        try {
            recreateIndex();
            addDataToIndex(myLat, myLon, total);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void search(double myLon, double myLat, int r, int size, Object o) {

    }

    public void searchNearBy() {
        int size = 10, r = 100;
        System.out.println("开始获取距离" + myName + r + "m以内人");
        search(myLon, myLat, r, size, null);

    }

//    public People.Location randomPoint(double myLat, double myLon) {
//        //坐标范围，最小米
//        double min = 0.000001;
//        //坐标范围，最大10000米
//        double max = 0.00002;
//        double s = Integer.valueOf(5) % (max - min + 1) + max;
//        DecimalFormat decimalFormat = new DecimalFormat("######0.0000000");
//        String alon = decimalFormat.format(s + myLon);
//        String alat = decimalFormat.format(s + myLat);
//        double dlon = Double.valueOf(alon);
//        double dlat = Double.valueOf(alat);
//        People.Location location = new People.Location();
//        location.setLat(dlat);
//        location.setLon(dlon);
//        return location;
//    }


    public GeoPoint randomGeoPoint(double myLat, double myLon) {

        double generatorDouble = new Random().nextDouble();

        //坐标范围，最小米
        double min = 0.000001;
        //坐标范围，最大10000米
        double max = 0.00002;
//        System.out.println("generatorDouble：" + generatorDouble);
        double s = generatorDouble % (max - min + 1) + max;
//        System.out.println("s:" + s);

        DecimalFormat decimalFormat = new DecimalFormat("######0.0000000");
        String alon = decimalFormat.format(s + myLon);
        String alat = decimalFormat.format(s + myLat);
        double dlon = Double.valueOf(alon);
        double dlat = Double.valueOf(alat);

        return new GeoPoint(dlat, dlon);
    }


//    public SearchResult search(double lat, double lon, int radius, int size, String sex) {
////        SearchResult result = new SearchResult();
//        //坐标范围计算单位
//        String unit = DistanceUnit.METERS.toString();
//    }


}
