package com.kelvin.githubapiapp.userdetail

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.kelvin.githubapiapp.userdetail.viewmodel.UserDetailViewModel
import com.kelvin.githubapiapp.shared.component.CollapsingToolbar

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun UserDetailScreen(vm: UserDetailViewModel = hiltViewModel(), id: Int) {
    CollapsingToolbar(
        Modifier
            .fillMaxSize(), vm, id
    )
}
