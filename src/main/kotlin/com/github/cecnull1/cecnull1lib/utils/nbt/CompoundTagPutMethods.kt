package com.github.cecnull1.cecnull1lib.utils.nbt

import net.minecraft.nbt.*

// Put supported primitive NBT types only (no recursive objects or unsupported types)
fun CompoundTag.putSupported(key: String, value: Any?) {
    if (value == null) {
        this.remove(key)
        return
    }

    when (value) {
        is Byte -> putByte(key, value)
        is Short -> putShort(key, value)
        is Int -> putInt(key, value)
        is Long -> putLong(key, value)
        is Float -> putFloat(key, value)
        is Double -> putDouble(key, value)
        is String -> putString(key, value)
        is Boolean -> putBoolean(key, value) // 自动转为 ByteTag
        is ByteArray -> putByteArray(key, value)
        is IntArray -> putIntArray(key, value)
        is LongArray -> putLongArray(key, value)
        is CompoundTag -> put(key, value)
        is ListTag -> put(key, value)
        else -> error("Unsupported type for putSupported: ${value::class.java.name}")
    }
}