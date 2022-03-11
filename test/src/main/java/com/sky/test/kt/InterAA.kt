package com.sky.test.kt

/**
 *
 * @Description:
 * @Author: 李彬
 * @CreateDate: 2021/8/23 6:31 下午
 * @Version: 1.0
 */
class InterAA {
    val a = 8;
    val aa = "dklahfdkjlal"

    fun textAA(){
    }


    internal class AA {
        public val aa = 9
        fun textA1(){
//            println(a)
        }
    }

    open inner class BB {
        val b = 0
        fun textB1(){
            println(a)
        }
    }
    sealed class CC{
        val c = 0
        fun textc1(){
            textc2()
        }
        fun textc2(){
        }
    }
}