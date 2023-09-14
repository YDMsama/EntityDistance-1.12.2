//package com.ydmsama.entitydistance.client.gui;
//
//import com.ydmsama.entitydistance.config.ModConfig;
//import net.minecraft.client.gui.GuiPageButtonList;
//import net.minecraft.client.gui.GuiScreen;
//import net.minecraft.client.gui.GuiSlider;
//import net.minecraft.client.gui.GuiVideoSettings;
//import net.minecraft.client.settings.GameSettings;
//
//public class CustomGuiVideoSettings extends GuiVideoSettings {
//    private GuiSlider renderDistanceSlider;
//
//    public CustomGuiVideoSettings(GuiScreen parentScreenIn, GameSettings gameSettingsIn) {
//        super(parentScreenIn, gameSettingsIn);
//    }
//
//    @Override
//    public void initGui() {
//        super.initGui();
//
//        this.renderDistanceSlider = new GuiSlider(new GuiPageButtonList.GuiResponder() {
//            @Override
//            public void setEntryValue(int id, boolean value) {
//            }
//
//            @Override
//            public void setEntryValue(int id, float value) {
//                ModConfig.renderDistanceMultiplier = value / 100.0F;
//            }
//
//            @Override
//            public void setEntryValue(int id, String value) {
//            }
//        }, 0, this.width / 2 - 155, this.height / 6 + 1 * 25, "Entity Render Distance: ", 0, 1000, (float) ModConfig.renderDistanceMultiplier * 100, null);
//
//        this.buttonList.add(this.renderDistanceSlider);
//    }
//
//    @Override
//    public void onGuiClosed() {
//        super.onGuiClosed();
//
//        ModConfig.renderDistanceMultiplier = (float) this.renderDistanceSlider.getSliderValue() / 100.0F;
//    }
//}