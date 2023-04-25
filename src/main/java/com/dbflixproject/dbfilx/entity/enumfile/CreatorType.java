package com.dbflixproject.dbfilx.entity.enumfile;

public enum CreatorType {
    배우,감독;

    public static CreatorType from(String gen) {
        try {
            return CreatorType.valueOf(gen);
        } catch (Exception e) {
            return null;
        }
    }
}
