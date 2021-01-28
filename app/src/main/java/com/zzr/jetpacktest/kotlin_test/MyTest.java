package com.zzr.jetpacktest.kotlin_test;

import com.zzr.jetpacktest.entity.User;

/**
 * @Author zzr
 * @Desc
 * @Date 2020/12/7
 */
public class MyTest {
    public static void main(String[] args) {
        User user1 = new User("david", 50);
        User user2 = new User("david", 50);
        System.out.println(user1.equals(user2));
        System.out.println(user1 == user2);
    }
}
