package com.example.redi.redischoolenrollapp.viewModels;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.view.View;
import android.widget.Toast;

import com.android.databinding.library.baseAdapters.BR;
import com.example.redi.redischoolenrollapp.activities.UserMainActivity;
import com.example.redi.redischoolenrollapp.entities.User;
import com.example.redi.redischoolenrollapp.http.RestClient;
import com.example.redi.redischoolenrollapp.http.UserService;

import java.util.ArrayList;
import java.util.List;

import lombok.experimental.Builder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ReDI on 2/7/2017.
 */
/*

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
*/
@Builder
public class LoginViewModel extends BaseObservable {

    List<User> userList = new ArrayList<>();
    /*private RetainFragment retainFragment;
*/
    private User currentUser;
    private String email;
    private String password;
    private String emailValidateErrorMessage;
    private String passwordValidateErrorMessage;
    private boolean showProgressBar = false;
    private boolean showAccountNotFound = false;
    private Context context;
    private UserService userService;

    private ProgressDialog progressDialog;

    ////////////////////////////////////////

    ////////////////////////////////////////
    @NonNull
    private ProgressDialog createProgressDialog() {
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        return progressDialog;
    }

    public User logIn(String email, String password) {

        createProgressDialog();

        userService = RestClient.getInstance().createService(UserService.class);

        Call<User> call = userService.login(email, password);

        call.enqueue(new Callback<User>() {

            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    currentUser = response.body();
                    if (currentUser == null) {
                        new WaitPeriod().execute("");
                    } else {
                        new WaitPeriod().execute("success");
                    }

                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                currentUser = null;
                progressDialog.dismiss();
                showNetError();
            }
        });

        return currentUser;
    }

    private void showNetError() {
        Toast.makeText(context, "Network Error! Operation Failed! :", Toast.LENGTH_LONG).show();
    }

    private void showMessageSuccessful(String msg, String user_name) {
        Toast.makeText(context, msg + user_name, Toast.LENGTH_LONG).show();
    }

    public void onClickLoginBtn(View view) {
        if (loginValidation()) {

            logIn(email, password);
        }
    }




 /*   public void onClickSignUpBtn(View view){


    }*/

    public void validateEmailOnTextChanged(Editable editable) {
        validateEmail();
    }

    public void validatePasswordOnTextChanged(Editable editable) {
        validatePassword();
    }

    @Bindable
    public String getEmailValidateErrorMessage() {
        return emailValidateErrorMessage;
    }

    public void setEmailValidateErrorMessage(String emailValidateErrorMessage) {
        this.emailValidateErrorMessage = emailValidateErrorMessage;
        notifyPropertyChanged(BR.emailValidateErrorMessage);

    }

    @Bindable
    public String getPasswordValidateErrorMessage() {
        return passwordValidateErrorMessage;
    }

    public void setPasswordValidateErrorMessage(String passwordValidateErrorMessage) {
        this.passwordValidateErrorMessage = passwordValidateErrorMessage;
        notifyPropertyChanged(BR.passwordValidateErrorMessage);

    }

    public boolean loginValidation() {
        boolean valid = true;

        if (!validateEmail()) {
            valid = false;
        }

        if (!validatePassword()) {
            valid = false;
        }

        return valid;
    }

    private boolean validateEmail() {
        if (email == null || email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            setEmailValidateErrorMessage("Enter a valid Email address!");
            return false;
        }

        setEmailValidateErrorMessage(null);
        return true;
    }

    private boolean validatePassword() {
        if (password == null || password.isEmpty() || password.length() < 4 || password.contains(" ")) {
            setPasswordValidateErrorMessage("Password is too short!");
            return false;
        }

        setPasswordValidateErrorMessage(null);
        return true;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Bindable
    public boolean isShowProgressBar() {
        return showProgressBar;
    }

    public void setShowProgressBar(boolean showProgressBar) {
        this.showProgressBar = showProgressBar;
        notifyPropertyChanged(BR.showProgressBar);
    }

    @Bindable
    public boolean isShowAccountNotFound() {
        return showAccountNotFound;
    }

    public void setShowAccountNotFound(boolean showAccountNotFound) {
        this.showAccountNotFound = showAccountNotFound;
        notifyPropertyChanged(BR.showAccountNotFound);

    }

    private class WaitPeriod extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... responses) {
            try {
                Thread.currentThread().sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return responses[0];
        }

        @Override
        protected void onPostExecute(String result) {
            if (result.equals("success")) {
                progressDialog.dismiss();
                setShowAccountNotFound(false);
                showMessageSuccessful("Login is successful for User", currentUser.getEmail());
                context.startActivity(new Intent(context, UserMainActivity.class));
            } else {
                progressDialog.dismiss();
                setShowAccountNotFound(true);
            }
            super.onPostExecute(result);
        }


    }


}
