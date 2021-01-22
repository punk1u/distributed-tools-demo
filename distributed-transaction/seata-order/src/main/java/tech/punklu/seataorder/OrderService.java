package tech.punklu.seataorder;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class OrderService {

    public Boolean create(Integer count){
        // 调用product 扣库存
        String url = "http://localhost:8086/deduct?productId=5001&count=5";
        Boolean result = new RestTemplate().getForObject(url,Boolean.class);
        if (result != null && result){
            // 可能抛出异常
            if (count == 5){
                throw new RuntimeException("order发生异常了");
            }
            log.info("数据库开始创建订单");

            return true;
        }
        return false;
    }
}
