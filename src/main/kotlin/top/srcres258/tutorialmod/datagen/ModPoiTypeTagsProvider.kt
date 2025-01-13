package top.srcres258.tutorialmod.datagen

import net.minecraft.core.HolderLookup
import net.minecraft.data.PackOutput
import net.minecraft.data.tags.PoiTypeTagsProvider
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.PoiTypeTags
import net.minecraftforge.common.data.ExistingFileHelper
import top.srcres258.tutorialmod.TutorialMod
import java.util.concurrent.CompletableFuture

class ModPoiTypeTagsProvider(
    p_256012_: PackOutput,
    p_256617_: CompletableFuture<HolderLookup.Provider>,
    existingFileHelper: ExistingFileHelper?
) : PoiTypeTagsProvider(p_256012_, p_256617_, TutorialMod.MOD_ID, existingFileHelper) {
    override fun addTags(pProvider: HolderLookup.Provider) {
        tag(PoiTypeTags.ACQUIRABLE_JOB_SITE)
            .addOptional(ResourceLocation(TutorialMod.MOD_ID, "sound_poi"))
    }
}