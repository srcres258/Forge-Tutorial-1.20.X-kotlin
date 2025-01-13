package top.srcres258.tutorialmod.item.custom

import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.effect.MobEffects
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ArmorItem
import net.minecraft.world.item.ArmorMaterial
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level
import top.srcres258.tutorialmod.item.ModArmorMaterials

private val MATERIAL_TO_EFFECT_MAP = mapOf<ArmorMaterial, MobEffectInstance>(
    Pair(ModArmorMaterials.SAPPHIRE, MobEffectInstance(MobEffects.NIGHT_VISION, 200, 1,
        false, false, false))
)

class ModArmorItem(
    pMaterial: ArmorMaterial,
    pType: Type,
    pProperties: Properties
) : ArmorItem(pMaterial, pType, pProperties) {
    override fun onArmorTick(stack: ItemStack, level: Level, player: Player) {
        if (!level.isClientSide) {
            if (hasFullSuitOfArmorOn(player)) {
                evaluateArmorEffects(player)
            }
        }
    }
}

private fun evaluateArmorEffects(player: Player) {
    for (entry in MATERIAL_TO_EFFECT_MAP.entries) {
        val mapArmorMaterial = entry.key
        val mapStatusEffect = entry.value

        if (hasCorrectArmorOn(mapArmorMaterial, player)) {
            addStatusEffectForMaterial(player, mapArmorMaterial, mapStatusEffect)
        }
    }
}

private fun addStatusEffectForMaterial(
    player: Player,
    mapArmorMaterial: ArmorMaterial,
    mapStatusEffect: MobEffectInstance
) {
    val hasPlayerEffect = player.hasEffect(mapStatusEffect.effect)

    if (hasCorrectArmorOn(mapArmorMaterial, player) && !hasPlayerEffect) {
        player.addEffect(MobEffectInstance(mapStatusEffect))
    }
}

private fun hasFullSuitOfArmorOn(player: Player): Boolean {
    val boots = player.inventory.getArmor(0)
    val leggings = player.inventory.getArmor(1)
    val chestplate = player.inventory.getArmor(2)
    val helmet = player.inventory.getArmor(3)

    return !helmet.isEmpty
            && !chestplate.isEmpty
            && !leggings.isEmpty
            && !boots.isEmpty
}

private fun hasCorrectArmorOn(material: ArmorMaterial, player: Player): Boolean {
    for (armorStack in player.inventory.armor) {
        if (armorStack.item !is ArmorItem) {
            return false
        }
    }

    val boots = player.inventory.getArmor(0).item as ArmorItem
    val leggings = player.inventory.getArmor(1).item as ArmorItem
    val chestplate = player.inventory.getArmor(2).item as ArmorItem
    val helmet = player.inventory.getArmor(3).item as ArmorItem

    return helmet.material == material
            && chestplate.material == material
            && leggings.material == material
            && boots.material == material
}