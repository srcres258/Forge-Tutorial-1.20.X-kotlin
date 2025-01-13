package top.srcres258.tutorialmod.datagen.loot

import net.minecraft.advancements.critereon.StatePropertiesPredicate
import net.minecraft.data.loot.BlockLootSubProvider
import net.minecraft.world.flag.FeatureFlags
import net.minecraft.world.item.Item
import net.minecraft.world.item.enchantment.Enchantments
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.storage.loot.entries.LootItem
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator
import net.minecraftforge.registries.RegistryObject
import top.srcres258.tutorialmod.block.ModBlocks
import top.srcres258.tutorialmod.block.custom.CornCropBlock
import top.srcres258.tutorialmod.block.custom.StrawberryCropBlock
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

        dropSelf(ModBlocks.SAPPHIRE_STAIRS.get())
        dropSelf(ModBlocks.SAPPHIRE_BUTTON.get())
        dropSelf(ModBlocks.SAPPHIRE_PRESSURE_PLATE.get())
        dropSelf(ModBlocks.SAPPHIRE_TRAPDOOR.get())
        dropSelf(ModBlocks.SAPPHIRE_FENCE.get())
        dropSelf(ModBlocks.SAPPHIRE_FENCE_GATE.get())
        dropSelf(ModBlocks.SAPPHIRE_WALL.get())

        add(ModBlocks.SAPPHIRE_SLAB.get()) {
            createSlabItemTable(ModBlocks.SAPPHIRE_SLAB.get())
        }
        add(ModBlocks.SAPPHIRE_DOOR.get()) {
            createDoorTable(ModBlocks.SAPPHIRE_DOOR.get())
        }

        LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.STRAWBERRY_CROP.get())
            .setProperties(StatePropertiesPredicate.Builder.properties()
                .hasProperty(StrawberryCropBlock.AGE, 5))
            .let { builder ->
                add(ModBlocks.STRAWBERRY_CROP.get(), createCropDrops(ModBlocks.STRAWBERRY_CROP.get(), ModItems.STRAWBERRY.get(),
                    ModItems.STRAWBERRY_SEEDS.get(), builder))
            }

        LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.CORN_CROP.get())
            .setProperties(StatePropertiesPredicate.Builder.properties()
                .hasProperty(CornCropBlock.AGE, CornCropBlock.FIRST_STAGE_MAX_AGE))
            .or(LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.CORN_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder.properties()
                    .hasProperty(CornCropBlock.AGE,
                        CornCropBlock.FIRST_STAGE_MAX_AGE + CornCropBlock.SECOND_STAGE_MAX_AGE)))
            .let { builder ->
                add(ModBlocks.CORN_CROP.get(), createCropDrops(ModBlocks.CORN_CROP.get(), ModItems.CORN.get(),
                    ModItems.CORN_SEEDS.get(), builder))
            }

        dropSelf(ModBlocks.CATMINT.get())
        add(ModBlocks.POTTED_CATMINT.get(), createPotFlowerItemTable(ModBlocks.CATMINT.get()))
    }

    private fun createCopperLikeOreDrops(block: Block, item: Item) =
        createSilkTouchDispatchTable(block, applyExplosionDecay(block,
            LootItem.lootTableItem(item)
                .apply(SetItemCountFunction.setCount(UniformGenerator.between(2F, 5F)))
                .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))))

    override fun getKnownBlocks() =
        Iterable(ModBlocks.BLOCKS.entries.map(RegistryObject<Block>::get)::iterator)
}