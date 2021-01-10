package com.zbl.code;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.code.zbl.ElasticSearchApplication;
import com.code.zbl.pojo.User;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.IndicesClient;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.WildcardQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

//过时import
//import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
//import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;

/**
 * @Author: zbl
 * @Date: Created in 2021/1/9
 * @Description:
 * @Version: $
 */
@SpringBootTest(classes = ElasticSearchApplication.class)
@RunWith(SpringRunner.class)
public class EsApiApplicationTest {

    @Resource
    private RestHighLevelClient restHighLevelClient;

    @Autowired
    @Qualifier("restHighLevelClient")
    private RestHighLevelClient client;
    
    private static final String index = "zbl5337";
    

    //测试索引的创建 Request
    @Test
    public void test01() throws Exception {
        //1. 创建索引请求
        CreateIndexRequest request = new CreateIndexRequest(index);
        //2. 执行创建请求 IndicesClient, 请求后获得响应
        CreateIndexResponse response = client.indices().create(request, RequestOptions.DEFAULT);
        System.out.println(response.index());
    }

    //测试索引的创建 Request
    @Test
    public void test02() throws Exception {
        //1. 创建索引请求
        CreateIndexRequest request = new CreateIndexRequest("zbl5338");
        //2. 执行创建请求 IndicesClient, 请求后获得响应
        IndicesClient indicesClient = restHighLevelClient.indices();
        CreateIndexResponse response = indicesClient.create(request, RequestOptions.DEFAULT);
        System.out.println(response.index());
    }

    //测试索引的删除 Request
    @Test
    public void test03() throws Exception {
        //1. 创建索引请求
        DeleteIndexRequest request = new DeleteIndexRequest("zbl5339");
        //2. 执行创建请求 IndicesClient, 请求后获得响应
        AcknowledgedResponse response = restHighLevelClient.indices().delete(request, RequestOptions.DEFAULT);
        //是否删除成功, 删除失败报错
        System.out.println(response.isAcknowledged());
    }

    //判断索引是否存在
    @Test
    public void test04() throws Exception {
        //1. 创建索引请求
        GetIndexRequest request = new GetIndexRequest(index);
        //2. 执行创建请求 IndicesClient, 请求后获得响应
        boolean exists = restHighLevelClient.indices().exists(request, RequestOptions.DEFAULT);
        System.out.println(exists);
    }

    //添加文档
    @Test
    public void test05() throws Exception {
        //创建对象
        User user = new User("zbl", 5);
        //创建请求
        IndexRequest request = new IndexRequest(index);
        //规则 PUT /zbl5337/_doc/1
        request.id("1");
        request.timeout(TimeValue.timeValueSeconds(1));
        //request.timeout("1s");
        //将数据放入请求 json
        request.source(JSON.toJSONString(user), XContentType.JSON);
        //客户端发送请求
        IndexResponse response = restHighLevelClient.index(request, RequestOptions.DEFAULT);
        //获取响应结果
        System.out.println(response.toString());
        System.out.println(response.status());
    }

    //获取文档 GET /zbl/doc/1
    @Test
    public void test06() throws Exception {
        GetRequest request = new GetRequest(index, "1");
        //不获取_source的上下文了 效率更高
        request.fetchSourceContext(new FetchSourceContext(false));
        request.storedFields("_none_");
        boolean response = restHighLevelClient.exists(request, RequestOptions.DEFAULT);
        System.out.println(response);
    }

    //获取文档信息
    @Test
    public void test07() throws Exception {
        GetRequest request = new GetRequest(index, "1");
        GetResponse response = restHighLevelClient.get(request, RequestOptions.DEFAULT);
        System.out.println(response);
        //真正put进去的数据
        System.out.println(response.getSource());
        System.out.println(response.getSourceAsString());
        User user = JSONObject.parseObject(response.getSourceAsString(), User.class);
        System.out.println(user);
    }

    //更新文档信息
    @Test
    public void test08() throws Exception {
        UpdateRequest request = new UpdateRequest(index, "1");
        request.timeout(TimeValue.timeValueSeconds(1));
        User user = new User("zbl;", 17);
        request.doc(user, XContentType.JSON);
        UpdateResponse response = restHighLevelClient.update(request, RequestOptions.DEFAULT);
        System.out.println(response);
        //UpdateResponse[index=zbl5337,type=_doc,id=1,version=2,seqNo=1,primaryTerm=1,result=updated,shards=ShardInfo{total=2, successful=1, failures=[]}]
        System.out.println(response.status());
    }

    //删除文档信息
    @Test
    public void test09() throws Exception {
        DeleteRequest request = new DeleteRequest(index, "3");
        request.timeout(TimeValue.timeValueSeconds(1));
        DeleteResponse response = restHighLevelClient.delete(request, RequestOptions.DEFAULT);
        System.out.println(response);
        //DeleteResponse[index=zbl5337,type=_doc,id=1,version=3,result=deleted,shards=ShardInfo{total=2, successful=1, failures=[]}]
        System.out.println(response.status());
    }

    //特殊的, 真实的项目 一般都会批量插入数据!
    @Test
    public void test10() throws Exception {
        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.timeout(TimeValue.timeValueSeconds(10));
        List<User> users = Arrays.asList(
                new User("zbl1", 1),
                new User("zbl2", 2),
                new User("zbl3", 3),
                new User("zbl4", 4),
                new User("zbl5", 5),
                new User("zbl6", 6),
                new User("zbl7", 7),
                new User("frozen", 8)
        );
        //批处理请求
        for (int i = 0; i < users.size(); i++) {
            //批量更新删除同理
            bulkRequest.add(
                    //不写id 随机生成一个id
                    new IndexRequest(index)
                            .id("" + (i+1))
                            .source(JSON.toJSONString(users.get(i)), XContentType.JSON)
            );
        }
        BulkResponse bulkResponse = client.bulk(bulkRequest, RequestOptions.DEFAULT);
        System.out.println(bulkResponse.hasFailures());
    }

    //查询
    //SearchRequest         搜索请求
    //SearchSourceBuilder  搜索条件构建
    // HighLightBuilder 构建高亮
    // TermQueryBuilder 精确查询
    // MatchAllQueryBuilder
    // xxx QueryBuilder 对应命令
    //
    @Test
    public void test11() throws Exception {
        SearchRequest searchRequest = new SearchRequest(index);
        //构建搜索条件
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        //QueryBuilders 实现
        // term精确匹配
//        TermQueryBuilder termQuery = QueryBuilders.termQuery("name", "frozen");
        WildcardQueryBuilder wildcardQuery = QueryBuilders.wildcardQuery("name", "zbl*");
//        MatchQueryBuilder matchQuery = QueryBuilders.matchQuery("name", "zbl");
//        MatchAllQueryBuilder matchAllQuery = QueryBuilders.matchAllQuery();
        sourceBuilder.query(wildcardQuery);
        //分页
        //sourceBuilder.from();
        //sourceBuilder.size();
        sourceBuilder.timeout(TimeValue.timeValueSeconds(60));

        searchRequest.source(sourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println(searchResponse.getHits());
        System.out.println(JSON.toJSONString(searchResponse.getHits()));
        for (SearchHit documentFields : searchResponse.getHits()) {
            //{name=frozen, age=8}
            System.out.println(documentFields.getSourceAsMap());
        }

    }


}
