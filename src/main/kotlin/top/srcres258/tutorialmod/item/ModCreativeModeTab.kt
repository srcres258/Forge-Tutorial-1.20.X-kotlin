package top.srcres258.tutorialmod.item

import net.minecraft.core.registries.Registries
import net.minecraft.network.chat.Component
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraftforge.eventbus.api.IEventBus
import net.minecraftforge.registries.DeferredRegister
import thedarkcolour.kotlinforforge.forge.registerObject
import top.srcres258.tutorialmod.TutorialMod
import top.srcres258.tutorialmod.block.ModBlocks

object ModCreativeModeTab {
    val CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TutorialMod.MOD_ID)

    val TUTORIAL_TAB by CREATIVE_MODE_TABS.registerObject("tutorial_tab") {
        CreativeModeTab.builder()
            .icon { ItemStack(ModItems.SAPPHIRE.get()) }
            .title(Component.translatable("creativetab.tutorial_tab"))
            .displayItems { _, output ->
                output.run {
                    // items
                    ModItems.run {
                        // mod
                        accept(SAPPHIRE.get())
                        accept(RAW_SAPPHIRE.get())

                        accept(METAL_DETECTOR.get())

                        accept(STRAWBERRY.get())

                        accept(PINE_CONE.get())

                        accept(SAPPHIRE_STAFF.get())

                        accept(SAPPHIRE_SWORD.get())
                        accept(SAPPHIRE_PICKAXE.get())
                        accept(SAPPHIRE_AXE.get())
                        accept(SAPPHIRE_SHOVEL.get())
                        accept(SAPPHIRE_HOE.get())

                        accept(SAPPHIRE_HELMET.get())
                        accept(SAPPHIRE_CHESTPLATE.get())
                        accept(SAPPHIRE_LEGGINGS.get())
                        accept(SAPPHIRE_BOOTS.get())

                        accept(STRAWBERRY_SEEDS.get())

                        accept(CORN_SEEDS.get())
                        accept(CORN.get())

                        accept(BAR_BRAWL_MUSIC_DISC.get())

                        accept(RHINO_SPAWN_EGG.get())

                        accept(PINE_SIGN.get())
                        accept(PINE_HANGING_SIGN.get())

                        accept(PINE_BOAT.get())
                        accept(PINE_CHEST_BOAT.get())

                        accept(DICE.get())

                        // vanilla
                        accept(Items.DIAMOND)
                    }

                    // blocks
                    ModBlocks.run {
                        accept(SAPPHIRE_BLOCK.get())
                        accept(RAW_SAPPHIRE_BLOCK.get())

                        accept(SAPPHIRE_ORE.get())
                        accept(DEEPSLATE_SAPPHIRE_ORE.get())
                        accept(NETHER_SAPPHIRE_ORE.get())
                        accept(END_STONE_SAPPHIRE_ORE.get())

                        accept(SOUND_BLOCK.get())

                        accept(SAPPHIRE_STAIRS.get())
                        accept(SAPPHIRE_SLAB.get())

                        accept(SAPPHIRE_BUTTON.get())
                        accept(SAPPHIRE_PRESSURE_PLATE.get())

                        accept(SAPPHIRE_FENCE.get())
                        accept(SAPPHIRE_FENCE_GATE.get())
                        accept(SAPPHIRE_WALL.get())

                        accept(SAPPHIRE_DOOR.get())
                        accept(SAPPHIRE_TRAPDOOR.get())

                        accept(CATMINT.get())

                        accept(GEM_POLISHING_STATION.get())

                        accept(PINE_LOG.get())
                        accept(PINE_WOOD.get())
                        accept(STRIPPED_PINE_LOG.get())
                        accept(STRIPPED_PINE_WOOD.get())

                        accept(PINE_PLANKS.get())
                        accept(PINE_LEAVES.get())

                        accept(PINE_SAPLING.get())
                    }
                }
            }
            .build()
    }

    fun register(eventBus: IEventBus) {
        CREATIVE_MODE_TABS.register(eventBus)
    }
}