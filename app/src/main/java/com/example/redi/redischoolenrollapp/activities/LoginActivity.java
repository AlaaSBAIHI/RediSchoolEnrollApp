package com.example.redi.redischoolenrollapp.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.redi.redischoolenrollapp.R;
import com.example.redi.redischoolenrollapp.databinding.ActivityLoginBinding;
import com.example.redi.redischoolenrollapp.viewModels.LoginViewModel;


public class LoginActivity extends AppCompatActivity {

/*    public static final String NETWORK_FRAGMENT_TAG = "NETWORK_FRAGMENT";

    private RetainFragment retainFragment;*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityLoginBinding loginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);


        LoginViewModel loginViewModel = LoginViewModel.builder().context(this).build();

        loginBinding.setLoginViewModel(loginViewModel);


        Button signUp = (Button) findViewById(R.id.login_btn_signUp);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
                //startActivity(new Intent(LoginActivity.this, UserMainActivity.class));

            }
        });


        Button main = (Button) findViewById(R.id.login_btn_main);
        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, UserMainActivity.class));

            }
        });

    }


/*    public RetainFragment getRetainFragment() {
        retainFragment = (RetainFragment) getSupportFragmentManager().findFragmentByTag(NETWORK_FRAGMENT_TAG);
        if (retainFragment == null) {
            retainFragment = new RetainFragment();
            getSupportFragmentManager().beginTransaction().add(retainFragment, NETWORK_FRAGMENT_TAG).commit();
        }

        return retainFragment;
    }*/


}
