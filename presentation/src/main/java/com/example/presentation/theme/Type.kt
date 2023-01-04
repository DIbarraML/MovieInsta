package com.example.presentation.theme

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.presentation.R

private val fontMonserrat_sub = FontFamily(
    Font(R.font.montserrat_subrayada_regular, weight = FontWeight.Normal),
    Font(R.font.montserrat_subrayada_bold, weight = FontWeight.Bold),
    Font(R.font.montserrat_medium, weight = FontWeight.Medium)
)

private val fontMonserrat = FontFamily(
    Font(R.font.montserrat_medium, weight = FontWeight.Medium),
    Font(R.font.montserrat_bold, weight = FontWeight.Bold)
)

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    h2 = TextStyle(
        fontFamily = fontMonserrat,
        fontSize = 36.sp,
        color = Color.White,
        fontWeight = FontWeight.Bold
    ),
    h3 = TextStyle(
        fontFamily = fontMonserrat_sub,
        fontSize = 16.sp,
        color = Color.White,
        fontWeight = FontWeight.Normal
    ),
    h4 = TextStyle(
        fontFamily = fontMonserrat,
        fontSize = 16.sp,
        color = Color.White,
        fontWeight = FontWeight.Bold
    ),
    h5 = TextStyle(
        fontFamily = fontMonserrat,
        fontSize = 16.sp,
        color = Color.White,
        fontWeight = FontWeight.Normal
    ),
    subtitle1 = TextStyle(
        fontFamily = fontMonserrat,
        fontSize = 8.sp,
        color = Color.White,
        fontWeight = FontWeight.Normal
    )
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)