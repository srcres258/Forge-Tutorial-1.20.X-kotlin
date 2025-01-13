package top.srcres258.tutorialmod.sound

import net.minecraft.resources.ResourceLocation
import net.minecraft.sounds.SoundEvent
import net.minecraftforge.common.util.ForgeSoundType
import net.minecraftforge.eventbus.api.IEventBus
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import net.minecraftforge.registries.RegistryObject
import top.srcres258.tutorialmod.TutorialMod

object ModSounds {
    val SOUND_EVENTS: DeferredRegister<SoundEvent> =
        DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, TutorialMod.MOD_ID)

    val METAL_DETECTOR_FOUND_ORE: RegistryObject<SoundEvent> = registerSoundEvents("metal_detector_found_ore")

    val SOUND_BLOCK_BREAK: RegistryObject<SoundEvent> = registerSoundEvents("sound_block_break")
    val SOUND_BLOCK_STEP: RegistryObject<SoundEvent> = registerSoundEvents("sound_block_step")
    val SOUND_BLOCK_FALL: RegistryObject<SoundEvent> = registerSoundEvents("sound_block_fall")
    val SOUND_BLOCK_PLACE: RegistryObject<SoundEvent> = registerSoundEvents("sound_block_place")
    val SOUND_BLOCK_HIT: RegistryObject<SoundEvent> = registerSoundEvents("sound_block_hit")

    val BAR_BRAWL: RegistryObject<SoundEvent> = registerSoundEvents("bar_brawl")

    val SOUND_BLOCK_SOUNDS = ForgeSoundType(1F, 1F,
        SOUND_BLOCK_BREAK, SOUND_BLOCK_STEP, SOUND_BLOCK_PLACE,
        SOUND_BLOCK_HIT, SOUND_BLOCK_FALL)

    private fun registerSoundEvents(name: String) =
        SOUND_EVENTS.register(name) {
            SoundEvent.createVariableRangeEvent(ResourceLocation(TutorialMod.MOD_ID, name))
        }

    fun register(eventBus: IEventBus) {
        SOUND_EVENTS.register(eventBus)
    }
}