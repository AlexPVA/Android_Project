package csc207phase2.gamecentre;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * The Sign-Up activity.
 */
public class SignUpActivity extends AppCompatActivity{

    /**
     * The username and password fields the user wishes to sign up with.
     */
    private FirebaseAuth mAuth;
    private EditText txt_password;
    private EditText txt_email;
    private Button btn_register;
    private Button btn_signin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        txt_email = findViewById(R.id.Email);
        txt_password = findViewById(R.id.Password);
        btn_register = findViewById(R.id.Register);
        btn_signin = findViewById(R.id.Login);

        mAuth = FirebaseAuth.getInstance();

        addSignUpButtonListener();
        addSignInButtonListener();
    }

    /**
     * Activate the signup button.
     */
    private void addSignUpButtonListener() {
        btn_register = findViewById(R.id.Register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }

    /**
     * Activate the signin button.
     */
    private void addSignInButtonListener() {
        btn_signin = findViewById(R.id.Login);
        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == btn_signin) {
                    startActivity(new Intent(getApplicationContext(), SignInActivity.class));
                }
            }
        });
    }

    /**
     * Registers the user for an account.
     */
    public void registerUser() {
        String email = this.txt_email.getText().toString().trim();
        String password = this.txt_password.getText().toString().trim();

        if (TextUtils.isEmpty(email)){
            Toast.makeText(this, "Email field is empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)){
            Toast.makeText(this, "Password field is empty", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        try {
                            if (task.isSuccessful()) {
                                Toast.makeText(SignUpActivity.this, "registration successful", Toast.LENGTH_SHORT).show();
                                finish();
                                Intent tmp = new Intent(getApplicationContext(), GameSelectActivity.class);
                                startActivity(tmp);
                            } else {
                                Toast.makeText(SignUpActivity.this, "Couldn't register, try again", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            Log.e("registration activity", "Can not register: " + e.toString());
                        }
                    }
                });
    }
}