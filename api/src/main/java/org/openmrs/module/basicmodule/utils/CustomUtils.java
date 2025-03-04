package org.openmrs.module.basicmodule.utils;

import org.openmrs.api.context.Context;
import org.openmrs.module.basicmodule.constants.Sync2Constants;

import java.net.InetSocketAddress;
import java.net.Socket;

public class CustomUtils {

    public static String serverInfo;
    public static boolean isOnline() {
        boolean status = false;
        Socket sock = new Socket();
        final String openHimUlr = Context.getAdministrationService().getGlobalProperty(Sync2Constants.GLOBAL_PROPERTY_OPENHIM_NIDA_API);

        int port = 80;
        if (openHimUlr != null && !openHimUlr.isEmpty()) {
            String[] value = openHimUlr.replace("http://","").replace("https://", "").split(":");
            serverInfo = value[0];
            port = Integer.parseInt(value[1]);
        }

        InetSocketAddress address = new InetSocketAddress(serverInfo, port);

        try {
            sock.connect(address, 3000);
            if (sock.isConnected()) status = true;
        } catch (Exception e) {
            status = false;
        } finally {
            try {
                sock.close();
            } catch (Exception e) {
            }
        }

        return status;
    }

}
