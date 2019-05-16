package com.duangframework.dingtalk.service.handle;

import com.dingtalk.api.response.OapiUserCreateResponse;
import com.duangframework.db.DbClientFactory;
import com.duangframework.db.mongodb.client.MongoClientAdapter;
import com.duangframework.dingtalk.sdk.dto.DingtalkResponse;
import com.duangframework.dingtalk.service.UserService;
import com.duangframework.kit.ToolsKit;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *  保存到mongodb
 * @author laotang
 * @date 2015-05-14
 */
public class MongodbDataHandle implements IDateHandle {

    private static final Logger logger = LoggerFactory.getLogger(MongodbDataHandle.class);
    private static final String  DATABASE_NAME = "";
    private static MongoClient client;

    private MongoClient getClient() {
        if(ToolsKit.isNotEmpty(client)) {
            return client;
        }
        try {
            if (ToolsKit.isEmpty(client)) {
                String clientId = DbClientFactory.getMongoDefaultClientId();
                MongoClientAdapter adapter = DbClientFactory.getMongoDbClient(clientId);
                client = adapter.getClient();
            }
        } catch (Exception e) {
            logger.warn(e.getMessage(), e);
        }
        return client;
    }


    private MongoCollection<Document> getCollection(String collection) {
        return getClient().getDatabase(DATABASE_NAME).getCollection(collection);
    }


    @Override
    public void handle(String optFlag, String tableName, DingtalkResponse response) {
        Document document = null;
        if(UserService.CREATE_FLAG_FIELD.equalsIgnoreCase(optFlag)) {
            //存旧表
            OapiUserCreateResponse userCreateResponse = (OapiUserCreateResponse)response.getResult();
            // 要转换数据格式

            // 存新表
            document = new Document(response.toMap());
            logger.warn("######: " + document.toString());
//            getCollection(tableName).insertOne(document);
        }

    }
}
