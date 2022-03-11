package com.sky.test.kt

import java.io.Serializable

/**
 * @Description:
 * @Author: 李彬
 * @CreateDate: 2021/8/16 7:01 下午
 * @Version: 1.0
 */
data class Person(var name: String, var score: Float, var sex: Int = 1) : Serializable {
    companion object {
        private const val serialVersionUID = 1L
        const val PAA = 1
        var PBB = 23
    }

}