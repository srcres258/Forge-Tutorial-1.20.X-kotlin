package top.srcres258.tutorialmod.datagen

import net.minecraft.data.PackOutput
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition
import net.minecraftforge.common.data.GlobalLootModifierProvider
import net.minecraftforge.common.loot.LootTableIdCondition
import top.srcres258.tutorialmod.TutorialMod
import top.srcres258.tutorialmod.item.ModItems
import top.srcres258.tutorialmod.loot.AddItemModifier

class ModGlobalLootModifiersProvider(
    output: PackOutput
) : GlobalLootModifierProvider(output, TutorialMod.MOD_ID) {
    override fun start() {
        add("pine_cone_from_grass", AddItemModifier(
            arrayOf(
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.GRASS).build(),
                LootItemRandomChanceCondition.randomChance(0.35F).build()
            ),
            ModItems.PINE_CONE.get()
        ))

        add("pine_cone_from_creeper", AddItemModifier(
            arrayOf(
                LootTableIdCondition.Builder(ResourceLocation("entities/creeper")).build()
            ),
            ModItems.PINE_CONE.get()
        ))

        add("metal_detector_from_jungle_temple", AddItemModifier(
            arrayOf(
                LootTableIdCondition.Builder(ResourceLocation("chests/jungle_temple")).build()
            ),
            ModItems.METAL_DETECTOR.get()
        ))
    }
}