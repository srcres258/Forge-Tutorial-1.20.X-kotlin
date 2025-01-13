package top.srcres258.tutorialmod.item

import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.Tier
import net.minecraft.world.item.Tiers
import net.minecraft.world.item.crafting.Ingredient
import net.minecraftforge.common.ForgeTier
import net.minecraftforge.common.TierSortingRegistry
import top.srcres258.tutorialmod.TutorialMod
import top.srcres258.tutorialmod.util.ModTags

object ModToolTiers {
    val SAPPHIRE: Tier = TierSortingRegistry.registerTier(
        ForgeTier(5, 1500, 5F, 4F, 25,
            ModTags.Blocks.NEEDS_SAPPHIRE_TOOL) { Ingredient.of(ModItems.SAPPHIRE.get()) },
        ResourceLocation(TutorialMod.MOD_ID, "sapphire"),
        listOf(Tiers.NETHERITE),
        listOf()
    )
}