package com.ydmsama.entitydistance.client.gui;//package com.ydmsama.entitydistance.client.gui;
//
//import com.ydmsama.entitydistance.config.ModConfig;
//import net.minecraft.client.Minecraft;
//import net.minecraft.client.gui.GuiButton;
//import net.minecraft.client.renderer.GlStateManager;
//import net.minecraft.util.math.MathHelper;
//
//public class CustomGuiSlider extends GuiButton {
//    private float sliderValue;
//    private boolean isDragging;
//    private float valStep = 10.0F;
//
//    public CustomGuiSlider(int buttonId, int x, int y, String buttonText, float initialValue) {
//        super(buttonId, x, y, 150, 20, buttonText);
//        this.sliderValue = initialValue;
//    }
//
//    @Override
//    protected void mouseDragged(Minecraft mc, int mouseX, int mouseY) {
//        if (this.visible) {
//            if (this.isDragging) {
//                this.sliderValue = (float)(mouseX - (this.x + 4)) / (float)(this.width - 8);
//                this.sliderValue = MathHelper.clamp(this.sliderValue, 0.0F, 500.0F);
//                this.sliderValue = Math.round(this.sliderValue * valStep) / valStep;
//                // Update your config value here
//                ModConfig.renderDistanceMultiplier = this.sliderValue;
//                this.displayString = "Entity Render Distance" + this.sliderValue;
//            }
//
////            mc.getTextureManager().bindTexture(BUTTON_TEXTURES);
//            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
//            this.drawTexturedModalRect(this.x + (int)(this.sliderValue * (float)(this.width - 8)), this.y, 0, 66, 4, 20);
//            this.drawTexturedModalRect(this.x + (int)(this.sliderValue * (float)(this.width - 8)) + 4, this.y, 196, 66, 4, 20);
//        }
//    }
//
//    @Override
//    public boolean mousePressed(Minecraft mc, int mouseX, int mouseY) {
//        if (super.mousePressed(mc, mouseX, mouseY)) {
//            this.sliderValue = (float)(mouseX - (this.x + 4)) / (float)(this.width - 8);
//            this.sliderValue = MathHelper.clamp(this.sliderValue, 0.0F, 1.0F);
//            this.isDragging = true;
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    @Override
//    public void mouseReleased(int mouseX, int mouseY) {
//        this.isDragging = false;
//    }
//
//    public float getSliderValue() {
//        return this.sliderValue;
//    }
//}


import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiPageButtonList;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSlider;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class CustomGuiSlider extends GuiButton
{
    private float sliderPosition = 1.0F;
    public boolean isMouseDown;
    private final String name;
    private final float min;
    private final float max;
    private final GuiPageButtonList.GuiResponder responder;
    private GuiSlider.FormatHelper formatHelper;
    private float valStep = 50.0F;

    public CustomGuiSlider(GuiPageButtonList.GuiResponder guiResponder, int idIn, int x, int y, String nameIn, float minIn, float maxIn, float defaultValue, GuiSlider.FormatHelper formatter)
    {
        super(idIn, x, y, 150, 20, "");
        this.name = nameIn;
        this.min = minIn;
        this.max = maxIn;
        this.sliderPosition = (defaultValue - minIn) / (maxIn - minIn);
        this.formatHelper = formatter;
        this.responder = guiResponder;
        this.displayString = this.getDisplayString();
    }

    public float getSliderValue()
    {
        return this.min + (this.max - this.min) * this.sliderPosition;
    }

    public void setSliderValue(float value, boolean notifyResponder)
    {
        this.sliderPosition = (value - this.min) / (this.max - this.min);
        this.displayString = this.getDisplayString();

        if (notifyResponder)
        {
            this.responder.setEntryValue(this.id, this.getSliderValue());
        }
    }

    public float getSliderPosition()
    {
        return this.sliderPosition;
    }

    private String getDisplayString()
    {
        return this.formatHelper == null ? I18n.format(this.name) + ": " + (int)this.getSliderValue() + "%": this.formatHelper.getText(this.id, I18n.format(this.name), this.getSliderValue());
    }

    protected int getHoverState(boolean mouseOver)
    {
        return 0;
    }

    protected void mouseDragged(Minecraft mc, int mouseX, int mouseY)
    {
        if (this.visible)
        {
            if (this.isMouseDown)
            {
                this.sliderPosition = (float)(mouseX - (this.x + 4)) / (float)(this.width - 8);

                if (this.sliderPosition < 0.0F)
                {
                    this.sliderPosition = 0.0F;
                }

                if (this.sliderPosition > 1.0F)
                {
                    this.sliderPosition = 1.0F;
                }
                this.sliderPosition = roundToDecimalPlaces(Math.round(this.sliderPosition * valStep) / valStep, 2);

                this.displayString = this.getDisplayString();
                this.responder.setEntryValue(this.id, this.getSliderValue());
            }

            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            this.drawTexturedModalRect(this.x + (int)(this.sliderPosition * (float)(this.width - 8)), this.y, 0, 66, 4, 20);
            this.drawTexturedModalRect(this.x + (int)(this.sliderPosition * (float)(this.width - 8)) + 4, this.y, 196, 66, 4, 20);
        }
    }

    public float roundToDecimalPlaces(float value, int places) {
        float scale = (float) Math.pow(10, places);
        return Math.round(value * scale) / scale;
    }

    public void setSliderPosition(float position)
    {
        this.sliderPosition = position;
        this.displayString = this.getDisplayString();
        this.responder.setEntryValue(this.id, this.getSliderValue());
    }

    public boolean mousePressed(Minecraft mc, int mouseX, int mouseY)
    {
        if (super.mousePressed(mc, mouseX, mouseY))
        {
            this.sliderPosition = (float)(mouseX - (this.x + 4)) / (float)(this.width - 8);

            if (this.sliderPosition < 0.0F)
            {
                this.sliderPosition = 0.0F;
            }

            if (this.sliderPosition > 1.0F)
            {
                this.sliderPosition = 1.0F;
            }

            this.displayString = this.getDisplayString();
            this.responder.setEntryValue(this.id, this.getSliderValue());
            this.isMouseDown = true;
            return true;
        }
        else
        {
            return false;
        }
    }

    public void mouseReleased(int mouseX, int mouseY)
    {
        this.isMouseDown = false;
    }

    @SideOnly(Side.CLIENT)
    public interface FormatHelper
    {
        String getText(int id, String name, float value);
    }
}
