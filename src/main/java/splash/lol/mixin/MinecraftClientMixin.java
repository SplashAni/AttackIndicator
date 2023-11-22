package splash.lol.mixin;

import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import splash.lol.Event.TickEvent;
import splash.lol.Main;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {

    @Inject(method = "tick", at = @At("HEAD"))
    public void tick(CallbackInfo ci) {
        Main.EVENT_BUS.post(new TickEvent());
    }

}
