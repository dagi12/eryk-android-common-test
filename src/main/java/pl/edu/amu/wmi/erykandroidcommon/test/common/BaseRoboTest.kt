package pl.edu.amu.wmi.erykandroidcommon.test.common

import android.app.Activity
import org.junit.Rule
import org.powermock.modules.junit4.rule.PowerMockRule

/**
 * @author Eryk Mariankowski <eryk.mariankowski></eryk.mariankowski>@247.codes> on 18.01.18.
 */
open class BaseRoboTest {

    protected var activity: Activity? = null

    @JvmField
    @Rule
    var rule = PowerMockRule()

}