package top.srcres258.tutorialmod.datagen

import net.minecraftforge.data.event.GatherDataEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import top.srcres258.tutorialmod.TutorialMod

@Mod.EventBusSubscriber(modid = TutorialMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
object DataGenerators {
    @SubscribeEvent
    fun gatherData(event: GatherDataEvent) {
        val generator = event.generator
        val packOutput = generator.packOutput
        val existingFileHelper = event.existingFileHelper
        val lookupProvider = event.lookupProvider

        generator.addProvider(event.includeServer(), ModRecipeProvider(packOutput))
        generator.addProvider(event.includeServer(), ModLootTableProvider.create(packOutput))

        generator.addProvider(event.includeClient(), ModBlockStateProvider(packOutput, existingFileHelper))
        generator.addProvider(event.includeClient(), ModItemModelProvider(packOutput, existingFileHelper))

        val blockTagGenerator = generator.addProvider(event.includeServer(),
            ModBlockTagGenerator(packOutput, lookupProvider, existingFileHelper))
        generator.addProvider(event.includeServer(), ModItemTagGenerator(packOutput, lookupProvider,
            blockTagGenerator.contentsGetter(), existingFileHelper))

        generator.addProvider(event.includeServer(), ModGlobalLootModifiersProvider(packOutput))
    }
}