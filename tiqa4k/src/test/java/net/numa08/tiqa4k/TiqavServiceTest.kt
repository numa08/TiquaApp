package net.numa08.tiqa4k

import net.numa08.mock.tiqa4k.MockTiqavService
import net.numa08.test.CustomRobolectricTestRunner
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.test.assertEquals

@RunWith(CustomRobolectricTestRunner::class)
class TiqavServiceTest {

    @Test
    public fun requestByRetrofit() {
        val service = MockTiqavService.newest
        val tiqavs = service
                .newest()
                .subscribe { tl ->
                    assertEquals(tl.size, 30)

                    val tiqav = Tiqav("5LA", "png", 250, 188, "http://morotyo.net/tmp/11796a7bdc2611fbf4582a613db1d5b6.png")
                    val exTiqav = tl.get(0)

                    assertEquals(tiqav.id, exTiqav.id)
                    assertEquals(tiqav.ext, exTiqav.ext)
                    assertEquals(tiqav.height, exTiqav.height)
                    assertEquals(tiqav.width, exTiqav.width)
                    assertEquals(tiqav.sourceURL, exTiqav.sourceURL)

                }
    }

}