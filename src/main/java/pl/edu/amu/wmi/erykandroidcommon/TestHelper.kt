package pl.edu.amu.wmi.erykandroidcommon

import android.app.Activity
import org.junit.Assert.assertEquals
import org.robolectric.Shadows.shadowOf

/**
 * @author Eryk Mariankowski <eryk.mariankowski></eryk.mariankowski>@247.codes> on 19.11.17.
 */
class TestHelper {

    fun assertActivityStarted(actvity: Activity, expected: Class<*>) {
        val startedIntent = shadowOf(actvity).nextStartedActivity
        val shadowIntent = shadowOf(startedIntent)
        assertEquals(expected, shadowIntent.intentClass)
    }

}