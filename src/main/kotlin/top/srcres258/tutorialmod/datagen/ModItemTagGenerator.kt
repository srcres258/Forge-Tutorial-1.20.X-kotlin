package top.srcres258.tutorialmod.datagen

import net.minecraft.core.HolderLookup
import net.minecraft.data.PackOutput
import net.minecraft.data.tags.ItemTagsProvider
import net.minecraft.tags.ItemTags
import net.minecraft.world.level.block.Block
import net.minecraftforge.common.data.ExistingFileHelper
import top.srcres258.tutorialmod.TutorialMod
import top.srcres258.tutorialmod.block.ModBlocks
import top.srcres258.tutorialmod.item.ModItems
import java.util.concurrent.CompletableFuture

class ModItemTagGenerator(
    p_275343_: PackOutput,
    p_275729_: CompletableFuture<HolderLookup.Provider>,
    p_275322_: CompletableFuture<TagLookup<Block>>,
    existingFileHelper: ExistingFileHelper?
) : ItemTagsProvider(p_275343_, p_275729_, p_275322_, TutorialMod.MOD_ID, existingFileHelper) {
    override fun addTags(p0: HolderLookup.Provider) {
        tag(ItemTags.TRIMMABLE_ARMOR)
            .add(
                ModItems.SAPPHIRE_HELMET.get(),
                ModItems.SAPPHIRE_CHESTPLATE.get(),
                ModItems.SAPPHIRE_LEGGINGS.get(),
                ModItems.SAPPHIRE_BOOTS.get()
            )

        tag(ItemTags.MUSIC_DISCS)
            .add(ModItems.BAR_BRAWL_MUSIC_DISC.get())

        tag(ItemTags.CREEPER_DROP_MUSIC_DISCS)
            .add(ModItems.BAR_BRAWL_MUSIC_DISC.get())

        tag(ItemTags.LOGS_THAT_BURN)
            .add(ModBlocks.PINE_LOG.get().asItem())
            .add(ModBlocks.PINE_WOOD.get().asItem())
            .add(ModBlocks.STRIPPED_PINE_LOG.get().asItem())
            .add(ModBlocks.STRIPPED_PINE_WOOD.get().asItem())

        tag(ItemTags.PLANKS)
            .add(ModBlocks.PINE_PLANKS.get().asItem())
    }
}