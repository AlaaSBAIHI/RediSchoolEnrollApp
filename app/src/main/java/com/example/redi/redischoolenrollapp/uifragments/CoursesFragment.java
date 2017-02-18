package com.example.redi.redischoolenrollapp.uifragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.redi.redischoolenrollapp.R;
import com.example.redi.redischoolenrollapp.entities.Course;
import com.example.redi.redischoolenrollapp.http.CourseService;
import com.example.redi.redischoolenrollapp.http.RestClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class CoursesFragment extends Fragment {

    private List<Course> courseList = new ArrayList<>();
    private CourseService courseService = RestClient.getInstance().createService(CourseService.class);
    private Course course;

    public CoursesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_courses, container, false);
/*        FragmentCoursesBinding fragmentCoursesBinding = DataBindingUtil.setContentView(this, R.layout.fragment_courses);

        AddCourseViewModel addCourseViewModel = AddCourseViewModel.builder().build();

        fragmentCoursesBinding.setAddCourseViewModel(addCourseViewModel);*/
    }


    public Course addCourse(String name, String describe, String url) {
        Call<Course> call = courseService.addCourse(name, describe, url);

        call.enqueue(new Callback<Course>() {
            @Override
            public void onResponse(Call<Course> call, Response<Course> response) {
                if (response.isSuccessful()) {
                    course = response.body();
                    if (course != null) {
                        courseList.add(response.body());
                        showMessageSuccessful("Successful added: ", course.getName());
                    }

                }
            }

            @Override
            public void onFailure(Call<Course> call, Throwable t) {
                showNetError();
            }
        });

        return course;
    }

    private void showNetError() {
        Toast.makeText(getActivity(), "Network Error! Operation Failed! :", Toast.LENGTH_LONG).show();
    }


    private void showMessageSuccessful(String msg, String course_name) {
        Toast.makeText(getActivity(), msg + course_name, Toast.LENGTH_LONG).show();
    }
}
