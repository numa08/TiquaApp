package net.numa08.tiqavapp;

import org.robolectric.TestLifecycleApplication;

import java.lang.reflect.Method;

public class TestTiqavApplication extends TiqavApplication implements TestLifecycleApplication {

    public ApplicationComponent component;

    @Override
    public ApplicationComponent getComponent() {
        return component;
    }

    @Override
    public void beforeTest(Method method) {
    }

    @Override
    public void prepareTest(Object test) {

    }

    @Override
    public void afterTest(Method method) {

    }
}
