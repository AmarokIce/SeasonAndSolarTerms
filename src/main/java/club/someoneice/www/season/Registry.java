package club.someoneice.www.season;

import club.someoneice.www.season.season.Seasons;
import net.minecraft.block.IGrowable;
import net.minecraft.init.Blocks;

public class Registry {
    public static final CropManager WHEAT = CropManager.registerCropManager((IGrowable) Blocks.wheat);
    public static final CropManager CARROT = CropManager.registerCropManager((IGrowable) Blocks.carrots);
    public static final CropManager POTATO = CropManager.registerCropManager((IGrowable) Blocks.potatoes);

    public static final CropManager PUMPKIN = CropManager.registerCropManager((IGrowable) Blocks.pumpkin_stem);
    public static final CropManager MELON = CropManager.registerCropManager((IGrowable) Blocks.melon_stem);

    public static void init() {
        registerCropManager();
    }

    private static void registerCropManager() {
        WHEAT.set(0, new Seasons[] { Seasons.SPRING, Seasons.WINTER }, new Seasons[] { Seasons.SUMMER }, 0.00f, 1.0f);
        WHEAT.set(1, new Seasons[] { Seasons.SPRING, Seasons.SUMMER, Seasons.AUTUMN, Seasons.WINTER }, new Seasons[] {}, -0.10f, 1.3f);
        WHEAT.set(5, new Seasons[] { Seasons.SPRING, Seasons.AUTUMN }, new Seasons[] {}, -0.20f, 0.7f);
        WHEAT.set(6, new Seasons[] { Seasons.SPRING, Seasons.AUTUMN }, new Seasons[] { Seasons.SUMMER }, -0.30f, 0.7f);

        CARROT.set(0, new Seasons[] { Seasons.SPRING, Seasons.AUTUMN }, new Seasons[] {}, -0.35f, 1.2f);

        POTATO.set(0, new Seasons[] { Seasons.SPRING, Seasons.SUMMER, Seasons.AUTUMN, Seasons.WINTER }, new Seasons[] {}, -0.45f, 1.8f);



        PUMPKIN.set(0, new Seasons[] { Seasons.SPRING }, new Seasons[] { Seasons.WINTER }, 0.16f, 1.0f);
        PUMPKIN.set(2, new Seasons[] { Seasons.SPRING }, new Seasons[] {}, 0.16f, 1.0f);

        MELON.set(0, new Seasons[] { Seasons.SUMMER }, new Seasons[] { Seasons.AUTUMN, Seasons.WINTER }, 0.26f, 1.5f);


    }
}
