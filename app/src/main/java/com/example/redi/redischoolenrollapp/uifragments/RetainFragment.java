package com.example.redi.redischoolenrollapp.uifragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.example.redi.redischoolenrollapp.activities.SignUpActivity;
import com.example.redi.redischoolenrollapp.activities.UserMainActivity;
import com.example.redi.redischoolenrollapp.entities.User;
import com.example.redi.redischoolenrollapp.entities.UserType;
import com.example.redi.redischoolenrollapp.http.RestClient;
import com.example.redi.redischoolenrollapp.http.UserService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ReDI on 2/6/2017.
 */

public class RetainFragment extends Fragment {

    List<User> userList = new ArrayList<>();
    private UserService userService = RestClient.getInstance().createService(UserService.class);
    private User user = null;
    private ProgressDialog progressDialog;

    @NonNull
    private ProgressDialog createProgressDialog() {
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        return progressDialog;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }


    public User signUp(final String firstName, final String lastName, final UserType userType, final String email,
                       final String password, final String confirmPassword, final String address, final String describe) {

        createProgressDialog();
        Call<User> call = userService.getUserByEmail(email);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    user = response.body();
                    if (user != null) {
                        progressDialog.dismiss();
                        Toast.makeText(getActivity(), "This Email is exist! Please Try another one", Toast.LENGTH_LONG).show();
                    } else {
                        /////////////////////////////////
                        Call<User> signUpCall = userService.signUp(firstName, lastName, userType, email, password, confirmPassword, address, describe);

                        signUpCall.enqueue(new Callback<User>() {
                            @Override
                            public void onResponse(Call<User> call, Response<User> response) {
                                if (response.isSuccessful()) {
                                    user = response.body();
                                    if (user != null) {
                                        userList.add(response.body());
                                        showMessageSuccessful("Sign Up successful for ", user.getFirstName());
                                        progressDialog.dismiss();
                                        getContext().startActivity(new Intent(getContext(), UserMainActivity.class));

                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call<User> call, Throwable t) {
                                user = null;
                                showNetError();
                            }
                        });
                    }
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                user = null;
                showNetError();
            }
        });
        return user;
    }


    public User logIn(String email, String password) {

        createProgressDialog();

        Call<User> call = userService.login(email, password);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    user = response.body();
                    if (user == null) {
                        progressDialog.dismiss();
                        getContext().startActivity(new Intent(getContext(), SignUpActivity.class));
                        userList.add(response.body());
                        //showMessageSuccessful("", response.body().getEmail().toString());
                    } else {
                        progressDialog.dismiss();
                        getContext().startActivity(new Intent(getContext(), UserMainActivity.class));


                    }

                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

                System.out.println(t.getMessage());
                System.out.println(call.request().url());
                System.out.println(call.request().headers().toString());
                showNetError();
            }
        });

        return user;
    }


    public User findUserByEmail(String email) {
        Call<User> call = userService.getUserByEmail(email);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    user = response.body();
                    if (user != null) {
                        Toast.makeText(getActivity(), "This Email is exist! Please Try another one", Toast.LENGTH_LONG).show();
                    } else
                        Toast.makeText(getActivity(), "This Email is not Exist! Please Try another one", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                System.out.println(t.getMessage());
                System.out.println(call.request().url());
                System.out.println(call.request().headers().toString());

                showNetError();
            }
        });

        return user;
    }


    private void showNetError() {
        Toast.makeText(getActivity(), "Network Error! Operation Failed! :", Toast.LENGTH_LONG).show();
    }

    private void showMessageSuccessful(String msg, String user_name) {
        Toast.makeText(getActivity(), msg + user_name, Toast.LENGTH_LONG).show();
    }


}
