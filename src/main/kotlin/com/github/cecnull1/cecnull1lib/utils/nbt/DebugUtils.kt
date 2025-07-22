// DebugUtils.kt

package com.github.cecnull1.cecnull1lib.utils.nbt

import net.minecraft.nbt.CompoundTag
import net.minecraft.nbt.ListTag
import net.minecraft.nbt.Tag

fun CompoundTag.print(tagName: String = "NBT"): String {
    val sb = StringBuilder()
    printRecursive(this, tagName, sb, 0)
    return sb.toString()
}

private fun printRecursive(tag: Tag, name: String, sb: StringBuilder, indent: Int) {
    val indentStr = " ".repeat(indent * 2)
    sb.append("$indentStr$name: ").append(tag.javaClass.simpleName).append(" = ").append(tag.toString()).append("\n")

    if (tag is CompoundTag) {
        for ((key, value) in tag.entries) {
            printRecursive(value, key, sb, indent + 1)
        }
    }

    if (tag is ListTag) {
        for (i in tag.indices) {
            printRecursive(tag[i], "[${i}]", sb, indent + 1)
        }
    }
}

fun main() {
    val nbt = buildNBT {
        set("e", buildNBT {
            set("a", 1)
        })
    }
    println(nbt.print())
    nbt["e"].asCompoundTag()["e"] = 3
    nbt["a"] = 123.toShort()
    println(nbt.print())
}