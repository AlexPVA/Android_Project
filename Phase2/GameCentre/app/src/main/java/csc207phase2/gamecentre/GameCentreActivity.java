package csc207phase2.gamecentre;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * The game centre activity.
 */
public class GameCentreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_centre);

        addSignupButtonListener();
        addSigninButtonListener();
    }

    /**
     * Activate the signup button.
     */
    private void addSignupButtonListener() {
        Button signupButton = findViewById(R.id.Sign_Up);
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToSignup();
            }
        });
    }

    /**
     * Activate the signin button.
     */
    private void addSigninButtonListener() {
        Button signinButton = findViewById(R.id.Sign_In);
        signinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToSignin();
            }
        });
    }

    /**
     * Switch to the SignUpActivity view to register new user.
     */
    private void switchToSignup() {
        Intent tmp = new Intent(this, SignUpActivity.class);
        startActivity(tmp);
    }

    /**
     * Switch to the SignInActivity view to sign in an existing user.
     */
    private void switchToSignin() {
        Intent tmp = new Intent(this, SignInActivity.class);
        startActivity(tmp);
    }
}

