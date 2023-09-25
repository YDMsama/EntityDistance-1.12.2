package com.ydmsama.entitydistance.client.gui;

import com.ydmsama.entitydistance.EntityDistance;
import com.ydmsama.entitydistance.config.ModConfig;
import net.minecraft.client.gui.*;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class CustomGuiOptions extends GuiOptions {
    private CustomGuiSlider renderDistanceSlider;
    private CustomGuiSlider trackDistanceSlider;
    public String entityRenderDistance = I18n.format("entity.render.distance");
    public String entityTrackDistance = I18n.format("entity.track.distance");

    public CustomGuiOptions(GuiScreen parentScreenIn, GameSettings gameSettingsIn) {
        super(parentScreenIn, gameSettingsIn);
    }

    @Override
    public void initGui() {
        super.initGui();

        this.renderDistanceSlider = new CustomGuiSlider(new GuiPageButtonList.GuiResponder() {
            @Override
            public void setEntryValue(int id, boolean value) {
            }

            @Override
            public void setEntryValue(int id, float value) {
                ModConfig.renderDistanceMultiplier = value;
            }

            @Override
            public void setEntryValue(int id, String value) {
            }
        }, 0, this.width / 2 - 155, this.height / 6 + 1 * 16, entityRenderDistance, 0, 500, (float) ModConfig.renderDistanceMultiplier * 100.0F, null, 50.0F);

        this.trackDistanceSlider = new CustomGuiSlider(new GuiPageButtonList.GuiResponder() {
            @Override
            public void setEntryValue(int id, boolean value) {
            }

            @Override
            public void setEntryValue(int id, float value) {
                ModConfig.trackDistanceMultiplier = value;
            }

            @Override
            public void setEntryValue(int id, String value) {
            }
        }, 1, this.width / 2 + 5, this.height / 6 + 1 * 16, entityTrackDistance, 0, 500, (float) ModConfig.trackDistanceMultiplier * 100.0F, null, 50.0F);

        this.buttonList.add(this.renderDistanceSlider);
        this.buttonList.add(this.trackDistanceSlider);
    }

    @Override
    public void onGuiClosed() {
        super.onGuiClosed();
        ModConfig.renderDistanceMultiplier = this.renderDistanceSlider.getSliderValue() / 100.0F;
        ModConfig.trackDistanceMultiplier = this.trackDistanceSlider.getSliderValue() / 100.0F;

        ConfigChangedEvent.OnConfigChangedEvent event = new ConfigChangedEvent.OnConfigChangedEvent(EntityDistance.MOD_ID, null, false, false);
        MinecraftForge.EVENT_BUS.post(event);
    }
}
