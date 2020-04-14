package com.xogame.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textview.MaterialTextView
import com.xogame.GameContract
import com.xogame.R
import com.xogame.models.Item

class GameAdapter(
    private val presenter: GameContract.Presenter
) : RecyclerView.Adapter<GameAdapter.GameViewHolder>() {

    private lateinit var context: Context

    private val items: MutableList<Item> = mutableListOf()

    init {
        for (i in 0..8) {
            items.add(Item())
        }
        presenter.setGameAdapterItems(items)
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        context = recyclerView.context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        return GameViewHolder(
            LayoutInflater.from(context).inflate(R.layout.row_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        holder.bind(items[position], position)
    }

    override fun getItemCount(): Int = items.size

    inner class GameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var viewHorizontalDivider: View? = null
        private var textViewType: MaterialTextView? = null

        init {
            viewHorizontalDivider = itemView.findViewById(R.id.view_horizontal_divider)
            textViewType = itemView.findViewById(R.id.text_view_item_type)
        }

        fun bind(item: Item, position: Int) {
            handelTextViewType(item)
            handelLastRowHorizontalDivider(position)
            itemView.setOnClickListener {
                presenter.onGameAdapterItemClick(item, position)
            }
        }

        private fun handelTextViewType(item: Item) {
            textViewType?.text = if (item.itemType != null) item.itemType.toString() else ""
        }

        private fun handelLastRowHorizontalDivider(position: Int) {
            if (position > 5) {
                viewHorizontalDivider?.visibility = View.GONE
            } else {
                viewHorizontalDivider?.visibility = View.VISIBLE
            }
        }
    }
}