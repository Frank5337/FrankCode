package com.code.zbl.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @Author: zbl
 * @Date: Created in 2021/1/10
 * @Description:
 * @Version: $
 */
@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String name;

    private Integer age;
}
