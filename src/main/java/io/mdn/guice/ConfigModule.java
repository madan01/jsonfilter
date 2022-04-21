package io.mdn.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import okhttp3.OkHttpClient;

import java.util.concurrent.TimeUnit;

/**
 * Created by madan on 03/08/20.
 **/

public class ConfigModule extends AbstractModule {
    @Override
    protected void configure() {
    }
    @Provides
    public OkHttpClient provideOkHttpClient() {
        return new OkHttpClient.Builder().readTimeout(300, TimeUnit.SECONDS)
                .callTimeout(300, TimeUnit.SECONDS)
                .writeTimeout(300, TimeUnit.SECONDS)
                .build();
    }
}
