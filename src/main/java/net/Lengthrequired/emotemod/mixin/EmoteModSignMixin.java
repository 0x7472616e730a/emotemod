package net.Lengthrequired.emotemod.mixin;

import net.Lengthrequired.emotemod.CommonVariable;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.SignEditScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SignEditScreen.class)
public class EmoteModSignMixin extends Screen {
    protected EmoteModSignMixin(Text title) {
        super(title);
    }
    @Inject(at = @At("TAIL"), method = "init()V")
    private void init(CallbackInfo ci) {
        int m = this.height /2-100 ;

        int p= 0;
        String[] emotes = CommonVariable.emotes;
        int length = emotes.length;
        for (int n=0;n<4;n++){
            for (int o=0;o<4;o++){
                String emote = emotes[p];
                this.addDrawableChild(new ButtonWidget(this.width /2 -400 +o*30+o*15*2,m+22+ n *30,50,20,Text.of(emote),(buttonWidget) -> {

                    this.client.keyboard.setClipboard(emote);

                }));
                p++;
            }
        }


    }



}
