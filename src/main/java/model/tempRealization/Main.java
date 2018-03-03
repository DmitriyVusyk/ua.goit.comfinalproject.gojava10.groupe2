package model.tempRealization;

import model.tempController.QueryFromYoutube;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class Main {
    private static String channelId = "UC8mndg3Tr2g9iMoJMI4ob5A";
    public static void main(String[] args) throws InterruptedException {
//        test1();
//        Thread.sleep(700);
        System.out.println("\n=================================================\n");
        test2();
    }

    /**
     * тест №1 - отримуєм назву каналу по айді
     * @throws InterruptedException - лінь ловити в try/catch
     */
    private static void test1() throws InterruptedException{
        System.out.printf("Test 1: \"Query one channel\". \n" +
                "Channel id: %s\n" +
                "Task: get title name from YouTubeAPI, by channel id\n\n", channelId);
        Thread.sleep(700);

        QueryFromYoutube query = new QueryFromYoutube();
        query.setChannelId(channelId);
        query.makeQuary();
        String tittle = query.getTitle();
        System.out.println("Tittle = \"" + tittle + "\"");
    }

    /**
     * тест №2 - отримуємо всі необхідні данні
     *  назва каналу - tittle
     *  кількість переглядів - viewCount
     *  кількість відео -videoCount
     *  subscribeHidden - булєвське значення - чи приховано в налаштуваннях інфа по фоловерах
     *  subscribeCount - кількість фоловерів
     *  published - час створення каналу
     * @throws InterruptedException
     */
    private static void test2() throws InterruptedException{
        System.out.printf("Test 2: \"Query one channel\". \n" +
                "Channel id: %s\n" +
                "Task: get all fields from YouTubeAPI, by channel id\n\n", channelId);


        QueryFromYoutube query = new QueryFromYoutube();
        query.setChannelId(channelId);
//(!) **тут затримка в запиті - знайти і помістити в окремий потік
        query.makeQuary();

        String tittle = query.getTitle();
        Integer viewCount = query.getViewCount();
        Integer videoCount = query.getVideoCount();
        Integer subscriberCount = query.getSubscriberCount();
        Boolean subscriberHidden = query.getHiddenSubscriberCount();
        Instant published = query.getPublishedAt();

        System.out.println("Tittle            : \"" + tittle + "\"");
        Thread.sleep(900);
        System.out.println("View count        = " + viewCount);
        Thread.sleep(900);
        System.out.println("Video count       = " + videoCount);
        Thread.sleep(900);
        System.out.println("Subscriber hidden = " + subscriberHidden);
        Thread.sleep(900);
        System.out.println("Subscriber count  = " + subscriberCount);
        Thread.sleep(900);
        System.out.println("Published at      : " + new Main().publishedToString(published));
    }
    private String publishedToString(Instant timestamp){
        LocalDateTime ldt = LocalDateTime.ofInstant(timestamp, ZoneId.systemDefault());
        return String.format("%s %d %d at %d:%d%n", ldt.getMonth(), ldt.getDayOfMonth(),
                ldt.getYear(), ldt.getHour(), ldt.getMinute());
    }
}
