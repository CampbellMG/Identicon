package com.cmgcode.kidenticon

import java.awt.Color
import java.awt.Graphics2D
import java.awt.RenderingHints
import java.awt.image.BufferedImage
import java.io.ByteArrayOutputStream
import java.security.MessageDigest
import javax.imageio.ImageIO


class KIdenticon(private val palette: Palette = Palette.DEFAULT, config: Config = Config.DEFAULT) {
    private val heightWidth = config.heightWidth
    private val minBubbleSize = config.minBubbleSize
    private val maxBubbleSize = heightWidth / 2

    fun generatePNG(text: String): ByteArray {
        val md5 = md5Hex(text)
        val identicon = BufferedImage(heightWidth, heightWidth, BufferedImage.TYPE_INT_ARGB)
        val graphics = identicon.createGraphics()

        drawBackground(graphics)

        for (i in 0 until 16) {
            drawBubble(md5, i, graphics)
        }

        return convertToBytes(identicon)
    }

    private fun drawBackground(graphics: Graphics2D) {
        graphics.color = palette.background
        graphics.fillRect(0, 0, heightWidth, heightWidth)
    }

    private fun drawBubble(md5: String, index: Int, graphics: Graphics2D) {
        val x = md5[2 * index].toString().toInt(16)
        val y = md5[2 * index + 1].toString().toInt(16)
        val z = md5[(x * y) % 32].toString().toInt(16)
        val colour = getPaletteColour(z)

        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
        graphics.color = colour
        graphics.fillOval(scaleCoordinate(x), scaleCoordinate(y), scaleRadius(z), scaleRadius(z))
    }

    private fun md5Hex(message: String): String = MessageDigest
        .getInstance("MD5")
        .digest(message.toByteArray(charset("CP1252")))
        .toHex()

    private fun scaleCoordinate(x: Int) = (heightWidth / 16) * x

    private fun scaleRadius(x: Int): Int =
        (minBubbleSize + ((x.toFloat() / 16) * (maxBubbleSize - minBubbleSize))).toInt()

    private fun getPaletteColour(x: Int): Color = palette.colours.run { get(x % size) }

    private fun convertToBytes(identicon: BufferedImage) = ByteArrayOutputStream()
        .use {
            ImageIO.write(identicon, "png", it)
            it.flush()
            it.toByteArray()
        }

    private fun ByteArray.toHex() = joinToString("") {
        Integer.toHexString((it.toInt() and 0xFF) or 0x100).substring(1, 3)
    }
}
