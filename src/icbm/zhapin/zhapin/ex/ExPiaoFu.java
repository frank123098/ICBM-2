package icbm.zhapin.zhapin.ex;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import icbm.core.MICBM;
import icbm.core.SheDing;
import icbm.zhapin.baozha.bz.BzPiaoFu;
import icbm.zhapin.muoxing.daodan.MMPiaoFu;
import icbm.zhapin.zhapin.daodan.DaoDan;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraftforge.oredict.ShapedOreRecipe;
import universalelectricity.prefab.RecipeHelper;

public class ExPiaoFu extends DaoDan
{
	public ExPiaoFu(String mingZi, int tier)
	{
		super(mingZi, tier);
	}

	@Override
	public void init()
	{
		RecipeHelper.addRecipe(new ShapedOreRecipe(this.getItemStack(), new Object[] { "EEE", "ETE", "EEE", 'T', tui.getItemStack(), 'E', Item.eyeOfEnder }), this.getUnlocalizedName(), SheDing.CONFIGURATION, true);
	}

	@Override
	public void doCreateExplosion(World world, double x, double y, double z, Entity entity)
	{
		new BzPiaoFu(world, entity, x, y, z, 30).explode();
	}

	@SideOnly(Side.CLIENT)
	@Override
	public MICBM getMuoXing()
	{
		return new MMPiaoFu();
	}
}