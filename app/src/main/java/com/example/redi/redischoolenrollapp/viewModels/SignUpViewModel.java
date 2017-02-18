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
import android.widget.AdapterView;
import android.widget.Toast;

import com.android.databinding.library.baseAdapters.BR;
import com.example.redi.redischoolenrollapp.activities.UserMainActivity;
import com.example.redi.redischoolenrollapp.entities.User;
import com.example.redi.redischoolenrollapp.entities.UserType;
import com.example.redi.redischoolenrollapp.http.RestClient;
import com.example.redi.redischoolenrollapp.http.UserService;

import lombok.experimental.Builder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ReDI on 2/10/2017.
 */

@Builder
public class SignUpViewModel extends BaseObservable {

    private User currentUser;

    private String firstName;

    private String lastName;

    private UserType userType;

    private String email;

    private String password;

    private String confirmPassword;

    private String address;

    private String describe;

    private String firstNameValidateErrorMessage;

    private String lastNameValidateErrorMessage;

    private String emailValidateErrorMessage;

    private String passwordValidateErrorMessage;

    private String confirmPasswordValidateErrorMessage;

    private String addressValidateErrorMessage;

    private String describeValidateErrorMessage;

    private boolean showProgressBar = false;

    private Context context;

    private ProgressDialog progressDialog;

    private UserService userService;

    @NonNull
    private ProgressDialog createProgressDialog() {
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        return progressDialog;
    }

