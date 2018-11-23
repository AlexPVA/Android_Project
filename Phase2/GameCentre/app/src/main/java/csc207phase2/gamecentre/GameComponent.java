package csc207phase2.gamecentre;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Handles saving and other commonalities between all games for the game centre.
 */
abstract class GameComponent extends AppCompatActivity implements Serializable {

    /**
     * Firebase authentication.
     */
    private FirebaseAuth mAuth;

    /**
     * The current Firebase user account.
     */
    private FirebaseUser currentUser;

    /**
     * The account name of the current user.
     */
    private String accountName;

    /**
     * The main save file.
     */
    public static final String SAVE_FILENAME = "save_file.ser";

    /**
     * A temporary save file.
     */
    public static final String TEMP_SAVE_FILENAME = "save_file_tmp.ser";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        accountName = currentUser.getEmail();
    }

    /**
     * Load the game manager from fileName.
     *
     * @param fileName the name of the file
     */
    protected void loadFromFile(String fileName) {
        fileName = getName() + "_" + accountName + "_" + fileName;
        try {
            InputStream inputStream = this.openFileInput(fileName);
            if (inputStream != null) {
                Log.e("load activity", "input stream isn't null");
                ObjectInputStream input = new ObjectInputStream(inputStream);
                setBoardManager((BoardManager) input.readObject());
                inputStream.close();
            }else{
                Log.e("load activity", "input stream isn't null");
            }
        } catch (FileNotFoundException e) {
            Log.e("load activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("load activity", "Can not read file: " + e.toString());
        } catch (ClassNotFoundException e) {
            Log.e("load activity", "File contained unexpected data type: " + e.toString());
        }
    }

    /**
     * Save the game manager to fileName.
     *
     * @param fileName the name of the file
     */
    public void saveToFile(String fileName) {
        fileName = getName() + "_" + accountName + "_" + fileName;

        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(
                    this.openFileOutput(fileName, MODE_PRIVATE));
            outputStream.writeObject(getBoardManager());
            outputStream.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    /**
     * Set the game manager.
     *
     * @param m the manager for the game
     */
    abstract void setBoardManager(BoardManager m);

    /**
     * Get the current game manager.
     *
     * @return the current game manager.
     */
    abstract BoardManager getBoardManager();

    /**
     * Get the name of the current game, needs to be implemented by child class.
     *
     * @return the name of the current game.
     */
    abstract String getName();

}
