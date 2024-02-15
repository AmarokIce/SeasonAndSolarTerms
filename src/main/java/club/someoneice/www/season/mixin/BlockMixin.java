package club.someoneice.www.season.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockGrass;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Block.class)
public class BlockMixin {
    @Inject(method = "onBlockActivated", at = @At("HEAD"))
    public void onActivated(World world, int x, int y, int z, EntityPlayer player, int side, float fx, float fy, float fz, CallbackInfoReturnable<Boolean> cir) {
        Block block = (Block) (Object) this;
        if (block instanceof BlockGrass) {
            player.addChatMessage(new ChatComponentText("Hello from GrassBlock"));
        }
    }
}
