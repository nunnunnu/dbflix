package com.dbflixproject.dbfilx.entity.enumfile;

public enum MovieRole {
    주연,조연,감독;
    public static MovieRole from(String role) {
        try {
            return MovieRole.valueOf(role);
        } catch (Exception e) {
            return null;
        }
    }
}
