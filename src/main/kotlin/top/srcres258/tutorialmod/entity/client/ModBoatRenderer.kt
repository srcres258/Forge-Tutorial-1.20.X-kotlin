package top.srcres258.tutorialmod.entity.client

import net.minecraft.client.model.BoatModel
import net.minecraft.client.model.ChestBoatModel
import net.minecraft.client.model.ListModel
import net.minecraft.client.model.geom.ModelLayerLocation
import net.minecraft.client.renderer.entity.BoatRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.entity.vehicle.Boat
import top.srcres258.tutorialmod.TutorialMod
import top.srcres258.tutorialmod.entity.custom.ModBoatEntity

class ModBoatRenderer(
    pContext: EntityRendererProvider.Context,
    pChestBoat: Boolean
) : BoatRenderer(pContext, pChestBoat) {
    companion object {
        private fun getTextureLocation(type: ModBoatEntity.Type, chestBoat: Boolean) =
            if (chestBoat) {
                "textures/entity/chest_boat/${type.name_}.png"
            } else {
                "textures/entity/boat/${type.name_}.png"
            }

        private fun createBoatModel(
            context: EntityRendererProvider.Context,
            type: ModBoatEntity.Type,
            chestBoat: Boolean
        ): ListModel<Boat> {
            val mll = if (chestBoat) createChestBoatModelName(type) else createBoatModelName(type)
            val mp = context.bakeLayer(mll)
            return if (chestBoat) ChestBoatModel(mp) else BoatModel(mp)
        }
    }

    private val boatResources: Map<ModBoatEntity.Type, Pair<ResourceLocation, ListModel<Boat>>> =
        mutableMapOf<ModBoatEntity.Type, Pair<ResourceLocation, ListModel<Boat>>>().also { map ->
            ModBoatEntity.Type.entries.forEach { type ->
                map[type] = Pair(
                    ResourceLocation(TutorialMod.MOD_ID, getTextureLocation(type, pChestBoat)),
                    createBoatModel(pContext, type, pChestBoat)
                )
            }
        }
}

private fun createBoatModelName(type: ModBoatEntity.Type) =
    createLocation("boat/${type.name_}", "main")

private fun createChestBoatModelName(type: ModBoatEntity.Type) =
    createLocation("chest_boat/${type.name_}", "main")

private fun createLocation(path: String, model: String) =
    ModelLayerLocation(ResourceLocation(TutorialMod.MOD_ID, path), model)