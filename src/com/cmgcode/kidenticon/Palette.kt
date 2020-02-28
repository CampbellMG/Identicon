package com.cmgcode.kidenticon

import java.awt.Color
import java.lang.IllegalArgumentException

data class Palette(private val hexBackground: String, private val hexColours: List<String>) {
    val background: Color
        get() = hexToRGB(hexBackground)
    val colours: List<Color>
        get() = hexColours.map { hexToRGB(it) }

    init {
        if (hexBackground.length != 7) throw InvalidColourException()
        if (hexColours.any { it.length != 7 }) throw InvalidColourException()
    }

    class InvalidColourException : IllegalArgumentException("All colours must be 7 characters (#FFFFFF)")

    companion object {
        val DEFAULT = Palette(
            "#F7F7F7",
            listOf(
                "#54F590",
                "#F2FEF7",
                "#96F8BA",
                "#18F56A",
                "#00F35B",
                "#5AC8F2",
                "#F3FBFE",
                "#98DBF5",
                "#20B8F1",
                "#01ADEF",
                "#FFAF58",
                "#FFF9F3",
                "#FFCF9A",
                "#FF9119",
                "#FF8500",
                "#FF7558",
                "#FFF5F3",
                "#FFAC9A",
                "#FF4119",
                "#FF2C00"
            )
        )

        private fun hexToRGB(hexColour: String): Color {
            return Color(
                hexColour.substring(1, 3).toInt(16),
                hexColour.substring(3, 5).toInt(16),
                hexColour.substring(5, 7).toInt(16)
            )
        }
    }
}

