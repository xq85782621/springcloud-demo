import cn.hutool.core.util.StrUtil;
import com.meizhi.RedisApplication;
import com.meizhi.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RedisApplication.class)
public class RedisTest {


    @Autowired
    private RedisTemplate redisTemplate;




    @Test
    public void test(){
        User user = new User();
        user.setUsername("许强");
        user.setAddress("中环城");
        user.setId(1);
        redisTemplate.opsForValue().set("userInfo", user);
    }

}
