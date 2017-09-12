package com.muatik;

import com.muatik.service.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class StatsCollectorApplication {
    public static void main(String[] args) {
		SpringApplication.run(StatsCollectorApplication.class, args);
	}

	@Autowired
    StatsService statsService;

	@Scheduled(fixedDelay = 200)
	public void clearExpiredStats() {
        statsService.clear();
    }

//	@Bean
//    CommandLineRunner runner(StatsService statsService) {
//        return (params) -> {
//            double amount = 10;
//            long timestamp = System.currentTimeMillis();
//            for (int i = 0; i < 10; i++) {
//                statsService.add(new Stat(amount, timestamp - (50 +1)* 1000 ));
//            }
//            System.out.println(statsService.getSummary());
//        };
//    };

}
