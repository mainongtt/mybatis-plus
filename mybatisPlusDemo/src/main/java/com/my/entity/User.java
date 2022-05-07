package com.my.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_user")
public class User {
    private Long id;
    @TableField("name")
    private String name;
    private Integer sex;
    private Integer age;
    private String email;

    @TableLogic
    @TableField("isDeleted")
    private Integer isDeleted;

    public User(String name, Integer age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.isDeleted = 0;
    }
}
