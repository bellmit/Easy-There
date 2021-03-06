package com.nexorel.et.content.items.Talismans;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class VenomProtectionTalismanItem extends TalismanItem {

    boolean flag = false;

    public VenomProtectionTalismanItem() {
        this(0, 0, 0, 0, 100);
    }

    public VenomProtectionTalismanItem(double accuracy, double agility, double strength, double fortune, int cc) {
        super(accuracy, agility, strength, fortune, cc);
    }


    @Override
    protected void SpecialBuffs(Level world, Entity entity) {
        if (entity instanceof LivingEntity livingEntity) {
            livingEntity.removeEffect(MobEffects.POISON);
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> textComponentList, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, world, textComponentList, tooltipFlag);
        if (Screen.hasShiftDown()) {
            textComponentList.add(new TextComponent(ChatFormatting.BLUE + "Stats:"));
            textComponentList.add(new TextComponent(ChatFormatting.RED + "Accuracy" + " \u2609 " + ChatFormatting.WHITE + this.getAccuracy()));
            textComponentList.add(new TextComponent(ChatFormatting.WHITE + "Agility" + " \u2668 " + ChatFormatting.WHITE + this.getAgility()));
            textComponentList.add(new TextComponent(ChatFormatting.DARK_RED + "Strength" + " \u2694 " + ChatFormatting.WHITE + this.getStrength()));
            textComponentList.add(new TextComponent(ChatFormatting.GREEN + "Fortune" + " \u2618 " + ChatFormatting.WHITE + this.getFortune()));
            textComponentList.add(new TextComponent(ChatFormatting.BLUE + "Crit Chance" + " \u2623 " + ChatFormatting.WHITE + this.getCc()));
        } else {
            textComponentList.add(new TextComponent(ChatFormatting.GREEN + "Gain immunity from Poison"));
        }
    }
}
