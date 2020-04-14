package com.xogame

import com.xogame.adapters.GameAdapter
import com.xogame.di.ActivityScope
import dagger.Module
import dagger.Provides

@Module
object GameModule {

    @JvmStatic
    @Provides
    fun provideGameAdapter(presenter: GameContract.Presenter): GameAdapter {
        return GameAdapter(presenter)
    }

    @JvmStatic
    @Provides
    @ActivityScope
    fun provideGamePresenter(view: GameActivity): GameContract.Presenter {
        return GamePresenter(view)
    }
}