package com.example.redi.redischoolenrollapp.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aurel on 16/01/17.
 */
public enum UserType {
    STUDENT, TEACHER, REDI;

    public static String[] myValues() {

        List<String> list = new ArrayList<>();
        for (UserType s : UserType.values()) {
            list.add(s.name());
        }

        return list.toArray(new String[list.size()]);
    }
}
