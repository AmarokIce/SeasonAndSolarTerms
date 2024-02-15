package club.someoneice.www.season.mixin;

import club.someoneice.www.season.season.Seasons;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.BlockGrass;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.world.IBlockAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.awt.*;

@Mixin(BlockGrass.class)
public class GrassBlockMixin {
    @Inject(method = "colorMultiplier", at = @At("HEAD"), cancellable = true)
    public void colorMap(IBlockAccess block, int x, int y, int z, CallbackInfoReturnable<Integer> cir) {
        int v1, v2;

        WorldClient mcWorld = Minecraft.getMinecraft().theWorld;
        Seasons season = Seasons.getSeasonByWorld(mcWorld);
        if (season == Seasons.AUTUMN) {
            v1 = Color.YELLOW.getRGB();
            v2 = new Color(255, 215, 0).getRGB();
        } else if (season == Seasons.WINTER) {
            v1 = Color.LIGHT_GRAY.getRGB();
            v2 = Color.GRAY.getRGB();
        } else return;

        int l = 0;
        int i1 = 0;
        int j1 = 0;

        for (int k1 = -1; k1 <= 1; ++k1) {
            for (int l1 = -1; l1 <= 1; ++l1) {
                int i2 = block.getBiomeGenForCoords(x + l1, z + k1).getBiomeGrassColor(x + l1, y, z + k1);
                l += (i2 & v1) >> 16;
                i1 += (i2 & v2) >> 8;
                j1 += i2 & 255;
            }
        }

        cir.setReturnValue((l / 9 & 255) << 16 | (i1 / 9 & 255) << 8 | j1 / 9 & 255);
    }

}
