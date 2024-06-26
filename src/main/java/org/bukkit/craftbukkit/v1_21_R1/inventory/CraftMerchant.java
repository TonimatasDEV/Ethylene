package org.bukkit.craftbukkit.v1_21_R1.inventory;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import dev.tonimatas.ethylene.link.world.item.trading.MerchantOfferLink;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.trading.MerchantOffers;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.Merchant;
import org.bukkit.inventory.MerchantRecipe;

import java.util.Collections;
import java.util.List;

public class CraftMerchant implements Merchant {

    protected final net.minecraft.world.item.trading.Merchant merchant;

    public CraftMerchant(net.minecraft.world.item.trading.Merchant merchant) {
        this.merchant = merchant;
    }

    public net.minecraft.world.item.trading.Merchant getMerchant() {
        return merchant;
    }

    @Override
    public List<MerchantRecipe> getRecipes() {
        return Collections.unmodifiableList(Lists.transform(merchant.getOffers(), new Function<net.minecraft.world.item.trading.MerchantOffer, MerchantRecipe>() {
            @Override
            public MerchantRecipe apply(net.minecraft.world.item.trading.MerchantOffer recipe) {
                return ((MerchantOfferLink) recipe).asBukkit(); // Ethylene
            }
        }));
    }

    @Override
    public void setRecipes(List<MerchantRecipe> recipes) {
        MerchantOffers recipesList = merchant.getOffers();
        recipesList.clear();
        for (MerchantRecipe recipe : recipes) {
            recipesList.add(CraftMerchantRecipe.fromBukkit(recipe).toMinecraft());
        }
    }

    @Override
    public MerchantRecipe getRecipe(int i) {
        return ((MerchantOfferLink) merchant.getOffers().get(i)).asBukkit(); // Ethylene
    }

    @Override
    public void setRecipe(int i, MerchantRecipe merchantRecipe) {
        merchant.getOffers().set(i, CraftMerchantRecipe.fromBukkit(merchantRecipe).toMinecraft());
    }

    @Override
    public int getRecipeCount() {
        return merchant.getOffers().size();
    }

    @Override
    public boolean isTrading() {
        return getTrader() != null;
    }

    @Override
    public HumanEntity getTrader() {
        Player eh = merchant.getTradingPlayer();
        return eh == null ? null : eh.getBukkitEntity();
    }

    @Override
    public int hashCode() {
        return merchant.hashCode();
    }

    @Override
    public boolean equals(final Object obj) {
        return obj instanceof CraftMerchant && ((CraftMerchant) obj).merchant.equals(this.merchant);
    }
}
