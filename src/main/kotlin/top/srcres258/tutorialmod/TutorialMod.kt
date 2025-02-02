package top.srcres258.tutorialmod

import net.minecraft.client.gui.screens.MenuScreens
import net.minecraft.client.renderer.Sheets
import net.minecraft.client.renderer.entity.EntityRenderers
import net.minecraft.client.renderer.entity.ThrownItemRenderer
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
import terrablender.api.SurfaceRuleManager
import thedarkcolour.kotlinforforge.forge.MOD_BUS
import top.srcres258.tutorialmod.block.ModBlocks
import top.srcres258.tutorialmod.block.entity.ModBlockEntities
import top.srcres258.tutorialmod.entity.ModEntities
import top.srcres258.tutorialmod.entity.client.ModBoatRenderer
import top.srcres258.tutorialmod.entity.client.RhinoRenderer
import top.srcres258.tutorialmod.item.ModCreativeModeTab
import top.srcres258.tutorialmod.item.ModItems
import top.srcres258.tutorialmod.loot.ModLootModifiers
import top.srcres258.tutorialmod.recipe.ModRecipes
import top.srcres258.tutorialmod.screen.GemPolishingStationScreen
import top.srcres258.tutorialmod.screen.ModMenuTypes
import top.srcres258.tutorialmod.sound.ModSounds
import top.srcres258.tutorialmod.util.ModWoodTypes
import top.srcres258.tutorialmod.villager.ModVillagers
import top.srcres258.tutorialmod.worldgen.biome.ModTerraBlender
import top.srcres258.tutorialmod.worldgen.biome.surface.ModSurfaceRules
import top.srcres258.tutorialmod.worldgen.tree.ModFoliagePlacers
import top.srcres258.tutorialmod.worldgen.tree.ModTrunkPlacerTypes

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

        MOD_BUS.let { bus ->
            ModCreativeModeTab.register(bus)
            ModItems.register(bus)
            ModBlocks.register(bus)
            ModLootModifiers.register(bus)
            ModVillagers.register(bus)
            ModSounds.register(bus)
            ModEntities.register(bus)
            ModBlockEntities.register(bus)
            ModMenuTypes.register(bus)
            ModRecipes.register(bus)
            ModTrunkPlacerTypes.register(bus)
            ModFoliagePlacers.register(bus)
            ModTerraBlender.registerBiomes()

            bus.addListener(TutorialMod::commonSetup)
            MinecraftForge.EVENT_BUS.register(this)
            bus.addListener(TutorialMod::addCreative)
        }
    }

    private fun commonSetup(event: FMLCommonSetupEvent) {
        event.enqueueWork {
            (Blocks.FLOWER_POT as FlowerPotBlock).addPlant(ModBlocks.CATMINT.id, ModBlocks.POTTED_CATMINT)

            SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, MOD_ID,
                ModSurfaceRules.makeRules())
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
            Sheets.addWoodType(ModWoodTypes.PINE)

            EntityRenderers.register(ModEntities.RHINO.get(), ::RhinoRenderer)
            EntityRenderers.register(ModEntities.MOD_BOAT.get()) { context ->
                ModBoatRenderer(context, false)
            }
            EntityRenderers.register(ModEntities.MOD_CHEST_BOAT.get()) { context ->
                ModBoatRenderer(context, true)
            }

            EntityRenderers.register(ModEntities.DICE_PROJECTILE.get(), ::ThrownItemRenderer)

            MenuScreens.register(ModMenuTypes.GEM_POLISHING_MENU.get(), ::GemPolishingStationScreen)
        }
    }
}