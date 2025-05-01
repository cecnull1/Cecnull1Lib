package com.github.cecnull1.cecnull1lib.utils.nbt

import net.minecraft.nbt.*
import java.util.*

inline fun <reified T : Tag> CompoundTag.getTagOrNull(key: String): T? =
    get(key) as? T

fun CompoundTag.getStringOrNull(key: String): String? =
    if (contains(key, Tag.TAG_STRING.toInt())) getString(key) else null

fun CompoundTag.getIntOrNull(key: String): Int? =
    if (contains(key, Tag.TAG_INT.toInt())) getInt(key) else null

fun CompoundTag.getBooleanOrElse(key: String, defaultValue: Boolean): Boolean =
    if (contains(key, Tag.TAG_BYTE.toInt())) getBoolean(key) else defaultValue

inline fun <reified T : Tag> CompoundTag.get(key: String): T? = get(key) as? T

inline fun <reified T> CompoundTag.getOrNull(key: String): T? {
    return when (T::class) {
        Byte::class -> getByte(key) as T?
        Short::class -> getShort(key) as T?
        Int::class -> getInt(key) as T?
        Long::class -> getLong(key) as T?
        Float::class -> getFloat(key) as T?
        Double::class -> getDouble(key) as T?
        String::class -> getString(key) as T?
        Boolean::class -> getBoolean(key) as T?
        ByteArray::class -> getByteArray(key) as T?
        IntArray::class -> getIntArray(key) as T?
        LongArray::class -> getLongArray(key) as T?
        UUID::class -> getUUID(key) as T?
        CompoundTag::class -> getCompound(key) as T?
        ListTag::class -> getList(key, Tag.TAG_ANY_NUMERIC.toInt()) as T?
        else -> null
    }
}

inline fun <reified T> CompoundTag.getOrElse(key: String, defaultValue: () -> T): T {
    return this.getOrNull<T>(key) ?: defaultValue()
}

