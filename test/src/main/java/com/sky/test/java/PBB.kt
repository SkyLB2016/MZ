package com.sky.test.java

import java.io.Serializable
import javax.swing.Action

/**
 * @Description:
 * @Author: 李彬
 * @CreateDate: 2021/8/23 6:39 下午
 * @Version: 1.0
 */
class PBB<T> where T : Comparable<*>?, T : Serializable? {
    protected var a = 9
    protected var t: T? = null

    companion object {
        var aa = "ABC"
    }
}