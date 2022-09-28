package com.kele.penetrate.utils;

import com.kele.penetrate.factory.annotation.Recognizer;

@Recognizer
@SuppressWarnings("unused")
public class CheckUtils {
    public boolean checkDomainName(String mappingName) {
        boolean check = false;
        if (mappingName != null) {
            String trim = mappingName.trim();
            if (trim.length() > 0) {
                String regex = "^[a-z0-9.]+$";
                check = trim.matches(regex);
            }
            if (check) {
                String last = trim.substring(trim.length() - 1);
                check = (!last.equals("."));
            }
        }
        return check;
    }

    public boolean checkPort(String port) {
        boolean check = false;
        if (port != null) {
            String trim = port.trim();
            if (port.length() > 0) {
                String regex = "^[0-9]+$";
                check = port.matches(regex);
            }
        }
        return check;
    }

    public boolean checkIp(String str) {
        return str != null && str.trim().length() > 0;
    }

}
