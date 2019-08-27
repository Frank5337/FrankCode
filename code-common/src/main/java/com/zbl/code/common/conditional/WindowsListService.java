package com.zbl.code.common.conditional;

/**
 * @Author: zbl
 * @Date: Created in 15:02 2019/8/27
 * @Description:
 * @Version: $
 */
public class WindowsListService implements ListService {

    @Override
    public String showListCmd() {
        return "dir";
    }

}
