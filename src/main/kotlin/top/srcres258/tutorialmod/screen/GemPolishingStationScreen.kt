package top.srcres258.tutorialmod.screen

import com.mojang.blaze3d.systems.RenderSystem
import net.minecraft.client.gui.GuiGraphics
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen
import net.minecraft.client.renderer.GameRenderer
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.entity.player.Inventory
import top.srcres258.tutorialmod.TutorialMod

private val TEXTURE = ResourceLocation(TutorialMod.MOD_ID, "textures/gui/gem_polishing_station_gui.png")

class GemPolishingStationScreen(
    pMenu: GemPolishingStationMenu,
    pPlayerInventory: Inventory,
    pTitle: Component
) : AbstractContainerScreen<GemPolishingStationMenu>(pMenu, pPlayerInventory, pTitle) {
    override fun init() {
        super.init()
        inventoryLabelY = 10000
        titleLabelY = 10000
    }

    override fun renderBg(guiGraphics: GuiGraphics, p1: Float, p2: Int, p3: Int) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader)
        RenderSystem.setShaderColor(1F, 1F, 1F, 1F)
        RenderSystem.setShaderTexture(0, TEXTURE)
        val x = (width - imageWidth) / 2
        val y = (height - imageHeight) / 2

        guiGraphics.blit(TEXTURE, x, y, 0, 0, imageWidth, imageHeight)

        renderProgressArrow(guiGraphics, x, y)
    }

    private fun renderProgressArrow(guiGraphics: GuiGraphics, x: Int, y: Int) {
        if (menu.isCrafting) {
            guiGraphics.blit(TEXTURE, x + 85, y + 30, 176, 0, 8, menu.scaledProgress)
        }
    }

    override fun render(pGuiGraphics: GuiGraphics, pMouseX: Int, pMouseY: Int, pPartialTick: Float) {
        renderBackground(pGuiGraphics)
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick)
        renderTooltip(pGuiGraphics, pMouseX, pMouseY)
    }
}