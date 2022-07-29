package net.Lengthrequired.emotemod.mixin;


import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.swing.text.Element;
import javax.swing.text.html.ImageView;
import java.awt.*;

import static com.ibm.icu.lang.UCharacter.GraphemeClusterBreak.T;

@Mixin(InGameHud.class)
public class InGameHudMixin {

    Identifier identifier = new Identifier("emotemod/images/download20220604192040.png");





    @Inject(at = @At("HEAD"), method = "render")
    public void render(float f) {
        RenderSystem.setShaderTexture(0, this.identifier);

    }

}
