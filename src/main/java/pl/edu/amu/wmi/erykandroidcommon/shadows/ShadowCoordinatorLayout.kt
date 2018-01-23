package pl.edu.amu.wmi.erykandroidcommon.shadows

import android.widget.EditText
import org.robolectric.annotation.Implements
import org.robolectric.shadows.ShadowTextView

@Implements(EditText::class)
class ShadowEditText : ShadowTextView()
