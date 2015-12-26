package net.numa08.tiqavapp.modules

import android.os.Build
import net.numa08.tiqa4k.Tiqav
import net.numa08.tiqavapp.BuildConfig
import net.numa08.tiqavapp.CustomRoboRunner
import org.apache.maven.artifact.ant.shaded.IOUtil
import org.hamcrest.core.Is
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(CustomRoboRunner::class)
@Config(manifest = Config.NONE, sdk = intArrayOf(Build.VERSION_CODES.LOLLIPOP), constants = BuildConfig::class)
class TiqavModuleTest {

    @Test
    public fun parseJsonByGSON() {
        val input = TiqavModuleTest::class.java.classLoader.getResourceAsStream("newest.json")
        val json = IOUtil.toString(input)
        input.close()

        val gson = TiqavModule().gson
        val tiqavs = gson.fromJson(json, Array<Tiqav>::class.java)

        Assert.assertThat(tiqavs.size, Is.`is`(30))
        val tiqav = tiqavs.get(0)
        Assert.assertThat(tiqav.id, Is.`is`("5LA"))
        Assert.assertThat(tiqav.ext, Is.`is`("png"))
        Assert.assertThat(tiqav.height, Is.`is`(250))
        Assert.assertThat(tiqav.width, Is.`is`(188))
        Assert.assertThat(tiqav.sourceURL, Is.`is`("http://morotyo.net/tmp/11796a7bdc2611fbf4582a613db1d5b6.png"))
    }

    @Test
    public fun retrofitBaseURL() {
        var retrofit = TiqavModule().retrofit
        Assert.assertThat(retrofit.baseUrl().url().toString(), Is.`is`(TiqavModule.END_POINT))

    }
}