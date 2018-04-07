package site.shzu.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Author: Kinson
 * @Description: Redis测试
 * @Date: Created in 2018/04/07 19:05
 * @Version: 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class RedisCacheTest {
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Test
    public void redisTest() throws Exception {
        //保存字符串
        stringRedisTemplate.opsForValue().set("aaa", "111");
        //读取字符串
        String aaa = stringRedisTemplate.opsForValue().get("aaa");
        System.out.println(aaa);
    }
}
