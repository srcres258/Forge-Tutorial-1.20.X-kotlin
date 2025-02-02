package top.srcres258.tutorialmod.entity.client

import net.minecraft.client.model.geom.ModelLayerLocation
import net.minecraft.resources.ResourceLocation
import top.srcres258.tutorialmod.TutorialMod

object ModModelLayers {
    val RHINO_LAYER = ModelLayerLocation(
        ResourceLocation(TutorialMod.MOD_ID, "rhino_layer"),
        "main"
    )

    val PINE_BOAT_LAYER = ModelLayerLocation(
        ResourceLocation(TutorialMod.MOD_ID, "boat/pine"),
        "main"
    )
    val PINE_CHEST_BOAT_LAYER = ModelLayerLocation(
        ResourceLocation(TutorialMod.MOD_ID, "chest_boat/pine"),
        "main"
    )
}