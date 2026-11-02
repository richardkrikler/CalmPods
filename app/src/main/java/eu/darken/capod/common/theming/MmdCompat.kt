package eu.darken.capod.common.theming

import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.mudita.mmd.components.cards.CardColorsMMD
import com.mudita.mmd.components.cards.CardDefaultsMMD
import com.mudita.mmd.components.cards.CardElevationMMD

// CalmPods: M3 CardDefaults helpers that MMD lacks, so CAPod call sites compile against CardDefaultsMMD
// via an import alias. E-ink cards are flat, so every elevation is ignored.

@Suppress("UNUSED_PARAMETER")
@Composable
fun CardDefaultsMMD.elevatedCardElevation(defaultElevation: Dp = Dp.Unspecified): CardElevationMMD = cardElevation()

@Composable
fun CardDefaultsMMD.elevatedCardColors(
    containerColor: Color = Color.Unspecified,
    contentColor: Color = contentColorFor(containerColor),
): CardColorsMMD = cardColors(containerColor = containerColor, contentColor = contentColor)

@Composable
fun CardDefaultsMMD.outlinedCardColors(
    containerColor: Color = Color.Unspecified,
    contentColor: Color = contentColorFor(containerColor),
): CardColorsMMD = cardColors(containerColor = containerColor, contentColor = contentColor)

