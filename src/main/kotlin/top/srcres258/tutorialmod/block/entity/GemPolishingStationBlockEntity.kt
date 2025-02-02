package top.srcres258.tutorialmod.block.entity

import net.minecraft.core.BlockPos
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.chat.Component
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket
import net.minecraft.world.Containers
import net.minecraft.world.MenuProvider
import net.minecraft.world.SimpleContainer
import net.minecraft.world.entity.player.Inventory
import net.minecraft.world.entity.player.Player
import net.minecraft.world.inventory.ContainerData
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockState
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.capabilities.ForgeCapabilities
import net.minecraftforge.common.util.LazyOptional
import net.minecraftforge.items.IItemHandler
import net.minecraftforge.items.ItemStackHandler
import top.srcres258.tutorialmod.recipe.GemPolishingRecipe
import top.srcres258.tutorialmod.screen.GemPolishingStationMenu
import top.srcres258.tutorialmod.util.BLANK_REGISTRY_ACCESS

private const val INPUT_SLOT = 0
private const val OUTPUT_SLOT = 1

class GemPolishingStationBlockEntity(
    pPos: BlockPos,
    pBlockState: BlockState
) : BlockEntity(ModBlockEntities.GEM_POLISHING_BE.get(), pPos, pBlockState), MenuProvider {
    private val itemHandler = object : ItemStackHandler(2) {
        override fun onContentsChanged(slot: Int) {
            setChanged()
            val level = level ?: return
            if (!level.isClientSide) {
                level.sendBlockUpdated(blockPos, blockState, blockState, 3)
            }
        }
    }
    private var lazyItemHandler: LazyOptional<IItemHandler> = LazyOptional.empty()

    private val data = object : ContainerData {
        override fun get(index: Int) = when (index) {
            0 -> this@GemPolishingStationBlockEntity.progress
            1 -> this@GemPolishingStationBlockEntity.maxProgress
            else -> throw IndexOutOfBoundsException("Index $index is out of the container's data count $count.")
        }

        override fun set(index: Int, value: Int) {
            when (index) {
                0 -> this@GemPolishingStationBlockEntity.progress = value
                1 -> this@GemPolishingStationBlockEntity.maxProgress = value
            }
        }

        override fun getCount() = 2
    }
    private var progress = 0
    private var maxProgress = 78

    val renderStack: ItemStack
        get() = if (itemHandler.getStackInSlot(OUTPUT_SLOT).isEmpty) {
            itemHandler.getStackInSlot(INPUT_SLOT)
        } else {
            itemHandler.getStackInSlot(OUTPUT_SLOT)
        }

    override fun <T : Any?> getCapability(cap: Capability<T>): LazyOptional<T> {
        if (cap == ForgeCapabilities.ITEM_HANDLER) {
            return lazyItemHandler.cast()
        }

        return super.getCapability(cap)
    }

    override fun onLoad() {
        super.onLoad()
        lazyItemHandler = LazyOptional.of { itemHandler }
    }

    override fun invalidateCaps() {
        super.invalidateCaps()
        lazyItemHandler.invalidate()
    }

    fun drops() {
        val inventory = SimpleContainer(itemHandler.slots)
        for (i in 0 ..< itemHandler.slots) {
            inventory.setItem(i, itemHandler.getStackInSlot(i))
        }
        level?.let { Containers.dropContents(it, worldPosition, inventory) }
    }

    override fun createMenu(containerId: Int, playerInventory: Inventory, p2: Player) =
        GemPolishingStationMenu(containerId, playerInventory, this, data)

    override fun getDisplayName(): Component = Component.literal("block.tutorialmod.gem_polishing_station")

    override fun saveAdditional(pTag: CompoundTag) {
        pTag.run {
            put("inventory", itemHandler.serializeNBT())
            putInt("gem_polishing_station.progress", progress)
        }

        super.saveAdditional(pTag)
    }

    override fun load(pTag: CompoundTag) {
        super.load(pTag)

        pTag.run {
            itemHandler.deserializeNBT(getCompound("inventory"))
            progress = getInt("gem_polishing_station.progress")
        }
    }

    fun tick(level: Level, pos: BlockPos, state: BlockState) {
        if (hasRecipe) {
            increaseCraftingProgress()
            setChanged(level, pos, state)

            if (hasProgressFinished) {
                craftItem()
                resetProgress()
            }
        } else {
            resetProgress()
        }
    }

    private fun resetProgress() {
        progress = 0
    }

    private fun craftItem() {
        val recipe = currentRecipe ?: return
        val result = recipe.getResultItem(BLANK_REGISTRY_ACCESS)

        itemHandler.extractItem(INPUT_SLOT, 1, false)

        itemHandler.setStackInSlot(OUTPUT_SLOT, ItemStack(result.item,
            itemHandler.getStackInSlot(OUTPUT_SLOT).count + result.count))
    }

    private val hasRecipe: Boolean
        get() {
            val recipe = currentRecipe ?: return false
            val result = recipe.getResultItem(level?.registryAccess() ?: return false)

            return canInsertAmountIntoOutputSlot(result.count)
                    && canInsertItemIntoOutputSlot(result.item)
        }

    private val currentRecipe: GemPolishingRecipe?
        get() {
            val inventory = SimpleContainer(itemHandler.slots)
            for (i in 0 ..< itemHandler.slots) {
                inventory.setItem(i, itemHandler.getStackInSlot(i))
            }

            return level?.let { level ->
                level.recipeManager.getRecipeFor(GemPolishingRecipe.Type, inventory, level)
                    .orElse(null)
            }
        }

    private fun canInsertItemIntoOutputSlot(item: Item) =
        itemHandler.getStackInSlot(OUTPUT_SLOT).run { isEmpty || `is`(item) }

    private fun canInsertAmountIntoOutputSlot(count: Int) =
        itemHandler.getStackInSlot(OUTPUT_SLOT).run { this.count + count <= maxStackSize }

    private val hasProgressFinished
        get() = progress >= maxProgress

    private fun increaseCraftingProgress() {
        progress++
    }

    override fun getUpdatePacket(): ClientboundBlockEntityDataPacket =
        // NOTE: may **not** work for Minecraft 1.20.2 and higher.
        ClientboundBlockEntityDataPacket.create(this)

    override fun getUpdateTag(): CompoundTag = saveWithoutMetadata()
}