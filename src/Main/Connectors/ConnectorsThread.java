package Main.Connectors;

import java.io.IOException;

public class ConnectorsThread extends Thread { // class to work wth threads in app

    public Connectors connector;

    public ConnectorsThread(Connectors connector) {
        this.connector = connector;
    }

    /**
     * smth what thread should do
     * in this case, thread should call integration
     */
    @Override
    public void run() {

        try {
            this.connector.getConnection();
        } catch (IOException e) {
            System.out.println("Error in thread wth Connector");
            throw new RuntimeException(e);
        }
    }
}
