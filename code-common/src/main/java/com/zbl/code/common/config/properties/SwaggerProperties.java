package com.zbl.code.common.config.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author zbl
 * @date 2019/9/23
 */
@Data
@Configuration
public class SwaggerProperties {
    private String version = "1.0";
    private String license = "Apache License 2.0";
    private String licenseUrl = "https://www.apache.org/licenses/LICENSE-2.0";
    @Value("${zbl-code.swagger.basePackage}")
    private String basePackage;
    @Value("${zbl-code.swagger.title}")
    private String title;
    @Value("${zbl-code.swagger.description}")
    private String description;
    @Value("${zbl-code.swagger.contactName}")
    private String contactName;
    @Value("${zbl-code.swagger.contactUrl}")
    private String contactUrl;
    @Value("${zbl-code.swagger.contactEmail}")
    private String contactEmail;
}
