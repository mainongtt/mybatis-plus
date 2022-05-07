package com.my.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Data;
import lombok.Getter;

@Getter
public enum SexEnmu {
    MALE(1,"男"),
    FMALE(2,"女");


    @EnumValue
    private Integer sex;
    private String sexName;

    SexEnmu(Integer sex, String sexName) {
        this.sex = sex;
        this.sexName = sexName;
    }
}
