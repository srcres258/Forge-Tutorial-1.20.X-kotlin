package top.srcres258.tutorialmod.datagen

import net.minecraft.core.HolderLookup
import net.minecraft.data.PackOutput
import net.minecraft.data.tags.ItemTagsProvider
import net.minecraft.world.level.block.Block
import net.minecraftforge.common.data.ExistingFileHelper
import top.srcres258.tutorialmod.TutorialMod
import java.util.concurrent.CompletableFuture

class ModItemTagGenerator(
    p_275343_: PackOutput,
    p_275729_: CompletableFuture<HolderLookup.Provider>,
    p_275322_: CompletableFuture<TagLookup<Block>>,
    existingFileHelper: ExistingFileHelper?
) : ItemTagsProvider(p_275343_, p_275729_, p_275322_, TutorialMod.MOD_ID, existingFileHelper) {
    override fun addTags(p0: HolderLookup.Provider) {

    }
}