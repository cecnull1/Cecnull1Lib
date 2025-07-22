package com.github.cecnull1.cecnull1lib.utils.nbt

import com.github.cecnull1.cecnull1lib.utils.vector.KVec3
import com.github.cecnull1.cecnull1lib.utils.vector.putKVec3
import net.minecraft.nbt.*
import java.util.*

// ====== Setters ======
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
operator fun CompoundTag.set(key: String, value: Tag) = put(key, value)
operator fun CompoundTag.set(key: String, value: Short) = putShort(key, value)
operator fun CompoundTag.set(key: String, value: KVec3) = putKVec3(key, value)

// ====== Getters - Safe with defaults ======
fun Tag?.asInt(default: Int = 0): Int = (this as? IntTag)?.asInt ?: default
fun Tag?.asLong(default: Long = 0L): Long = (this as? LongTag)?.asLong ?: default
fun Tag?.asFloat(default: Float = 0f): Float = (this as? FloatTag)?.asFloat ?: default
fun Tag?.asDouble(default: Double = 0.0): Double = (this as? DoubleTag)?.asDouble ?: default
fun Tag?.asString(default: String = ""): String = (this as? StringTag)?.asString() ?: default
fun Tag?.asShort(default: Short = 0): Short = (this as? ShortTag)?.asShort ?: default
fun Tag?.asBoolean(default: Boolean = false): Boolean = if (this is ByteTag) asByte != 0.toByte() else default
fun Tag?.asCompoundTag(default: CompoundTag = CompoundTag()): CompoundTag = (this as? CompoundTag) ?: default
fun Tag?.asUUID(default: UUID = UUID(0L, 0L)): UUID {
    return try {
        NbtUtils.loadUUID(this)
    } catch (e: IllegalArgumentException) {
        default
    }
}

// ====== Getters - Strict with exceptions ======
fun Tag?.requireUUID(): UUID {
    return NbtUtils.loadUUID(this)
}