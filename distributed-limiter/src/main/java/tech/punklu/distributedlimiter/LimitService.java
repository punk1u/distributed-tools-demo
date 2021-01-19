package tech.punklu.distributedlimiter;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.stereotype.Service;

@Service
public class LimitService {

    @SentinelResource("LimiterService.process")
    public String process(){
        return "process";
    }
}
