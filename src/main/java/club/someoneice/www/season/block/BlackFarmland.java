package club.someoneice.www.season.block;

import club.someoneice.www.init.Tags;
import club.someoneice.www.season.SeasonMain;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockFarmland;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;

public class BlackFarmland extends BlockFarmland {
    @SideOnly(Side.CLIENT)
    private IIcon top;

    public BlackFarmland() {
        Tags.DIRT_TAG.put(this);
        this.setBlockName("black_farmland");
        GameRegistry.registerBlock(this, "black_farmland");
    }

    @Override
    public void registerBlockIcons(IIconRegister register) {
        this.top = register.registerIcon(SeasonMain.MODID + ":black_farmland");
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        return side == 1 ? top : Blocks.dirt.getBlockTextureFromSide(side);
    }
}
