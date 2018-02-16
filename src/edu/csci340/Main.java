package edu.csci340;

import edu.csci340.web.client.TcpClient;
import edu.csci340.web.server.HttpServer;

import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) {
        try {
            final int port = 1180;
            ServerSocket serverSocket = new ServerSocket(port);
            // TcpClient tcpClient = new TcpClient(arg[0], arg[1])
            TcpClient tcpClient = new TcpClient("HEAD","www.cs.xu.edu/csci340/17s/hereIAm.html", "theNewFile.html", 80);
            tcpClient.delegate();
            while(true) {
                System.out.println("Starting server...");
                Socket socket = serverSocket.accept();
                HttpServer httpRequest = new HttpServer(socket);
                // thread
                Thread thread = new Thread(httpRequest);
                thread.start();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
