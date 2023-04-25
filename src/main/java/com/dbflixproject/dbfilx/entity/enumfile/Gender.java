package com.dbflixproject.dbfilx.entity.enumfile;

public enum Gender {
    남, 여, 선택안함;

    public static Gender from(String gen) {
        try {
            return Gender.valueOf(gen);
        } catch (Exception e) {
            return null;
        }
    }
}
