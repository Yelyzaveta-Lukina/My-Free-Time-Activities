package edu.lukina.myfreetimeactivities

/**
 * Created by Yelyzaveta Lukina on 11.15.2025.
 */

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Hobby::class], version = 1, exportSchema = false)
abstract class HobbyDatabase : RoomDatabase() {

    abstract fun hobbyDao(): HobbyDao

    companion object {
        @Volatile
        private var INSTANCE: HobbyDatabase? = null

        fun getDatabase(context: Context): HobbyDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    HobbyDatabase::class.java,
                    "hobby_database"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(PREPOPULATE)
                    .build()

                INSTANCE = instance
                instance
            }
        }

        // Pre-populate the database with your 5 hobbies
        private val PREPOPULATE = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)

                CoroutineScope(Dispatchers.IO).launch {
                    INSTANCE?.hobbyDao()?.insertAll(
                        listOf(
                            Hobby(name = "Dancing", description = "I'm a passionate dancer with 10 years of experience. Dancing brings me so much joy and energy. Although I sometimes join classes, I can't attend weekly right now because of my studies — but dancing will always be a part of me."),
                            Hobby(name = "Baking", description = "I love baking for my friends and family — it’s one of my favorite ways to relax. There’s something so calming about creating something sweet and sharing it with people I care about."),
                            Hobby(name = "Gym", description = "I regularly go to the gym and absolutely love it. Working out has improved my health, boosted my mood, and helped shape my body. It truly changed my life — I lost 10 kg quickly and now I’m able to maintain my weight while still enjoying the food I love."),
                            Hobby(name = "Biking", description = "I love biking with my friends and family — it’s one of my favorite ways to stay active and spend quality time together. Whether it’s riding through parks or along nature trails, biking helps me relax, enjoy the outdoors, and get some great exercise at the same time."),
                            Hobby(name = "Dog Walking", description = "I’ve always loved animals, so in my free time I work as a dog walker in my neighborhood. I really enjoy spending time with the dogs—it’s fun, relaxing, and also a great way to stay active!")
                        )
                    )
                }
            }
        }
    }
}