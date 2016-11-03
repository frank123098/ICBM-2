package com.builtbroken.icbm.content.rail.powered;

import com.builtbroken.icbm.ICBM;
import com.builtbroken.mc.prefab.tile.Tile;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * @see <a href="https://github.com/BuiltBrokenModding/VoltzEngine/blob/development/license.md">License</a> for what you can and can't do with the code.
 * Created by Dark(DarkGuardsman, Robert) on 10/30/2016.
 */
public class TilePowerRailClient extends TilePowerRail
{
    private static IIcon main;
    private static IIcon[] arrow1;
    private static IIcon[] arrow2;
    private static IIcon[] arrow3;
    private static IIcon[] arrow4;

    @Override
    public Tile newTile()
    {
        return new TilePowerRailClient();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister)
    {
        main = iconRegister.registerIcon(ICBM.PREFIX + "PowerRailBody");

        arrow1 = new IIcon[]{
                iconRegister.registerIcon(ICBM.PREFIX + "PowerRailArrowUp"),
                iconRegister.registerIcon(ICBM.PREFIX + "PowerRailArrowUpClockwise"),
                iconRegister.registerIcon(ICBM.PREFIX + "PowerRailArrowUpAClockwise")};
        arrow2 = new IIcon[]{
                iconRegister.registerIcon(ICBM.PREFIX + "PowerRailArrowDown"),
                iconRegister.registerIcon(ICBM.PREFIX + "PowerRailArrowDownClockwise"),
                iconRegister.registerIcon(ICBM.PREFIX + "PowerRailArrowDownAClockwise")};
        arrow3 = new IIcon[]{
                iconRegister.registerIcon(ICBM.PREFIX + "PowerRailArrowLeft"),
                iconRegister.registerIcon(ICBM.PREFIX + "PowerRailArrowLeftClockwise"),
                iconRegister.registerIcon(ICBM.PREFIX + "PowerRailArrowLeftAClockwise")};
        arrow4 = new IIcon[]{
                iconRegister.registerIcon(ICBM.PREFIX + "PowerRailArrowRight"),
                iconRegister.registerIcon(ICBM.PREFIX + "PowerRailArrowRightClockwise"),
                iconRegister.registerIcon(ICBM.PREFIX + "PowerRailArrowRightAClockwise")};
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
        if (this != block.staticTile)
        {
            if (side == getAttachedDirection().ordinal())
            {
                if (getAttachedDirection() == ForgeDirection.UP)
                {
                    if (getFacingDirection() == ForgeDirection.NORTH)
                    {
                        return isPoweredRail() ? arrow1[0] : rotateClockwise ? arrow1[1] : arrow1[2];
                    }
                    else if (getFacingDirection() == ForgeDirection.EAST)
                    {
                        return isPoweredRail() ? arrow4[0] : rotateClockwise ? arrow4[1] : arrow4[2];
                    }
                    else if (getFacingDirection() == ForgeDirection.SOUTH)
                    {
                        return isPoweredRail() ? arrow2[0] : rotateClockwise ? arrow2[1] : arrow2[2];
                    }
                    else if (getFacingDirection() == ForgeDirection.WEST)
                    {
                        return isPoweredRail() ? arrow3[0] : rotateClockwise ? arrow3[1] : arrow3[2];
                    }
                }
                else if (getAttachedDirection() == ForgeDirection.DOWN)
                {
                    if (getFacingDirection() == ForgeDirection.NORTH)
                    {
                        return isPoweredRail() ? arrow1[0] : rotateClockwise ? arrow1[1] : arrow1[2];
                    }
                    else if (getFacingDirection() == ForgeDirection.EAST)
                    {
                        return isPoweredRail() ? arrow3[0] : rotateClockwise ? arrow3[1] : arrow3[2];
                    }
                    else if (getFacingDirection() == ForgeDirection.SOUTH)
                    {
                        return isPoweredRail() ? arrow2[0] : rotateClockwise ? arrow2[1] : arrow2[2];
                    }
                    else if (getFacingDirection() == ForgeDirection.WEST)
                    {
                        return isPoweredRail() ? arrow4[0] : rotateClockwise ? arrow4[1] : arrow4[2];
                    }
                }
                else if (getAttachedDirection() == ForgeDirection.EAST)
                {
                    if (getFacingDirection() == ForgeDirection.NORTH)
                    {
                        return isPoweredRail() ? arrow4[0] : rotateClockwise ? arrow4[1] : arrow4[2];
                    }
                    else if (getFacingDirection() == ForgeDirection.UP)
                    {
                        return isPoweredRail() ? arrow1[0] : rotateClockwise ? arrow1[1] : arrow1[2];
                    }
                    else if (getFacingDirection() == ForgeDirection.SOUTH)
                    {
                        return isPoweredRail() ? arrow3[0] : rotateClockwise ? arrow3[1] : arrow3[2];
                    }
                    else if (getFacingDirection() == ForgeDirection.DOWN)
                    {
                        return isPoweredRail() ? arrow2[0] : rotateClockwise ? arrow2[1] : arrow2[2];
                    }
                }
                else if (getAttachedDirection() == ForgeDirection.WEST)
                {
                    if (getFacingDirection() == ForgeDirection.NORTH)
                    {
                        return isPoweredRail() ? arrow3[0] : rotateClockwise ? arrow3[1] : arrow3[2];
                    }
                    else if (getFacingDirection() == ForgeDirection.UP)
                    {
                        return isPoweredRail() ? arrow1[0] : rotateClockwise ? arrow1[1] : arrow1[2];
                    }
                    else if (getFacingDirection() == ForgeDirection.SOUTH)
                    {
                        return isPoweredRail() ? arrow4[0] : rotateClockwise ? arrow4[1] : arrow4[2];
                    }
                    else if (getFacingDirection() == ForgeDirection.DOWN)
                    {
                        return isPoweredRail() ? arrow2[0] : rotateClockwise ? arrow2[1] : arrow2[2];
                    }
                }
                else if (getAttachedDirection() == ForgeDirection.NORTH)
                {
                    if (getFacingDirection() == ForgeDirection.EAST)
                    {
                        return isPoweredRail() ? arrow3[0] : rotateClockwise ? arrow3[1] : arrow3[2];
                    }
                    else if (getFacingDirection() == ForgeDirection.UP)
                    {
                        return isPoweredRail() ? arrow1[0] : rotateClockwise ? arrow1[1] : arrow1[2];
                    }
                    else if (getFacingDirection() == ForgeDirection.WEST)
                    {
                        return isPoweredRail() ? arrow4[0] : rotateClockwise ? arrow4[1] : arrow4[2];
                    }
                    else if (getFacingDirection() == ForgeDirection.DOWN)
                    {
                        return isPoweredRail() ? arrow2[0] : rotateClockwise ? arrow2[1] : arrow2[2];
                    }
                }
                else if (getAttachedDirection() == ForgeDirection.SOUTH)
                {
                    if (getFacingDirection() == ForgeDirection.WEST)
                    {
                        return isPoweredRail() ? arrow3[0] : rotateClockwise ? arrow3[1] : arrow3[2];
                    }
                    else if (getFacingDirection() == ForgeDirection.UP)
                    {
                        return isPoweredRail() ? arrow1[0] : rotateClockwise ? arrow1[1] : arrow1[2];
                    }
                    else if (getFacingDirection() == ForgeDirection.EAST)
                    {
                        return isPoweredRail() ? arrow4[0] : rotateClockwise ? arrow4[1] : arrow4[2];
                    }
                    else if (getFacingDirection() == ForgeDirection.DOWN)
                    {
                        return isPoweredRail() ? arrow2[0] : rotateClockwise ? arrow2[1] : arrow2[2];
                    }
                }
            }
        }
        //Item renderer
        else if (side == 1)
        {
            return meta == 1 ? arrow4[0] : arrow4[1];
        }
        return main;
    }

    @Override
    public void readDescPacket(ByteBuf buf)
    {
        type = buf.readInt();
        setFacingDirection(ForgeDirection.getOrientation(buf.readInt()));
        if (isRotationRail())
        {
            rotateToAngle = buf.readBoolean();
            rotateClockwise = buf.readBoolean();
            rotateYaw = buf.readInt();
            world().markBlockRangeForRenderUpdate(xi(), yi(), zi(), xi(), yi(), zi());
        }
    }
}