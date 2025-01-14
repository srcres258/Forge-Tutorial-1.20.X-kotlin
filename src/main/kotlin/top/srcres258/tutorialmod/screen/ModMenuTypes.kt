package top.srcres258.tutorialmod.screen

import net.minecraft.world.inventory.AbstractContainerMenu
import net.minecraft.world.inventory.MenuType
import net.minecraftforge.common.extensions.IForgeMenuType
import net.minecraftforge.eventbus.api.IEventBus
import net.minecraftforge.network.IContainerFactory
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import net.minecraftforge.registries.RegistryObject
import top.srcres258.tutorialmod.TutorialMod

object ModMenuTypes {
    val MENUS: DeferredRegister<MenuType<*>> =
        DeferredRegister.create(ForgeRegistries.MENU_TYPES, TutorialMod.MOD_ID)

    val GEM_POLISHING_MENU: RegistryObject<MenuType<GemPolishingStationMenu>> =
        registerMenuType("gem_polishing_menu", ::GemPolishingStationMenu)

    private fun <T: AbstractContainerMenu> registerMenuType(
        name: String,
        factory: IContainerFactory<T>
    ): RegistryObject<MenuType<T>> = MENUS.register(name) {
        IForgeMenuType.create(factory)
    }

    fun register(eventBus: IEventBus) {
        MENUS.register(eventBus)
    }
}