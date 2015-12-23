package net.numa08.tiqavapp;

import android.os.Build;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.rule.PowerMockRule;
import org.robolectric.Robolectric;
import org.robolectric.annotation.Config;
import org.robolectric.util.ActivityController;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmFactory;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsSame.sameInstance;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(CustomRoboRunner.class)
@Config(manifest = Config.NONE, sdk = Build.VERSION_CODES.LOLLIPOP, constants = BuildConfig.class)
@PowerMockIgnore({"org.mockito.*", "org.robolectric.*", "android.*"})
@PrepareForTest({Realm.class, RealmFactory.class,CacheRealmConfigurator_.class})
public class MainActivityTest {

    @Rule
    public PowerMockRule rule = new PowerMockRule();

    RealmConfiguration configuration;
    CacheRealmConfigurator_ mockConfigurator;

    @Before
    public void mockRealm() {
        final Realm mock = mock(Realm.class);
        mockStatic(RealmFactory.class);
        when(RealmFactory.getInstance(Matchers.<RealmConfiguration>any())).thenReturn(mock);
    }

    @Before
    public void initConfig() {
        configuration = mock(RealmConfiguration.class);
        mockConfigurator = mock(CacheRealmConfigurator_.class);
        when(mockConfigurator.getRealmConfiguration()).thenReturn(configuration);
        mockStatic(CacheRealmConfigurator_.class);
        when(CacheRealmConfigurator_.getInstance_(any())).thenReturn(mockConfigurator);
    }

    @Test
    public void injectionTest() {
        final ActivityController<MainActivity_> activityController = Robolectric.buildActivity(MainActivity_.class);
        activityController.create();
        final MainActivity activity = activityController.get();

        assertThat(activity.realmConfigurator, is(sameInstance(mockConfigurator)));
        assertThat(activity.realmConfigurator.getRealmConfiguration(), is(sameInstance(configuration)));
    }
}
