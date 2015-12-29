package net.numa08.tiqavapp.utils

import org.junit.rules.ExternalResource
import org.robolectric.shadows.ShadowLog

public class LoggingRule: ExternalResource() {
    override fun before() {
        ShadowLog.stream = System.out
    }
}