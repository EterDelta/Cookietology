package com.github.eterdelta.cookietology.item;

import com.github.eterdelta.cookietology.registry.CookietologyItems;
import com.github.eterdelta.cookietology.util.CookieHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class CookieItem extends Item {
    public static final CookieProperties DEFAULT_PROPERTIES = new CookieProperties(32, 0.1F, 2);

    public CookieItem(Properties properties) {
        super(properties);
    }

    public static ItemStack create(int count, int eatSpeed, float saturation, int nutrition) {
        ItemStack cookieStack = new ItemStack(CookietologyItems.COOKIE.get(), count);
        CookieHelper.saveCookieProperties(cookieStack, eatSpeed, saturation, nutrition);
        return cookieStack;
    }

    @Override
    public int getUseDuration(ItemStack itemStack) {
        return CookieHelper.getCookieProperties(itemStack).eatSpeed();
    }

    @Override
    public void appendHoverText(ItemStack itemStack, Level level, List<Component> components, TooltipFlag tooltipFlag) {
        CookieProperties cookieProperties = CookieHelper.getCookieProperties(itemStack);
        components.add(new TranslatableComponent("cookie.eat_speed", new TextComponent(String.valueOf(cookieProperties.eatSpeed())).withStyle(Style.EMPTY.withColor(0xFF6100))
                .append(new TranslatableComponent("cookie.eat_speed.ticks"))).withStyle(ChatFormatting.GRAY));
        components.add(new TranslatableComponent("cookie.saturation", new TextComponent(String.valueOf(cookieProperties.saturation())).withStyle(ChatFormatting.GOLD)).withStyle(ChatFormatting.GRAY));
        components.add(new TranslatableComponent("cookie.nutrition", new TextComponent(String.valueOf(cookieProperties.nutrition())).withStyle(ChatFormatting.YELLOW)).withStyle(ChatFormatting.GRAY));
    }

    public record CookieProperties(int eatSpeed, float saturation, int nutrition) { }
}