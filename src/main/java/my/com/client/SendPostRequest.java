package my.com.client;

import org.eclipse.californium.core.coap.Request;
import org.eclipse.californium.core.coap.Response;

public class SendPostRequest implements Runnable {

    String path;
    String payload;
    SendPostRequest(String path, String payload){
        this.path = path;
        this.payload = payload;
    }
    @Override
    public void run() {
        Request request = Request.newPost();
        request.setURI(path);
        request.setPayload(payload.getBytes());
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
