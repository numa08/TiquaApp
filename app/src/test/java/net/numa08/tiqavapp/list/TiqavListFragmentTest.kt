package net.numa08.tiqavapp.list

import android.content.Context
import io.realm.*
import net.numa08.tiqa4k.Tiqav
import net.numa08.tiqavapp.BuildConfig
import net.numa08.tiqavapp.DaggerApplicationComponent
import net.numa08.tiqavapp.TiqavApplication
import net.numa08.tiqavapp.modules.ContextModule
import net.numa08.tiqavapp.realm.configurator.CacheRealmConfigurator
import net.numa08.tiqavapp.utils.FragmentTestingActivity
import net.numa08.tiqavapp.utils.LoggingRule
import net.numa08.tiqavapp.utils.TestComponentHelper
import net.numa08.tiqavapp.utils.loadJSON
import org.apache.maven.artifact.ant.shaded.IOUtil
import org.hamcrest.core.Is.*
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Matchers.any
import org.mockito.Matchers.anyInt
import org.powermock.api.mockito.PowerMockito.*
import org.powermock.core.classloader.annotations.PowerMockIgnore
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.rule.PowerMockRule
import org.robolectric.Robolectric
import org.robolectric.RobolectricGradleTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

@RunWith(RobolectricGradleTestRunner::class)
@Config(constants = BuildConfig::class, sdk = intArrayOf(21))
@PowerMockIgnore("org.mockito.*", "org.robolectric.*", "android.*")
@PrepareForTest(RealmResults::class ,Realm::class, RealmFactory::class, CacheRealmConfigurator::class)
public class TiqavListFragmentTest {

    @get:Rule
    public val mockRule = PowerMockRule()
    @get:Rule
    public val logRule = LoggingRule()

    val helper = TestComponentHelper()
    var realm: Realm? = null
    var activity: FragmentTestingActivity? = null

    @Before
    public fun setupActivity() {
        activity = Robolectric.setupActivity(FragmentTestingActivity::class.java)
    }

    @Before
    public fun startApplication() {
        TiqavApplication().onCreate()
    }

    @Before
    public fun mockRealm() {
        realm = mock(Realm::class.java)
        realm?.let{initHandlerController(it)}
        mockStatic(RealmFactory::class.java)
        `when`(RealmFactory.getInstance(any())).thenReturn(realm)
    }

    @Before
    public fun clear() {
        helper.clear()
    }

    @Test
    public fun loadTiqav() {
        helper.pushComponent(TiqavApplication.getApplication(),DaggerApplicationComponent.builder()
        .contextModule(object : ContextModule(RuntimeEnvironment.application) {
            override fun providesContext(): Context? {
                return RuntimeEnvironment.application
            }
        }).build())

        val tiqavs = TiqavListFragmentTest::class.java.classLoader.getResourceAsStream("newest.json").use { i ->
            val j = IOUtil.toString(i)
            loadJSON(j)
        }
        @Suppress("UNCHECKED_CAST")
        val result = mock(RealmResults::class.java) as RealmResults<Tiqav>
        `when`(result[anyInt()]).thenAnswer { it ->
            val idx = it.arguments[0] as Int
            tiqavs[idx]
        }
        `when`(result.size).thenReturn(tiqavs.size)
        realm?.let {
            `when`(it.allObjects(Tiqav::class.java)).thenReturn(result)
            setRealmForResult(result, it)
        }

        val fragment = TiqavListFragment_.builder().build()
        activity
        ?.supportFragmentManager
        ?.beginTransaction()
        ?.add(1, fragment, null)
        ?.commit()

        fragment.recyclerView.measure(0, 0)
        fragment.recyclerView.layout(0, 0, 100, 10000)
        assertThat(fragment.recyclerView.childCount, `is`(tiqavs.size))
    }
}