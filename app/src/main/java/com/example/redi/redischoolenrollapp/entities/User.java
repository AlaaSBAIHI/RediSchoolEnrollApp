package com.example.redi.redischoolenrollapp.entities;

import java.util.Set;
import java.util.UUID;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Builder;

/**
 * Created by ReDI on 2/5/2017.
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(of = {"id"})
@Builder
@Getter
public class User {

    private UUID id;

    private String email;

    private String password;

    private String cPassword;

    private String firstName;

    private String lastName;

    private String address;

    private String description;

    private UserType userType;

    private Set<Contact> contacts;

    private Set<Course> courses;

}
