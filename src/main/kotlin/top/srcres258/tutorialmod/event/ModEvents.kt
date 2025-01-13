package top.srcres258.tutorialmod.event

import net.minecraft.world.entity.npc.VillagerProfession
import net.minecraft.world.item.EnchantedBookItem
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.item.enchantment.EnchantmentInstance
import net.minecraft.world.item.enchantment.Enchantments
import net.minecraft.world.item.trading.MerchantOffer
import net.minecraftforge.event.village.VillagerTradesEvent
import net.minecraftforge.event.village.WandererTradesEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import top.srcres258.tutorialmod.TutorialMod
import top.srcres258.tutorialmod.item.ModItems

@Mod.EventBusSubscriber(modid = TutorialMod.MOD_ID)
object ModEvents {
    @SubscribeEvent
    fun addCustomTrades(event: VillagerTradesEvent) {
        val trades = event.trades

        when (event.type) {
            VillagerProfession.FARMER -> {
                // Level 1
                trades.get(1).add { _, _ ->
                    MerchantOffer(
                        ItemStack(Items.EMERALD, 2),
                        ItemStack(ModItems.STRAWBERRY.get(), 12),
                        10, 8, 0.02F
                    )
                }

                // Level 2
                trades.get(2).add { _, _ ->
                    MerchantOffer(
                        ItemStack(Items.EMERALD, 5),
                        ItemStack(ModItems.CORN.get(), 6),
                        5, 9, 0.035F
                    )
                }

                // Level 3
                trades.get(3).add { _, _ ->
                    MerchantOffer(
                        ItemStack(Items.GOLD_INGOT, 8),
                        ItemStack(ModItems.CORN_SEEDS.get(), 2),
                        2, 12, 0.075F
                    )
                }
            }

            VillagerProfession.LIBRARIAN -> {
                val enchantedBook = EnchantedBookItem.createForEnchantment(
                    EnchantmentInstance(Enchantments.THORNS, 2))

                // Level 1
                trades.get(1).add { _, _ ->
                    MerchantOffer(
                        ItemStack(Items.EMERALD, 32),
                        enchantedBook,
                        2, 8, 0.02F
                    )
                }
            }
        }
    }

    @SubscribeEvent
    fun addCustomWanderingTrades(event: WandererTradesEvent) {
        val genericTrades = event.genericTrades
        val rareTrades = event.rareTrades

        genericTrades.add { _, _ ->
            MerchantOffer(
                ItemStack(Items.EMERALD, 12),
                ItemStack(ModItems.SAPPHIRE_BOOTS.get(), 1),
                3, 2, 0.2F
            )
        }

        rareTrades.add { _, _ ->
            MerchantOffer(
                ItemStack(Items.EMERALD, 24),
                ItemStack(ModItems.SAPPHIRE_BOOTS.get(), 1),
                2, 12, 0.15F
            )
        }
    }
}