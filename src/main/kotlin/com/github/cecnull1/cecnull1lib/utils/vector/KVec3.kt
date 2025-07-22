package com.github.cecnull1.cecnull1lib.utils.vector

import com.github.cecnull1.cecnull1lib.utils.nbt.putList
import com.github.cecnull1.cecnull1lib.utils.nbt.add
import com.github.cecnull1.cecnull1lib.utils.nbt.asDouble
import net.minecraft.core.BlockPos
import net.minecraft.core.Position
import net.minecraft.core.Vec3i
import net.minecraft.nbt.CompoundTag
import net.minecraft.world.phys.Vec3
import kotlin.math.abs
import kotlin.math.sqrt

data class KVec3(
    val x: Double,
    val y: Double,
    val z: Double
) {
    operator fun plus(other: KVec3): KVec3 = KVec3(x + other.x, y + other.y, z + other.z)
    operator fun minus(other: KVec3): KVec3 = KVec3(x - other.x, y - other.y, z - other.z)
    operator fun times(other: KVec3): KVec3 = KVec3(x * other.x, y * other.y, z * other.z)
    operator fun div(other: KVec3): KVec3 = KVec3(x / other.x, y / other.y, z / other.z)
    operator fun plus(other: Double): KVec3 = KVec3(x + other, y + other, z + other)
    operator fun minus(other: Double): KVec3 = KVec3(x - other, y - other, z - other)
    operator fun times(other: Double): KVec3 = KVec3(x * other, y * other, z * other)
    operator fun div(other: Double): KVec3 = KVec3(x / other, y / other, z / other)
    operator fun unaryMinus(): KVec3 = KVec3(-x, -y, -z)
    operator fun unaryPlus(): KVec3 = KVec3(x, y, z)

    operator fun get(index: Int): Double = when (index) {
            0 -> x
            1 -> y
            2 -> z
            else -> throw IndexOutOfBoundsException("Index $index is out of bounds for KVec3")
    }
    fun set(index: Int, value: Double) = when (index) {
            0 -> KVec3(value, y, z)
            1 -> KVec3(x, value, z)
            2 -> KVec3(x, y, value)
            else -> throw IndexOutOfBoundsException("Index $index is out of bounds for KVec3")
    }
    fun set(x: Double = this.x, y: Double = this.y, z: Double = this.z) = KVec3(x, y, z)

    fun cross(other: KVec3): KVec3 = KVec3(
        y * other.z - z * other.y,
        z * other.x - x * other.z,
        x * other.y - y * other.x
    )
    fun normalized() = this * (1.0 / length())

    fun dot(other: KVec3): Double = x * other.x + y * other.y + z * other.z
    fun length() = sqrt(x * x + y * y + z * z)
    fun sum() = x + y + z

    fun isUnit(epsilon: Double = 1e-6): Boolean {
        return abs(length() - 1.0) < epsilon
    }

    fun hasNaN() = x.isNaN() || y.isNaN() || z.isNaN()
    fun isFinite() = x.isFinite() && y.isFinite() && z.isFinite()
    fun isZero() = x == 0.0 && y == 0.0 && z == 0.0
    fun isZero(epsilon: Double = 1e-6) =
        abs(x) < epsilon && abs(y) < epsilon && abs(z) < epsilon

    fun max() = maxOf(x, y, z)
    fun min() = minOf(x, y, z)

    inline fun map(action: (Double) -> Double) = mapEach(action)
    inline fun mapEach(action: (Double) -> Double) = KVec3(
        action(x),
        action(y),
        action(z)
    )
    inline fun mapPairwise(action: (Double, Double) -> Double) = KVec3(
        action(x, y),
        action(y, z),
        action(z, x)
    )
    inline fun mapAll(action: (Double, Double, Double) -> Double) = KVec3(
        action(x, y, z),
        action(y, z, x),
        action(z, x, y)
    )

    companion object {
        val ZERO = KVec3(0.0, 0.0, 0.0)
        val ONE = KVec3(1.0, 1.0, 1.0)
        val NAN = KVec3(Double.NaN, Double.NaN, Double.NaN)
        val INFINITY = KVec3(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY)

        val X_AXIS = KVec3(1.0, 0.0, 0.0)
        val Y_AXIS = KVec3(0.0, 1.0, 0.0)
        val Z_AXIS = KVec3(0.0, 0.0, 1.0)
    }
}

fun CompoundTag.putKVec3(key: String, vec3: KVec3) {
    putList(key) {
        add(vec3.x)
        add(vec3.y)
        add(vec3.z)
    }
}

fun CompoundTag.getKVec3(key: String, isFloat: Boolean = false, default: KVec3 = KVec3.ZERO): KVec3 {
    val list = getList(key,
        if (isFloat) {
            CompoundTag.TAG_FLOAT.toInt()
        } else {
            CompoundTag.TAG_DOUBLE.toInt()
        }
    )
    return if (list.size == 3) {
        KVec3(
            list[0].asDouble(),
            list[1].asDouble(),
            list[2].asDouble()
        )
    } else default
}

fun KVec3.toVec3() = Vec3(x, y, z)
fun KVec3.toBlockPos() = BlockPos(x.toInt(), y.toInt(), z.toInt())

fun Position.toKVec3() = KVec3(x(), y(), z())

fun Vec3i.toKVec3(offset: KVec3 = KVec3(0.5, 0.5, 0.5)) = KVec3(
    x.toDouble()+offset.x, y.toDouble()+offset.y, z.toDouble()+offset.z
)

fun Vec3i.toKVec3(offsetX: Double = 0.5, offsetY: Double = 0.5, offsetZ: Double = 0.5) = KVec3(
    x.toDouble()+offsetX, y.toDouble()+offsetY, z.toDouble()+offsetZ
)