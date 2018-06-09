package project.mvpdemo.dagger;


import project.mvpdemo.dagger.components.AppComponent;
import project.mvpdemo.dagger.components.DaggerAppComponent;
import project.mvpdemo.dagger.modules.AppModule;

/**
 * Created by Keyur Tailor on 07-Jun-2018.
 */


public class DaggerInjector {
    private static AppComponent appComponent =
            DaggerAppComponent.builder().appModule(new AppModule()).build();


    public static AppComponent get() {
        return appComponent;
    }

}