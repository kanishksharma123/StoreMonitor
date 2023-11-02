package com.example.StoreMonitor;
import com.example.StoreMonitor.Repositories.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StoreMonitorApplication {

	public static void main(String[] args) {
		SpringApplication.run(StoreMonitorApplication.class, args);
	}

}
