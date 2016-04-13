package com.android.chordas.exdagger;

import android.app.Application;
import dagger.Component;
import javax.inject.Singleton;

/**
 * Created by sam_chordas on 4/13/16.
 */
public class ExDaggerApplication extends Application {
  @Singleton
  @Component(modules = {ExDaggerModule.class, DataModule.class})
  public interface ApplicationComponent {
    void inject(ExDaggerApplication app);
    void inject(LoginActivity loginActivity);
  }

  private ApplicationComponent applicationComponent;

  @Override public void onCreate() {
    super.onCreate();
    applicationComponent = DaggerExDaggerApplication_ApplicationComponent.builder()
        .exDaggerModule(new ExDaggerModule(this))
        .dataModule(new DataModule("https://api.github.com"))
        .build();
    applicationComponent.inject(this);
  }

  public ApplicationComponent applicationComponent(){
    return applicationComponent;
  }
}
