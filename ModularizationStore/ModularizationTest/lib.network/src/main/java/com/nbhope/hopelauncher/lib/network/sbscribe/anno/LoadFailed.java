package com.nbhope.hopelauncher.lib.network.sbscribe.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by EthanCo on 2016/8/12.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LoadFailed {
    int DEFAULT_VALUE = -1860;

    int value() default DEFAULT_VALUE;
}
