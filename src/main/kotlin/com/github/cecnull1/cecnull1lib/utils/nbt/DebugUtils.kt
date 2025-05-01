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
        putString("string", "Hello, World!")
        putInt("int", 42)
        putLong("long", 1234567890L)
        putFloat("float", 3.14f)
        putDouble("double", 2.71828)
        putBoolean("boolean", true)
        putSupported("compound", buildNBT {
            putString("nestedString", "Nested Value")
            putInt("nestedInt", 123)
            put("nestedList", listTagOf {
                int(1)
                int(2)
                int(3)
                int(4)
            })
        })
    }
    println(nbt.print())
}