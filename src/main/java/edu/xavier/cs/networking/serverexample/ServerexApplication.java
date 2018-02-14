package edu.xavier.cs.networking.serverexample;

import edu.xavier.cs.networking.serverexample.service.web.TCPClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class ServerexApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerexApplication.class, args);
		try {
			TCPClient tcpClient = new TCPClient("fileinfo.com/extension/html", "/Users/o_in25/Downloads/P1ClientStarters", "test.html", 80);
			tcpClient.dumpToFile(5000);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}