package top.srcres258.tutorialmod.loot

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import it.unimi.dsi.fastutil.objects.ObjectArrayList
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.storage.loot.LootContext
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition
import net.minecraftforge.common.loot.LootModifier
import net.minecraftforge.registries.ForgeRegistries

class AddItemModifier(
    conditionsIn: Array<out LootItemCondition>?,
    private val item: Item
) : LootModifier(conditionsIn) {
    companion object {
        val CODEC by lazy<Codec<AddItemModifier>> {
            RecordCodecBuilder.create { inst ->
                codecStart(inst)
                    .and(ForgeRegistries.ITEMS.codec.fieldOf("item").forGetter { m -> m.item })
                    .apply(inst, ::AddItemModifier)
            }
        }
    }

    override fun codec() = CODEC

    override fun doApply(
        generatedLoot: ObjectArrayList<ItemStack>,
        context: LootContext
    ): ObjectArrayList<ItemStack> {
        for (condition in conditions) {
            if (!condition.test(context)) {
                return generatedLoot
            }
        }

        generatedLoot.add(ItemStack(item))

        return generatedLoot
    }

}