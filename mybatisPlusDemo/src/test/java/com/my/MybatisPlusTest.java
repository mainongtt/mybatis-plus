package com.my;

import com.my.entity.User;
import com.my.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisPlusTest {
    @Autowired(required = false)
    private UserMapper userMapper;

    //SelectList()
    @Test
    public void testSelectList(){
        userMapper.selectList(null).forEach(System.out::println);
    }

    //Insert()
    @Test
    public void testInsert(){
        User user = new User("张三", 23, "zhangsan@atguigu.com");
        int insert = userMapper.insert(user);
        System.out.println("影响的行数" + insert);
        System.out.println(user.getId());
    }
    //删除
    @Test
    public void deleteById(){
        Long id = 6L;
        int delete = userMapper.deleteById(id);
        System.out.println("影响的行数" + delete);
    }

    //批量删除
    @Test
    public void deleteBatchIds(){
        ArrayList<Long> list = new ArrayList<>();
        list.add(3L);
        list.add(4L);
        list.add(5L);
        int delete = userMapper.deleteBatchIds(list);
        System.out.println("影响的行数" + delete);
    }

    //Mapper条件进行删除
    @Test
    public void testDeleteByMap(){
        HashMap<String,Object> map = new HashMap<>();
        map.put("name","Jone");
        map.put("age",18);
        int delete = userMapper.deleteByMap(map);
        System.out.println("影响的行数" + delete);
    }

}
