package com.example.redi.redischoolenrollapp.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.redi.redischoolenrollapp.R;
import com.example.redi.redischoolenrollapp.databinding.ActivitySignUpBinding;
import com.example.redi.redischoolenrollapp.uifragments.RetainFragment;
import com.example.redi.redischoolenrollapp.viewModels.SignUpViewModel;

public class SignUpActivity extends AppCompatActivity {

    public static final String NETWORK_FRAGMENT_TAG = "NETWORK_FRAGMENT";

    private RetainFragment retainFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getRetainFragment();

        ActivitySignUpBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up);

        SignUpViewModel signUpViewModel = SignUpViewModel.builder().retainFragment(getRetainFragment()).build();

        binding.setSignUpViewModel(signUpViewModel);


    }


    public RetainFragment getRetainFragment() {
        retainFragment = (RetainFragment) getSupportFragmentManager().findFragmentByTag(NETWORK_FRAGMENT_TAG);
        if (retainFragment == null) {
            retainFragment = new RetainFragment();
            getSupportFragmentManager().beginTransaction().add(retainFragment, NETWORK_FRAGMENT_TAG).commit();
        }

        return retainFragment;
    }
}


