package com.github.cecnull1.cecnull1lib.utils.nbt

import net.minecraft.nbt.*
import java.util.*

operator fun CompoundTag.set(key: String, value: Byte) = putByte(key, value)
operator fun CompoundTag.set(key: String, value: Int) = putInt(key, value)
operator fun CompoundTag.set(key: String, value: Long) = putLong(key, value)
operator fun CompoundTag.set(key: String, value: Float) = putFloat(key, value)
operator fun CompoundTag.set(key: String, value: Double) = putDouble(key, value)
operator fun CompoundTag.set(key: String, value: String) = putString(key, value)
operator fun CompoundTag.set(key: String, value: Boolean) = putBoolean(key, value)
operator fun CompoundTag.set(key: String, value: ByteArray) = putByteArray(key, value)
operator fun CompoundTag.set(key: String, value: IntArray) = putIntArray(key, value)
operator fun CompoundTag.set(key: String, value: LongArray) = putLongArray(key, value)
operator fun CompoundTag.set(key: String, value: UUID) = putUUID(key, value)
operator fun CompoundTag.set(key: String, value: CompoundTag) = put(key, value)