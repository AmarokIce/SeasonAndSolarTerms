package club.someoneice.www.season.mixin;

import club.someoneice.www.season.CropManager;
import club.someoneice.www.season.season.Season;
import net.minecraft.block.BlockCrops;
import net.minecraft.init.Blocks;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(BlockCrops.class)
public class CropMixin {
    @Inject(method = "updateTick", at = @At("HEAD"), remap = false, cancellable = true)
    public void onGrow(World world, int x, int y, int z, Random random, CallbackInfo ci) {
        BlockCrops thiz = (BlockCrops) (Object) this;
        if (!CropManager.CROP_MANAGER_MAP.containsKey(thiz)) return;
        CropManager manager = CropManager.CROP_MANAGER_MAP.get(thiz);
        int age = world.getBlockMetadata(x, y, z);
        float temperature = Season.calculateTemperature(world, new ChunkPosition(x, y, z));
        if (!manager.checkCanLive(age, temperature)) {
            if (random.nextDouble() > 0.25) ci.cancel();
            world.setBlock(x, y, z, Blocks.deadbush);
            world.notifyBlockChange(x, y, z, Blocks.deadbush);
        }
        if (!manager.canGrow(age, temperature, Season.getSeasonByWorld(world))) ci.cancel();
    }
}
