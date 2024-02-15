package club.someoneice.www.season.event;

import club.someoneice.www.season.CropManager;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.IGrowable;
import net.minecraftforge.event.entity.player.BonemealEvent;

public class CropEvent {
    @SubscribeEvent
    public void onBonemealUsing(BonemealEvent event) {
        if (event.block instanceof IGrowable && CropManager.CROP_MANAGER_MAP.containsKey((IGrowable) event.block)) event.setCanceled(true);
    }
}
