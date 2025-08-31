package com.github.cecnull1.cecnull1lib.utils

import net.minecraft.nbt.CompoundTag
import net.minecraft.world.entity.Entity
import net.minecraft.world.level.Level
import net.minecraftforge.eventbus.api.IEventBus
import net.minecraftforge.registries.DeferredRegister
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

object InfixFunction {
    infix fun <T> DeferredRegister<T>.addTo(bus: IEventBus?) = this.register(bus)

    infix fun CompoundTag.nbtIn(key: String): Boolean = this.contains(key)

    infix fun CompoundTag.nbtNotIn(key: String): Boolean = !(this nbtIn key)

    @OptIn(ExperimentalContracts::class)
    inline infix fun <T> Level?.serverRun (f: Level.() -> T?) : T? {
        contract {
            callsInPlace(f, InvocationKind.AT_MOST_ONCE)
        }
        this ?: return null
        if (!this.isClientSide) {
            return f(this)
        }
        return null
    }

    @OptIn(ExperimentalContracts::class)
    inline infix fun <reified T : Entity> T.serverRun(f: T.() -> Unit): T {
        contract {
            callsInPlace(f, InvocationKind.AT_MOST_ONCE)
        }
        if (!this.level().isClientSide) {
            f(this) // `this` 类型为 T（如 Player）
        }
        return this
    }

    @OptIn(ExperimentalContracts::class)
    inline infix fun <T> Level?.clientRun (f: Level.() -> T?) : T? {
        contract {
            callsInPlace(f, InvocationKind.AT_MOST_ONCE)
        }
        this ?: return null
        if (this.isClientSide) {
            return f(this)
        }
        return null
    }

    @OptIn(ExperimentalContracts::class)
    inline infix fun <reified T : Entity> T.clientRun(f: T.() -> Unit): T {
        contract {
            callsInPlace(f, InvocationKind.AT_MOST_ONCE)
        }
        if (this.level().isClientSide) {
            f(this) // `this` 类型为 T（如 Player）
        }
        return this
    }
}