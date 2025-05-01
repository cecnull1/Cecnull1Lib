package com.github.cecnull1.cecnull1lib.utils.nbt

import net.minecraft.nbt.*

// 扩展 CompoundTag 实现类似 Map 的迭代器支持
operator fun CompoundTag.iterator(): Iterator<Map.Entry<String, Tag>> {
    return this.allKeys.map { key -> object : Map.Entry<String, Tag> {
        override val key: String = key
        override val value: Tag = this@iterator.get(key) ?: error("Key '$key' does not exist in the compound tag.")
    }}.iterator()
}

// 提供类似 Map.entries 的访问方式
val CompoundTag.entries: Set<Map.Entry<String, Tag>>
    get() = this.allKeys.map { key ->
        object : Map.Entry<String, Tag> {
            override val key: String = key
            override val value: Tag = this@entries.get(key) ?: error("Key '$key' does not exist in the compound tag.")
        }
    }.toSet()

// 获取键集合
val CompoundTag.keys: Set<String>
    get() = this.allKeys.toSet()

// 获取值集合，排除 null 值
val CompoundTag.values: List<Tag>
    get() = this.allKeys.mapNotNull { this.get(it) }

// 获取指定键的值或默认值
fun CompoundTag.getOrDefault(key: String, defaultValue: Tag): Tag {
    return this.get(key) ?: defaultValue
}

// 判断是否包含某个键
fun CompoundTag.containsKey(key: String): Boolean {
    return contains(key)
}

// 判断是否包含某个值
fun CompoundTag.containsValue(value: Tag): Boolean {
    return this.allKeys.any { this.get(it) == value }
}

// 过滤符合条件的键值对
fun CompoundTag.filter(predicate: (String, Tag) -> Boolean): CompoundTag {
    val result = CompoundTag()
    for (key in this.allKeys) {
        val value = this.get(key)
        if (value != null && predicate(key, value)) {
            result.put(key, value)
        }
    }
    return result
}

// 将每个键值对映射为另一个对象，形成列表
fun <R> CompoundTag.map(transform: (String, Tag) -> R): List<R> {
    return this.allKeys.mapNotNull { key -> this.get(key)?.let { transform(key, it) } }
}

// 从 CompoundTag 中移除符合条件的键值对
fun CompoundTag.removeIf(predicate: (String, Tag) -> Boolean): CompoundTag {
    val result = CompoundTag()
    for (key in this.allKeys) {
        val value = this.get(key)
        if (value != null && !predicate(key, value)) {
            result.put(key, value)
        }
    }
    return result
}