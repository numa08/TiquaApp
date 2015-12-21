package net.numa08.tiqavapp.service

import android.content.Intent
import android.os.Build
import net.numa08.tiqa4k.Tiqav
import net.numa08.tiqa4k.mock.mockNewest
import net.numa08.tiqavapp.BuildConfig
import net.numa08.tiqavapp.CustomRoboRunner
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.powermock.core.classloader.annotations.PowerMockIgnore
import org.powermock.modules.junit4.rule.PowerMockRule
import org.robolectric.annotation.Config
import rx.Observer
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import kotlin.test.assertNotNull
import kotlin.test.fail

@RunWith(CustomRoboRunner::class)
@Config(sdk = intArrayOf(Build.VERSION_CODES.LOLLIPOP),constants = BuildConfig::class, manifest = Config.NONE)
@PowerMockIgnore("org.mockito.*", "org.robolectric.*", "android.*")
public class LoadTiqavServiceTest() {

    @get:Rule
    public var rule = PowerMockRule()

    @Test
    public fun loadNewest() {
        val tiqavs = arrayOf(Tiqav("id", "ext", 100, 100, "url"))
        val loadService = mockNewest(tiqavs)

        val service = LoadTiqavService()
        val latch = CountDownLatch(1)

        var result :Array<Tiqav>? = null
        service.observer = object : Observer<Array<Tiqav>> {
            override fun onError(e: Throwable?) {
                fail("fail on Error " + e?.message)
            }

            override fun onCompleted() { }

            override fun onNext(t: Array<Tiqav>?) {
                result = t
                latch.countDown()
            }
        }
        service.tiqavService = loadService
        service.onHandleIntent(Intent(LoadTiqavService.ACTION_LOAD))

        latch.await(5, TimeUnit.SECONDS)

        assertNotNull(result)
        Assert.assertArrayEquals(tiqavs, result)
    }

}