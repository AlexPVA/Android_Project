package csc207phase2.gamecentre;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * The options select activity.
 */
public class OptionsActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        addProfileButtonListener();
        addPlayGameButtonListener();
    }

    /**
     * Activate the Profiles button.
     */
    private void addProfileButtonListener() {
        Button ProfileButton = findViewById(R.id.Profile);
        ProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToProfile();
            }
        });
    }

    /**
     * Activate the Play Games button.
     */
    private void addPlayGameButtonListener() {
        Button PlayGameButton = findViewById(R.id.PlayGames);
        PlayGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToPlayGames();
            }
        });
    }

    /**
     * Switch to the ProfileActivity to start view the user profile.
     */
    private void switchToProfile() {
        Intent tmp = new Intent(this, ProfileActivity.class);
        startActivity(tmp);
    }

    /**
     * Switch to the GameSelectActivity to choose which game to play.
     */
    private void switchToPlayGames() {
        Intent tmp = new Intent(this, GameSelectActivity.class);
        startActivity(tmp);
    }

}
