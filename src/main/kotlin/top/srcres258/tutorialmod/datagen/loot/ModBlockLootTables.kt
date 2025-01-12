package top.srcres258.tutorialmod.datagen.loot

import net.minecraft.data.loot.BlockLootSubProvider
import net.minecraft.world.flag.FeatureFlags
import net.minecraft.world.item.Item
import net.minecraft.world.item.enchantment.Enchantments
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.storage.loot.entries.LootItem
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator
import net.minecraftforge.registries.RegistryObject
import top.srcres258.tutorialmod.block.ModBlocks
import top.srcres258.tutorialmod.item.ModItems

class ModBlockLootTables : BlockLootSubProvider(setOf(), FeatureFlags.REGISTRY.allFlags()) {
    override fun generate() {
        dropSelf(ModBlocks.SAPPHIRE_BLOCK.get())
        dropSelf(ModBlocks.RAW_SAPPHIRE_BLOCK.get())
        dropSelf(ModBlocks.SOUND_BLOCK.get())

        add(ModBlocks.SAPPHIRE_ORE.get()) {
            createCopperLikeOreDrops(ModBlocks.SAPPHIRE_ORE.get(), ModItems.RAW_SAPPHIRE.get())
        }
        add(ModBlocks.DEEPSLATE_SAPPHIRE_ORE.get()) {
            createCopperLikeOreDrops(ModBlocks.DEEPSLATE_SAPPHIRE_ORE.get(), ModItems.RAW_SAPPHIRE.get())
        }
        add(ModBlocks.NETHER_SAPPHIRE_ORE.get()) {
            createCopperLikeOreDrops(ModBlocks.NETHER_SAPPHIRE_ORE.get(), ModItems.RAW_SAPPHIRE.get())
        }
        add(ModBlocks.END_STONE_SAPPHIRE_ORE.get()) {
            createCopperLikeOreDrops(ModBlocks.END_STONE_SAPPHIRE_ORE.get(), ModItems.RAW_SAPPHIRE.get())
        }
    }

    private fun createCopperLikeOreDrops(block: Block, item: Item) =
        createSilkTouchDispatchTable(block, applyExplosionDecay(block,
            LootItem.lootTableItem(item)
                .apply(SetItemCountFunction.setCount(UniformGenerator.between(2F, 5F)))
                .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))))

    override fun getKnownBlocks() =
        Iterable(ModBlocks.BLOCKS.entries.map(RegistryObject<Block>::get)::iterator)
}