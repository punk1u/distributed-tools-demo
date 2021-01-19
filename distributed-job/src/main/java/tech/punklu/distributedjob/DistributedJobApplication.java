package tech.punklu.distributedjob;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@SpringBootApplication
// 开启定时任务
@EnableScheduling
public class DistributedJobApplication {

    public static void main(String[] args) {
        SpringApplication.run(DistributedJobApplication.class, args);
    }


    @Bean
    public TaskScheduler taskScheduler(){
        ThreadPoolTaskScheduler taskExecutor  = new ThreadPoolTaskScheduler();
        taskExecutor.setPoolSize(10);
        return taskExecutor;
    }
}
