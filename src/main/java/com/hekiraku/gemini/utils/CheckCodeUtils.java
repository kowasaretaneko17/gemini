/**
 * *****************************************************
 * Copyright (C) 2020 bytedance.com. All Rights Reserved
 * This file is part of bytedance EA project.
 * Unauthorized copy of this file, via any medium is strictly prohibited.
 * Proprietary and Confidential.
 * ****************************************************
 **/
package com.hekiraku.gemini.utils;

/**
 * 验证码生成器
 *
 * @author bytedance<bytedance @ bytedance.com>
 * @date 03/26/2020 6:48 下午
 */
public class CheckCodeUtils {
        public static int[] shuffle(int[] arr){
            for(int i = 0;i<arr.length;i++){
                int j = (int)(i + Math.floor(Math.random()*(arr.length-i)));
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
            return arr;
        }
        public static int sixLength(){
            int [] arr = {1,2,3,4,5,6,7,8,9};
            shuffle(arr);
            int mix = 1;
            int res = 0;
            for(int i = 0;i<6;i++){
                res += mix*arr[i];
                mix = mix*10;
            }
            return res;
        }

    public static void main(String[] args) {
        System.out.println(sixLength());
    }
}
