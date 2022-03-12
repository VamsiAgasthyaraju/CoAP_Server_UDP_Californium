package my.com.server;

import org.eclipse.californium.core.CoapServer;
import org.eclipse.californium.core.config.CoapConfig;
import org.eclipse.californium.elements.config.UdpConfig;

public class MyCoapServer_UDP {

    public static void main(String[] args) {
        System.out.println("Begin...");
        MyCoapServer_UDP obj = new MyCoapServer_UDP();
        obj.code();
        System.out.println("(Force stop if required)");
    }

    void code(){
        CoapConfig.register();
        UdpConfig.register();
        CoapServer server = new CoapServer();

        // handles /abc/... any sub paths also
        server.add(new MyResource_API("abc"));

        System.out.println("Starting CoAP server ...");
        server.start();
    }
}
