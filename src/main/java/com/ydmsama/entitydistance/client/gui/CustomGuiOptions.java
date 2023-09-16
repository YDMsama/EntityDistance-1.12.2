package com.ydmsama.entitydistance.client.gui;

import java.math.BigDecimal;
import java.math.RoundingMode;
import com.ydmsama.entitydistance.EntityDistance;
import com.ydmsama.entitydistance.config.ModConfig;
import net.minecraft.client.gui.*;
import net.minecraft.client.settings.GameSettings;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;

public class CustomGuiOptions extends GuiOptions {
    private CustomGuiSlider renderDistanceSlider;

    public float roundToDecimalPlaces(float value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(Float.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.floatValue();
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
                ModConfig.renderDistanceMultiplier = value;
            }

            @Override
            public void setEntryValue(int id, String value) {
            }
        }, 0, this.width / 2 - 155, this.height / 6 + 1 * 16, "Entity Render Distance", 0, 500, (float) ModConfig.renderDistanceMultiplier * 100.0F, null);

        this.buttonList.add(this.renderDistanceSlider);
    }

    @Override
    public void onGuiClosed() {
        super.onGuiClosed();
        ModConfig.renderDistanceMultiplier = this.renderDistanceSlider.getSliderValue() / 100.0F;

//        ModConfig.renderDistanceMultiplier = roundToDecimalPlaces(this.renderDistanceSlider.getSliderValue() / 100.0F, 2);
        ConfigChangedEvent.OnConfigChangedEvent event = new ConfigChangedEvent.OnConfigChangedEvent(EntityDistance.MOD_ID, null, false, false);
        MinecraftForge.EVENT_BUS.post(event);
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

