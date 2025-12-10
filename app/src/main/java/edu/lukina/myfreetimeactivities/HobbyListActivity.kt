package edu.lukina.myfreetimeactivities

/**
 * Created by Yelyzaveta Lukina on 11.15.2025.
 */

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer

class HobbyListActivity : AppCompatActivity() {

    private lateinit var listView: ListView

    private val viewModel: HobbyViewModel by viewModels {
        HobbyViewModelFactory(HobbyRepository.getInstance(this))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hobby_list)

        supportActionBar?.title = "My Hobbies"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        listView = findViewById(R.id.hobby_list_view)

        viewModel.allHobbies.observe(this, Observer { hobbies ->
            val names = hobbies.map { it.name }
            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, names)
            listView.adapter = adapter

            listView.setOnItemClickListener { _, _, position, _ ->
                val selectedId = hobbies[position].id
                val intent = Intent(this, HobbyDetailActivity::class.java)
                intent.putExtra("hobbyId", selectedId)
                startActivity(intent)
            }
        })

        val backToMainButton = findViewById<Button>(R.id.back_to_main_button)
        backToMainButton.setOnClickListener {
            finish()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
