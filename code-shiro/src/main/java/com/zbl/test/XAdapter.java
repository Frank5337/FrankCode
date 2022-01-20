package com.zbl.test;

import org.springframework.stereotype.Component;

/**
 * @Author: zbl
 * @Date: Created in 2022/1/12 10:12
 * @Description:
 * @Version: $
 */
@Component("xAdapter")
public class XAdapter extends DefaultAdapter {

    @Override
    public String getName() {
        return "xAdapter";
    }

}
