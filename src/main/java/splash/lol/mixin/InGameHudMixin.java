package splash.lol.mixin;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import splash.lol.Event.RenderEvent;
import splash.lol.Main;

@Mixin(InGameHud.class)
public class InGameHudMixin {

    @Inject(method = "render", at = @At("HEAD"))
    public void render(DrawContext context, float tickDelta, CallbackInfo ci) {
        Main.EVENT_BUS.post(new RenderEvent(context));
    }

}
