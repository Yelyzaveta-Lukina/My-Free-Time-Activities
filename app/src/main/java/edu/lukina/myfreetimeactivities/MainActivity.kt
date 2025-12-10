package edu.lukina.myfreetimeactivities

/**
 * Created by Yelyzaveta Lukina on 11.15.2025.
 */

// Imports the Intent class, used to start activities or send messages between components
import android.content.Intent
// Imports the Bundle class, used for passing data between activities or saving instance state
import android.os.Bundle
// Imports the Button class, a UI element that the user can tap or click to perform an action
import android.widget.Button
// Imports the AppCompatActivity class from AndroidX, a base class for activities
// that use the support library action bar features
import androidx.appcompat.app.AppCompatActivity

// Declares the MainActivity class, inheriting from AppCompatActivity
class MainActivity : AppCompatActivity() {
    // Overrides the onCreate method, which is called when the activity is first created
    override fun onCreate(savedInstanceState: Bundle?) {
        // Calls the superclass's (AppCompatActivity) onCreate method
        // to perform default initialization
        super.onCreate(savedInstanceState)
        // Sets the user interface layout for this activity
        // from the XML file R.layout.activity_main
        setContentView(R.layout.activity_main)

        // Accesses the support action bar (if it exists)
        // and sets its title to "My Free Time Activities"
        supportActionBar?.title = "My Free Time Activities"

        // Finds the Button widget within the activity's layout using its ID R.id.open_list_button
        // Assigns the found Button to the 'openListButton' variable
        val openListButton = findViewById<Button>(R.id.open_list_button)
        // Sets an OnClickListener for the 'openListButton'
        // This lambda function will be executed when the button is clicked
        openListButton.setOnClickListener {
            // Creates a new Intent to start the HobbyListActivity
            val intent = Intent(this, HobbyListActivity::class.java)
            // Starts the HobbyListActivity using the created Intent
            startActivity(intent)
        }
    }
}


