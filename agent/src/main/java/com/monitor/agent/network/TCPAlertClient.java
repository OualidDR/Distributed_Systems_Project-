package com.monitor.agent.network;

import com.monitor.shared.model.Alert;
import com.monitor.shared.utils.SerializationUtils;
import com.monitor.shared.constants.Constants;
import java.io.*;
import java.net.Socket;

public class TCPAlertClient {
    public void sendAlert(Alert alert) throws Exception {
        try (Socket socket = new Socket("localhost", Constants.TCP_PORT);
             OutputStream os = socket.getOutputStream()) {
            os.write(SerializationUtils.serialize(alert));
        }
    }
}
