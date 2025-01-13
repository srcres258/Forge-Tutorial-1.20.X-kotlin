package top.srcres258.tutorialmod.item

import net.minecraft.sounds.SoundEvent
import net.minecraft.sounds.SoundEvents
import net.minecraft.world.item.ArmorItem
import net.minecraft.world.item.ArmorMaterial
import net.minecraft.world.item.crafting.Ingredient
import top.srcres258.tutorialmod.TutorialMod

private val BASE_DURABILITY = intArrayOf(11, 16, 16, 13)

enum class ModArmorMaterials(
    private val name_: String,
    private val durabilityMultiplier: Int,
    private val protectionAmounts: IntArray,
    private val enchantmentValue: Int,
    private val equipSound: SoundEvent,
    private val toughness: Float,
    private val knockbackResistance: Float,
    private val repairIngredient: () -> Ingredient
) : ArmorMaterial {
    SAPPHIRE("sapphire", 26, intArrayOf(5, 7, 5, 4), 25,
        SoundEvents.ARMOR_EQUIP_GOLD, 1F, 0F, { Ingredient.of(ModItems.SAPPHIRE.get()) });

    override fun getDurabilityForType(pType: ArmorItem.Type) =
        BASE_DURABILITY[pType.ordinal] * durabilityMultiplier

    override fun getDefenseForType(pType: ArmorItem.Type) =
        protectionAmounts[pType.ordinal]

    override fun getEnchantmentValue() = enchantmentValue

    override fun getEquipSound() = equipSound

    override fun getRepairIngredient() = repairIngredient()

    override fun getName() = "${TutorialMod.MOD_ID}:$name_"

    override fun getToughness() = toughness

    override fun getKnockbackResistance() = knockbackResistance
}