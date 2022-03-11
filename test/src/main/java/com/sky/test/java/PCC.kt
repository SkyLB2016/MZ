package com.sky.test.java

import java.io.Serializable

/**
 * @Description:
 * @Author: 李彬
 * @CreateDate: 2021/8/23 6:39 下午
 * @Version: 1.0
 */
class PCC<T : Serializable> {
    protected var a = 9

    companion object {
        var aa = "ABC"
    }
}