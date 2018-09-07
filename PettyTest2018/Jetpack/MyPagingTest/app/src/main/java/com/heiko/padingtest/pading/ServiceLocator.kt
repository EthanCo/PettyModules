package com.heiko.padingtest.pading

import java.util.concurrent.Executors

/**
 * Created by juan on 2018/05/23.
 */
interface ServiceLocator {
    companion object {
        private val LOCK = Any()
        private var instance: ServiceLocator? = null
        fun instance(): ServiceLocator {
            synchronized(LOCK) {
                if (instance == null) {
                    instance = DefaultServiceLocator()
                }
                return instance!!
            }
        }
    }
    fun getRepository(): StudentRepository
}
open class DefaultServiceLocator : ServiceLocator {
    override fun getRepository(): StudentRepository {
        return StudentDataRepository(Executors.newFixedThreadPool(5))
    }
}