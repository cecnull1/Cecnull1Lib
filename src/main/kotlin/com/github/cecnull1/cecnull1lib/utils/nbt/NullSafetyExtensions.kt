// NullSafetyExtensions.kt

package com.github.cecnull1.cecnull1lib.utils.nbt

import net.minecraft.nbt.CompoundTag
import net.minecraft.nbt.Tag

fun CompoundTag.hasKey(key: String): Boolean = contains(key)
fun CompoundTag.hasInt(key: String): Boolean = contains(key, Tag.TAG_INT.toInt())
fun CompoundTag.hasString(key: String): Boolean = contains(key, Tag.TAG_STRING.toInt())
fun CompoundTag.hasCompound(key: String): Boolean = contains(key, Tag.TAG_COMPOUND.toInt())
fun CompoundTag.hasList(key: String): Boolean = contains(key, Tag.TAG_LIST.toInt())