package com.flab.jojoboard.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME) // aspectJ는 컴파일 타임에 AOP를 적용하므로, CLASS단계까지만 Retention을 설정한다. 만약 Spring AOP를 사용한다면 runtime으로 설정해야한다.
public @interface ReturnDataAOP {
    Class<?> responseType() default Object.class;
}
