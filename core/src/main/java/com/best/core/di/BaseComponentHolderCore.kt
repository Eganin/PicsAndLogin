package com.best.core.di

sealed class BaseComponentHolderCore<T>{

    protected var component: T? = null

    fun get(): T = component ?: throw ComponentNotInitializedException()

    fun reset(){
        component=null
    }

    abstract class Dependent<T, R>: BaseComponentHolderCore<T>(){

        protected abstract fun createComponent(dependencies: R): T

        fun init(dependencies: R): T{
            if (component == null) component = createComponent(dependencies)
            return component!!
        }
    }

    abstract class Base<T> : BaseComponentHolderCore<T>(){

        protected abstract fun createComponent(): T

        fun init(): T{
            if (component == null) component = createComponent()
            return component!!
        }
    }
}
