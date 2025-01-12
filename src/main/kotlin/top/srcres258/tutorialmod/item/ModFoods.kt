package top.srcres258.tutorialmod.item

import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.effect.MobEffects
import net.minecraft.world.food.FoodProperties

object ModFoods {
    val STRAWBERRY = FoodProperties.Builder()
        .nutrition(2)
        .fast()
        .saturationMod(0.2F)
        .effect({ MobEffectInstance(MobEffects.MOVEMENT_SPEED, 200) }, 0.1F)
        .build()
}