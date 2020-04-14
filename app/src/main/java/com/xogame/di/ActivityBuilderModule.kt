package com.xogame.di

import com.xogame.GameActivity
import com.xogame.GameModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [GameModule::class])
    abstract fun bindGameActivity(): GameActivity
}