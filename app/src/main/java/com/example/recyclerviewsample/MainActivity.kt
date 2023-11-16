package com.example.recyclerviewsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.recyclerviewsample.databinding.ActivityMainBinding
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: AffirmationListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        val list = DataSource().loadAffirmations()
//        val adapter = AffirmationAdapter(list, this)
        adapter = AffirmationListAdapter(this)
        binding.affirmations.adapter = adapter

        adapter.submitList(listOf(Item.Loading))
        thread {
            Thread.sleep(3000)

            adapter.submitList(
                listOf(
                    Item.Header(R.string.header1),
                    Item.Affirmation(R.string.affirmation1, R.drawable.image1),
                    Item.Affirmation(R.string.affirmation2, R.drawable.image2),
                    Item.Affirmation(R.string.affirmation3, R.drawable.image3),
                    Item.Affirmation(R.string.affirmation4, R.drawable.image4),
                    Item.Header(R.string.header2),
                    Item.Affirmation(R.string.affirmation5, R.drawable.image5),
                    Item.Affirmation(R.string.affirmation6, R.drawable.image6),
                    Item.Affirmation(R.string.affirmation7, R.drawable.image7),
                    Item.Affirmation(R.string.affirmation8, R.drawable.image8),
                    Item.Header(R.string.header3),
                    Item.Affirmation(R.string.affirmation9, R.drawable.image9),
                    Item.Affirmation(R.string.affirmation10, R.drawable.image10),
                )
            )
        }
    }
}