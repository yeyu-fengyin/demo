package com.example.common.application

import org.springframework.aop.support.AopUtils
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component
import org.springframework.util.ClassUtils
import java.lang.reflect.Method

/**
 *
 *@Auth yeyu
 *@Date 2024/9/30
 *
 **/
@Configuration
class SpringBootMyApplication: ApplicationContextAware {
    private var applicationContext: ApplicationContext? = null

    override fun setApplicationContext(applicationContext: ApplicationContext) {
        this.applicationContext = applicationContext;
    }
    fun <T> getBean(clazz: Class<T>): T? {
        return applicationContext?.getBean(clazz)
    }

    fun getBeansWithAnnotation(annotation: Class<out Annotation>): MutableMap<String, Any>? {
        return applicationContext?.getBeansWithAnnotation(annotation)
    }

    fun getMethodsWithAnnotation(annotation: Class<*>):  MutableMap<Method,Any>? {
        val component = applicationContext?.getBeansWithAnnotation(Component::class.java)
        val configuration = applicationContext?.getBeansWithAnnotation(Configuration::class.java)

        val map: MutableMap<Method,Any> = mutableMapOf()
        initMap(component, annotation, map)
        initMap(configuration, annotation, map)
        return map
    }

    private fun initMap(
        component: MutableMap<String, Any>?,
        annotation: Class<*>,
        map: MutableMap<Method, Any>
    ) {
        component?.forEach() {
            val methods = it.value.javaClass.methods
            methods.forEach { method ->
                method.declaredAnnotations.forEach { an ->
                    if (annotation == an.annotationClass.java) {
                        map[method] = it.value
                    }
                }
            }
        }
    }
}