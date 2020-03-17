package com.zbl.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: zbl
 * @Date: Created in 16:08 2020/3/4
 * @Description:
 * @Version: $
 */
@Data
public class UserJson implements Serializable {

    private String fileName;

    private String secDomainName;

    private String secDomainIndex;
}
