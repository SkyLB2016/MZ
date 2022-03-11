package com.sky.test.kt

import java.io.Serializable

/**
 * @Description:
 * @Author: 李彬
 * @CreateDate: 2021/8/17 4:11 下午
 * @Version: 1.0
 */
class CourseEntity : Serializable {
    var name: String? = null
    var score = 0f
    var sex = 0

    constructor() {}
    constructor(name: String?, score: Float) {
        this.name = name
        this.score = score
    }

    constructor(name: String?, score: Float, sex: Int) {
        this.name = name
        this.score = score
        this.sex = sex
    }

    override fun toString(): String {
        return "CourseEntity{" +
                "name='" + name + '\'' +
                ", score=" + score +
                ", sex=" + sex +
                '}'
    }

    companion object {
        private const val serialVersionUID = 1L
    }
}