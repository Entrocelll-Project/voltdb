package org.example.client;

import org.example.voltdb.VoltDb;
import org.voltdb.client.Client;
import org.voltdb.client.ClientConfig;
import org.voltdb.client.ClientFactory;
import org.voltdb.client.ClientResponse;
import org.voltdb.client.ProcCallException;

import java.io.IOException;
import java.math.BigDecimal;

public class VoltdbClient {

    private String id;
    private int port;
    private Client client;

    public VoltdbClient() {
        this.id = "34.18.69.164";
        this.port = 32804;

        try {
            this.client = getClientVoltDB();
        } catch (IOException | ProcCallException e) {
            e.printStackTrace();
        }
    }

    public Client getClientVoltDB() throws IOException, ProcCallException {
        ClientConfig config = new ClientConfig();
        Client client = ClientFactory.createClient(config);
        client.createConnection(id, port);
        return client;
    }

    public void closeClient() throws InterruptedException {
        if (client != null) {
            client.close();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // Create an instance of VoltdbClient
        VoltdbClient voltdbClient = new VoltdbClient();

        // Create an instance of VoltDb using the client
        VoltDb voltDb = new VoltDb(voltdbClient);

        // Call the getSMSBalance method
        String msisdn = "5340344358";
        String smsBalance = voltDb.getPackageName(msisdn);

        // Print the result
        System.out.println("voice Balance for MSISDN " + msisdn + ": " + smsBalance);

        // Close the VoltDB client when done
        voltdbClient.closeClient();
    }

    // getClientVoltDB metodunu getClient olarak değiştirdim
    public Client getClient() {
        return client;
    }
}
