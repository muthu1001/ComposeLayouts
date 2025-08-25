/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.codelabs.state

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun WellnessScreen(
    modifier: Modifier = Modifier,
    wellnessViewModel: WellnessViewModel = viewModel()
) {
    Column(modifier = modifier) {

        StatelessCounter("glass water",wellnessViewModel.waterDrunk, { wellnessViewModel.countWater() })

        StatelessCounter("standing",wellnessViewModel.timesStood, { wellnessViewModel.countStanding() })
        if((wellnessViewModel.waterDrunk*2) < wellnessViewModel.timesStood)
            Text("Water deficiency drink ${(wellnessViewModel.timesStood/2).toDouble() + (if(wellnessViewModel.timesStood%2==1) 0.5 else 0.0) - (wellnessViewModel.waterDrunk)} glass of water")
        else if(wellnessViewModel.waterDrunk>0)
            Text("Since you had ${wellnessViewModel.waterDrunk} glass water, standup ${(wellnessViewModel.waterDrunk*2 - wellnessViewModel.timesStood)} times")
        else
            Text("Drink water")
        WellnessTasksList(
            list = wellnessViewModel.tasks,
            onCheckedTask = { task, checked ->
                wellnessViewModel.changeTaskChecked(task, checked)
            },
            onCloseTask = { task ->
                wellnessViewModel.remove(task)
            }
        )
    }
}
