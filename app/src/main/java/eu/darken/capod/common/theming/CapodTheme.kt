package eu.darken.capod.common.theming

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import com.mudita.mmd.ThemeMMD
import com.mudita.mmd.eInkColorScheme

/**
 * CalmPods: E-ink only. Pure black/white MMD scheme, no dynamic colours, no ripple (ThemeMMD).
 * The colour/style settings from CAPod are ignored on purpose; only the light/dark mode is honoured.
 */
@Composable
fun CapodTheme(
    state: ThemeState = ThemeState(),
    content: @Composable () -> Unit,
) {
    val darkTheme = when (state.mode) {
        ThemeMode.SYSTEM -> isSystemInDarkTheme()
        ThemeMode.DARK -> true
        ThemeMode.LIGHT -> false
    }

    CompositionLocalProvider(
        LocalBatteryColors provides if (darkTheme) BatteryColors.EinkDark else BatteryColors.EinkLight,
    ) {
        ThemeMMD(
            colorScheme = if (darkTheme) EinkDarkColorScheme else EinkLightColorScheme,
            content = content,
        )
    }
}

// MMD leaves several surfaceContainer slots Unspecified; CAPod's cards draw on them.
private val EinkLightColorScheme = eInkColorScheme.copy(
    surfaceContainerLowest = Color.White,
    surfaceContainer = Color.White,
    surfaceContainerHigh = Color.White,
    surfaceContainerHighest = Color.White,
)

private val EinkDarkColorScheme = darkColorScheme(
    primary = Color.White,
    onPrimary = Color.Black,
    primaryContainer = Color.Black,
    onPrimaryContainer = Color.White,
    secondary = Color.White,
    onSecondary = Color.Black,
    secondaryContainer = Color.Black,
    onSecondaryContainer = Color.White,
    tertiary = Color.White,
    onTertiary = Color.Black,
    tertiaryContainer = Color.Black,
    onTertiaryContainer = Color.White,
    error = Color.White,
    onError = Color.Black,
    errorContainer = Color.Black,
    onErrorContainer = Color.White,
    background = Color.Black,
    onBackground = Color.White,
    surface = Color.Black,
    onSurface = Color.White,
    surfaceVariant = Color.Black,
    onSurfaceVariant = Color.White,
    surfaceContainerLowest = Color.Black,
    surfaceContainerLow = Color.Black,
    surfaceContainer = Color.Black,
    surfaceContainerHigh = Color.Black,
    surfaceContainerHighest = Color.Black,
    outline = Color.White,
    outlineVariant = Color.White,
    inverseSurface = Color.White,
    inverseOnSurface = Color.Black,
)
