package com.sky.test.kt

/**
 *
 * @Description:
 * @Author: 李彬
 * @CreateDate: 2021/8/24 10:17 上午
 * @Version: 1.0
 */
sealed class SealedAA {
    object AA : SealedAA()
    object BB : SealedAA()
    object CC : SealedAA()
}