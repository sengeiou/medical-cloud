package com.zhou.es;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.zhou.es.bean.Article;
import com.zhou.es.bean.Book;
import com.zhou.es.repository.ArticleRepository;
import com.zhou.es.repository.BookRepository;
import io.searchbox.client.JestClient;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.ResultsExtractor;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EsApplicationTests {

    @Autowired
    JestClient jestClient;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    ArticleRepository articleRepository;

    @Autowired(required = false)
    private ElasticsearchTemplate elasticsearchTemplate;

    @Test
    public void test05() {
        Article article = new Article();
        article.setId(1);
        article.setTitle("好消息");
        article.setAuthor("来有去");
        article.setContents("Hello Word");
        articleRepository.index(article);
    }

    @Test
    public void test02() {
        Book book = new Book();
        book.setId(2);
        book.setBookName("zouzouzou");
        book.setAuthor("fafafa");
        bookRepository.index(book);
    }

    @Test
    public void find() {
        List<Book> list = bookRepository.findByBookNameLike("来有去");

        Gson gson = new Gson();
        System.out.println(gson.toJson(list));

    }


    @Test
    public void elasticsearchTemplateSearch() {
//        IndexQuery indexQuery = new IndexQueryBuilder().withIndexName("zhou")
//                .withType("book").build();
//
//
//        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(QueryBuilders.queryStringQuery("spring boot OR 书籍")).build();
//
//        QueryBuilder qb = QueryBuilders.boolQuery().must(QueryBuilders.termQuery("content", "test1"))
//
//        elasticsearchTemplate.queryForList(qb,Book.class);
    }

    @Test
    public void contain() {
//        .operator(MatchQueryBuilder.Operator.AND).minimumShouldMatch("75%")
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(QueryBuilders.matchQuery("bookName", "发发发").operator(MatchQueryBuilder.Operator.OR).minimumShouldMatch("75%")).build();
//        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(QueryBuilders.matchQuery("bookName", "发发发")).build();

        Gson gson = new Gson();
//        System.out.println(gson.toJson(elasticsearchTemplate.queryForList(searchQuery, Book.class)));

       String result = elasticsearchTemplate.query(searchQuery, response -> {
            //直接返回es原封结果到前端
//            return JSONObject.parseObject(response.toString());{
            return gson.toJson(response.toString());
        });
        System.out.println("result:"+result);
    }


    @Test
    public void contextLoads() {
        //1.给es保存一个索引
        Article article = new Article();
        article.setId(1);
        article.setTitle("好消息");
        article.setAuthor("zhouzhou");
        article.setContents("Hello Word");

        Index index = new Index.Builder(article).index("zhou").type("news").build();

        try {
            jestClient.execute(index);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //测试搜索
    @Test
    public void search() {
        String json = "{\n" +
                "    \"query\" : {\n" +
                "        \"match\" : {\n" +
                "            \"about\" : \"rock\"\n" +
                "        }\n" +
                "    }" +
                "}";
//        Search search = new Search.Builder(json).addIndex("zhou").addType("news").build();

        Search search = new Search.Builder(json).addIndex("megacorp").build();
        try {
            SearchResult result = jestClient.execute(search);
            System.out.println(result.getJsonString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Test
    public void testRandom_generatingDouble0To1() throws Exception {

        double generatorDouble = new Random().nextDouble();
        System.out.println(generatorDouble);
    }


}
