package pl.softra.test

import android.content.Context
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmObject
import io.realm.RealmQuery
import io.realm.RealmResults
import io.realm.internal.RealmCore
import io.realm.log.RealmLog
import org.junit.Before
import org.mockito.Matchers
import org.powermock.api.mockito.PowerMockito
import org.powermock.api.mockito.PowerMockito.`when`
import org.powermock.api.mockito.PowerMockito.mockStatic
import org.robolectric.RuntimeEnvironment
import pl.edu.amu.wmi.erykandroidcommon.test.common.BaseRoboTest
import pl.edu.amu.wmi.erykandroidcommon.user.UserInterface

/**
 * @author Eryk Mariankowski <eryk.mariankowski></eryk.mariankowski>@247.codes> on 18.01.18.
 */
abstract class BaseRealmTest<T : RealmObject, UserInterface>(private val userMock: T, private val clazz: Class<T>) : BaseRoboTest() {

    private var mockRealm: Realm? = null

    @Before
    @Throws(Exception::class)
    fun setup() {
        mockStatic(RealmCore::class.java)
        mockStatic(RealmLog::class.java)
        mockStatic(Realm::class.java)
        mockStatic(RealmConfiguration::class.java)
        Realm.init(RuntimeEnvironment.application)
        val mockRealm = PowerMockito.mock(Realm::class.java)
        val mockRealmConfig = PowerMockito.mock(RealmConfiguration::class.java)
        PowerMockito.doNothing().`when`(RealmCore::class.java)
        RealmCore.loadLibrary(Matchers.any(Context::class.java))
        PowerMockito.whenNew(RealmConfiguration::class.java).withAnyArguments().thenReturn(mockRealmConfig)
        `when`(Realm.getDefaultInstance()).thenReturn(mockRealm)
        `when`<T>(mockRealm.createObject(clazz)).thenReturn(userMock)
        val p1 = userMock
        val personList = listOf(p1)
        val personQuery = mockRealmQuery<T>()
        `when`<T>(personQuery.findFirst()).thenReturn(personList[0])
        `when`(mockRealm.where(clazz)).thenReturn(personQuery)
        `when`(personQuery.equalTo(Matchers.anyString(), Matchers.anyInt())).thenReturn(personQuery)
        mockStatic(RealmResults::class.java)
        val people = mockRealmResults<T>()
        `when`(mockRealm.where(clazz).findAll()).thenReturn(people)
        `when`(personQuery.between(Matchers.anyString(), Matchers.anyInt(), Matchers.anyInt())).thenReturn(personQuery)
        `when`(personQuery.beginsWith(Matchers.anyString(), Matchers.anyString())).thenReturn(personQuery)
        `when`(personQuery.findAll()).thenReturn(people)
        `when`<Iterator<T>>(people.iterator()).thenReturn(personList.iterator())
        `when`(people.size).thenReturn(personList.size)
        this.mockRealm = mockRealm
    }

    @Suppress("UNCHECKED_CAST")
    private fun <T : RealmObject> mockRealmQuery(): RealmQuery<T> {
        return PowerMockito.mock(RealmQuery::class.java) as RealmQuery<T>
    }

    @Suppress("UNCHECKED_CAST")
    private fun <T : RealmObject> mockRealmResults(): RealmResults<T> {
        return PowerMockito.mock(RealmResults::class.java) as RealmResults<T>
    }
}