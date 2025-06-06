package com.example.proyectodirectorio.onBoardViews

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.proyectodirectorio.dataStore.StoreBoarding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun ButtonFinish(currentPage:Int,navController: NavController,store: StoreBoarding){
    Row(modifier= Modifier
        .padding(bottom = 20.dp)
        .fillMaxWidth(),
        horizontalArrangement = if(currentPage !=2) Arrangement.SpaceBetween
        else Arrangement.Center
    ){
        if (currentPage==2){
            Button(
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF23CE6B),
                    contentColor = Color(0xFFFFFFFF),
                    disabledContainerColor = Color(0xFFD4FFD6),
                    disabledContentColor = Color(0xFFFFFFFF)),

                onClick = {
                CoroutineScope(Dispatchers.IO).launch {
                    store.saveBoarding(true)
                }
                navController.navigate("home"){
                    popUpTo(0)
                }
            },
                ) {
                Text(text="Entrar",modifier=Modifier
                    .padding(vertical=8.dp, horizontal=40.dp),
                    color= Color.Black
                )
            }
        }
    }
}