package net.numa08.tiqa4k

import net.numa08.tiqa4k_test.BuildConfig
import org.robolectric.RobolectricTestRunner

public class CustomRoboRunner(testClass: Class<*>) : RobolectricTestRunner(testClass) {
    init {
        val  buildVariant = (if(BuildConfig.FLAVOR.isEmpty()) "" else BuildConfig.FLAVOR )+ "/" + BuildConfig.BUILD_TYPE;
        System.setProperty("android.package", BuildConfig.APPLICATION_ID);
        System.setProperty("android.manifest", "build/intermediates/manifests/full/$buildVariant/AndroidManifest.xml");
        System.setProperty("android.resources", "build/intermediates/res/merged/" + buildVariant);
        System.setProperty("android.assets", "build/intermediates/assets/" + buildVariant);
    }
}