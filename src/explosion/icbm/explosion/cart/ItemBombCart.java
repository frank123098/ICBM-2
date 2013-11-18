package icbm.explosion.cart;

import icbm.core.base.ItemICBMBase;
import icbm.explosion.missile.Explosive;
import icbm.explosion.missile.ExplosiveRegistry;

import java.util.List;

import net.minecraft.block.BlockRailBase;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBombCart extends ItemICBMBase
{
	public ItemBombCart(int id)
	{
		super(id, "minecart");
		this.setMaxStackSize(1);
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
	}

	/**
	 * Callback for item usage. If the item does something special on right clicking, he will have
	 * one of those. Return True if something happen and false if it don't. This is for ITEMS, not
	 * BLOCKS
	 */
	@Override
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int x, int y, int z, int par7, float par8, float par9, float par10)
	{
		int var11 = par3World.getBlockId(x, y, z);

		if (BlockRailBase.isRailBlock(var11))
		{
			if (!par3World.isRemote)
			{
				par3World.spawnEntityInWorld(new EntityBombCart(par3World, x + 0.5F, y + 0.5F, z + 0.5F, par1ItemStack.getItemDamage()));
			}

			--par1ItemStack.stackSize;
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public int getMetadata(int damage)
	{
		return damage;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister iconRegister)
	{
		this.itemIcon = iconRegister.registerIcon("minecart_tnt");
	}

	@Override
	public String getUnlocalizedName(ItemStack itemstack)
	{
		return "icbm.minecart." + ExplosiveRegistry.get(itemstack.getItemDamage()).getUnlocalizedName();
	}

	@Override
	public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List)
	{
		for (Explosive zhaPin : ExplosiveRegistry.getAllZhaPin())
		{
			if (zhaPin.hasMinecartForm())
			{
				par3List.add(new ItemStack(par1, 1, zhaPin.getID()));

			}
		}
	}
}