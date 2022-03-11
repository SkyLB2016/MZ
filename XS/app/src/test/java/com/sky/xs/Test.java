package com.sky.xs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @Description:
 * @Author: 李彬
 * @CreateDate: 2021/12/29 3:09 下午
 * @Version: 1.0
 */
public class Test {

    public static void main(String[] args) throws IOException {
        Process process = Runtime.getRuntime().exec("java -version");
        BufferedReader br = new BufferedReader(new InputStreamReader(process.getErrorStream()));
        String line = br.readLine();
        System.out.println(line);
        process.destroy();
    }
}
