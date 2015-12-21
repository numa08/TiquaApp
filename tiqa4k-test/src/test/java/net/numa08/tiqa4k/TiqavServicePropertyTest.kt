package net.numa08.tiqa4k

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import rx.Observable
import kotlin.test.assertNotNull

@RunWith(CustomRoboRunner::class)
@Config(manifest = Config.NONE)
public class TiqavServicePropertyTest{

    class Stub {
        var service by TiqavServiceProperty()
    }

    @Test
    public fun getService() {
        val service = Stub().service
        assertNotNull(service)
    }

    @Test
    public fun setService() {
        val stub = Stub()
        val mockService = object : TiqavService {
            override fun newest(): Observable<Array<Tiqav>> {
                throw UnsupportedOperationException()
            }
        }
        Assert.assertNotSame(mockService, stub.service)
        stub.service = mockService
        Assert.assertSame(mockService, stub.service)
    }
}