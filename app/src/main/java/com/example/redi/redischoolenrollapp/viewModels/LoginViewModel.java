package com.example.redi.redischoolenrollapp.viewModels;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.text.Editable;
import android.view.View;

import com.android.databinding.library.baseAdapters.BR;
import com.example.redi.redischoolenrollapp.activities.SignUpActivity;
import com.example.redi.redischoolenrollapp.entities.User;
import com.example.redi.redischoolenrollapp.uifragments.RetainFragment;

import lombok.experimental.Builder;

/**
 * Created by ReDI on 2/7/2017.
 */
/*

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
*/
@Builder
public class LoginViewModel extends BaseObservable {

    private RetainFragment retainFragment;

    private User currentUser;

    private String email;

    private String password;

    private String emailValidateErrorMessage;

    private String passwordValidateErrorMessage;

    private boolean showProgressBar = false;

    private boolean showAccountNotFound = false;

    private Context context;

 /*   public void onClickSignUpBtn(View view){


    }*/

    public void onClickLoginBtn(View view) {
        if (loginValidation()) {
            setShowProgressBar(true);

            currentUser = retainFragment.logIn(email, password);

            if (currentUser == null) {
                setShowProgressBar(false);
                setShowAccountNotFound(true);
                context.startActivity(new Intent(context, SignUpActivity.class));

            } else {
                //context.startActivity(new Intent(context, SignUpActivity.class));
            }
        }
    }


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


    public RetainFragment getRetainFragment() {
        return retainFragment;
    }

    public void setRetainFragment(RetainFragment retainFragment) {
        this.retainFragment = retainFragment;
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


}
