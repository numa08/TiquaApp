package net.numa08.test

import covelline.com.feather_twitter.BuildConfig
import org.robolectric.RobolectricTestRunner

class CustomRobolectricTestRunner(testClass : Class<*>) : RobolectricTestRunner(testClass) {
    init {
        val  buildVariant = if(BuildConfig.FLAVOR.isEmpty()) "" else BuildConfig.FLAVOR + "/" + BuildConfig.BUILD_TYPE
        System.setProperty("android.package", BuildConfig.APPLICATION_ID);
        System.setProperty("android.manifest", "build/intermediates/manifests/full/$buildVariant/AndroidManifest.xml");
        System.setProperty("android.resources", "build/intermediates/res/merged/$buildVariant");
        System.setProperty("android.assets", "build/intermediates/assets/$buildVariant");
    }
}