package repository;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.cluster.StateResponse;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.indices.*;
import co.elastic.clients.elasticsearch.sql.Column;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.endpoints.BooleanResponse;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import com.fasterxml.jackson.databind.node.ObjectNode;
import config.ElasticsearchConnectMode;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.message.BasicHeader;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;

import java.io.IOException;
import java.util.List;

import static config.ElasticsearchConnectMode.HTTP;
import static config.ElasticsearchConnectMode.HTTP_SUPERUSER;

/**
 * ES连接方式
 *
 * @author WTF
 */
public class ElasticsearchService {

    private static final String IP = "";
    private static final String PORT = "";
    private static final String USERNAME = "";
    private static final String PWD = "";
    private static final String CONNECT_MODE = "HTTP_SUPERUSER";

    protected ElasticsearchClient client;

    public ElasticsearchService() {

        RestClientBuilder restClientBuilder = RestClient.builder(new HttpHost(IP, Integer.parseInt(PORT)));
        // Create the low-level client
        RestClient restClient = null;
        switch (ElasticsearchConnectMode.valueOf(CONNECT_MODE)) {
            case HTTP:
                restClient = connectByHttp(restClientBuilder);
                break;
            case HTTP_SUPERUSER:
                restClient = this.connectByHttpSuperuser(restClientBuilder);
                break;
            default:
                break;
        }
        // Create the transport with a Jackson mapper
        ElasticsearchTransport transport = new RestClientTransport(
                restClient, new JacksonJsonpMapper());
        // And create the API client
        client = new ElasticsearchClient(transport);
    }

    private RestClient connectByHttpSuperuser(RestClientBuilder builder) {

        //用户名密码认证
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(USERNAME, PWD));

        return builder
                .setDefaultHeaders(new Header[]{new BasicHeader("X-Elastic-Product", "Elasticsearch")})
                .setHttpClientConfigCallback(x -> x.setDefaultCredentialsProvider(credentialsProvider))
                .build();
    }

    private RestClient connectByHttp(RestClientBuilder builder) {

        return builder
                .setDefaultHeaders(new Header[]{new BasicHeader("X-Elastic-Product", "Elasticsearch")})
                .build();
    }

    /**
     * 获取集群状态
     * GET /_cluster/state/metadata/index_1,index_2
     * metadata: 用这种写法筛选是因为如果全返回会影响集群性能，并且返回数据很大
     * index_1,index_2: 最后筛选索引，如果不传具体索引会把内置的索引也查出来，如.kibana_
     *
     * @resource: https://www.elastic.co/guide/en/elasticsearch/reference/8.1/cluster-state.html
     * TODO: 解析返回数据
     */
    public void clusterState(List<String> indices) {
        try {
            StateResponse state = client.cluster().state(x -> x
                    //筛选返回的对象
                    .metric("metadata")
                    .index(indices)
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 校验表是否存在
     *
     * @param myTableName 表名
     * @return isTableExist
     */
    public boolean isTableExist(String myTableName) {
        try {
            BooleanResponse booleanResponse = client.existsSource(e -> e.index(myTableName));
            return booleanResponse.value();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * @resource https://www.elastic.co/guide/en/elasticsearch/reference/8.1/indices-delete-index.html
     */
    public boolean deleteTable(String myTableName) {
        try {
            return client.indices().delete(d -> d.index(myTableName)).acknowledged();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     *
     * @resource https://www.elastic.co/guide/en/elasticsearch/reference/8.1/indices-create-index.html
     */
//    @Override
//    public boolean createTable(String tableName, List<Column> columnList) {
////        if (DataSongStringUtils.isEmpty(tableName) || columnList == null
////                || columnList.isEmpty()) {
////            return false;
////        }
//
//
//        //2. 基础配置
//        IndexSettings indexSettings =
//                IndexSettings.of(
//                        indexSetting -> indexSetting
//                                .numberOfShards(config.getShardCount())
//                                .numberOfReplicas(config.getReplicasCount())
//                                .refreshInterval(interval -> interval.time(config.getRefreshInterval()))
//                                .maxResultWindow(Integer.MAX_VALUE)
//                                .translog(tl -> tl
//                                        .durability(config.getTranslogDurability())
//                                        .flushThresholdSize(config.getTranslogFlushThresholdSize())
//                                )
//                        //额外配置
////                                .withJson(config.getIndexExtraSettings())
//                );
//
//        try {
//            //3. 创建索引
//            CreateIndexRequest createIndexRequest =
//                    CreateIndexRequest.of(c -> c
//                            .index(tableName)
//                            .settings(indexSettings)
//                    );
//
//            CreateIndexResponse createIndexResponse = client.indices().create(createIndexRequest);
////            if (Boolean.FALSE.equals(createIndexResponse.acknowledged())) {
////                throw new RuntimeException(Status.SERVER_ERROR,
////                        String.format("create table [%s] request error of db[%s] in service [%s]",
////                                tableName, dbName, ElasticSearchConstant.COMPONENT_ID));
////            }
//
//            //3. 更新mapping
////            PutMappingRequest putMappingRequest = columns2Properties(tableName, columnList);
//
//            try {
////                PutMappingResponse putMappingResponse = client.indices().putMapping(putMappingRequest);
////                return putMappingResponse.acknowledged();
//            } catch (Exception e) {
//                client.indices().delete(d -> d.index(tableName));
//                e.printStackTrace();
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
//
//    public void a(){
//        SearchResponse<ObjectNode> searchResponse = client.search(g -> g
//                        .index("myTableName")
//                        .query(q -> q
//                                        .ids(id->id.values(ids))
////                                    .terms(t -> t
////                                            .field("_id")
////                                            .terms(tt -> tt
////                                                    .value(fieldValues)
////                                            )
////                                    )
//                        )
//                , ObjectNode.class
//        );
//    }


}
