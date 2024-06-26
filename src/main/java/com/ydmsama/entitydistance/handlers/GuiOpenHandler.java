package com.ydmsama.entitydistance.handlers;

import com.ydmsama.entitydistance.EntityDistance;
import com.ydmsama.entitydistance.client.gui.CustomGuiOptions;
import com.ydmsama.entitydistance.config.ModConfig;
import com.ydmsama.entitydistance.mixin.gui.GuiOptionsAccessor;
import net.minecraft.client.gui.GuiOptions;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber(modid = EntityDistance.MOD_ID, value = Side.CLIENT)
public class GuiOpenHandler {
    @SubscribeEvent
    public void onGuiOpen(GuiOpenEvent event) {
        if (event.getGui() instanceof GuiOptions && ModConfig.useModGui) {
            GuiOptions original = (GuiOptions) event.getGui();
            GuiOptionsAccessor accessor = (GuiOptionsAccessor) original;
            event.setGui(new CustomGuiOptions(accessor.getLastScreen(), accessor.getSettings()));
        }
    }
}
