import com.lmandy.WebApplication;
import com.lmandy.bean.User;
import com.lmandy.dao.IUserMapper;
import com.lmandy.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;

/**
 * Created by lmandy on 2017/10/9.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes=WebApplication.class)// 指定spring-boot的启动类
public class TestClass {

    @Autowired
    private IUserMapper userMapper;

    @Autowired
    private IUserService userService;

    @Autowired
    private DataSourceProperties dataSourceProperties;
    @Autowired
    private ApplicationContext applicationContext;

    @Value("${test.msg}")
    private String msg;

    @Test
    public void testOut(){
        System.out.println("测试输入："+msg);
    }

    @Test
    public void getUser(){
        System.out.println("spring boot 测试");

        User user = userMapper.getById(1);

        System.out.println(user.toString());

    }
    @Test
    public void insertUser(){
        User user = new User();
        user.setUserName("zhaowei666666");
        user.setPassWord("123");
        userService.insert(user);
        System.out.println("插入新用户id:"+user.getId());

//        if(user.getId() !=null){
//            userMapper.deleteUser(user.getId());
//        }
    }

    @Test
    public void testDataSource() throws Exception {
        // 获取配置的数据源
        DataSource dataSource = applicationContext.getBean(DataSource.class);
        // 查看配置数据源信息
        System.out.println(dataSource);
        System.out.println(dataSource.getClass().getName());
        System.out.println(dataSourceProperties.getName());
        String dataUsername = dataSourceProperties.getDataUsername();
    }

    @Test
    public void testMaper(){

    }

}
