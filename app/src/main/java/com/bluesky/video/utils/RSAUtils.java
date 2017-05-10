package com.bluesky.video.utils;

import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

/**
 * Created by duchao on 2017/5/11.
 */

public class RSAUtils
{
    private static final String ALGORITHM = "RSA";
    private static final String RSA_PUBLICE = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDojc2LEklm0WcBTwzGmjJGdCNq\rjVoJYavTM84sNJeWU2/D+fJL2udjMBUORN4LnKf0CeYEANtbP+IUyGCV7nkBuUa7\rl5mS8GE1qmmc2jyT6C9nwXHVTwFtsE7M/+sRF4UguL+bmrTtdc6m9Uxlkrmj1Vof\r8i7Pxo9k/TQjlgzyMwIDAQAB";

    private static PublicKey getPublicKeyFromX509(String algorithm, String bysKey) throws NoSuchAlgorithmException, Exception {
        byte[] decodedKey = Base64.decode(bysKey,Base64.DEFAULT);
        X509EncodedKeySpec x509 = new X509EncodedKeySpec(decodedKey);
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        return keyFactory.generatePublic(x509);
    }

    public static String encryptByPublic(String content) {
        try {
            PublicKey pubkey = getPublicKeyFromX509("RSA", "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDojc2LEklm0WcBTwzGmjJGdCNq\rjVoJYavTM84sNJeWU2/D+fJL2udjMBUORN4LnKf0CeYEANtbP+IUyGCV7nkBuUa7\rl5mS8GE1qmmc2jyT6C9nwXHVTwFtsE7M/+sRF4UguL+bmrTtdc6m9Uxlkrmj1Vof\r8i7Pxo9k/TQjlgzyMwIDAQAB");
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, pubkey);
            byte[] plaintext = content.getBytes("UTF-8");
            byte[] output = cipher.doFinal(plaintext);
            return new String(Base64.encode(output,Base64.DEFAULT));
        } catch(Exception e) {
            return null;
        }
    }

    public static String decryptByPublic(String content) {
        try {
            PublicKey pubkey = getPublicKeyFromX509("RSA", "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDojc2LEklm0WcBTwzGmjJGdCNq\rjVoJYavTM84sNJeWU2/D+fJL2udjMBUORN4LnKf0CeYEANtbP+IUyGCV7nkBuUa7\rl5mS8GE1qmmc2jyT6C9nwXHVTwFtsE7M/+sRF4UguL+bmrTtdc6m9Uxlkrmj1Vof\r8i7Pxo9k/TQjlgzyMwIDAQAB");
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.DECRYPT_MODE, pubkey);
            InputStream ins = new ByteArrayInputStream(Base64.decode(content,Base64.DEFAULT));
            ByteArrayOutputStream writer = new ByteArrayOutputStream();
            byte[] buf = new byte[128];
            int buf1;
            while((buf1 = ins.read(buf)) != -1) {
                byte[] block = null;
                if(buf.length == buf1) {
                    block = buf;
                } else {
                    block = new byte[buf1];
                    for(int i = 0; i < buf1; i++) {
                        block[i] = buf[i];
                    }
                }
                writer.write(cipher.doFinal(block));
            }
            return new String(writer.toByteArray(), "utf-8");
        } catch(Exception e) {
            return null;
        }
    }
}

