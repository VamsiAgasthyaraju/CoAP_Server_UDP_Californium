package my.com.server;

import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.server.resources.CoapExchange;
import org.eclipse.californium.core.server.resources.Resource;

import java.util.List;

public class MyResource_SubPath {
    CoapResource wildcard = new CoapResource("*") {
        @Override
        public void handlePOST(CoapExchange exchange) {

            //System.out.println(exchange.getRequestCode());
            //System.out.println(exchange.getRequestOptions());

            String payload = new String(exchange.getRequestPayload());
            String apiPath = "";
            String delimiter = "";
            List fullPath = exchange.getRequestOptions().getUriPath();
            for(Object ele : fullPath){
                apiPath += delimiter + ele;
                delimiter = "/";
            }
            System.out.println("API Called: " +
                    exchange.getRequestCode() + " " +
                    apiPath + " " +
                    payload);
            exchange.respond("response from " +
                    exchange.getRequestCode() + " " +
                    apiPath + " " +
                    payload);
        }

        @Override
        public void handleGET(CoapExchange exchange) {

            //System.out.println(exchange.getRequestCode());
            //System.out.println(exchange.getRequestOptions());
            String apiPath = "";
            String delimiter = "";
            List fullPath = exchange.getRequestOptions().getUriPath();
            for(Object ele : fullPath){
                apiPath += delimiter + ele;
                delimiter = "/";
            }
            System.out.println("API Called: " +
                    exchange.getRequestCode() +" " +
                    apiPath);
            exchange.respond("response from " +
                    exchange.getRequestCode() + " " +
                    apiPath);
        }
        @Override
        public Resource getChild(String name) {
            //System.out.println("inside getChild : "+name);
            MyResource_SubPath wildcardObj = new MyResource_SubPath();
            return wildcardObj.wildcard;
        }
    };
}
