package xyz.nasaknights.deepspace.subsystems.vision;

import xyz.nasaknights.deepspace.RobotMap;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class VisionTCPClient {

    private static volatile double x = 0.0;
    private static volatile double y = 0.0;
    private static volatile double rotation = 0.0;

    private static final Thread visionClient = new Thread() {
        private Socket clientSocket;
        private DataOutputStream out;
        private BufferedReader in;

        @Override
        public synchronized void start() {
            try {
                clientSocket = new Socket(RobotMap.VISION_SERVER_IP, RobotMap.VISION_SERVER_PORT);

                out = new DataOutputStream(clientSocket.getOutputStream());
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            try {
                while (!isInterrupted()) {
                    out.writeBytes("poehali\n"); // If you're wondering what 'poehali' means: https://go.bradleyh.me/evhTP

                    String[] result = in.readLine().split(",");

                    x = Double.parseDouble(result[0]);
                    y = Double.parseDouble(result[1]);
                    rotation = Double.parseDouble(result[2]);

                    System.out.println(result[0] + result[1] + result[2]);

                    sleep(100);
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void interrupt() {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };

    public static void startVisionClient() {
        visionClient.start();
        visionClient.run();
    }

    public static void stopVisionClient() {
        visionClient.interrupt();
    }

    public static double getX() {
        return x;
    }

    public static double getY() {
        return y;
    }

    public static double getRotation() {
        return rotation;
    }
}