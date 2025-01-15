package top.srcres258.tutorialmod.entity.custom

import net.minecraft.nbt.CompoundTag
import net.minecraft.network.syncher.EntityDataAccessor
import net.minecraft.network.syncher.EntityDataSerializers
import net.minecraft.network.syncher.SynchedEntityData
import net.minecraft.util.ByIdMap
import net.minecraft.util.StringRepresentable
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.vehicle.Boat
import net.minecraft.world.item.Item
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block
import top.srcres258.tutorialmod.block.ModBlocks
import top.srcres258.tutorialmod.entity.ModEntities
import top.srcres258.tutorialmod.item.ModItems

private val DATA_ID_TYPE: EntityDataAccessor<Int> =
    SynchedEntityData.defineId(Boat::class.java, EntityDataSerializers.INT)

class ModBoatEntity(
    pEntityType: EntityType<out Boat>,
    pLevel: Level
) : Boat(pEntityType, pLevel) {
    constructor(level: Level, x: Double, y: Double, z: Double)
            : this(ModEntities.MOD_BOAT.get(), level) {
        setPos(x, y, z)
        xo = x
        yo = y
        zo = z
    }

    override fun getDropItem(): Item =
        when (modVariant) {
            Type.PINE -> ModItems.PINE_BOAT.get()
        }

    var variant: Type
        get() = Type.entries[entityData.get(DATA_ID_TYPE)]
        set(value) {
            entityData.set(DATA_ID_TYPE, value.ordinal)
        }

    val modVariant: Type
        get() = Type.byId(entityData.get(DATA_ID_TYPE))

    override fun defineSynchedData() {
        super.defineSynchedData()
        entityData.define(DATA_ID_TYPE, Type.PINE.ordinal)
    }

    override fun addAdditionalSaveData(pCompound: CompoundTag) {
        pCompound.putString("Type", modVariant.serializedName)
    }

    override fun readAdditionalSaveData(pCompound: CompoundTag) {
        if (pCompound.contains("Type", 8)) {
            Type.byName(pCompound.getString("Type"))?.let {
                variant = it
            }
        }
    }

    enum class Type(
        val planks: Block,
        val name_: String
    ) : StringRepresentable {
        PINE(ModBlocks.PINE_PLANKS.get(), "pine");

        companion object {
            val CODEC: StringRepresentable.EnumCodec<Type> =
                StringRepresentable.fromEnum { entries.toTypedArray() }
            private val BY_ID: (Int) -> Type = { id ->
                ByIdMap.continuous(
                    Type::ordinal,
                    entries.toTypedArray(),
                    ByIdMap.OutOfBoundsStrategy.ZERO
                ).apply(id)
            }

            fun byId(id: Int) = BY_ID(id)

            fun byName(name: String) = CODEC.byName(name, PINE)
        }

        override fun getSerializedName() = name_

        override fun toString() = name_
    }
}