package com.zhou.es.repository;

import com.zhou.es.bean.People;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface PeopleRepository extends ElasticsearchRepository<People,Integer> {

}
