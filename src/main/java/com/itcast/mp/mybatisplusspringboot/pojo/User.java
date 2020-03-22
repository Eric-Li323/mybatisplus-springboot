package com.itcast.mp.mybatisplusspringboot.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//getter && setter    无参构造方法   全参构造方法
//@TableName  实体类与数据库表映射
@Data
@NoArgsConstructor
@AllArgsConstructor
//@TableName("tb_user")
public class User {

//    @TableId(type = IdType.AUTO)
    private Long id;
    private String userName;

    @TableField(select = false) //查询时不返回该字段的值
    private String password;
    private String name;
    private Integer age;

    @TableField(value = "email") //指定数据库表中的字段名
    private String mail;

    @TableField(exist = false)   //代表该属性在数据库中不存在
    private String address; //在数据库表中不存在
}
