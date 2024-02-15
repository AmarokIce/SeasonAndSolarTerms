package club.someoneice.www.season.util;

import club.someoneice.www.season.CropManager;
import club.someoneice.www.season.block.BlackFarmland;
import club.someoneice.www.season.season.Seasons;
import club.someoneice.www.season.season.SolarTerms;
import net.minecraft.block.BlockCrops;
import net.minecraft.init.Blocks;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;

import java.util.Random;

public class CropHook {
    public static boolean growWeeds(World world, ChunkPosition pos, Random random) {
        if (world.getBlock(pos.chunkPosX, pos.chunkPosY - 1, pos.chunkPosZ) instanceof BlackFarmland) return false;

        int day = Seasons.getDayInYear(world);

        boolean SpringFlag = day >= SolarTerms.BEGINNING_OF_SPRING && day < SolarTerms.SPRING_EQUINOX;
        boolean SummerFlag = day >= SolarTerms.BEGINNING_OF_SUMMER && day < SolarTerms.SUMMER_SOLSTICE;
        boolean AutumnFlag = day >= SolarTerms.BEGINNING_OF_AUTUMN && day < SolarTerms.COLD_DEW;
        boolean WinterFlag = day >= SolarTerms.BEGINNING_OP_WINTER && day <= SolarTerms.MAJOR_COLD;

        if (SpringFlag || WinterFlag) {
            if (random.nextDouble() < 0.08) {
                world.setBlock(pos.chunkPosX, pos.chunkPosY, pos.chunkPosZ, Blocks.tallgrass, 2, 3);
                return true;
            }
        }

        if (AutumnFlag || SummerFlag) {
            if (random.nextDouble() < 0.04) {
                world.setBlock(pos.chunkPosX, pos.chunkPosY, pos.chunkPosZ, Blocks.tallgrass);
                return true;
            }
        }

        return false;
    }

    public static boolean canGrow(World world, ChunkPosition pos, Random random) {
        BlockCrops crop = (BlockCrops) world.getBlock(pos.chunkPosX, pos.chunkPosY, pos.chunkPosZ);

        if (!CropManager.CROP_MANAGER_MAP.containsKey(crop)) return false;
        CropManager manager = CropManager.CROP_MANAGER_MAP.get(crop);

        int age = world.getBlockMetadata(pos.chunkPosX, pos.chunkPosY, pos.chunkPosZ);
        float temperature = Seasons.calculateTemperature(world, pos);
        if (manager.checkCanLive(age, temperature)) return !manager.canGrow(age, temperature, Seasons.getSeasonByWorld(world));
        if (random.nextDouble() > 0.25) return true;
        world.setBlock(pos.chunkPosX, pos.chunkPosY, pos.chunkPosZ, Blocks.deadbush);
        world.notifyBlockChange(pos.chunkPosX, pos.chunkPosY, pos.chunkPosZ, Blocks.deadbush);
        return true;
    }
}
