package com.wyl;

import com.wyl.utils.SecurityUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FarmApplicationTests {

    @Test
    void contextLoads() {

        String s = SecurityUtils.encryptPassword("123456");
        System.out.println(s);
    }

}
