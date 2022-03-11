package com.sky.test

import com.sky.test.kt.InterCC

/**
 *
 * @Description:
 * @Author: 李彬
 * @CreateDate: 2021/8/23 6:35 下午
 * @Version: 1.0
 */
open class InterBB {
     open fun testBB(){
        InterCC().b
    }

    fun <T:Comparable<*>> testb2(){

    }
    fun  testb3(list:List<out Comparable<*>>){

    }


}