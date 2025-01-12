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
            .icon { ItemStack(ModItems.SAPPHIRE) }
            .title(Component.translatable("creativetab.tutorial_tab"))
            .displayItems { _, output ->
                // items
                run {
                    // mod
                    output.accept(ModItems.SAPPHIRE)
                    output.accept(ModItems.RAW_SAPPHIRE)

                    output.accept(ModItems.METAL_DETECTOR)

                    output.accept(ModItems.STRAWBERRY)

                    output.accept(ModItems.PINE_CONE)

                    // vanilla
                    output.accept(Items.DIAMOND)
                }

                // blocks
                run {
                    output.accept(ModBlocks.SAPPHIRE_BLOCK.get())
                    output.accept(ModBlocks.RAW_SAPPHIRE_BLOCK.get())

                    output.accept(ModBlocks.SAPPHIRE_ORE.get())
                    output.accept(ModBlocks.DEEPSLATE_SAPPHIRE_ORE.get())
                    output.accept(ModBlocks.NETHER_SAPPHIRE_ORE.get())
                    output.accept(ModBlocks.END_STONE_SAPPHIRE_ORE.get())

                    output.accept(ModBlocks.SOUND_BLOCK.get())
                }
            }
            .build()
    }

    fun register(eventBus: IEventBus) {
        CREATIVE_MODE_TABS.register(eventBus)
    }
}