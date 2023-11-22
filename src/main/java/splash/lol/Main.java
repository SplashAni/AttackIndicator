package splash.lol;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import net.fabricmc.api.ModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.Entity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import splash.lol.impl.events.RenderEvent;
import splash.lol.impl.events.TickEvent;

import java.awt.*;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

public class Main implements ModInitializer {

    public static final Logger LOGGER = LoggerFactory.getLogger("attackindicator");
    public static final EventBus EVENT_BUS = new EventBus();
    public MinecraftClient mc = MinecraftClient.getInstance();

    @Override
    public void onInitialize() {

        EVENT_BUS.register(this);

        LOGGER.info("big ballz pro");
    }

    AtomicBoolean canRender = new AtomicBoolean();

    @Subscribe
    public void tick(TickEvent event) {

        if (mc.world == null || mc.player == null) return;

        Item item = mc.player.getMainHandStack().getItem();


        if (!(item instanceof SwordItem ||
                item instanceof AxeItem) ||
                mc.player.getAttackCooldownProgress(0.5f) < 1 ||
                !lookingAtEntity()) {

            canRender.set(false);
            return;
        }


        canRender.set(true);

    }

    public boolean lookingAtEntity() {
        HitResult result = mc.crosshairTarget;

        if (Objects.requireNonNull(result).getType() != HitResult.Type.ENTITY) return false;

        Entity entity = ((EntityHitResult) result).getEntity();

        return entity.isAttackable() && entity.isAlive();
    }

    @Subscribe
    public void render(RenderEvent event) {

        if (!canRender.get() || mc.gameRenderer == null) return;

        DrawContext context = event.getContext();


        /*CHANGE IF NEEDED*/

        int start = new Color(0, 255, 0, 110).getRGB();
        int end = new Color(24, 85, 213, 255).getRGB();

        context.fillGradient(0, 0, context.getScaledWindowWidth(), context.getScaledWindowHeight(), start, end);

    }
}