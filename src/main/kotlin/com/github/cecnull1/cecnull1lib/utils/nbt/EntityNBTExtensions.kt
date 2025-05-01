package com.github.cecnull1.cecnull1lib.utils.nbt

import net.minecraft.world.entity.Entity
import net.minecraft.nbt.CompoundTag

val Entity.modPersistentData: CompoundTag
    get() = persistentData

fun Entity.getModData(namespace: String): CompoundTag =
    persistentData.getCompound(namespace)