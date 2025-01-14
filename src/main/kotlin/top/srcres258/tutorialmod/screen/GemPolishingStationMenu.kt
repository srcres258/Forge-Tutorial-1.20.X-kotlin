package top.srcres258.tutorialmod.screen

import net.minecraft.network.FriendlyByteBuf
import net.minecraft.world.entity.player.Inventory
import net.minecraft.world.entity.player.Player
import net.minecraft.world.inventory.AbstractContainerMenu
import net.minecraft.world.inventory.ContainerData
import net.minecraft.world.inventory.ContainerLevelAccess
import net.minecraft.world.inventory.SimpleContainerData
import net.minecraft.world.inventory.Slot
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level
import net.minecraftforge.common.capabilities.ForgeCapabilities
import net.minecraftforge.items.SlotItemHandler
import top.srcres258.tutorialmod.block.ModBlocks
import top.srcres258.tutorialmod.block.entity.GemPolishingStationBlockEntity

// CREDIT GOES TO: diesieben07 | https://github.com/diesieben07/SevenCommons
// must assign a slot number to each of the slots used by the GUI.
// For this container, we can see both the tile inventory's slots as well as the player inventory slots and the hotbar.
// Each time we add a Slot to the container, it automatically increases the slotIndex, which means
//  0 - 8 = hotbar slots (which will map to the InventoryPlayer slot numbers 0 - 8)
//  9 - 35 = player inventory slots (which map to the InventoryPlayer slot numbers 9 - 35)
//  36 - 44 = TileInventory slots, which map to our TileEntity slot numbers 0 - 8)
private const val HOTBAR_SLOT_COUNT = 9
private const val PLAYER_INVENTORY_ROW_COUNT = 3
private const val PLAYER_INVENTORY_COLUMN_COUNT = 9
private const val PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT
private const val VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT
private const val VANILLA_FIRST_SLOT_INDEX = 0
private const val TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT

// THIS YOU HAVE TO DEFINE!
private const val TE_INVENTORY_SLOT_COUNT = 2 // must be the number of slots you have!

class GemPolishingStationMenu(
    containerId: Int,
    inv: Inventory,
    val blockEntity: GemPolishingStationBlockEntity,
    private val data: ContainerData
) : AbstractContainerMenu(ModMenuTypes.GEM_POLISHING_MENU.get(), containerId) {
    init { // Preconditions
        checkContainerSize(inv, 2)
    }

    private val level: Level = inv.player.level()

    init {
        addPlayerInventory(inv)
        addPlayerHotbar(inv)

        blockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent { handler ->
            addSlot(SlotItemHandler(handler, 0, 80, 11))
            addSlot(SlotItemHandler(handler, 1, 80, 59))
        }

        addDataSlots(data)
    }

    constructor(
        containerId: Int,
        inv: Inventory,
        extraData: FriendlyByteBuf
    ) : this(
        containerId,
        inv,
        inv.player.level().getBlockEntity(extraData.readBlockPos()) as GemPolishingStationBlockEntity,
        SimpleContainerData(2)
    )

    val isCrafting
        get() = data[0] > 0

    val scaledProgress: Int
        get() {
            val progress = data[0]
            val maxProgress = data[1]
            val progressArrowSize = 26

            return if (maxProgress != 0 && progress != 0) {
                progress * progressArrowSize / maxProgress
            } else {
                0
            }
        }

    override fun quickMoveStack(playerIn: Player, pIndex: Int): ItemStack {
        val sourceSlot = slots[pIndex]
        if (!sourceSlot.hasItem()) {
            return ItemStack.EMPTY
        }
        val sourceStack = sourceSlot.item
        val copyOfSourceStack = sourceStack.copy()

        // Check if the slot clicked is one of the vanilla container slots
        if (pIndex < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT) {
            // This is a vanilla container slot so merge the stack into the tile inventory
            if (!moveItemStackTo(sourceStack, TE_INVENTORY_FIRST_SLOT_INDEX,
                    TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT, false)) {
                return ItemStack.EMPTY
            }
        } else if (pIndex < TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT) {
            // This is a TE slot so merge the stack into the players inventory
            if (!moveItemStackTo(sourceStack, VANILLA_FIRST_SLOT_INDEX,
                    VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT, false)) {
                return ItemStack.EMPTY
            }
        } else {
            throw IllegalArgumentException("Invalid slot index: $pIndex")
        }
        // If stack size == 0 (the entire stack was moved) set slot contents to null
        if (sourceStack.count == 0) {
            sourceSlot.set(ItemStack.EMPTY)
        } else {
            sourceSlot.setChanged()
        }
        sourceSlot.onTake(playerIn, sourceStack)
        return copyOfSourceStack
    }

    override fun stillValid(player: Player) = stillValid(ContainerLevelAccess.create(level, blockEntity.blockPos),
        player, ModBlocks.GEM_POLISHING_STATION.get())

    private fun addPlayerInventory(inv: Inventory) {
        for (i in 0 ..< 3) {
            for (l in 0 ..< 9) {
                addSlot(Slot(inv, l + i * 9 + 9, 8 + l * 18, 84 + i * 18))
            }
        }
    }

    private fun addPlayerHotbar(inv: Inventory) {
        for (i in 0 ..< 9) {
            addSlot(Slot(inv, i, 8 + i * 18, 142))
        }
    }
}