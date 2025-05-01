package com.github.cecnull1.cecnull1lib.utils.nbt

import net.minecraft.nbt.*
import java.util.*

class ListTagBuilder {
    private val elements = mutableListOf<() -> Tag>()

    // 添加原生 Tag
    fun add(tag: Tag) {
        elements.add { tag }
    }

    // 明确的方法用于添加不同类型的数据
    fun int(value: Int) = elements.add { IntTag.valueOf(value) }
    fun byte(value: Byte) = elements.add { ByteTag.valueOf(value) }
    fun short(value: Short) = elements.add { ShortTag.valueOf(value) }
    fun long(value: Long) = elements.add { LongTag.valueOf(value) }
    fun float(value: Float) = elements.add { FloatTag.valueOf(value) }
    fun double(value: Double) = elements.add { DoubleTag.valueOf(value) }
    fun string(value: String) = elements.add { StringTag.valueOf(value) }
    fun bool(value: Boolean) = elements.add { ByteTag.valueOf(if (value) 1 else 0) }
    fun byteArray(value: ByteArray) = elements.add { ByteArrayTag(value) }
    fun intArray(value: IntArray) = elements.add { IntArrayTag(value) }
    fun longArray(value: LongArray) = elements.add { LongArrayTag(value) }
    fun uuid(value: UUID) = elements.add { NbtUtils.createUUID(value) }
    fun compound(block: CompoundTag.() -> Unit) {
        val tag = CompoundTag()
        tag.block()
        elements.add { tag }
    }
    fun list(block: ListTagBuilder.() -> Unit) {
        val tag = listTagOf(block)
        elements.add { tag }
    }

    // 构建最终的 ListTag
    fun build(): ListTag {
        return ListTag().apply {
            for (element in elements) {
                add(element.invoke())
            }
        }
    }
}

// 入口函数：listTagOf {}
fun listTagOf(block: ListTagBuilder.() -> Unit): ListTag {
    val builder = ListTagBuilder()
    builder.block()
    return builder.build()
}