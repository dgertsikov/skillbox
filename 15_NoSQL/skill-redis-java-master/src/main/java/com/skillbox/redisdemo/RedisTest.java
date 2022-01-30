package com.skillbox.redisdemo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import static java.lang.System.out;

public class RedisTest {

    // Запуск докер-контейнера:
    // docker run --rm --name skill-redis -p 127.0.0.1:6379:6379/tcp -d redis

    // Для теста будем считать неактивными пользователей, которые не заходили 2 секунды
    private static final int DELETE_SECONDS_AGO = 2;

    // Допустим пользователи делают 500 запросов к сайту в секунду
    private static final int RPS = 500;

    // И всего на сайт заходило 1000 различных пользователей
    private static final int USERS = 1000;

    // Также мы добавим задержку между посещениями
    private static final int SLEEP = 2; // 1 миллисекунда

    private static final SimpleDateFormat DF = new SimpleDateFormat("HH:mm:ss");

    private static void log(int UsersOnline) {
        String log = String.format("[%s] Пользователей онлайн: %d", DF.format(new Date()), UsersOnline);
        out.println(log);
    }

    public static void main(String[] args) throws InterruptedException {

        int[] usersArray = new int[20];
        UserOnline[] uoArray = new UserOnline[20];

        RedisStorage redis = new RedisStorage();
        redis.init();

        // Создаем 20 пользователей
        for(int request = 0; request <= 19; request++) {
            int user_id = new Random().nextInt(USERS);
            usersArray[request] = user_id;

            out.println("user_id " + user_id);

            uoArray[request] = redis.logPageVisit(user_id);

        }
        int usersOnline = redis.calculateUsersNumber();
        log(usersOnline);

        int n = 0;

        //Типа бесконечный цикл
        for(int seconds=0; seconds <= 3; seconds++) {
            for (UserOnline item : redis.onlineUsers) {
                n++;

                out.println("На главной странице показываем пользователя " + item.getName());
                redis.onlineUsers.remove(item);
                redis.logPageVisit(Integer.valueOf(item.getName()));

                if (n == 10) {
                    int nRandom = new Random().nextInt(20);
                    UserOnline userRandom = uoArray[nRandom];
                    out.println("Пользователь " + userRandom.getName() + " оплатил платную услугу");
                    out.println("На главной странице показываем пользователя " + userRandom.getName());
                    redis.onlineUsers.remove(userRandom);
                    redis.logPageVisit(Integer.valueOf(userRandom.getName()));
                    Thread.sleep(SLEEP);
                    n = 0;
                }
            }
            //out.println("\n");
        }
        redis.shutdown();
    }
}
