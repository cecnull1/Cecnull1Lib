// NestedTagAccessors.kt

package com.github.cecnull1.cecnull1lib.utils.nbt

import net.minecraft.nbt.CompoundTag
import net.minecraft.nbt.Tag

fun CompoundTag.getNested(vararg path: String): CompoundTag? {
    var current = this
    for (p in path) {
        if (!current.contains(p, Tag.TAG_COMPOUND.toInt())) return null
        current = current.getCompound(p)
    }
    return current
}

fun CompoundTag.getOrCreateNested(vararg path: String): CompoundTag {
    var current = this
    for (p in path) {
        if (!current.contains(p, Tag.TAG_COMPOUND.toInt())) {
            val newTag = CompoundTag()
            current.put(p, newTag)
            current = newTag
        } else {
            current = current.getCompound(p)
        }
    }
    return current
}