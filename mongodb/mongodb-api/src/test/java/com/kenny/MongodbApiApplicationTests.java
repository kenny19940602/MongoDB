package com.kenny;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertManyResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

@SpringBootTest
class MongodbApiApplicationTests {

    @Autowired
    private MongoClient mongoClient;

    private MongoCollection<Document> collection;
    @Test
    void contextLoads() {
    }

    @BeforeEach
    void init() {
        MongoDatabase database = mongoClient.getDatabase("mydb");
        collection = database.getCollection("test");
    }

    @AfterEach
    void closeMongoClient() {
        mongoClient.close();
    }

    @Test
    void mongoClient() {
        System.out.println(collection);
    }
    @Test
    void insertOne() {
        Document doc = new Document("name", "MongoDB")
                .append("type", "database")
                .append("count", 1)
                .append("versions", Arrays.asList("v3.2", "v3.0", "v2.6"))
                .append("info", new Document("x", 203).append("y", 102));
        InsertOneResult insertOneResult = collection.insertOne(doc);
        System.out.println(insertOneResult.getInsertedId());
    }

    @Test
    void insertMany() {
        List<Document> documents = new ArrayList<Document>();
        for (int i = 0; i < 100; i++) {
            documents.add(new Document("i", i));
        }

        InsertManyResult insertManyResult = collection.insertMany(documents);
        System.out.println(collection.countDocuments());
        insertManyResult.getInsertedIds().forEach((k, v) -> {
            System.out.println("key :" + k);
            System.out.println("value :" + v);
        });
    }
    @Test
    void findFirst() {
        Document myDoc = collection.find().first();
        System.out.println(myDoc.toJson());
    }
    @Test
    void findAll() {
        MongoCursor<Document> cursor = collection.find().iterator();
        try {
            while (cursor.hasNext()) {
                System.out.println(cursor.next().toJson());
            }
        } finally {
            cursor.close();
        }
    }
    @Test
    void findSingle() {
        Document myDoc = collection.find(Filters.eq("i", 71)).first();
        System.out.println(myDoc.toJson());
    }
    @Test
    void findAll4Filter() {
        collection.find(Filters.gt("i", 50)).forEach(new Consumer<Document>() {
            @Override
            public void accept(Document document) {
                System.out.println(document.toJson());
            }
        });
    }
    @Test
    void findAll4FilterAnd() {
        collection.find(Filters.and(Filters.gt("i", 50),
                Filters.lte("i", 100))).
                forEach(new Consumer<Document>() {
            @Override
            public void accept(Document document) {
                System.out.println(document.toJson());
            }
        });
    }
    @Test
    void updateOne() {
        collection.updateOne(Filters.eq("i", 10), Updates.set("i", 110));
    }
    @Test
    void updateMany() {
        UpdateResult updateResult = collection.updateMany(Filters.lt("i", 100), Updates.inc("i", 100));
        System.out.println(updateResult.getModifiedCount());
    }
    @Test
    void deleteOne() {
        collection.deleteOne(Filters.eq("i", 110));
    }
    @Test
    void deleteMany() {
        DeleteResult deleteResult = collection.deleteMany(Filters.gte("i", 100));
        System.out.println(deleteResult.getDeletedCount());
    }
    @Test
    void createIndex() {
        collection.createIndex(new Document("i", -1));
    }


}
