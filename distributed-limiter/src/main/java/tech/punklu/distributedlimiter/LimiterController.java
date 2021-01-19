package tech.punklu.distributedlimiter;

import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class LimiterController {

    // 创建可放2令牌的桶且每秒放2令牌，0.5秒放1令牌
    private RateLimiter rateLimiter = RateLimiter.create(10);

    @GetMapping("/guava")
    public void guava(){

        // 每次获取一个令牌
        boolean tryAcquire = rateLimiter.tryAcquire(1);
        if (tryAcquire){
            log.info("抢到了!");
            // 扣库存
            // 下单
            // ...
        }else {
            log.info("抢购失败");
        }
    }

    @GetMapping("/sentinel")
    public String sentinel(){
        return "sentinel";
    }

    @Autowired
    private LimitService limitService;

    @GetMapping("/limitInternalMethod")
    public String limitInternalMethod(){
        limitService.process();
        return "limitInternalMethod";
    }
}
