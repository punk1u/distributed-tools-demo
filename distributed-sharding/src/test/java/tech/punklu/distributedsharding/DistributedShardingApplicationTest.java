package tech.punklu.distributedsharding;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Random;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class DistributedShardingApplicationTest {

    @Autowired
    private OrderDao orderDao;

    @Test
    public void insert(){
        for (int i = 0 ;i<10;i++){
            OrderEntity orderEntity = new OrderEntity();
            orderEntity.setUserId(new Random().nextInt(999));
            orderDao.save(orderEntity);
        }

    }

    @Test
    public void findByOrderId(){
        OrderEntity orderEntity = orderDao.findByOrderId(558795615644397569L);
        log.info("OrderId={}",orderEntity);

    }

    @Test
    public void findByUserId(){
        List<OrderEntity> orderEntity = orderDao.findByUserId(150);
        log.info("UserId={}",orderEntity);

    }
}
