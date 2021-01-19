package tech.punklu.distributedsharding;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DistributedShardingApplicationTest {

    @Autowired
    private OrderDao orderDao;

    @Test
    public void insert(){
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderId(System.currentTimeMillis());
        orderEntity.setUserId(new Random().nextInt(999));
        orderDao.save(orderEntity);
    }
}
