package com.immunopass.util;

import lombok.experimental.UtilityClass;


@UtilityClass
public class ParameterCheckUtil {
    public static String checkEmptyOrNull(String value) {
        if (value == null && value.isEmpty()) {
            throw new RuntimeException("value is null or empty");
        }
        return value;
    }

    public static String checkLength(String value, Integer length) {
        if (value.length() > length) {
            throw new RuntimeException(String.format("value is too long, size should be smaller than %d chars.", length));
        }
        return value;
    }

    public static String cleanName(String name) {
        name = name.trim().replaceAll("[^a-zA-Z ]", "");
        return name;
    }

    public static String cleanMobileNumber(String number) {
        number = number.trim().replaceAll("[^0-9]", "");
        if (number.length() >= 10) {
            number = "+91" + number.substring(number.length() - 10);
        } else {
            throw new RuntimeException("incorrect mobile number " + number);
        }
        return number;
    }
}
