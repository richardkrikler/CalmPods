package eu.darken.capod.common.theming

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mudita.mmd.components.cards.CardColorsMMD
import com.mudita.mmd.components.cards.CardDefaultsMMD
import com.mudita.mmd.components.cards.CardElevationMMD
import com.mudita.mmd.components.lazy.LazyColumnMMD

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

/**
 * LazyColumnMMD pages by [scrollStep] items per swipe (MMD default 4). CAPod lists hold large cards,
 * so one item per swipe keeps every row reachable. Imported as `LazyColumn` via alias.
 */
@Composable
fun EinkLazyColumn(
    modifier: Modifier = Modifier,
    state: LazyListState = rememberLazyListState(),
    contentPadding: PaddingValues = PaddingValues(0.dp),
    reverseLayout: Boolean = false,
    verticalArrangement: Arrangement.Vertical = if (!reverseLayout) Arrangement.Top else Arrangement.Bottom,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    content: LazyListScope.() -> Unit,
) = LazyColumnMMD(
    modifier = modifier,
    state = state,
    contentPadding = contentPadding,
    reverseLayout = reverseLayout,
    verticalArrangement = verticalArrangement,
    horizontalAlignment = horizontalAlignment,
    scrollStep = 1,
    content = content,
)
