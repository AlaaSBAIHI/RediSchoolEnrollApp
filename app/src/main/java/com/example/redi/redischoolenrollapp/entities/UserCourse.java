package com.example.redi.redischoolenrollapp.entities;

import java.io.Serializable;
import java.util.UUID;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Builder;


/**
 * Created by ReDI on 1/29/2017.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(of = {"id"})
@Builder
@Getter
public class UserCourse implements Serializable {

    private UUID id;

    private UUID courseId;

    private UUID userId;

    private CourseStatus courseStatus;
}
