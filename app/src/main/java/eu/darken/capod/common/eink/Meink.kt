package eu.darken.capod.common.eink

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.IBinder
import android.os.Parcel
import eu.darken.capod.common.debug.logging.Logging.Priority.WARN
import eu.darken.capod.common.debug.logging.asLog
import eu.darken.capod.common.debug.logging.log
import eu.darken.capod.common.debug.logging.logTag

/**
 * CalmPods: sets the Mudita Kompakt `meink` refresh mode for our package. No-op elsewhere.
 * Adapted from Katapult (GPL-3), originally by UndefinedProgrammer/InkMaster.
 */
object Meink {
    private val TAG = logTag("Eink", "Meink")
    private const val DESCRIPTOR = "android.meink.IMeinkService"
    private const val TRANSACTION_SET_MODE = 5

    const val MODE_CONTRAST = 1
    const val MODE_CLEAR = 3
    const val MODE_READING = 4

    // ponytail: fixed mode; expose as a setting if Clear ghosts too much on some screens.
    private const val DEFAULT_MODE = MODE_CLEAR

    val isKompakt: Boolean
        get() = Build.BRAND.equals("Mudita", ignoreCase = true) ||
            Build.MODEL.equals("Kompakt", ignoreCase = true) ||
            Build.DEVICE.equals("Kompakt", ignoreCase = true)

    @SuppressLint("PrivateApi")
    fun apply(context: Context, mode: Int = DEFAULT_MODE) {
        if (!isKompakt) return
        // A vendor binder: any failure must fall back to the device default, never crash.
        val data = Parcel.obtain()
        val reply = Parcel.obtain()
        try {
            val binder = Class.forName("android.os.ServiceManager")
                .getDeclaredMethod("getService", String::class.java)
                .invoke(null, "meink") as? IBinder ?: return
            data.writeInterfaceToken(DESCRIPTOR)
            data.writeString(context.packageName)
            data.writeInt(mode)
            if (binder.transact(TRANSACTION_SET_MODE, data, reply, 0)) reply.readException()
        } catch (e: Exception) {
            log(TAG, WARN) { "apply($mode) failed: ${e.asLog()}" }
        } finally {
            data.recycle()
            reply.recycle()
        }
    }
}
