package tech.punklu.distributedjob.Job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MyJob {


    // fixedDelay每三秒执行一次,从前次任务的结束到下次任务的开始间隔为三秒
    // fixedRate 时间间隔是前次任务的开始和下次任务的开始
    // initialDelay设置启动后5秒再开始执行，默认是启动时就开始执行
    // @Scheduled(fixedDelay = 3000,initialDelay = 5000)
    // cron：cron表达式，支持定时调用
    /*@Scheduled(cron = "0,5 * * * * ?")
    public void process(){
        log.info("process.....start");
    }*/

    @Scheduled(cron = "0,5 * * * * ?")
    public void process1(){
        log.info("process1.....start");
    }

    @Scheduled(cron = "0,5 * * * * ?")
    public void process2(){
        log.info("process2.....start");
    }
}
