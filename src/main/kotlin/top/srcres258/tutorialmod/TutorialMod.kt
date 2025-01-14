package top.srcres258.tutorialmod

import net.minecraft.client.gui.screens.MenuScreens
import net.minecraft.client.renderer.entity.EntityRenderers
import net.minecraft.world.item.CreativeModeTabs
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.FlowerPotBlock
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent
import net.minecraftforge.event.server.ServerStartingEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent
import org.apache.logging.log4j.Level
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import thedarkcolour.kotlinforforge.forge.MOD_BUS
import top.srcres258.tutorialmod.block.ModBlocks
import top.srcres258.tutorialmod.block.entity.ModBlockEntities
import top.srcres258.tutorialmod.entity.ModEntities
import top.srcres258.tutorialmod.entity.client.RhinoRenderer
import top.srcres258.tutorialmod.item.ModCreativeModeTab
import top.srcres258.tutorialmod.item.ModItems
import top.srcres258.tutorialmod.loot.ModLootModifiers
import top.srcres258.tutorialmod.screen.GemPolishingStationScreen
import top.srcres258.tutorialmod.screen.ModMenuTypes
import top.srcres258.tutorialmod.sound.ModSounds
import top.srcres258.tutorialmod.villager.ModVillagers

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
        ModBlocks.register(MOD_BUS)
        ModLootModifiers.register(MOD_BUS)
        ModVillagers.register(MOD_BUS)
        ModSounds.register(MOD_BUS)
        ModEntities.register(MOD_BUS)
        ModBlockEntities.register(MOD_BUS)
        ModMenuTypes.register(MOD_BUS)

        MOD_BUS.addListener(TutorialMod::commonSetup)
        MinecraftForge.EVENT_BUS.register(this)
        MOD_BUS.addListener(TutorialMod::addCreative)
    }

    private fun commonSetup(event: FMLCommonSetupEvent) {
        event.enqueueWork {
            (Blocks.FLOWER_POT as FlowerPotBlock).addPlant(ModBlocks.CATMINT.id, ModBlocks.POTTED_CATMINT)
        }
    }

    private fun addCreative(event: BuildCreativeModeTabContentsEvent) {
        if (event.tabKey == CreativeModeTabs.INGREDIENTS) {
            event.accept(ModItems.SAPPHIRE)
            event.accept(ModItems.RAW_SAPPHIRE)
        }
    }

    @SubscribeEvent
    fun onServerStarting(event: ServerStartingEvent) {

    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = [Dist.CLIENT])
    object ClientModEvents {
        @SubscribeEvent
        fun onClientSetup(event: FMLClientSetupEvent) {
            EntityRenderers.register(ModEntities.RHINO.get(), ::RhinoRenderer)

            MenuScreens.register(ModMenuTypes.GEM_POLISHING_MENU.get(), ::GemPolishingStationScreen)
        }
    }
}