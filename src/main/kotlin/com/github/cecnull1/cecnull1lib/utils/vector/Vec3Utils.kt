package com.github.cecnull1.cecnull1lib.utils.vector

import net.minecraft.world.phys.Vec3

operator fun Vec3.plus(other: Vec3): Vec3 {
    return Vec3(x + other.x, y + other.y, z + other.z)
}

operator fun Vec3.minus(other: Vec3): Vec3 {
    return Vec3(x - other.x, y - other.y, z - other.z)
}

operator fun Vec3.times(other: Vec3): Vec3 {
    return Vec3(x * other.x, y * other.y, z * other.z)
}

operator fun Vec3.div(other: Vec3): Vec3 {
    return Vec3(x / other.x, y / other.y, z / other.z)
}