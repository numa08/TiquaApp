package net.numa08.tiqavapp.service

import android.os.Build
import io.realm.Realm
import io.realm.RealmFactory
import net.numa08.tiqa4k.Tiqav
import net.numa08.tiqa4k.mock.mockNewest
import net.numa08.tiqavapp.BuildConfig
import net.numa08.tiqavapp.CustomRoboRunner
import net.numa08.tiqavapp.TestApplication
import net.numa08.tiqavapp.realm.configurator.CacheRealmConfigurator
import net.numa08.tiqavapp.utils.loadJSON
import org.apache.maven.artifact.ant.shaded.IOUtil
import org.hamcrest.core.Is
import org.hamcrest.core.IsNot
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Matchers
import org.powermock.api.mockito.PowerMockito
import org.powermock.core.classloader.annotations.PowerMockIgnore
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.rule.PowerMockRule
import org.robolectric.Robolectric
import org.robolectric.annotation.Config
import org.robolectric.shadows.ShadowLog
import java.util.*

@RunWith(CustomRoboRunner::class)
@Config(manifest = Config.NONE, sdk = intArrayOf(Build.VERSION_CODES.LOLLIPOP), constants = BuildConfig::class, application = TestApplication::class)
@PowerMockIgnore("org.mockito.*", "org.robolectric.*", "android.*")
@PrepareForTest(Realm::class, RealmFactory::class,CacheRealmConfigurator::class)
public class LoadTiqavServiceTest{

    @get:Rule
    public val rule = PowerMockRule()

    var realm: Realm? = null

    @Before
    public fun initMockRealm() {
        PowerMockito.mockStatic(RealmFactory::class.java)
        realm = PowerMockito.mock(Realm::class.java)
        PowerMockito.`when`(RealmFactory.getInstance(Matchers.any())).thenReturn(realm)
   }

    @Before
    public fun log() {
        ShadowLog.stream = System.out
    }

    @Test
    public fun loadNewest() {
        val tiqav = LoadTiqavServiceTest::class.java.classLoader.getResourceAsStream("newest.json").use { i ->
            val json = IOUtil.toString(i)
            loadJSON(json)
        }

        val service = mockNewest(tiqav)
        val loadTiqavService = Robolectric.buildService(LoadTiqavService_::class.java).get()
        loadTiqavService.tiqavService = service
        loadTiqavService.cacheRealmConfigurator = PowerMockito.mock(CacheRealmConfigurator::class.java)

        var loadedTiqav = arrayListOf<Tiqav>()
        realm?.let { r ->
            PowerMockito.doAnswer { it ->
                (it.arguments[0] as Realm.Transaction).execute(r)

            }.`when`(r).executeTransaction(Matchers.anyObject())

            PowerMockito.`when`(r.copyToRealmOrUpdate(Matchers.anyCollectionOf(Tiqav::class.java))).thenAnswer { it ->
                (it.arguments[0] as AbstractList<*>).forEach { e ->
                    loadedTiqav.add(e as Tiqav)
                }
                loadedTiqav
            }
        }
        loadTiqavService.loadNewest()
        Assert.assertThat(loadedTiqav.isEmpty(), Is.`is`(IsNot.not(true)))
        Assert.assertThat(loadedTiqav.size, Is.`is`(tiqav.size))
        Assert.assertArrayEquals(tiqav,loadedTiqav.toArray())
    }

}