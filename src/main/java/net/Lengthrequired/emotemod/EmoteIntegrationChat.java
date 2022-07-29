package net.Lengthrequired.emotemod;


import com.mojang.blaze3d.systems.RenderSystem;
import net.Lengthrequired.emotemod.mixin.EmoteModChatMixin;
import net.Lengthrequired.emotemod.mixin.EmoteModMixin;
import net.minecraft.client.gui.screen.Screen;


import net.minecraft.client.gui.screen.ingame.AnvilScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;



public class EmoteIntegrationChat extends Screen{

    private final Screen parent;
    private final GameOptions settings;

    public EmoteIntegrationChat(Screen parent, GameOptions gOptions) {
        super(Text.literal("Spot Mod"));
        this.parent = parent;
        this.settings = gOptions;

    }



    protected void init(){


        int m = this.height /2-75 ;

        int i= 0;
        String[] emotes = CommonVariable.emotes;
        int length = emotes.length;
        for (int n=0;n<4;n++){
        for (int j=0;j<4;j++){
            String emote = emotes[i];
            this.addDrawableChild(new ButtonWidget(this.width /2 -75 +j*30+j*15*2,m+22+ n *30,50,20,Text.of(emote),(buttonWidget) -> {

                this.client.keyboard.setClipboard(emote);

            }));
            i++;
        }
        }
        this.addDrawableChild(new ButtonWidget(this.width /2 -150,m+22,50,20,Text.of("Back"),(buttonWidget) -> {

            this.client.setScreen(this.parent);


        }));
    }





}



