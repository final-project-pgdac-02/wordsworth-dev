package com.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.app.service.IBookService;
import com.app.service.IFeedbackService;

@SpringBootApplication
public class WordsworthApplication {
	
	@Autowired
	IFeedbackService feedbackServ;

	public static void main(String[] args) {
		SpringApplication.run(WordsworthApplication.class, args);
	}
	
	@Scheduled(fixedDelay = 30000)
	void testScheduled() throws InterruptedException{
		System.out.println("Scheduled method runnnnnnnnnnnnnnnnninggggggggg");
//		bookServ.getAllBooks().forEach(s->System.out.println(s.getBookTitle()));
		System.out.println(feedbackServ.updateAllBookRatings());
//		Thread.sleep(0);
	}

}

@Configuration
@EnableScheduling
class SpringConfig {
   
}
