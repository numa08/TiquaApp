package net.numa08.tiqavapp

import android.os.Build
import net.numa08.tiqa4k.Tiqav
import org.apache.maven.artifact.ant.shaded.IOUtil
import org.hamcrest.core.Is.*
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(CustomRoboRunner::class)
@Config(manifest = Config.NONE, sdk = intArrayOf(Build.VERSION_CODES.LOLLIPOP), constants = BuildConfig::class)
class TiqavServiceConfigurationTest {

    @Test
    public fun parseJsonByGson() {
        val input = TiqavServiceConfigurationTest::class.java.classLoader.getResourceAsStream("newest.json")
        val json = IOUtil.toString(input)
        input.close()

        val gson = TiqavServiceConfiguration().gson
        val tiqavs = gson.fromJson(json, Array<Tiqav>::class.java)

        assertThat(tiqavs.size, `is`(30))
        val tiqav = tiqavs.get(0)
        assertThat(tiqav.id, `is`("5LA"))
        assertThat(tiqav.ext, `is`("png"))
        assertThat(tiqav.height, `is`(250))
        assertThat(tiqav.width, `is`(188))
        assertThat(tiqav.sourceURL, `is`("http://morotyo.net/tmp/11796a7bdc2611fbf4582a613db1d5b6.png"))
    }

    @Test
    public fun retrofitBaseURL() {
        val retrofit = TiqavServiceConfiguration().retrofit
        assertThat(retrofit.baseUrl().url().toString(), `is`(TiqavServiceConfiguration.END_POINT))
    }

}