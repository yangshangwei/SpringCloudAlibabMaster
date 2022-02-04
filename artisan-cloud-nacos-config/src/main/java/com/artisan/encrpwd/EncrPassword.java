package com.artisan.encrpwd;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author 小工匠
 * @version 1.0
 * @description: TODO
 * @date 2022/2/4 20:11
 * @mark: show me the code , change the world
 */
public class EncrPassword {

    public static void main(String[] args) {
        String encode = new BCryptPasswordEncoder().encode("urpassword");
        System.out.println(encode);
    }
}
    