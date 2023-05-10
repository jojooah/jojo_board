package com.flab.jojoboard.common;

import com.flab.jojoboard.common.aop.ControllerAOP;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@Configuration
public class AOPConfiguration {
    @Bean
    public ControllerAOP responseAdvice() {
        return new ControllerAOP();
    }

}
