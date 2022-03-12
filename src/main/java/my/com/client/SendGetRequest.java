package my.com.client;

import org.eclipse.californium.core.coap.Request;
import org.eclipse.californium.core.coap.Response;

public class SendGetRequest implements Runnable {

    String path;
    SendGetRequest(String path){
        this.path = path;
    }
    @Override
    public void run() {
        Request request = Request.newGet();
        request.setURI(path);
        request.send();


        Response response = null;
        try {
            response = request.waitForResponse(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("received "+response);
    }
}
