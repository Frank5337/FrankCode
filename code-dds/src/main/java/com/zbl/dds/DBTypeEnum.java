package com.zbl.dds;


/**
 * @Author: zbl
 * @Date: Created in 2020/11/11
 * @Description: 数据源enum
 * @Version: $
 */
public enum DBTypeEnum {
    /**
     * 主
     */
    MASTER("master"),
    /**
     * 从
     */
    SLAVE("slave")

    ;

    public String name;

    DBTypeEnum(String type ){
        this.name = type ;
    }

    public static String getNameByKey(int key) {
        DBTypeEnum[] enums = DBTypeEnum.values();
        return enums[key].name ;
    }
}
