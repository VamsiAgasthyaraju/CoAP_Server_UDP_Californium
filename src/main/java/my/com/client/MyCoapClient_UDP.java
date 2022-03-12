package my.com.client;

import java.util.concurrent.*;

public class MyCoapClient_UDP {
    public static void main(String[] args) {
        System.out.println("Client Begin ...");
        MyCoapClient_UDP obj = new MyCoapClient_UDP();
        obj.code();
        System.out.println("Client Done.");
    }
    void code (){
        myGets();
        //myPosts();
    }
    void myGets(){
        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        SendGetRequest req1 = new SendGetRequest("coap://localhost:5683/abc");
        threadPool.execute(req1);

        threadPool.execute(new SendGetRequest("coap://localhost:5683/abc/hi"));
        threadPool.execute(new SendGetRequest("coap://localhost:5683/abc/hi/one"));
        threadPool.execute(new SendGetRequest("coap://localhost:5683/abc/hi/two"));
        threadPool.execute(new SendGetRequest("coap://localhost:5683/abc/hi/three/"));

        try {
            threadPool.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadPool.shutdownNow();
    }
    void myPosts(){
        ExecutorService threadPool = Executors.newFixedThreadPool(3);


        SendPostRequest req1 = new SendPostRequest("coap://localhost:5683/abc","{my custom Payload}");
        threadPool.execute(req1);

        threadPool.execute(new SendPostRequest("coap://localhost:5683/abc/hi","{my custom Payload}"));
        threadPool.execute(new SendPostRequest("coap://localhost:5683/abc/hi/one","{my custom Payload}"));
        threadPool.execute(new SendPostRequest("coap://localhost:5683/abc/hi/two","{my custom Payload}"));
        threadPool.execute(new SendPostRequest("coap://localhost:5683/abc/hi/three/","{my custom Payload}"));

        try {
            threadPool.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadPool.shutdownNow();
    }
}
