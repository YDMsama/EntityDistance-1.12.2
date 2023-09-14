package com.ydmsama.entitydistance.mixin.gui;

import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.settings.GameSettings;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(GuiOptions.class)
public interface GuiOptionsAccessor {
    @Accessor("lastScreen")
    GuiScreen getLastScreen();

    @Accessor("settings")
    GameSettings getSettings();
}
