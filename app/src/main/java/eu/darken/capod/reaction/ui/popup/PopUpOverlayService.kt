package eu.darken.capod.reaction.ui.popup

import android.accessibilityservice.AccessibilityService
import android.content.Context
import android.provider.Settings
import android.view.accessibility.AccessibilityEvent
import eu.darken.capod.common.debug.logging.log
import eu.darken.capod.common.debug.logging.logTag

/**
 * CalmPods: exists only so [PopUpWindow] can add a TYPE_ACCESSIBILITY_OVERLAY window. That works
 * without SYSTEM_ALERT_WINDOW and also above the lock screen. Listens to no events.
 */
class PopUpOverlayService : AccessibilityService() {

    override fun onServiceConnected() {
        log(TAG) { "onServiceConnected()" }
        instance = this
    }

    override fun onDestroy() {
        log(TAG) { "onDestroy()" }
        if (instance === this) instance = null
        super.onDestroy()
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent?) = Unit

    override fun onInterrupt() = Unit

    companion object {
        private val TAG = logTag("Reaction", "PopUp", "OverlayService")

        @Volatile var instance: PopUpOverlayService? = null
            private set

        /** Enabled in system settings, even if not (yet) bound. */
        fun isEnabled(context: Context): Boolean = Settings.Secure.getString(
            context.contentResolver,
            Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES,
        )?.contains("${context.packageName}/${PopUpOverlayService::class.java.name}") == true
    }
}
