package project.mvpdemo.dagger.components;


import javax.inject.Singleton;

import dagger.Component;
import project.mvpdemo.dagger.modules.AppModule;
import project.mvpdemo.ecommerce.view.DashboardActivity;

/**
 * Created by Keyur Tailor on 07-Jun-2018.
 */


@Component(modules = {AppModule.class})
@Singleton
public interface AppComponent {

    void inject(DashboardActivity dashboardActivity);
}
