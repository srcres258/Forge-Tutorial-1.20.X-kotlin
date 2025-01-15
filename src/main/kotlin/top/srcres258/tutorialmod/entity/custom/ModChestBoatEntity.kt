package top.srcres258.tutorialmod.entity.custom

import net.minecraft.nbt.CompoundTag
import net.minecraft.network.syncher.EntityDataAccessor
import net.minecraft.network.syncher.EntityDataSerializers
import net.minecraft.network.syncher.SynchedEntityData
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.vehicle.Boat
import net.minecraft.world.entity.vehicle.ChestBoat
import net.minecraft.world.item.Item
import net.minecraft.world.level.Level
import top.srcres258.tutorialmod.entity.ModEntities
import top.srcres258.tutorialmod.item.ModItems

private val DATA_ID_TYPE: EntityDataAccessor<Int> =
    SynchedEntityData.defineId(Boat::class.java, EntityDataSerializers.INT)

class ModChestBoatEntity(
    pEntityType: EntityType<out Boat>,
    pLevel: Level
) : ChestBoat(pEntityType, pLevel) {
    constructor(level: Level, x: Double, y: Double, z: Double)
            : this(ModEntities.MOD_CHEST_BOAT.get(), level) {
        setPos(x, y, z)
        xo = x
        yo = y
        zo = z
    }

    override fun getDropItem(): Item =
        when (modVariant) {
            ModBoatEntity.Type.PINE -> ModItems.PINE_CHEST_BOAT.get()
        }

    var variant: ModBoatEntity.Type
        get() = ModBoatEntity.Type.entries[entityData.get(DATA_ID_TYPE)]
        set(value) {
            entityData.set(DATA_ID_TYPE, value.ordinal)
        }

    override fun defineSynchedData() {
        super.defineSynchedData()
        entityData.define(DATA_ID_TYPE, ModBoatEntity.Type.PINE.ordinal)
    }

    override fun addAdditionalSaveData(pCompound: CompoundTag) {
        pCompound.putString("Type", modVariant.serializedName)
    }

    override fun readAdditionalSaveData(pCompound: CompoundTag) {
        if (pCompound.contains("Type", 8)) {
            ModBoatEntity.Type.byName(pCompound.getString("Type"))?.let {
                variant = it
            }
        }
    }

    val modVariant: ModBoatEntity.Type
        get() = ModBoatEntity.Type.byId(entityData.get(DATA_ID_TYPE))
}