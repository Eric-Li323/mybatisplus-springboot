package com.itcast.mp.mybatisplusspringboot;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itcast.mp.mybatisplusspringboot.mapper.UserMapper;
import com.itcast.mp.mybatisplusspringboot.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@RunWith(SpringJUnit4ClassRunner)
@SpringBootTest
class MybatisplusSpringbootApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {
    }

    @Test
    public void testSelectList(){
        List<User> userList = userMapper.selectList(null);
        for (User user : userList) {
            System.out.println(user);
        }
    }

    @Test
    public void testInsert(){
        User user = new User();
        user.setUserName("曹操1");
        user.setPassword("123456");
        user.setAge(99);
        user.setMail("caocao@qq.com");
        user.setAddress("北京");

        int insert = userMapper.insert(user);//返回值为数据库受影响的行数,并不是自增长的行数id
        System.out.println("result =>"+insert);

        //获取自增长的id值，原因是mybatisplus插件新增数据后，自增长id会回填到user对象当中
        System.out.println("id =>"+user.getId());
    }

    @Test
    public void testSelectById(){
        User user = userMapper.selectById(1L);
        System.out.println(user);
    }

    @Test
    public void testUpdateById(){
        User user = new User();
        user.setId(1L);  //条件，根据id更新
        user.setAge(19); //更新的字段
        user.setPassword("666666");
        int result = userMapper.updateById(user);
        System.out.println("resutl  =>"+result);
    }

    @Test
    public void testUpdate(){
        User user = new User();

        user.setAge(20); //更新的字段
        user.setPassword("88888888");

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name","zhangsan"); //根据数据库字段做更新（更新条件）


        int result = userMapper.update(user, wrapper);
        System.out.println("result =>"+result);
    }

    @Test
    public void testUpdate2(){

        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.set("age",21).set("password","999999") //更新的字段
        .eq("user_name","zhangsan");   //更新的条件


        int result = userMapper.update(null, wrapper);
        System.out.println("result =>"+result);
    }

    @Test
    public void testDeleteById(){
        //根据id删除数据
        int result = userMapper.deleteById(9L);
        System.out.println("result =>"+result);
    }

    @Test
    public void testDeleteByMap(){
        Map<String,Object> map = new HashMap<>();
        map.put("user_name","zhangsan");
        map.put("password","999999");
        //根据map删除数据，多条件之间是and关系
        int result = userMapper.deleteByMap(map);
        System.out.println("result =>"+result);
    }

    @Test
    public void testDelete(){
        //用法1
//        QueryWrapper<User> wrapper = new QueryWrapper<>();
//        wrapper.eq("user_name","曹操1")
//                .eq("password","123456");
        //用法2  (推荐用法，不需要写数据库字段)
        User user = new User();
        user.setPassword("123456");
        user.setUserName("曹操");
        QueryWrapper<User> wrapper = new QueryWrapper<>(user);

        //根据包装条件做删除  条件为and并列关系
        int result =userMapper.delete(wrapper);
        System.out.println("result =>"+result);
    }

    @Test
    public void testDeleteBatchIds(){
        //根据id批量删除数据
        int result = userMapper.deleteBatchIds(Arrays.asList(10L, 11L));
        System.out.println("result =>"+result);
    }

    /**
     * 查询操作
     */

    @Test
    public void testSelectBatchIds(){
        //根据id集合批量查询数据
        List<User> userList = userMapper.selectBatchIds(Arrays.asList(2L, 3L, 4L, 100L));
        for (User user : userList) {
            System.out.println(user);
        }
    }

    @Test
    public void testSelectOne(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //查询条件
        wrapper.eq("user_name","lisi");
        //查询的数据超过一条时，会抛出异常
        User user = userMapper.selectOne(wrapper);
        System.out.println(user);
    }

    @Test
    public void testSelectCount(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.gt("age",20); //条件：年龄大于20岁的用户会被查询出来

        //根据条件查询数据条数
        Integer count = userMapper.selectCount(wrapper);
        System.out.println("count =>"+ count);
    }

    @Test
    public void testSelctList(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //设置查询条件
        wrapper.like("email","qq");

        List<User> userList = userMapper.selectList(wrapper);
        for (User user : userList) {
            System.out.println(user);

        }
    }

    //查询指定的部分字段
    @Test
    public void testSelect(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //两个条件 用 or()连接表示 “或者”，不使用or(),直接连接表示and
        wrapper.eq("name","王五")
                .or()
                .eq("age","21")
                .select("id","name","age");  //指定查询的字段

        List<User> userList = userMapper.selectList(wrapper);
        for (User user : userList) {
            System.out.println(user);
        }
    }

    /**
     * 分页查询
     */
    @Test
    public void testSelectPage(){

        Page<User> page = new Page<>(2,1); //查询第2页，查询1条数据


        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //设置查询条件
        wrapper.like("email","qq");

        IPage<User> iPage = userMapper.selectPage(page, wrapper);
        System.out.println("数据总条数： "+iPage.getTotal());
        System.out.println("数据总页数： "+iPage.getPages());
        System.out.println("当前页数： "+iPage.getCurrent());

        List<User> records = iPage.getRecords();
        for (User record : records) {
            System.out.println(record);
        }
    }

    /**
     * 自定义的sql
     */

    @Test
    public void testFindById(){
        User user = userMapper.findById(2L);
        System.out.println(user);
    }

    /**
     * allEq使用
     */
    @Test
    public void testAllEq(){

        Map<String,Object> params = new HashMap<>();
        params.put("name","李四");
        params.put("age","20");
        params.put("password",null);


        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //查询条件为and关系 SELECT id,uer_name,email AS mail FROM tb_user WHERE password IS NULL AND name = ? AND age = ?
        //wrapper.allEq(params);

        //查询条件为and关系并且忽视null   SELECT id,uer_name,email AS mail FROM tb_user WHERE name = ? AND age = ?
        //wrapper.allEq(params,false);

        //筛选过滤条件  params只有符合条件的字段才作为判断条件
        //此时的sql 为  SELECT id,user_name,name,age,email AS mail FROM tb_user WHERE age = ?
        //wrapper.allEq((k,v) -> (k.equals("age") || k.equals("id")),params);
        wrapper.allEq((k,v) -> (k.equals("age") || k.equals("id") || k.equals("name")),params);

        List<User> userList = userMapper.selectList(wrapper);
        for (User user : userList) {
            System.out.println(user);
        }
    }

    /**
     * 基本的比较操作
     * eq  等于 =
     * ne  不等于 <>
     * gt  大于 >
     * ge  大于等于 >=
     * lt  小于 <
     * le  小于等于 <=
     * between        BETWEEN 值1 AND 值2
     * notBetween     NOT BETWEEN 值1 AND 值2
     * in             字段 IN (value.get(0),value.get(1),...)
     * notin          字段 NOT IN (v0,v1,...)
     */
    @Test
    public void testEq(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();

        //SELECT id,user_name,password,name,age,email FROM tb_user WHERE password = ? AND age >= ？AND name IN (?,?,?)
        wrapper.eq("password","123456")
                .ge("age",20)
                .in("name","李四","王五","赵六");
        List<User> users = userMapper.selectList(wrapper);
        for (User user : users) {
            System.out.println(user);

        }
    }

    /**
     * 模糊查询
     * like          Like '%值%'          例：like("name","王") --> name like '%王%'
     * notLike       notlike '%值%'       例：notlike("name","王") --> name not like '%王%'
     * likeLeft      like "%值'           例：likeLeft("name","王")--> name like '%王'
     * likeRight     like "值%'           例：likeRight("name","王")--> name like '王%'
     */
    @Test
    public void testLike(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.likeLeft("name","五");

        List<User> userList = userMapper.selectList(wrapper);
        for (User user : userList) {
            System.out.println(user);
        }
    }

    /**
     * 排序
     * orderBy       ORDERBY 字段,...         例：orderBy(true,true,"id","name") --> order by id ASC,name ASC
     * orderByAsc    ORDERBY 字段,...ASC      例：orderByAsc("id","name")        --> order by id ASC,name ASC
     * orderByDesc   ORDERBY 字段,...DESC     例：orderByDesc("id","name")       --> order by id DESC,name DESC
     */
    @Test
    public void testOrderByAgeDesc(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //按照年级倒序排序
        wrapper.orderByDesc("age");

        List<User> userList = userMapper.selectList(wrapper);
        for (User user : userList) {
            System.out.println(user);
        }
    }

    /**
     * 逻辑查询
     * or    拼凑OR       主动调用or表示紧接着下一个方法不是用and连接（不使用or,则默认使用and连接）
     * and   AND嵌套      例：and(i -> i.eq("name","李白").ne("status","活着"))  -->  and (name = '李白' and status <> '活着'）
     */
    @Test
    public void testOr(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //两个条件 用 or()连接表示 “或者”，不使用or(),直接连接表示and
        wrapper.eq("name","王五").or().eq("age","21");

        List<User> userList = userMapper.selectList(wrapper);
        for (User user : userList) {
            System.out.println(user);
        }
    }

}