    public User signUp(final String firstName, final String lastName, final UserType userType, final String email,
                       final String password, final String confirmPassword, final String address, final String describe) {

        createProgressDialog();

        userService = RestClient.getInstance().createService(UserService.class);

        Call<User> call = userService.getUserByEmail(email);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    currentUser = response.body();
                    if (currentUser != null) {
                        progressDialog.dismiss();
                        Toast.makeText(context, "This Email is exist! Please Try another one", Toast.LENGTH_LONG).show();
                    } else {
                        /////////////////////////////////
                        Call<User> signUpCall = userService.signUp(firstName, lastName, userType, email, password, confirmPassword, address, describe);

                        signUpCall.enqueue(new Callback<User>() {
                            @Override
                            public void onResponse(Call<User> call, Response<User> response) {
                                if (response.isSuccessful()) {
                                    currentUser = response.body();
                                    if (currentUser != null) {
                                        new WaitPeriod().execute("success");
                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call<User> call, Throwable t) {
                                progressDialog.dismiss();
                                currentUser = null;
                                showNetError();
                            }
                        });
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

    public void onClickRegisterBtn(View view) {

        if (signUpValidation()) {

            signUp(firstName, lastName, userType, email, password, confirmPassword, address, describe);
        }
    }

    public void onUserTypeSelected(AdapterView<?> parent, View view, int position, long id) {
        userType = UserType.valueOf(String.valueOf(parent.getSelectedItem()));
    }

    public void validateFirstNameOnTextChanged(Editable editable) {
        //    validateFirstName();
    }

    public void validateLastNameOnTextChanged(Editable editable) {
        //   validateLastName();
    }

    public void validateEmailOnTextChanged(Editable editable) {
        //   validateEmail();
    }

    public void validatePasswordOnTextChanged(Editable editable) {
        // validatePassword();
    }

    public void validateConfirmPasswordOnTextChanged(Editable editable) {
        //  validateConfirmPassword();
    }

    public void validateAddressOnTextChanged(Editable editable) {
        //   validateAddress();
    }

    public void validateDescribeOnTextChanged(Editable editable) {
        //   validateDescribe();
    }

    public boolean signUpValidation() {
        boolean valid = true;

        if (!validateFirstName()) {
            valid = false;
        }

        if (!validateLastName()) {
            valid = false;
        }

        if (!validateEmail()) {
            valid = false;
        }

        if (!validatePassword()) {
            valid = false;
        }

        if (!validateConfirmPassword()) {
            valid = false;
        }

        if (!validateAddress()) {
            valid = false;
        }

        if (!validateDescribe()) {
            valid = false;
        }


        return valid;
    }

    private boolean validateFirstName() {
        if (firstName == null || firstName.isEmpty() || firstName.length() < 2) {
            setFirstNameValidateErrorMessage("Please enter a valid First Name!");
            return false;
        }

        setFirstNameValidateErrorMessage(null);
        return true;
    }

    private boolean validateLastName() {
        if (lastName == null || lastName.isEmpty() || lastName.length() < 2) {
            setLastNameValidateErrorMessage("Please enter a valid Last Name!");
            return false;
        }

        setLastNameValidateErrorMessage(null);
        return true;
    }

    private boolean validateEmail() {
        if (email == null || email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            setEmailValidateErrorMessage("Please enter a valid Email address!");
            return false;
        }

 /*       if(retainFragment.findUserByEmail(email) != null){
            setEmailValidateErrorMessage("This Email is exist! Please Try another one");
            return false;
        }*/

        setEmailValidateErrorMessage(null);
        return true;
    }

    private boolean validatePassword() {
        if (password == null || password.isEmpty() || password.length() < 4 || password.contains(" ")) {
            setPasswordValidateErrorMessage("Password is too short!");
            return false;
        }
        validateConfirmPassword();
        setPasswordValidateErrorMessage(null);
        return true;
    }

    private boolean validateConfirmPassword() {
        if (confirmPassword != null && !confirmPassword.equals(password)) {

            setConfirmPasswordValidateErrorMessage("Confirm Password is not equal with Password!");
            return false;
        }

        setConfirmPasswordValidateErrorMessage(null);
        return true;
    }

    private boolean validateAddress() {
        if (address == null || address.isEmpty() || address.length() < 4) {
            setAddressValidateErrorMessage("Please enter a valid Address!");
            return false;
        }

        setAddressValidateErrorMessage(null);
        return true;
    }

    private boolean validateDescribe() {
        if (describe == null || describe.isEmpty() || describe.length() < 4) {
            setDescribeValidateErrorMessage("Please enter a valid Description!");
            return false;
        }

        setDescribeValidateErrorMessage(null);
        return true;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    @Bindable
    public String getFirstNameValidateErrorMessage() {
        return firstNameValidateErrorMessage;
    }

    public void setFirstNameValidateErrorMessage(String firstNameValidateErrorMessage) {
        this.firstNameValidateErrorMessage = firstNameValidateErrorMessage;
        notifyPropertyChanged(BR.firstNameValidateErrorMessage);
    }

    @Bindable
    public String getLastNameValidateErrorMessage() {
        return lastNameValidateErrorMessage;
    }

    public void setLastNameValidateErrorMessage(String lastNameValidateErrorMessage) {
        this.lastNameValidateErrorMessage = lastNameValidateErrorMessage;
        notifyPropertyChanged(BR.lastNameValidateErrorMessage);
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

    @Bindable
    public String getConfirmPasswordValidateErrorMessage() {
        return confirmPasswordValidateErrorMessage;
    }

    public void setConfirmPasswordValidateErrorMessage(String confirmPasswordValidateErrorMessage) {
        this.confirmPasswordValidateErrorMessage = confirmPasswordValidateErrorMessage;
        notifyPropertyChanged(BR.confirmPasswordValidateErrorMessage);
    }

    @Bindable
    public String getAddressValidateErrorMessage() {
        return addressValidateErrorMessage;
    }

    public void setAddressValidateErrorMessage(String addressValidateErrorMessage) {
        this.addressValidateErrorMessage = addressValidateErrorMessage;
        notifyPropertyChanged(BR.addressValidateErrorMessage);
    }

    @Bindable
    public String getDescribeValidateErrorMessage() {
        return describeValidateErrorMessage;
    }

    public void setDescribeValidateErrorMessage(String describeValidateErrorMessage) {
        this.describeValidateErrorMessage = describeValidateErrorMessage;
        notifyPropertyChanged(BR.describeValidateErrorMessage);
    }

    @Bindable
    public boolean isShowProgressBar() {
        return showProgressBar;
    }

    public void setShowProgressBar(boolean showProgressBar) {
        this.showProgressBar = showProgressBar;
        notifyPropertyChanged(BR.showProgressBar);
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
                showMessageSuccessful("Sign Up successful for ", currentUser.getFirstName());
                progressDialog.dismiss();
                context.startActivity(new Intent(context, UserMainActivity.class));
            } else {
                progressDialog.dismiss();
            }

            super.onPostExecute(result);
        }


    }

}
