package top.srcres258.tutorialmod.datagen

import net.minecraft.data.PackOutput
import net.minecraft.data.loot.LootTableProvider
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets
import top.srcres258.tutorialmod.datagen.loot.ModBlockLootTables

class ModLootTableProvider {
    companion object {
        fun create(output: PackOutput) =
            LootTableProvider(output, setOf(), listOf(LootTableProvider.SubProviderEntry(::ModBlockLootTables, LootContextParamSets.BLOCK)))
    }
}