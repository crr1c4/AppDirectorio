package com.example.proyectodirectorio.onBoardViews

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.proyectodirectorio.R
import com.example.proyectodirectorio.models.PageData
import com.example.proyectodirectorio.dataStore.StoreBoarding
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState


@OptIn(
    ExperimentalPagerApi::class,
    ExperimentalFoundationApi::class
)
@Composable
fun MainOnBoarding(navController: NavController, store: StoreBoarding) {
    val items = ArrayList<PageData>()

    items.add(
        PageData(
            R.raw.page1,
            "¡Organizate!",
            "Guarda nombres, teléfonos y correos de forma rápida y segura."
        )
    )
    items.add(
        PageData(
            R.raw.page2,
            "Búsqueda inteligente",
            "Encuentra cualquier contacto con solo escribir parte del nombre o número."
        )
    )

    items.add(
        PageData(
            R.raw.page3,
            "Tu agenda, siempre contigo",
            "Haga clic para entrar."
        )
    )


    val pagerState = rememberPagerState(
        pageCount = items.size,
        initialOffscreenLimit = 2,
        infiniteLoop = false
    )

    OnBoardingPager(
        item = items, pagerState = pagerState, modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.White), navController, store
    )
}