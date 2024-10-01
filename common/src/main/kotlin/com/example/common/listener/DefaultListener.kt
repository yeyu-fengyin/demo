package com.example.common.listener

import com.example.common.application.SpringBootMyApplication
import com.example.common.event.MyEvent
import java.lang.reflect.Method

/**
 *
 *@Auth yeyu
 *@Date 2024/9/30
 *
 **/
class DefaultListener: MyListener() {

    private lateinit var ob:Any;
    private lateinit var method:Method;
    private lateinit var application: SpringBootMyApplication

    override fun handleEvent(event: MyEvent) {
        val parameters = method.parameters
        val params= arrayListOf<Any>()
        parameters.forEach { parameter ->
            if (parameter.type == MyEvent::class.java){
                params.add(event)
            }else{
                val bean = application.getBean(parameter.type)
                if (bean != null) {
                    params.add(bean)
                }else{
                    throw RuntimeException("参数不正确")
                }
            }
        }
        method.invoke(ob,*params.toTypedArray())
    }

    fun init(method:Method,ob:Any):DefaultListener{
        this.ob = ob
        this.method = method
        return this
    }
}