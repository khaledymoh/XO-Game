package com.xogame

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.xogame.adapters.GameAdapter
import com.xogame.databinding.ActivityGameBinding
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class GameActivity : DaggerAppCompatActivity(), GameContract.View {

    @Inject
    lateinit var gameAdapter: GameAdapter

    @Inject
    lateinit var presenter: GameContract.Presenter

    private var binding: ActivityGameBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initContentView()
        initRecyclerViewAdapter()
        initListener()
    }

    private fun initContentView() {
        binding = ActivityGameBinding.inflate(layoutInflater)
        val view = binding?.root
        setContentView(view)
    }

    private fun initRecyclerViewAdapter() {
        binding?.recyclerViewGame?.adapter = gameAdapter
    }

    override fun notifyAdapter() {
        gameAdapter.notifyDataSetChanged()
    }

    override fun notifyAdapter(position: Int) {
        gameAdapter.notifyItemChanged(position)
    }

    private fun initListener() {
        binding?.imageViewResetGameIcon?.setOnClickListener {
            presenter.resetGame()
            resetViews()
        }
    }

    override fun onOWon() {
        binding?.textViewO?.text = getString(R.string.label_game_activity_your_are_the_winner)
        handelOVisibility()
    }

    override fun onXWon() {
        binding?.textViewX?.text = getString(R.string.label_game_activity_your_are_the_winner)
        handelXVisibility()
    }

    override fun xTurn() {
        binding?.textViewX?.text = getString(R.string.label_game_activity_your_turn)
        handelXVisibility()
    }

    override fun oTurn() {
        binding?.textViewO?.text = getString(R.string.label_game_activity_your_turn)
        handelOVisibility()
    }

    private fun handelXVisibility() {
        binding?.textViewX?.visibility = View.VISIBLE
        binding?.textViewO?.visibility = View.GONE
    }

    private fun handelOVisibility() {
        binding?.textViewO?.visibility = View.VISIBLE
        binding?.textViewX?.visibility = View.GONE
    }

    private fun resetViews() {
        xTurn()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.destroy()
    }
}
