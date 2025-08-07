package com.elegidocodes.demo.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.elegidocodes.demo.R;
import com.elegidocodes.demo.databinding.ActivityLoginBinding;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;

    private Context context;

    private FirebaseAuth firebaseAuth;

    private TextInputLayout inputLayoutEmail;
    private TextInputLayout inputLayoutPassword;
    private TextInputEditText inputEditTextEmail;
    private TextInputEditText inputEditTextPassword;
    private MaterialButton btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        bindViews();

        context = LoginActivity.this;

        Log.d("FIREBASE_PROJECT_ID", FirebaseApp.getInstance().getOptions().getProjectId());

        firebaseAuth = FirebaseAuth.getInstance();

        checkIfAlreadyAuthenticated();
        btnLogin.setOnClickListener(v -> performLogin());
    }

    private void bindViews() {
        this.btnLogin = binding.btnLogin;
        this.inputLayoutEmail = binding.emailInputLayout;
        this.inputLayoutPassword = binding.passwordInputLayout;
        this.inputEditTextEmail = binding.emailEditText;
        this.inputEditTextPassword = binding.passwordEditText;
    }

    private void checkIfAlreadyAuthenticated() {
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user != null) {
            Toast.makeText(context, "Welcome back", Toast.LENGTH_SHORT).show();
        }
    }

    private void performLogin() {

        if (inputEditTextEmail.getText() == null) {
            inputLayoutEmail.setError("Email cannot be empty.");
            return;
        }

        if (inputEditTextPassword.getText() == null) {
            inputLayoutPassword.setError("Password cannot be empty.");
            return;
        }

        String email = inputEditTextEmail.getText().toString().trim();
        String password = inputEditTextPassword.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Email and password are required", Toast.LENGTH_SHORT).show();
            return;
        }

        btnLogin.setEnabled(false);

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        goToMainActivity();
                    } else {
                        firebaseAuth.createUserWithEmailAndPassword(email, password)
                                .addOnCompleteListener(registerTask -> {
                                    btnLogin.setEnabled(true);
                                    if (registerTask.isSuccessful()) {
                                        Toast.makeText(this, "User created and signed in", Toast.LENGTH_SHORT).show();
                                        goToMainActivity();
                                    } else {
                                        Toast.makeText(this, "Auth failed.", Toast.LENGTH_LONG).show();
                                        Log.e("LOGIN_ACTIVITY", "Error", registerTask.getException());
                                    }
                                });
                    }
                });
    }

    private void goToMainActivity() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
