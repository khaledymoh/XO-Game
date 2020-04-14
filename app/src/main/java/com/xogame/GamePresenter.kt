package com.xogame

import com.xogame.models.Item
import com.xogame.models.ItemType

class GamePresenter(
    private var view: GameContract.View?
) : GameContract.Presenter {

    private var items: MutableList<Item> = mutableListOf()
    private var xItemPositions: MutableList<Int> = mutableListOf()
    private var oItemPositions: MutableList<Int> = mutableListOf()

    private var winningPaths: List<Set<Int>> = listOf(
        setOf(0, 1, 2),
        setOf(3, 4, 5),
        setOf(6, 7, 8),
        setOf(0, 4, 8),
        setOf(2, 4, 6),
        setOf(3, 4, 6),
        setOf(0, 3, 6),
        setOf(1, 4, 7),
        setOf(2, 5, 8)
    )

    private var currentType: ItemType = ItemType.X

    override fun onGameAdapterItemClick(item: Item, position: Int) {
        handelGameAdapterItemClick(item, position)
    }

    override fun setGameAdapterItems(items: MutableList<Item>) {
        this.items = items
    }

    private fun handelGameAdapterItemClick(item: Item, position: Int) {
        item.itemType = currentType
        currentType = if (currentType == ItemType.X) {
            xItemPositions.add(position)
            view?.oTurn()
            ItemType.O
        } else {
            oItemPositions.add(position)
            view?.xTurn()
            ItemType.X
        }
        view?.notifyAdapter(position)
        checkIfWon()
    }

    private fun checkIfWon() {
        val isXWon = winningPaths.any {
            it.all { int ->
                int in xItemPositions
            }
        }

        val isOWon = winningPaths.any {
            it.all { int ->
                int in oItemPositions
            }
        }

        if (isXWon) {
            view?.onXWon()
            return
        }

        if (isOWon) {
            view?.onOWon()
            return
        }
    }

    override fun resetGame() {
        items.map { it.itemType = null }
        xItemPositions.clear()
        oItemPositions.clear()
        currentType = ItemType.X
        view?.notifyAdapter()
    }

    override fun destroy() {
        view = null
    }
}