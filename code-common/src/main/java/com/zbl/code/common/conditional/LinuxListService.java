package com.zbl.code.common.conditional;

/**
 * @Author: zbl
 * @Date: Created in 15:03 2019/8/27
 * @Description:
 * @Version: $
 */
public class LinuxListService implements ListService {

    @Override
    public String showListCmd() {
        return "ls";
    }
}
