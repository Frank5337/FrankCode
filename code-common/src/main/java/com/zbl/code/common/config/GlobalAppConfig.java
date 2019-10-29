package com.zbl.code.common.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.zbl.code.common.auth.AuthInterceptor;
import com.zbl.code.common.convert.EnumConverterFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.math.BigInteger;
import java.util.List;

/**
 * @Author: zbl
 * @Date: Created in 10:06 2019/8/27
 * @Description: 注册 拦截器(拦截OPTION请求、登录验证) / 转化器 (转化枚举类型)
 * @Configuration 等同于spring的XML配置文件；使用Java代码可以检查类型安全。
 * @SuppressWarnings("deprecation") 禁止与弃用相关的警告
 * 当我们开启了EbableAspectJAutoProxy后,每次Bean的装配时,都会执行这段逻辑.
 * .proxyTargetClass = true 使用Cglib动态代理, 默认是java的Proxy
 * @Version: $ WebMvcConfigurationSupport
 */
@Configuration
@SuppressWarnings("deprecation")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class GlobalAppConfig extends WebMvcConfigurerAdapter {
    @Bean
    public AuthInterceptor authInterceptor() {
        return new AuthInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //这里可以添加多个拦截器
        registry.addInterceptor(authInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverterFactory(new EnumConverterFactory());
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
                .maxAge(3600)
                .allowCredentials(true);
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule simpleModule = new SimpleModule();

        //将Long, BigInteger 序列化的时候, 转化为String
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
        simpleModule.addSerializer(BigInteger.class, ToStringSerializer.instance);

        objectMapper.registerModule(simpleModule);
        messageConverter.setObjectMapper(objectMapper);
        converters.add(messageConverter);

    }
}
