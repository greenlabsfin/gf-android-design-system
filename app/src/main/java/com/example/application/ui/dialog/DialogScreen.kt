package com.example.application.ui.dialog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.application.R
import com.greenlabsfin.design.component.GFButton
import com.greenlabsfin.design.component.GFDialog
import com.greenlabsfin.design.component.GFDialogDefaults
import com.greenlabsfin.design.component.GFHeight
import com.greenlabsfin.design.component.GfTopBarLayout

@Composable
fun DialogScreen(onNavigationClick: () -> Unit = {}) {
    var defaultDialogVisibility by remember { mutableStateOf(false) }
    var buttonDialogVisibility by remember { mutableStateOf(false) }
    var negativeVisibility by remember { mutableStateOf(false) }
    GfTopBarLayout(
        title = stringResource(id = R.string.app_name),
        navigationIcon = Icons.Filled.Menu,
        onNavigationClick = onNavigationClick,
    ) {

        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                GFButton(
                    height = GFHeight.Medium,
                    colors = GFButton.Style.containerPrimary,
                    text = "기본 다이얼로그"
                ) {
                    defaultDialogVisibility = defaultDialogVisibility.not()
                }

                GFButton(
                    height = GFHeight.Medium,
                    colors = GFButton.Style.containerPrimary,
                    text = "기본 버튼 다이얼로그"
                ) {
                    buttonDialogVisibility = buttonDialogVisibility.not()
                }

                GFButton(
                    height = GFHeight.Medium,
                    colors = GFButton.Style.containerPrimary,
                    text = "기본 버튼 다이얼로그 with Negative"
                ) {
                    negativeVisibility = negativeVisibility.not()
                }
            }
        }

        GFDialog(
            itemVisible = defaultDialogVisibility,
            onDismissRequest = {
                defaultDialogVisibility = defaultDialogVisibility.not()
            },
            content = {
                GFDialogDefaults.Contents.DefaultText(title = "기본 타이틀", message = "기본 메시지")
            }
        )

        GFDialog(
            itemVisible = buttonDialogVisibility,
            onDismissRequest = {
                buttonDialogVisibility = buttonDialogVisibility.not()
            },
            content = {
                GFDialogDefaults.Contents.OnlyTitle(title = "버튼 타이틀")
            },
            buttonContent = {
                GFDialogDefaults.Buttons.SinglePrimary {
                    buttonDialogVisibility = false
                }
            }
        )

        GFDialog(
            itemVisible = negativeVisibility,
            onDismissRequest = {
                negativeVisibility = negativeVisibility.not()
            },
            content = {
                GFDialogDefaults.Contents.DefaultText(title = "버튼 타이틀", message = "Negative")
            },
            buttonContent = {
                GFDialogDefaults.Buttons.DoubleHorizontalPrimary(
                    onNegativeButtonClicked = {
                        negativeVisibility = false
                    },
                    onPositiveButtonClicked = {
                        negativeVisibility = false
                    }
                )

            }
        )
    }
}
