package com.ydmsama.entitydistance.client.gui;

import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CustomGameSettings {
    @SideOnly(Side.CLIENT)
    public static enum myOptions
    {
        ENTITY_RENDER_DISTANCE("entityrenderdistance", true, false, 0.0F, 500.0F, 10.0F),
        ENTITY_TRACK_DISTANCE("entitytrackdistance", true, false, 100.0F, 500.0F, 10.0F);

        private final boolean isFloat;
        private final boolean isBoolean;
        private final String translation;
        private final float valueStep;
        private float valueMin;
        private float valueMax;

        public static myOptions byOrdinal(int ordinal)
        {
            for (myOptions gamesettings$options : values())
            {
                if (gamesettings$options.getOrdinal() == ordinal)
                {
                    return gamesettings$options;
                }
            }

            return null;
        }

        private myOptions(String translation, boolean isFloat, boolean isBoolean)
        {
            this(translation, isFloat, isBoolean, 0.0F, 1.0F, 0.0F);
        }

        private myOptions(String translation, boolean isFloat, boolean isBoolean, float valMin, float valMax, float valStep)
        {
            this.translation = translation;
            this.isFloat = isFloat;
            this.isBoolean = isBoolean;
            this.valueMin = valMin;
            this.valueMax = valMax;
            this.valueStep = valStep;
        }

        public boolean isFloat()
        {
            return this.isFloat;
        }

        public boolean isBoolean()
        {
            return this.isBoolean;
        }

        public int getOrdinal()
        {
            return this.ordinal();
        }

        public String getTranslation()
        {
            return this.translation;
        }

        public float getValueMin()
        {
            return this.valueMin;
        }

        public float getValueMax()
        {
            return this.valueMax;
        }

        public void setValueMax(float value)
        {
            this.valueMax = value;
        }

        public float normalizeValue(float value)
        {
            return MathHelper.clamp((this.snapToStepClamp(value) - this.valueMin) / (this.valueMax - this.valueMin), 0.0F, 1.0F);
        }

        public float denormalizeValue(float value)
        {
            return this.snapToStepClamp(this.valueMin + (this.valueMax - this.valueMin) * MathHelper.clamp(value, 0.0F, 1.0F));
        }

        public float snapToStepClamp(float value)
        {
            value = this.snapToStep(value);
            return MathHelper.clamp(value, this.valueMin, this.valueMax);
        }

        private float snapToStep(float value)
        {
            if (this.valueStep > 0.0F)
            {
                value = this.valueStep * (float)Math.round(value / this.valueStep);
            }

            return value;
        }
    }

}
