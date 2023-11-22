package splash.lol;

import com.google.common.eventbus.EventBus;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main implements ModInitializer {

    public static final Logger LOGGER = LoggerFactory.getLogger("attackindicator");
    public static final EventBus EVENT_BUS = new EventBus();

    @Override
    public void onInitialize() {

        EVENT_BUS.register(this);

        LOGGER.info("big ballz pro");
    }
}