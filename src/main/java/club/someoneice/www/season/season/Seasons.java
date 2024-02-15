package club.someoneice.www.season.season;

import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;

import java.util.Random;

public enum Seasons {
    SPRING(0.1f),
    SUMMER(0.3f),
    AUTUMN(-0.15f),
    WINTER(-0.5f);

    public final float temperature;

    Seasons(float temperature) {
        this.temperature = temperature;
    }



    public static float calculateTemperature(World world, ChunkPosition pos) {
        Seasons season = getSeasonByWorld(world);
        return world.getBiomeGenForCoords(pos.chunkPosX, pos.chunkPosZ).getFloatTemperature(pos.chunkPosX, pos.chunkPosY, pos.chunkPosZ) + season.temperature + dailyTemperature(world, season);
    }

    public static Seasons getSeasonByInt(int i) {
         switch (i) {
             case 1: { return SPRING; }
             case 2: { return SUMMER; }
             case 3: { return AUTUMN; }
             case 4: { return WINTER; }
        }

        return SPRING;
    }

    public static Seasons getSeasonByWorld(World world) {
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

    public static float dailyTemperature(final World world, final Seasons season) {
        Random random = new Random(world.getSeed() + getYear(world) + getDayInYear(world));
        switch (season) {
            case SPRING: {
                return (float) (0.458f * random.nextDouble());
            }

            case SUMMER: {
                return (float) (0.6f * random.nextDouble());
            }

            case AUTUMN: {
                return (float) (-0.2f * random.nextDouble());
            }

            case WINTER: {
                return (float) (-0.5f * random.nextDouble());
            }
        }
        return 0.0f;
    }
}
