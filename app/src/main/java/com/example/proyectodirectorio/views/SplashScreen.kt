package com.example.proyectodirectorio.views

import android.graphics.Paint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.proyectodirectorio.R
import com.example.proyectodirectorio.components.TituloPrincipal
import com.example.proyectodirectorio.dataStore.StoreBoarding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.intellij.lang.annotations.JdkConstants


@Composable
fun SplashScreen(navController: NavController, store: Boolean?, storeBoarding: StoreBoarding) {
    val scope = rememberCoroutineScope()

    if (store == true) {
        LaunchedEffect(true) {
            navController.navigate("home") {
                popUpTo(0)
            }
        }
    } else {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFFFFFFF)),
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxSize()
            ) {
                Spacer(modifier = Modifier.height(40.dp))
                Image(
                    painter = painterResource(id = R.drawable.organizacion),
                    contentDescription = "Logo de bienvenida",
                    modifier = Modifier.size(220.dp)
                )
                Spacer(modifier = Modifier.height(40.dp))
                TituloPrincipal(title = "¡Bienvenido a Agendástico!", color = Color(0xFF212738))

                Text(
                    text = "Organiza tus contactos fácilmente en un solo lugar.",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black,
                    modifier = Modifier.padding(16.dp),
                    textAlign = TextAlign.Center
                )


                Button(
                    onClick = {
                        scope.launch {
                            storeBoarding.saveBoarding(true)
                            navController.navigate("home") {
                                popUpTo(0)
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 32.dp, vertical = 40.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF23CE6B),
                        contentColor = Color(0xFFFFFFFF),
                        disabledContainerColor = Color(0xFFD4FFD6),
                        disabledContentColor = Color(0xFFFFFFFF)
                    )
                ) {

                    Text(
                        text = "Comenzar",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}
