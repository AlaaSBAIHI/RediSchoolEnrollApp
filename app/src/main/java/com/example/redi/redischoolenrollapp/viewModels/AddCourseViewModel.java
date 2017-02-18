package com.example.redi.redischoolenrollapp.viewModels;

import android.content.Context;
import android.util.Patterns;

import com.example.redi.redischoolenrollapp.entities.Course;
import com.example.redi.redischoolenrollapp.uifragments.CoursesFragment;

import lombok.experimental.Builder;

/**
 * Created by ReDI on 2/17/2017.
 */

@Builder
public class AddCourseViewModel {

    private CoursesFragment coursesFragment;

    private Course currentCourse;

    private String name;

    private String describe;

    private String url;

    private Context context;


    public boolean addCourseValidation() {
        boolean valid = true;

        if (!validateName()) {
            valid = false;
        }

        if (!validateDescribe()) {
            valid = false;
        }

        if (!validateURL()) {
            valid = false;
        }


        return valid;
    }


    private boolean validateName() {
        if (name == null || name.isEmpty() || name.length() < 2) {
            //setFirstNameValidateErrorMessage("Please enter a valid First Name!");
            return false;
        }

        //setFirstNameValidateErrorMessage(null);
        return true;
    }


    private boolean validateDescribe() {
        if (describe == null || describe.isEmpty() || describe.length() < 2) {
            //setFirstNameValidateErrorMessage("Please enter a valid First Name!");
            return false;
        }

        //setFirstNameValidateErrorMessage(null);
        return true;
    }


    private boolean validateURL() {
        if (url == null || url.isEmpty() || url.length() < 2 || !Patterns.WEB_URL.matcher(url).matches()) {
            //setFirstNameValidateErrorMessage("Please enter a valid First Name!");
            return false;
        }

        //setFirstNameValidateErrorMessage(null);
        return true;
    }


    public CoursesFragment getCoursesFragment() {
        return coursesFragment;
    }

    public void setCoursesFragment(CoursesFragment coursesFragment) {
        this.coursesFragment = coursesFragment;
    }

    public Course getCurrentCourse() {
        return currentCourse;
    }

    public void setCurrentCourse(Course currentCourse) {
        this.currentCourse = currentCourse;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
