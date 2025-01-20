package top.srcres258.tutorialmod.datagen

import net.minecraftforge.data.event.GatherDataEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import top.srcres258.tutorialmod.TutorialMod

@Mod.EventBusSubscriber(modid = TutorialMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
object DataGenerators {
    @SubscribeEvent
    fun gatherData(event: GatherDataEvent) {
        val existingFileHelper = event.existingFileHelper
        val lookupProvider = event.lookupProvider

        event.generator.run {
            addProvider(event.includeServer(), ModRecipeProvider(packOutput))
            addProvider(event.includeServer(), ModLootTableProvider.create(packOutput))

            addProvider(event.includeClient(), ModBlockStateProvider(packOutput, existingFileHelper))
            addProvider(event.includeClient(), ModItemModelProvider(packOutput, existingFileHelper))

            addProvider(
                event.includeServer(),
                ModBlockTagGenerator(packOutput, lookupProvider, existingFileHelper)
            ).let {
                addProvider(event.includeServer(), ModItemTagGenerator(packOutput, lookupProvider,
                    it.contentsGetter(), existingFileHelper))
            }

            addProvider(event.includeServer(), ModGlobalLootModifiersProvider(packOutput))
            addProvider(event.includeServer(), ModPoiTypeTagsProvider(packOutput, lookupProvider,
                existingFileHelper))

            addProvider(event.includeServer(), ModWorldGenProvider(packOutput, lookupProvider))
        }
    }
}