package club.someoneice.www.season.mixin;

import club.someoneice.www.init.Tags;
import club.someoneice.www.season.util.CropHook;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(BlockCrops.class)
public class CropMixin {
    @Inject(method = "canPlaceBlockOn", at = @At("HEAD"), cancellable = true)
    public void dirt(Block block, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(Tags.DIRT_TAG.has(block));
    }

    @Inject(method = "updateTick", at = @At("HEAD"), remap = false, cancellable = true)
    public void onGrow(World world, int x, int y, int z, Random random, CallbackInfo ci) {
        ChunkPosition pos = new ChunkPosition(x, y, z);
        if (CropHook.growWeeds(world, pos, random)) ci.cancel();
        if (CropHook.canGrow(world, pos, random)) ci.cancel();
    }
}
