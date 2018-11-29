package csc207phase2.gamecentre;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity {

    /**
     * The current user current logged in.
     */
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        addScoresButtonListener();
        addLogoutButtonListener();
    }

    /**
     * Activate the Scores button.
     */
    private void addScoresButtonListener() {
        Button ScoreButton = findViewById(R.id.Scores);
        ScoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToScore();
            }
        });
    }

    /**
     * Activate the Logout button.
     */
    private void addLogoutButtonListener() {
        Button LogoutButton = findViewById(R.id.LogOut);
        LogoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentUser != null) {
                    mAuth.signOut();
                    Intent tmp = new Intent(getApplicationContext(), GameCentreActivity.class);
                    startActivity(tmp);
                }
            }
        });
    }

    /**
     * Switch to the ScoreActivity to view the user's highscores.
     */
    private void switchToScore() {
        Intent tmp = new Intent(this, ProfileScoreActivity.class);
        String user = currentUser.getEmail();
        tmp.putExtra("user", user);
        startActivity(tmp);
    }

}
