package club.someoneice.www.season;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = SeasonMain.MODID, name = SeasonMain.NAME, version = SeasonMain.VERSION)
public class SeasonMain {
    public static final String MODID = "seasons";
    public static final String NAME = "Season & Solar Terms";
    public static final String VERSION = "0.0.1";
    public static final Logger LOG = LogManager.getLogger(NAME);

    @Mod.Instance(MODID)
    public static SeasonMain INSTANCE = new SeasonMain();

    @Mod.EventHandler
    public void commonInit(FMLInitializationEvent event) {
        Registry.init();
    }
}
