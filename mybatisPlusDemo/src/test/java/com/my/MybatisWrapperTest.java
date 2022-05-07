package com.my;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.my.entity.User;
import com.my.mapper.UserMapper;
import net.minidev.json.writer.UpdaterMapper;
import org.junit.Test;
import org.junit.platform.commons.util.StringUtils;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisWrapperTest {

    @Autowired(required = false)
    private UserMapper userMapper;

    //测试组合查询条件
    @Test
    public void test01(){
        QueryWrapper<User> queryWrapper = new QueryWrapper();
        //name like '%王%'
        queryWrapper.like("name","J");
        //age between 10 and 20
        queryWrapper.between("age",10,20);
        //email is not null
        queryWrapper.isNotNull("email");
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    //组装排序条件
    public void test02(){
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.orderByAsc("age");
        userQueryWrapper.orderByDesc("id");
        List<User> users = userMapper.selectList(userQueryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    //组装删除条件
    public void test03(){
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.isNull("email");
        int delete = userMapper.delete(userQueryWrapper);
        System.out.println("受影响的行数：" + delete);
    }

    @Test
    //条件的优先级
    public void test04(){
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        //userQueryWrapper.gt("age",20).like("name","a").or().isNull("email");
        userQueryWrapper.like("name","a").and(i -> i.gt("age",20).or().isNull("email"));
        User user = new User("张三", 23, "zhangsan@atguigu.com");
        int update = userMapper.update(user, userQueryWrapper);
        System.out.println("受影响的行数：" + update);
    }

    @Test
    //组装select子句
    public void test05(){
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.select("name","age");
        List<User> users = userMapper.selectList(userQueryWrapper);
    }

    @Test
    //实现子查询
    public void test06(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.inSql("id","select id from t_user where id<=3");
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    //UpdateWrapper
    public void test07(){
        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
        userUpdateWrapper.set("name","Time").set("age",20).set("email","1819592017@qq.com")
                .like("name","a").and(i -> i.gt("age",20).isNotNull("email"));
        int update = userMapper.update(null, userUpdateWrapper);
        System.out.println("受影响的行数为："  + update);

    }

    @Test
    //condition
    public void test08(){

        String username = null;
        Integer ageBegin = 10;
        Integer ageEnd = 10;
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(username),"username",username).gt(ageBegin != null,"age",ageBegin)
                .le(ageEnd != null ,"age",ageEnd);
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    //LambdaQueryWrapper
    public void test09(){
        String username = null;
        Integer ageBegin = 10;
        Integer ageEnd = 10;
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().like(StringUtils.isNotBlank(username),User::getName,username).gt(ageBegin != null,User::getAge,ageBegin)
                .le(ageEnd != null ,User::getAge,ageEnd);
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    //LambdaUpdateWrapper
    public void test10(){
        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
        userUpdateWrapper.lambda().set(User::getName,"Times").set(User::getAge,23).set(User::getEmail,"1819592017@qq.com")
                .like(User::getName,"a").and(i -> i.gt(User::getAge,20).isNotNull(User::getEmail));
        int update = userMapper.update(null, userUpdateWrapper);
        System.out.println("受影响的行数为："  + update);
    }
}
