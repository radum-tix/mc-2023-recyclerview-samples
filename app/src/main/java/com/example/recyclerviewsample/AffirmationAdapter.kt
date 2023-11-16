package com.example.recyclerviewsample

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewsample.databinding.ViewItemBinding

class AffirmationAdapter(
    private val items: List<Affirmation>,
    private val context: Context
) : RecyclerView.Adapter<AffirmationAdapter.AffirmationViewHolder>() {

    class AffirmationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textView: TextView = itemView as TextView

        fun onBind(affirmation: Affirmation) {
            textView.setText(affirmation.stringResourceId)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AffirmationViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = ViewItemBinding.inflate(inflater).root
        return AffirmationViewHolder(view)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: AffirmationViewHolder, position: Int) {
        holder.onBind(items[position])
    }
}