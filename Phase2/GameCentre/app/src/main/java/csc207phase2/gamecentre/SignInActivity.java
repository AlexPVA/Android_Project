package csc207phase2.gamecentre;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * The Sign-In activity.
 */
public class SignInActivity extends AppCompatActivity{

    /**
     * The username and password fields the user entered.
     */
    private EditText txt_email;
    private EditText txt_password;
    private FirebaseAuth mAuth;
    private Button btn_signin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        mAuth = FirebaseAuth.getInstance();
        txt_email = findViewById(R.id.LoginEmail);
        txt_password = findViewById(R.id.LoginPassword);
        btn_signin = findViewById(R.id.login_button);

        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == btn_signin){
                    signinUser();
                }
            }
        });
    }

    /**
     * Signs the user into their account if it exists, and if it doesn't show the text:
     * "Sign In Error/Account not Found"
     */
    public void signinUser() {

        String Email = txt_email.getText().toString().trim();
        String Password = txt_password.getText().toString().trim();
        mAuth.signInWithEmailAndPassword(Email, Password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            finish();
                            Intent tmp = new Intent(getApplicationContext(), OptionsActivity.class);
                            startActivity(tmp);
                        } else {
                            Toast.makeText(SignInActivity.this, "Sign In Error/Account not Found", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}
