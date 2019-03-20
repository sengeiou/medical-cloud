package com.zhou.es.repository;

import com.zhou.es.bean.Article;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import java.util.List;

public interface ArticleRepository extends ElasticsearchRepository<Article,Integer> {

//    List<Article> findByBookNameLike(String bookName);

}
