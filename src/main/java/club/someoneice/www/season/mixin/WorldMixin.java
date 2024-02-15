package club.someoneice.www.season.mixin;

import club.someoneice.www.season.season.Seasons;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(World.class)
public class WorldMixin {
    @Inject(method = "canSnowAtBody", at = @At("HEAD"), cancellable = true)
    public void setSnow(int x, int y, int z, boolean isServer, CallbackInfoReturnable<Boolean> cir) {
        if (Seasons.getSeasonByWorld((World) (Object) this) == Seasons.WINTER) cir.setReturnValue(true);
    }
}
