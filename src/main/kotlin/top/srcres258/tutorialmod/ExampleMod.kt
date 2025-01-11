package top.srcres258.tutorialmod

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

/**
 * Main mod class. Should be an `object` declaration annotated with `@Mod`.
 * The modid should be declared in this object and should match the modId entry
 * in mods.toml.
 *
 * An example for blocks is in the `blocks` package of this mod.
 */
@Mod(ExampleMod.MOD_ID)
object ExampleMod {
    const val MOD_ID = "tutorialmod"

    // the logger for our mod
    val LOGGER: Logger = LogManager.getLogger(MOD_ID)

    init {
        LOGGER.log(Level.INFO, "Hello world!")

        MOD_BUS.addListener(ExampleMod::commonSetup)
        runWhenOn(Dist.CLIENT) {
            MOD_BUS.addListener(ExampleMod::clientSetup)
        }
        MOD_BUS.addListener(ExampleMod::addCreative)
    }

    private fun commonSetup(event: FMLCommonSetupEvent) {}

    private fun clientSetup(event: FMLClientSetupEvent) {}

    private fun addCreative(event: BuildCreativeModeTabContentsEvent) {}
}
