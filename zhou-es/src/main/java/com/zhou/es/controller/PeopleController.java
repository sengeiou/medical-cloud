package com.zhou.es.controller;

import com.zhou.es.bean.People;
import com.zhou.es.bean.dto.PeopleDTO;
import com.zhou.es.service.PeopleService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.geo.GeoDistance;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.sort.GeoDistanceSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.ResultsExtractor;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("people")
public class PeopleController {

    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    PeopleService peopleService;


    @RequestMapping("/buildGeoData")
    public Map<String, Object> buildGeoData() {

        peopleService.initData();

        Map<String, Object> map = new HashMap<>();
        map.put("data", "成功");
        map.put("code", 200);

        return map;
    }


    @RequestMapping("/query/{distance}")
    public Object query(@PathVariable Integer distance, String sex) {
        //东经
        double lon = 112.938815;
        //北纬
        double lat = 28.227878;

        System.out.println(lon + "," + lat);
        Long nowTime = System.currentTimeMillis();

        //查询某经纬度distance米范围内
        GeoDistanceQueryBuilder builder = QueryBuilders.geoDistanceQuery("location").point(lat, lon)
                .distance(distance, DistanceUnit.METERS);

        BoolQueryBuilder sexbuilder = QueryBuilders.boolQuery();
        //性别
        if (sex != null) {
            sexbuilder.must(QueryBuilders.matchQuery("sex",sex));
        }


        GeoDistanceSortBuilder sortBuilder = SortBuilders.geoDistanceSort("location")
                .point(lat, lon)
                .unit(DistanceUnit.METERS)
                .order(SortOrder.ASC);

        Pageable pageable = new PageRequest(0, 100);
        NativeSearchQueryBuilder builder1 = new NativeSearchQueryBuilder().withFilter(builder).withQuery(
                sexbuilder).withSort(sortBuilder).withPageable(pageable);
        SearchQuery searchQuery = builder1.build();


        List<PeopleDTO> personList = null;
        try {
            //queryForList默认是分页，走的是queryForPage，默认10个
            personList = elasticsearchTemplate.queryForList(searchQuery, PeopleDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
        }


        System.out.println("耗时：" + (System.currentTimeMillis() - nowTime));
        return personList;
    }


    @RequestMapping("/search/{distance}")
    public Object search(@PathVariable Integer distance, String sex,Integer pageNo,Integer pageSize) {
        //东经
        double lon = 112.938815;
        //北纬
        double lat = 28.227878;

        System.out.println(lon + "," + lat);
        Long nowTime = System.currentTimeMillis();

        //查询某经纬度distance米范围内
        GeoDistanceQueryBuilder builder = QueryBuilders.geoDistanceQuery("location").point(lat, lon).distance(distance, DistanceUnit.METERS);

        //坐标方位计量单位
        String unit = DistanceUnit.METERS.toString();

//        QueryBuilder qb = new GeoDistanceRangeQueryBuilder("location").point(lat,lon).from(0+unit).to(distance+unit).optimizeBbox("memory").geoDistance(GeoDistance.PLANE);


        BoolQueryBuilder sexbuilder = QueryBuilders.boolQuery();
        //性别
        if (sex != null) {
            sexbuilder.must(QueryBuilders.matchQuery("sex",sex));
        }


        GeoDistanceSortBuilder sortBuilder = SortBuilders.geoDistanceSort("location")
                .point(lat, lon)
                .unit(DistanceUnit.METERS)
                .order(SortOrder.ASC);


        Client client = elasticsearchTemplate.getClient();
        SearchRequestBuilder searchRequestBuilder =client.prepareSearch("zhou").setTypes("people");
        searchRequestBuilder.setPostFilter(builder);
        searchRequestBuilder.setQuery(sexbuilder);
        searchRequestBuilder.addSort(sortBuilder);
        searchRequestBuilder.setFrom((pageNo - 1) * pageSize).setSize(pageSize);

        SearchResponse searchResponse = searchRequestBuilder.execute().actionGet();

        System.out.println("本次查询耗时："+searchResponse.getTookInMillis()/1000f);

        List<Map> list = new ArrayList<>();

        for (SearchHit hit : searchResponse.getHits().getHits()) {

            //获取距离值，并保留两位小数点
            BigDecimal geoDis=new BigDecimal((double)hit.getSortValues()[0]);
            Map<String,Object> hitMap=hit.getSource();
            //在创建MAPPING的时候，属性名的不可为geoDistance。
            hitMap.put("geoDistance", geoDis.setScale(2, BigDecimal.ROUND_HALF_DOWN));

            System.out.println(hit.getSource());
            list.add(hit.getSource());

        }


        System.out.println("耗时11111111：" + (System.currentTimeMillis() - nowTime));

        return list;
    }

}

