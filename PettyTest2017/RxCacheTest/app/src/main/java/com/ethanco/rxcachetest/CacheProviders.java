package com.ethanco.rxcachetest;

import java.util.concurrent.TimeUnit;

import io.rx_cache.DynamicKey;
import io.rx_cache.EvictDynamicKey;
import io.rx_cache.LifeCache;
import rx.Observable;

/**
 * RxCache 接口
 *
 * @author EthanCo
 * @since 2017/3/13
 */

public interface CacheProviders {

    /**
     * @LifeCache设置缓存过期时间. 如果没有设置@LifeCache , 数据将被永久缓存理除，非你使用了 EvictProvider,EvictDynamicKey or EvictDynamicKeyGroup.
     * EvictProvider 可以明确地清理清理所有缓存数据.
     * EvictDynamicKey 可以明确地清理指定的数据 DynamicKey.
     * EvictDynamicKeyGroup 允许明确地清理一组特定的数据. DynamicKeyGroup.
     * DynamicKey 选择一个特定的键使用EvictDynamicKey相关的数据。比如分页，排序或筛选要求
     * DynamicKeyGroup 选择一组与key关联的数据，使用EvictDynamicKeyGroup。比如分页，排序或筛选要求
     */

    //这里设置缓存失效时间为2分钟。
    @LifeCache(duration = 2, timeUnit = TimeUnit.MINUTES)
    Observable<String> getTime(Observable<String> observable, DynamicKey userName, EvictDynamicKey evictDynamicKey);

    @LifeCache(duration = 2, timeUnit = TimeUnit.MINUTES)
    Observable<String> getTimeSimple(Observable<String> observable);
}
