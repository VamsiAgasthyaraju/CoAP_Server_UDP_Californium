package my.com.server;

import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.server.resources.CoapExchange;
import org.eclipse.californium.core.server.resources.Resource;

public class MyResource_API extends CoapResource {
    String apiName;
    public MyResource_API(String name) {
        super(name);
        apiName = name;
    }

    @Override
    public void handlePOST(CoapExchange exchange) {
        //System.out.println(exchange);
        String payload = new String(exchange.getRequestPayload());
        System.out.println("API Called: " +
                exchange.getRequestCode() + " " +
                apiName + " " +
                payload);
        exchange.respond("response from POST "+
                exchange.getRequestCode() + " " +
                apiName + " " +
                payload);
    }

    @Override
    public void handleGET(CoapExchange exchange) {
        System.out.println("GET called "+exchange);
        System.out.println(exchange.getRequestText());
        System.out.println(exchange.getQueryParameter("p1"));
        System.out.println(exchange.getRequestPayload().toString());
        System.out.println(exchange.getRequestCode());
        System.out.println(exchange.getRequestOptions());
        exchange.respond("response from GET "+apiName);
    }
    @Override
    public Resource getChild(String name) {
        //System.out.println("inside getChild : "+name);
        MyResource_SubPath wildcardObj = new MyResource_SubPath();
        return wildcardObj.wildcard;
    }
}
