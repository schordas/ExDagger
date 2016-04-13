package com.android.chordas.exdagger;

import android.content.Context;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

/**
 * Created by sam_chordas on 4/13/16.
 */
@Module
public class ExDaggerModule {
  private final ExDaggerApplication application;

  public ExDaggerModule(ExDaggerApplication application){
    this.application = application;
  }

  @Provides @Singleton Context provideApplicationContext(){
    return application;
  }
}
