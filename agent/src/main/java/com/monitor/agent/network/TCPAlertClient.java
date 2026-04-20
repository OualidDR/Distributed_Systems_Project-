package com.monitor.agent.network;

import java.io.OutputStream;
import java.net.Socket;

import com.monitor.shared.constants.NetworkConstants;
import com.monitor.shared.model.Alert;
import com.monitor.shared.utils.Logger;
import com.monitor.shared.utils.SerializationUtils;

public class TCPAlertClient {
    public void sendAlert(Alert alert) throws Exception {
        try (Socket socket = new Socket(NetworkConstants.SERVER_HOST, NetworkConstants.PORT_TCP);
             OutputStream os = socket.getOutputStream()) {
            os.write(SerializationUtils.serialize(alert));
            Logger.warning("TCPAlertClient",
                "Alerte envoyée : " + alert.type() + " [" + alert.severity() + "]");
        }
    }
}