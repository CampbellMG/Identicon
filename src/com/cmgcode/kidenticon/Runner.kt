package com.cmgcode.kidenticon

import java.io.FileOutputStream
import kotlin.system.exitProcess

object Runner {
    @JvmStatic
    fun main(args: Array<String>) {
        if (args.size != 2) {
            println("2 args required: <identicon salt (any text)> <output filepath>")
            exitProcess(1)
        }

        val text = args[0]
        val destination = "${args[1]}.png"

        val bytes = KIdenticon().generatePNG(text)

        FileOutputStream(destination).use {
            it.write(bytes)
        }

        println("Saved identicon to $destination")
    }
}
