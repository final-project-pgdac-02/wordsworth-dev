package com.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import com.app.service.IFeedbackService;

@SpringBootApplication
public class WordsworthApplication {
	
	@Autowired
	IFeedbackService feedbackServ;

	public static void main(String[] args) {
		SpringApplication.run(WordsworthApplication.class, args);
	}
	
	@Scheduled(fixedDelay = 60000)
	void testScheduled() throws InterruptedException{
		System.out.println("Scheduled method runnning");
		System.out.println(feedbackServ.updateAllBookRatings());
	}
}

@Configuration
@EnableScheduling
class SpringConfig {
   
}
