package com.android.chordas.exdagger;

import android.app.Application;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;
import dagger.Module;
import dagger.Provides;
import java.util.Timer;
import javax.inject.Singleton;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

/**
 * Created by sam_chordas on 4/13/16.
 */
@Module
public class DataModule {
  private String baseUrl;

  public DataModule(String baseUrl){
    this.baseUrl = baseUrl;
  }

  @Provides @Singleton OkHttpClient provideOkHttpClient(Application application){
    OkHttpClient client = new OkHttpClient();
    return client;
  }

  @Provides @Singleton Picasso providePicasso(Application app, OkHttpClient client){
    return new Picasso.Builder(app)
        .downloader(new OkHttp3Downloader(client))
        .listener((picasso, uri, e) -> Timber.e(e, "Failed to load image: %s", uri))
        .build();
  }

  @Provides @Singleton Retrofit provideRetrofit(OkHttpClient client){
    Retrofit retrofit = new Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(baseUrl)
        .client(client)
        .build();
    return retrofit;
  }
}
