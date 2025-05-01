package com.github.cecnull1.cecnull1lib.utils

import net.minecraft.nbt.CompoundTag
import net.minecraft.world.entity.Entity
import net.minecraft.world.level.Level
import net.minecraftforge.eventbus.api.IEventBus
import net.minecraftforge.registries.DeferredRegister

object InfixFunction {
    infix fun <T> DeferredRegister<T>.addTo(bus: IEventBus?) = this.register(bus)

    infix fun CompoundTag.nbtIn(key: String): Boolean = this.contains(key)

    infix fun CompoundTag.nbtNotIn(key: String): Boolean = !(this nbtIn key)

    inline infix fun <T> Level?.serverRun (f: (Level) -> T?) : T? {
        this ?: return null
        if (!this.isClientSide) {
            return f(this)
        }
        return null
    }

    inline infix fun <T> Entity?.serverRun (f: (Entity) -> T?) : T? {
        this ?: return null
        if (!this.level.isClientSide) {
            return f(this)
        }
        return null
    }

    inline infix fun <T> Level?.clientRun (f: (Level) -> T?) : T? {
        this ?: return null
        if (this.isClientSide) {
            return f(this)
        }
        return null
    }

    inline infix fun <T> Entity?.clientRun (f: (Entity) -> T?) : T? {
        this ?: return null
        if (this.level.isClientSide) {
            return f(this)
        }
        return null
    }
}