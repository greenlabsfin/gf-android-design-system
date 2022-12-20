package com.greenlabsfin.design.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Badge
import androidx.compose.material.BadgedBox
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.takeOrElse
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.greenlabsfin.design.core.GfTheme
import com.greenlabsfin.design.core.LocalGfColorScheme
import com.greenlabsfin.design.core.LocalGfTypoScheme
import com.greenlabsfin.design.core.color.red60

@Composable
fun GfText(
    modifier: Modifier = Modifier,
    text: String? = null,
    leftIcon: ImageVector? = null,
    rightIcon: ImageVector? = null,
    count: Int? = null,
    counterStyle: GfCountStyle? = null,
    badge: Boolean = false,
    color: Color = Color.Unspecified,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    style: TextStyle = LocalGfTypoScheme.current.body.mediumBold,
    enabled: Boolean = true,
) {
    // text color priority color -> style -> local provider
    val textColor = color.takeOrElse {
        style.color.takeOrElse {
            LocalGfColorScheme.current.contents.neutralPrimary
        }
    }

    val rememberTextColor by
    rememberUpdatedState(if (enabled) textColor else textColor.copy(alpha = .3f))

    Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        leftIcon?.let { imageVector ->
            Icon(
                imageVector = imageVector,
                contentDescription = imageVector.name,
                tint = textColor
            )
        }
        BadgedBox(
            modifier = if (badge) Modifier.padding(end = 4.dp) else Modifier,
            badge = {
                if (badge) {
                    Badge(
                        modifier = Modifier
                            .size(4.dp)
                            .offset(4.dp, 4.dp),
                        backgroundColor = red60

                    )
                }
            },
            content = {
                Text(
                    modifier = modifier,
                    text = text.toString(),
                    color = rememberTextColor,
                    style = style,
                    onTextLayout = onTextLayout,
                    overflow = overflow,
                    softWrap = softWrap,
                    maxLines = maxLines,
                )
            }
        )
        count?.let {
            counterStyle?.let { counterStyle ->
                GfCount(
                    count = count,
                    style = counterStyle,
                    enabled = enabled,
                )
            }
        }
        rightIcon?.let { imageVector ->
            Icon(
                imageVector = imageVector,
                contentDescription = imageVector.name,
                tint = textColor
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GfTextPreview() {
    GfTheme {
        Surface(
            color = GfTheme.colorScheme.container.background
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                GfText(
                    leftIcon = Icons.Filled.Menu,
                    text = "텍스트",
                    badge = true,
                    count = 3,
                    style = GfTheme.typoScheme.headline.largeBold,
                    counterStyle = GfCount.Style.neutral,
                    rightIcon = Icons.Filled.ArrowDropDown
                )

                GfText(
                    leftIcon = Icons.Filled.Menu,
                    text = "텍스트",
                    count = 3,
                    style = GfTheme.typoScheme.headline.largeBold,
                    counterStyle = GfCount.Style.neutral,
                    rightIcon = Icons.Filled.ArrowDropDown
                )

                GfText(
                    leftIcon = Icons.Filled.Menu,
                    text = "텍스트",
                    badge = true,
                    count = 3,
                    style = GfTheme.typoScheme.body.smallMedium,
                    counterStyle = GfCount.Style.neutral,
                    rightIcon = Icons.Filled.ArrowDropDown
                )
            }
        }
    }
}
