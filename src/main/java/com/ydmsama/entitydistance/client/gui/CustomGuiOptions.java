package com.ydmsama.entitydistance.client.gui;

import com.ydmsama.entitydistance.config.ModConfig;
import net.minecraft.client.gui.*;
import net.minecraft.client.settings.GameSettings;

import static com.ydmsama.entitydistance.client.gui.CustomGameSettings.myOptions.ENTITY_RENDER_DISTANCE;

public class CustomGuiOptions extends GuiOptions {
    private CustomGuiSlider renderDistanceSlider;

    public float roundToDecimalPlaces(float value, int places) {
        float scale = (float) Math.pow(10, places);
        return Math.round(value * scale) / scale;
    }

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
                ModConfig.renderDistanceMultiplier = roundToDecimalPlaces(value * 100F, 2);

            }

            @Override
            public void setEntryValue(int id, String value) {
            }
        }, 0, this.width / 2 - 155, this.height / 6 + 1 * 16, "Entity Render Distance", 0, 500, roundToDecimalPlaces((float) ModConfig.renderDistanceMultiplier * 100.0F, 2), null);

        this.buttonList.add(this.renderDistanceSlider);
    }

    @Override
    public void onGuiClosed() {
        super.onGuiClosed();

        ModConfig.renderDistanceMultiplier = roundToDecimalPlaces(this.renderDistanceSlider.getSliderValue() / 100.0F, 2);
    }
}


//package com.ydmsama.entitydistance.client.gui;
//
//import com.ydmsama.entitydistance.config.ModConfig;
//import net.minecraft.client.gui.*;
//import net.minecraft.client.settings.GameSettings;
//
//import static com.ydmsama.entitydistance.client.gui.CustomGameSettings.myOptions.ENTITY_RENDER_DISTANCE;
//
//public class CustomGuiOptions extends GuiOptions {
//    private CustomGuiSlider customSlider;  // 使用自定义滑条类
//
//    public CustomGuiOptions(GuiScreen parentScreenIn, GameSettings gameSettingsIn) {
//        super(parentScreenIn, gameSettingsIn);
//    }
//
//    @Override
//    public void initGui() {
//        super.initGui();
//        customSlider = new CustomGuiSlider(0, this.width / 2 - 155, this.height / 6 + 1 * 16, "Entity Render Distance: ", (float) ModConfig.renderDistanceMultiplier);
//        this.buttonList.add(customSlider);
//    }
//
//    @Override
//    public void onGuiClosed() {
//        super.onGuiClosed();
//
//        // 使用customSlider而不是renderDistanceSlider
//        ModConfig.renderDistanceMultiplier = (float)(Math.round(customSlider.getSliderValue()/100 * 10)) / 10.0F;
//    }
//}
//

