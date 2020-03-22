package com.itcast.mp.mybatisplusspringboot;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.itcast.mp.mybatisplusspringboot.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TestUserMapper {

    //ActiveRecord 模式 CRUD
    @Test
    public void testSelectById(){
        User user = new User();
        user.setId(2L);

        User user1 = user.selectById();
        System.out.println(user1);
    }

    @Test
    public void testInsert(){
        User user = new User();
        user.setUserName("liubei");
        user.setPassword("123456");
        user.setAge(30);
        user.setName("刘备");
        user.setMail("liubei@qq.com");


        boolean insert = user.insert();
        System.out.println("result =>"+insert);
        System.out.println("userId =>"+user.getId());
    }

    @Test
    public void testUpdata(){
        User user = new User();
        user.setId(13L); //查询条件
        user.setAge(31);

        boolean result = user.updateById();
        System.out.println("result =>"+result);
    }

    @Test
    public void testDelete(){
        User user = new User();
        boolean delete = user.deleteById(13L);//删除id为13的user
        System.out.println("result =>"+delete);
    }

    @Test
    public void testSelect(){
        User user= new User();
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.ge("age",30); //大于等于30岁的用户
        List<User> userList = user.selectList(wrapper);
        for (User user1 : userList) {
            System.out.println(user1);
        }
    }
}
