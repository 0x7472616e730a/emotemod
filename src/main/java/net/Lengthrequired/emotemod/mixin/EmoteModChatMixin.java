package net.Lengthrequired.emotemod.mixin;


import net.Lengthrequired.emotemod.CommonVariable;
import net.Lengthrequired.emotemod.EmoteIntegrationChat;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.LockButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.util.telemetry.TelemetrySender;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;

import static net.Lengthrequired.emotemod.CommonVariable.emotes;

@Mixin(ChatScreen.class)
public class EmoteModChatMixin extends Screen {

    private int nbleft = 0;
    private int nbright = 4;
    private ArrayList<ButtonWidget> buttons = new ArrayList<>();
    boolean hrt = false;


    protected EmoteModChatMixin(Text title) {
        super(title);
    }


    @Inject(method = "tick",
            at = @At(
                    value="TAIL"
            ),cancellable = true)
    private void render(CallbackInfo ci){
        if(!hrt) {
            this.addDrawableChild(new ButtonWidget(5, this.height - 35, 20, 20, Text.of("◀"), (buttonWidget) -> {

                if (!(nbleft == 0)) {
                    nbleft -= 4;
                    nbright -= 4;
                    clearChildren();
                    setEmoteList(nbleft, nbright);
                    hrt = false;
                }

            }));
            setEmoteList(nbleft, nbright);

            this.addDrawableChild(new ButtonWidget(25 + 240, this.height - 35, 20, 20, Text.of("▶"), (buttonWidget) -> {
                if (!(emotes.length - nbright < 4)) {
                    nbleft += 4;
                    nbright += 4;
                    clearChildren();
                    setEmoteList(nbleft, nbright);
                    hrt=false;
                } else {
                    clearChildren();
                    nbleft += 4;
                    nbright = emotes.length;
                    setEmoteList(nbleft, nbright);
                    hrt=false;
                }

            }));
        }
        hrt = true;
    }

    public void setEmoteList(int nbstart,int nbend){

        int p= nbstart;
        String[] emotes = CommonVariable.emotes;
        int length = emotes.length;
        if (nbend%4!=0 && length-nbstart<4){
            for (int o=0;o<length-nbstart;o++){
                String emote = emotes[p];
                this.addDrawableChild(new ButtonWidget(25 +o*60,this.height-35,60,20,Text.of(emote),(buttonWidget) -> {

                    this.insertText(emote,false);

                }));
                p++;
            }

    }else{
            for (int o=0;o<4;o++){
                String emote = emotes[p];
                this.addDrawableChild(new ButtonWidget(25 +o*60,this.height-35,60,20,Text.of(emote),(buttonWidget) -> {

                    this.insertText(emote,false);

                }));
                p++;
            }
        }
    }






}
