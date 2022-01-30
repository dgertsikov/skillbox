package com.skillbox.mongodemo;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.bson.BsonDocument;
import org.bson.Document;
import java.io.FileNotFoundException;
import java.io.FileReader;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {

    public static void main(String[] args) {
        MongoClient mongoClient = new MongoClient( "127.0.0.1" , 27017 );
        MongoDatabase database = mongoClient.getDatabase("local");
        // Создаем коллекцию
        MongoCollection<Document> collection = database.getCollection("Store");
        MongoCollection<Document> product = database.getCollection("Product");
        MongoCollection<Document> prodToColl = database.getCollection("ProdToColl");
        collection.drop();
        product.drop();
        prodToColl.drop();
        System.out.println("Введите команду");
        Pattern pattern = Pattern.compile("(ДОБАВИТЬ_МАГАЗИН|ДОБАВИТЬ_ТОВАР|ВЫСТАВИТЬ_ТОВАР|СТАТИСТИКА_ТОВАРОВ)\\s?(\\S*)\\s?(\\S*)", Pattern.CASE_INSENSITIVE);

        Scanner scanner = new Scanner(System.in);
        HashSet<String> emailList = new HashSet<>();

        while (true) {
            String input = scanner.nextLine();
            if (input.equals("0")) {
                break;
            }
            Matcher matcher = pattern.matcher(input);
            if (matcher.find()) {

                switch (matcher.group(1).toUpperCase()) {
                    case "ДОБАВИТЬ_МАГАЗИН":
                        collection.insertOne(new Document().append("Store", matcher.group(2)));
                        break;
                    case "ДОБАВИТЬ_ТОВАР":
                        System.out.println(matcher.group(3));
                        int price = Integer.valueOf(matcher.group(3));
                        product.insertOne(new Document()
                                .append("Product", matcher.group(2))
                                .append("Price", price));
                        break;
                    case "ВЫСТАВИТЬ_ТОВАР":
                        prodToColl.insertOne(new Document()
                                .append("Product", matcher.group(2))
                                .append("Store", matcher.group(3)));
                        break;
                    case "СТАТИСТИКА_ТОВАРОВ":

                        List<BsonDocument> productCount = new ArrayList<>();
                        productCount.add(BsonDocument.parse("{$count: 'Product'}"));
                        product.aggregate(productCount).forEach((Consumer<Document>) document -> {
                            System.out.println("Количество наименований:\n" + document);
                        });
                        List<BsonDocument> productAvg = new ArrayList<>();
                        productAvg.add(BsonDocument.parse("{" +
                                "$group: {" +
                                "_id: {name: ''}, " +
                                "avgage: {$avg: '$Price'}" +
                                "}}"));
                        product.aggregate(productAvg).forEach((Consumer<Document>) document -> {
                            System.out.println("Средняя цена товаров:\n" + document);
                        });
                        List<BsonDocument> productMax = new ArrayList<>();
                        productMax.add(BsonDocument.parse("{" +
                                "$group: {" +
                                "_id: {name: ''}, " +
                                "maxPrice: {$max: '$Price'}" +
                                "}}"));
                        product.aggregate(productMax).forEach((Consumer<Document>) document -> {
                            System.out.println("Максимальная цена:\n" + document);
                        });
                        List<BsonDocument> productMin = new ArrayList<>();
                        productMin.add(BsonDocument.parse("{" +
                                "$group: {" +
                                "_id: {name: ''}, " +
                                "minPrice: {$min: '$Price'}" +
                                "}}"));
                        product.aggregate(productMin).forEach((Consumer<Document>) document -> {
                            System.out.println("Минимальная цена:\n" + document);
                        });
                        List<BsonDocument> productCountMin = new ArrayList<>();
                        //productCountMin.add(BsonDocument.parse("{$query: {Price: {$lt: 100}}}"));
                        //productCountMin.add(BsonDocument.parse("{$count: 'Product', $query: {Price: {$lt: 100}}}"));
                        productCountMin.add(BsonDocument.parse("{$count: 'Product'}"));
                        product.aggregate(productCountMin).forEach((Consumer<Document>) document -> {
                            System.out.println("Количество наименований дешевле 100:\n" + document);
                        });
                        ////

                        break;
                }
            } else {
                System.out.println("Неизвестная команда");
            }

        }

    }

    public static void main22(String[] args) {
        MongoClient mongoClient = new MongoClient( "127.0.0.1" , 27017 );

        MongoDatabase database = mongoClient.getDatabase("local");

        // Создаем коллекцию
        MongoCollection<Document> collection = database.getCollection("TestSkillDemo");

        // Удалим из нее все документы
        collection.drop();

        // Создадим первый документ
        Document firstDocument = new Document()
                .append("Type", 1)
                .append("Description", "Это наш первый документ в MongoDB")
                .append("Author", "Я")
                .append("Time", new SimpleDateFormat().format(new Date()));


        // Вложенный объект
        Document nestedObject = new Document()
                .append("Course", "NoSQL Базы Данных")
                .append("Author", "Mike Ovchinnikov");

        firstDocument.append("Skillbox", nestedObject);


        // Вставляем документ в коллекцию
        collection.insertOne(firstDocument);

        collection.find().forEach((Consumer<Document>) document -> {
            System.out.println("Наш первый документ:\n" + document);
        });

        // Используем JSON-синтаксис для создания объекта
        Document secondDocument = Document.parse(
                "{Type: 2, Description:\"Мы создали и нашли этот документ с помощью JSON-синтаксиса\"}"
        );
        collection.insertOne(secondDocument);

        // Используем JSON-синтаксис для написания запроса (выбираем документы с Type=2)
        BsonDocument query = BsonDocument.parse("{Type: {$eq: 2}}");
        collection.find(query).forEach((Consumer<Document>) document -> {
            System.out.println("Наш второй документ:\n" + document);
        });

        collection.drop();

        try {
            CSVReader reader = new CSVReader(new FileReader("data/mongo.csv"));
            List<String[]> lines = reader.readAll();
            for (String[] element : lines){
                Document document = new Document()
                        .append("Name", element[0])
                        .append("Age", element[1])
                        .append("course", element[2]);
                collection.insertOne(document);

            }
            System.out.println("Количество студентов: "+collection.countDocuments());

            System.out.println("Количество студентов старше 40 лет: "+collection.countDocuments(BsonDocument.parse("{Age: {$gt: '40'}}")));

            collection.find().sort(BsonDocument.parse("{Age: 1}")).limit(1).forEach((Consumer<Document>) document -> {
                System.out.println("Имя самого молодого студента: " + document.get("Name"));
            });

            collection.find().sort(BsonDocument.parse("{Age: -1}")).limit(1).forEach((Consumer<Document>) document -> {
                System.out.println("Список курсов самого старого студента: " + document.get("course"));
            });

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvException e) {
            e.printStackTrace();
        }
    }
}
