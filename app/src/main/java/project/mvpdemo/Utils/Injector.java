package project.mvpdemo.Utils;


import org.greenrobot.eventbus.EventBus;

import project.mvpdemo.BuildConfig;

/**
 * Created by Keyur Tailor on 07-Jun-2018.
 */

public class Injector {

    private static EventBus eventBus;

    public static EventBus provideEventBus() {
        if (eventBus == null) {
            eventBus = EventBus.builder()
                    .logNoSubscriberMessages(BuildConfig.DEBUG)
                    .sendNoSubscriberEvent(BuildConfig.DEBUG)
                    .build();
        }
        return eventBus;
    }
}
