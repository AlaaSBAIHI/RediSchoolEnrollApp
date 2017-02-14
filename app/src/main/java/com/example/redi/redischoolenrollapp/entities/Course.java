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
 * Created by ReDI on 1/14/2017.
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(of = {"id"})
@Builder
@Getter
public class Course {

    private UUID id;

    private String name;

    private String description;

    private String url;

    private Set<User> users;

}
