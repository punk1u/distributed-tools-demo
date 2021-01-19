package tech.punklu.distributedjob.Job;

import com.google.gson.Gson;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import com.xxl.job.core.log.XxlJobLogger;
import com.xxl.job.core.util.ShardingUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
public class MyXxlJob {


    // 注解中的值即为任务调度中心中的JobHandler的值
    @XxlJob("myXxlJobHandler")
    public ReturnT<String> execute(String param) {


        ShardingUtil.ShardingVO shardingVO = ShardingUtil.getShardingVo();
        // 从数据库查询用户数据，并分别调度处理
        // 两台服务器，假设分别处理一半的数据
        List<Integer> list = Arrays.asList(1,2,3,4);
        for (Integer i: list ) {
            // 取模判断当前执行器是否要处理这部分数据
            if (i % shardingVO.getTotal() == shardingVO.getIndex()){
                // 将日志打印到Xxl-job中的执行日志中，方便在管理中心直接查看
                log.info("myXxlJobHandler execute...user={},shardingVo={}",
                        i,new Gson().toJson(shardingVO));
            }

        }

        return ReturnT.SUCCESS;
    }
}
