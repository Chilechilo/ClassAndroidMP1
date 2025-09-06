package com.jorgeromo.androidClassMp1.firstpartial.login.views

import androidx.annotation.RawRes
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import com.airbnb.lottie.LottieProperty
import com.airbnb.lottie.compose.*
import com.airbnb.lottie.compose.LottieConstants.IterateForever
import com.jorgeromo.androidClassMp1.R

@Composable
fun LottieAnimationView(
    modifier: Modifier = Modifier,
    @RawRes resId: Int = R.raw.animation,           // tu archivo res/raw/animation.json
    autoplay: Boolean = true,
    iterations: Int = IterateForever,               // 1 para una sola vez, IterateForever para loop
    speed: Float = 1f,
    contentScale: ContentScale = ContentScale.Fit

) {
    // Carga la composición desde /res/raw
    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(resId)
    )

    // Estado de reproducción/avance
    val progress by animateLottieCompositionAsState(
        composition = composition,
        isPlaying = autoplay,
        iterations = iterations,
        speed = speed,
        restartOnPlay = true
    )

    // Render de la animación
    LottieAnimation(
        composition = composition,
        progress = { progress },
        modifier = modifier,
        contentScale = contentScale
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewLottie() {
    LottieAnimationView(
        modifier = Modifier.fillMaxWidth(),
        resId = R.raw.animation,
        autoplay = true,
        iterations = IterateForever,
        speed = 1f,
        contentScale = ContentScale.Fit
    )
}