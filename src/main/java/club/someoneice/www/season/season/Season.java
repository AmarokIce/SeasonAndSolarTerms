package club.someoneice.www.season.season;

import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;

public enum Season {
    SPRING(0.1f),
    SUMMER(0.6f),
    AUTUMN(-0.15f),
    WINTER(-0.5f);

    public final float temperature;

    Season(float temperature) {
        this.temperature = temperature;
    }



    public static float calculateTemperature(World world, ChunkPosition pos) {
        return world.getBiomeGenForCoords(pos.chunkPosX, pos.chunkPosZ).getFloatTemperature(pos.chunkPosX, pos.chunkPosY, pos.chunkPosZ) + getSeasonByWorld(world).temperature;
    }

    public static Season getSeasonByInt(int i) {
         switch (i) {
             case 1: { return SPRING; }
             case 2: { return SUMMER; }
             case 3: { return AUTUMN; }
             case 4: { return WINTER; }
        }

        return SPRING;
    }

    public static Season getSeasonByWorld(World world) {
        return getSeasonByInt(getDayInYear(world) / 30 + 1);
    }

    public static int getDay(World world) {
        return (int) (world.getWorldTime() % 24000);
    }

    public static int getDayInYear(World world) {
        return (getDay(world) + 1) % 120;
    }

    public static int getYear(World world) {
        return (getDay(world) + 1) / 120;
    }
}
