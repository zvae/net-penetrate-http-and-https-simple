package com.kele.penetrate.utils;

import com.kele.penetrate.factory.annotation.Recognizer;

import java.util.UUID;

@Recognizer
@SuppressWarnings("unused")
public class UUIDUtils {
    public String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    //<editor-fold desc="生成8位数UUID">
    public static String[] chars = new String[]{"a", "b", "c", "d", "e", "f",
            "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
            "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9"};


    public String generateShortUUID() {
        StringBuilder shortBuffer = new StringBuilder();
        String uuid = getUUID();
        for (int i = 0; i < 8; i++) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            shortBuffer.append(chars[x % chars.length]);
        }
        return shortBuffer.toString();
    }
    //</editor-fold>
}
