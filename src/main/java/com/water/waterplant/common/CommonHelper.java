package com.water.waterplant.common;

import java.security.SecureRandom;

public class CommonHelper {

    public static int generateNewId() {
        return new SecureRandom().nextInt(1, 999999999);
    }
}
