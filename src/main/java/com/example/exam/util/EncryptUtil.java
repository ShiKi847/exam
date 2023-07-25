package com.example.exam.util;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;

import java.util.UUID;

public class EncryptUtil {
    public final static String hashAlgorithmName = Md5Hash.ALGORITHM_NAME;
    public final static int hashIterations = 512;
    public static String generateSalt(){
        return UUID.randomUUID().toString().replace("-","");
    }
    public static String generatePassword(String password,String salt){
        SimpleHash simpleHash = new SimpleHash(hashAlgorithmName, password, salt, hashIterations);
        return simpleHash.toString();
    }

    public static void main(String[] args) {
        String password="123";
        String salt = generateSalt();
        System.out.println(generatePassword(password,salt));
    }
}
