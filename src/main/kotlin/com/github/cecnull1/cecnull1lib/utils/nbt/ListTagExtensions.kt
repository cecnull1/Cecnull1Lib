// ListTagExtensions.kt

package com.github.cecnull1.cecnull1lib.utils.nbt

import net.minecraft.nbt.*
import java.util.*

fun CompoundTag.putList(key: String, block: ListTag.() -> Unit) {
    val list = ListTag()
    list.block()
    put(key, list)
}

inline fun <reified T : Tag> ListTag.getOrNull(index: Int): T? = get(index) as? T

// 在 ListTagExtensions.kt 中增加此方法

fun ListTag.add(tag: Tag) = add(tag)

inline fun <reified T> ListTag.add(value: T) {
    when (T::class) {
        Byte::class -> add(ByteTag.valueOf(value as Byte))
        Short::class -> add(ShortTag.valueOf(value as Short))
        Int::class -> add(IntTag.valueOf(value as Int))
        Long::class -> add(LongTag.valueOf(value as Long))
        Float::class -> add(FloatTag.valueOf(value as Float))
        Double::class -> add(DoubleTag.valueOf(value as Double))
        String::class -> add(StringTag.valueOf(value as String))
        Boolean::class -> add(ByteTag.valueOf((value as Boolean)))
        ByteArray::class -> add(ByteArrayTag(value as ByteArray))
        IntArray::class -> add(IntArrayTag(value as IntArray))
        LongArray::class -> add(LongArrayTag(value as LongArray))
        UUID::class -> add(NbtUtils.createUUID(value as UUID))
        CompoundTag::class -> add(value as CompoundTag)
        else -> throw IllegalArgumentException("Unsupported NBT type: ${T::class}")
    }
}