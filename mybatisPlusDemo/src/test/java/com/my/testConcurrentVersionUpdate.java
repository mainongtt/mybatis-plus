package com.my;

import com.my.entity.Product;
import com.my.mapper.ProductMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class testConcurrentVersionUpdate {
    @Autowired(required = false)
    ProductMapper productMapper;

    @Test
    public void test01(){
        Product p1 = productMapper.selectById(1L);
        Product p2 = productMapper.selectById(1L);
        p1.setPrice(p1.getPrice() + 50);
        int result1 = productMapper.updateById(p1);
        System.out.println("小李修改的结果为： " + result1);

        p2.setPrice(p2.getPrice() - 30);
        int result2 = productMapper.updateById(p2);
        System.out.println("小王修改的结果为：" + result2);
        int result3 = -1;
        if(result2 == 0){
            p2 = productMapper.selectById(1L);
            p2.setPrice(p2.getPrice() - 30);
            result3 = productMapper.updateById(p2);
        }
        System.out.println("小王重新修改的结果为" + result3);
    }
}
