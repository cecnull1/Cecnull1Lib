// CompoundTagDSL.kt

package com.github.cecnull1.cecnull1lib.utils.nbt

import net.minecraft.nbt.CompoundTag

fun buildNBT(block: CompoundTag.() -> Unit): CompoundTag {
    val tag = CompoundTag()
    tag.block()
    return tag
}