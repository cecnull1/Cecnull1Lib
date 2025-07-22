package com.github.cecnull1.cecnull1lib.utils

import net.ltxprogrammer.changed.util.ItemUtil.getWearingItems
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.enchantment.Enchantment
import net.minecraft.world.item.enchantment.EnchantmentHelper
import net.minecraftforge.fml.ModList

object EnchantmentUtils {

    /**
     * 检查玩家是否穿戴了带有指定附魔的物品。
     *
     * @param enchantment 要检查的附魔
     * @return 如果找到至少一件包含该附魔的装备，则返回 true
     */
    fun Player.hasSpecificEnchantment(enchantment: Enchantment): Boolean {
        if (ModList.get().isLoaded("changed")) {
            for (slot in getWearingItems(this)) {
                val itemStack = slot.itemStack
                if (hasEnchantment(itemStack, enchantment)) return true
            }
        }

        for (slot in EquipmentSlot.entries) {
            val itemStack = this.getItemBySlot(slot)
            if (hasEnchantment(itemStack, enchantment)) return true
        }

        return false
    }

    /**
     * 检查某个物品是否具有指定附魔。
     *
     * @param itemStack 要检查的物品堆栈
     * @param enchantment 要查找的附魔
     * @return 如果该物品拥有该附魔且等级大于0，返回 true
     */
    fun hasEnchantment(itemStack: ItemStack, enchantment: Enchantment): Boolean {
        return !itemStack.isEmpty && EnchantmentHelper.getItemEnchantmentLevel(enchantment, itemStack) > 0
    }

    /**
     * 获取玩家身上所有装备中特定附魔的最高等级。
     *
     * @param enchantment 要查询的附魔
     * @return 玩家身上的最大附魔等级
     */
    fun Player.getHighestEnchantmentLevel(enchantment: Enchantment): Int {
        var highestLevel = 0
        for (slot in EquipmentSlot.entries) {
            val itemStack = this.getItemBySlot(slot)
            if (!itemStack.isEmpty) {
                val level = EnchantmentHelper.getItemEnchantmentLevel(enchantment, itemStack)
                if (level > highestLevel) highestLevel = level
            }
        }
        return highestLevel
    }
}