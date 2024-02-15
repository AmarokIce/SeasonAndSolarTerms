package club.someoneice.www.season.event;

import club.someoneice.www.season.season.Seasons;
import club.someoneice.www.season.season.SolarTerms;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.block.BlockColored;
import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import net.minecraft.world.storage.WorldInfo;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.client.event.RenderBlockOverlayEvent;
import net.minecraftforge.client.event.RenderWorldEvent;
import net.minecraftforge.fluids.RenderBlockFluid;

public class WorldEvent {
    @SubscribeEvent
    public void worldTick(TickEvent.WorldTickEvent event) {
        World world = event.world;
        if (world.isRemote || world.provider.dimensionId != 0) return;
        int day = Seasons.getDayInYear(world);
        if (day >= SolarTerms.RAIN_WATER && day <= SolarTerms.GRAIN_RAIN) springRain(world);
        else if (Seasons.getSeasonByWorld(world) == Seasons.SUMMER) summerRain(world);
    }

    private void springRain(World world) {
        if (world.rand.nextDouble() < 0.65d) return;
        int i = world.rand.nextInt(24000);
        WorldInfo worldInfo = world.getWorldInfo();
        worldInfo.setRainTime(i);
        worldInfo.setRaining(true);
        worldInfo.setThundering(false);
    }

    private void summerRain(World world) {
        if (world.rand.nextDouble() < 0.3d) return;
        int i = world.rand.nextInt(24000);
        WorldInfo worldInfo = world.getWorldInfo();
        worldInfo.setRainTime(i);
        worldInfo.setRaining(true);
        worldInfo.setThundering(world.rand.nextBoolean());
    }
}
