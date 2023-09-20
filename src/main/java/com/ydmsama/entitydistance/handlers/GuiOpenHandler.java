package com.ydmsama.entitydistance.handlers;

import com.ydmsama.entitydistance.EntityDistance;
import com.ydmsama.entitydistance.client.gui.CustomGuiOptions;
import com.ydmsama.entitydistance.mixin.gui.GuiOptionsAccessor;
import net.minecraft.client.gui.GuiOptions;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = EntityDistance.MOD_ID)
public class GuiOpenHandler {
    @SubscribeEvent
    public void onGuiOpen(GuiOpenEvent event) {
        if (event.getGui() instanceof GuiOptions) {
            GuiOptions original = (GuiOptions) event.getGui();
            GuiOptionsAccessor accessor = (GuiOptionsAccessor) original;
            event.setGui(new CustomGuiOptions(accessor.getLastScreen(), accessor.getSettings()));
        }
    }
}
