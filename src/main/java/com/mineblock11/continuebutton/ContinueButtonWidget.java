package com.mineblock11.continuebutton;

import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;

import java.util.function.Supplier;

public class ContinueButtonWidget extends ButtonWidget {
    private final TooltipRender tooltipRender;

    public ContinueButtonWidget(int x, int y, int width, int height, PressAction onPress, TooltipRender tooltipRender) {
        super(x, y, width, height, Text.translatable("continuebutton.continueButtonTitle"), onPress, textSupplier -> Text.translatable("continuebutton.continueButtonTitle"));
        this.tooltipRender = tooltipRender;
    }

    @FunctionalInterface
    public interface TooltipRender {
        void render(ContinueButtonWidget button, MatrixStack matrixStack, int mouseX, int mouseY);
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        if (this.visible) {
            this.hovered = mouseX >= this.getX() && mouseY >= this.getY() && mouseX < this.getX() + this.width && mouseY < this.getY() + this.height;
            this.renderButton(matrices, mouseX, mouseY, delta);

            if(this.hovered) {
                tooltipRender.render(this, matrices, mouseX, mouseY);
            }
        }
    }
}
