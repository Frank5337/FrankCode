package com.zbl.code.common.conditional;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Objects;

/**
 * @Author: zbl
 * @Date: Created in 14:59 2019/8/27
 * @Description:
 * @Version: $
 */
public class WindowsCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata annotatedTypeMetadata) {
        return Objects.requireNonNull(context.getEnvironment().getProperty("os.name")).contains("Windows");
    }
}
