package com.ydmsama.entitydistance.client.gui;

import com.ydmsama.entitydistance.config.ModConfig;
import net.minecraft.client.gui.GuiPageButtonList;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSlider;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.settings.GameSettings;

public class CustomGuiOptions extends GuiOptions {
    private GuiSlider renderDistanceSlider;

    public CustomGuiOptions(GuiScreen parentScreenIn, GameSettings gameSettingsIn) {
        super(parentScreenIn, gameSettingsIn);
    }

    @Override
    public void initGui() {
        super.initGui();

        this.renderDistanceSlider = new GuiSlider(new GuiPageButtonList.GuiResponder() {
            @Override
            public void setEntryValue(int id, boolean value) {
            }

            @Override
            public void setEntryValue(int id, float value) {
                ModConfig.renderDistanceMultiplier = Math.round(value * 10) / 10.0f;  // 将值四舍五入到最接近的 0.1

            }

            @Override
            public void setEntryValue(int id, String value) {
            }
        }, 0, this.width / 2 - 155, this.height / 6 + 1 * 16, "Entity Render Distance: ", 0, 5, (float) ModConfig.renderDistanceMultiplier, null);

        this.buttonList.add(this.renderDistanceSlider);
    }

    @Override
    public void onGuiClosed() {
        super.onGuiClosed();

        ModConfig.renderDistanceMultiplier = (float)(Math.round(this.renderDistanceSlider.getSliderValue() * 10)) / 10.0F;
    }
}
