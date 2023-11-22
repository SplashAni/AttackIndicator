package splash.lol.impl.events;

import net.minecraft.client.gui.DrawContext;

public class RenderEvent {
    DrawContext context;
    public RenderEvent(DrawContext context){
        this.context = context;
    }

    public DrawContext getContext() {
        return context;
    }
}
