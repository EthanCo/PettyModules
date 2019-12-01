package com.ethanco.dagger2test;

import dagger.Module;
import dagger.Provides;

/**
 * 对Student进行封装
 * (视频例子中耳机的包裹)
 */
@Module
public class StudentModule {

    /**
     * Provides 暴露出去的意思
     * @return
     */
    @Provides
    public Student getStudent(){
        return new Student();
    }
}
