package project.mvpdemo.dagger.modules;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import project.mvpdemo.ApiManager.ApiManager;


/**
 * Created by Keyur Tailor on 07-Jun-2018.
 */


@Module
public class AppModule {

    @Provides
    @Singleton
    ApiManager provideApiManager() {
        return new ApiManager();
    }

}
