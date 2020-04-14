package com.xogame

import com.xogame.models.Item

interface GameContract {

    interface Presenter {

        fun onGameAdapterItemClick(item: Item, position: Int)

        fun setGameAdapterItems(items : MutableList<Item>)

        fun resetGame()

        fun destroy()
    }

    interface View {

        fun notifyAdapter(position: Int)

        fun notifyAdapter()

        fun xTurn()

        fun oTurn()

        fun onXWon()

        fun onOWon()
    }
}