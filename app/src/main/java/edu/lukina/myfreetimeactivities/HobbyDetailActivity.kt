// Defines the package for this Kotlin file
package edu.lukina.myfreetimeactivities

/**
 * Created by Yelyzaveta Lukina on 11.15.2025.
 */

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer

class HobbyDetailActivity : AppCompatActivity() {

    private val viewModel: HobbyViewModel by viewModels {
        HobbyViewModelFactory(HobbyRepository.getInstance(this))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hobby_detail)

        supportActionBar?.title = "Hobby Details"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val hobbyId = intent.getIntExtra("hobbyId", -1)

        val imageView = findViewById<ImageView>(R.id.hobby_image)
        val nameTextView = findViewById<TextView>(R.id.hobby_name)
        val descTextView = findViewById<TextView>(R.id.hobby_description)

        viewModel.getHobbyById(hobbyId).observe(this, Observer { hobby ->
            if (hobby != null) {

                val imageName = "hobby_${hobby.id}"
                val resId = resources.getIdentifier(imageName, "drawable", packageName)

                if (resId != 0) imageView.setImageResource(resId)

                nameTextView.text = "${hobby.id}. ${hobby.name}"
                descTextView.text = hobby.description
            }
        })

        findViewById<Button>(R.id.back_to_list_button).setOnClickListener {
            finish()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}