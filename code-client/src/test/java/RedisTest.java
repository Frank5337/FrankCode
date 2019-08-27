import com.zbl.code.ClientApplication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @Author: zbl
 * @Date: Created in 15:36 2019/8/26
 * @Description:
 * @Version: $
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ClientApplication.class)
public class RedisTest {
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private RedisTemplate redisTemplate;

    @Test
    public void test01() throws Exception{
        stringRedisTemplate.opsForValue().set("Lp","利普");
        //断言 如果相等  继续向下执行, 如果不相等 抛异常
        try {
            Assert.assertEquals("利普",stringRedisTemplate.opsForValue().get("Lp"));
            System.out.println(stringRedisTemplate.opsForValue().get("Lp"));
        } catch (Error e) {
            e.printStackTrace();
        }

    }

}
