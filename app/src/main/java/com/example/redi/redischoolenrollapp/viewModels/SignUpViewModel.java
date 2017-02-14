package com.example.redi.redischoolenrollapp.viewModels;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.text.Editable;
import android.view.View;
import android.widget.AdapterView;

import com.android.databinding.library.baseAdapters.BR;
import com.example.redi.redischoolenrollapp.entities.User;
import com.example.redi.redischoolenrollapp.entities.UserType;
import com.example.redi.redischoolenrollapp.uifragments.RetainFragment;

import lombok.experimental.Builder;

/**
 * Created by ReDI on 2/10/2017.
 */

@Builder
public class SignUpViewModel extends BaseObservable {

    private RetainFragment retainFragment;

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


    public void onClickRegisterBtn(View view) {

        if (signUpValidation()) {
            currentUser = retainFragment.signUp(firstName, lastName, userType, email, password, confirmPassword, address, describe);
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

}
