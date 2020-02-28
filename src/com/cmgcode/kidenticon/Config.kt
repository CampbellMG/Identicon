package com.cmgcode.kidenticon

import java.lang.IllegalArgumentException

data class Config(val heightWidth: Int, val minBubbleSize: Int) {

    init {
        if(heightWidth % 16 != 0) throw IllegalArgumentException("Height/Width must be divisible by 16")
    }

    companion object {
        val DEFAULT = Config(400, 40)
    }
}
