package top.srcres258.tutorialmod

import net.minecraft.world.item.CreativeModeTabs
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent
import org.apache.logging.log4j.Level
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import thedarkcolour.kotlinforforge.forge.MOD_BUS
import thedarkcolour.kotlinforforge.forge.runWhenOn
import top.srcres258.tutorialmod.item.ModCreativeModeTab
import top.srcres258.tutorialmod.item.ModItems

/**
 * Main mod class. Should be an `object` declaration annotated with `@Mod`.
 * The modid should be declared in this object and should match the modId entry
 * in mods.toml.
 *
 * An example for blocks is in the `blocks` package of this mod.
 */
@Mod(TutorialMod.MOD_ID)
object TutorialMod {
    const val MOD_ID = "tutorialmod"

    // the logger for our mod
    val LOGGER: Logger = LogManager.getLogger(MOD_ID)

    init {
        LOGGER.log(Level.INFO, "Hello world!")

        ModCreativeModeTab.register(MOD_BUS)
        ModItems.register(MOD_BUS)

        MOD_BUS.addListener(TutorialMod::commonSetup)
        runWhenOn(Dist.CLIENT) {
            MOD_BUS.addListener(TutorialMod::clientSetup)
        }
        MOD_BUS.addListener(TutorialMod::addCreative)
    }

    private fun commonSetup(event: FMLCommonSetupEvent) {}

    private fun clientSetup(event: FMLClientSetupEvent) {}

    private fun addCreative(event: BuildCreativeModeTabContentsEvent) {
        if (event.tabKey == CreativeModeTabs.INGREDIENTS) {
            event.accept(ModItems.SAPPHIRE)
            event.accept(ModItems.RAW_SAPPHIRE)
        }
    }
}