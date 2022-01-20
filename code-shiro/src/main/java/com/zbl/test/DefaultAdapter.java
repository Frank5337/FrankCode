package com.zbl.test;

import org.springframework.stereotype.Component;

/**
 * @Author: zbl
 * @Date: Created in 2022/1/12 10:12
 * @Description:
 * @Version: $
 */

@Component("defaultAdapter")
public class DefaultAdapter implements InterfaceAdapter {

    @Override
    public String getName() {
        return "defaultAdapter";
    }

}
