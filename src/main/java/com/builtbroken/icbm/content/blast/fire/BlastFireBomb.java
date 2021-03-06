package com.builtbroken.icbm.content.blast.fire;

import com.builtbroken.mc.api.edit.IWorldEdit;
import com.builtbroken.mc.api.explosive.IExplosiveHandler;
import com.builtbroken.mc.data.Direction;
import com.builtbroken.mc.imp.transform.vector.BlockPos;
import com.builtbroken.mc.imp.transform.vector.Location;
import com.builtbroken.mc.lib.world.edit.BlockEdit;
import com.builtbroken.mc.prefab.explosive.blast.BlastSimplePath;
import net.minecraft.init.Blocks;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * Blast that creates a very small area of fire at the target.
 *
 * @see <a href="https://github.com/BuiltBrokenModding/VoltzEngine/blob/development/license.md">License</a> for what you can and can't do with the code.
 * Created by Dark(DarkGuardsman, Robert) on 11/6/2015.
 */
public class BlastFireBomb extends BlastSimplePath<BlastFireBomb>
{
    public BlastFireBomb(IExplosiveHandler handler)
    {
        super(handler);
    }

    @Override
    public BlockEdit changeBlock(BlockPos location)
    {
        //TODO spawn random fire particle that can set fire to blocks up to 20 away
        if (location.isAirBlock(oldWorld))
        {
            Location loc = new Location(oldWorld, location).sub(0, 1, 0);
            if (!loc.isAirBlock() && loc.isSideSolid(ForgeDirection.UP))
            {
                BlockEdit edit = new BlockEdit(oldWorld, location);
                edit.set(Blocks.fire, 0, false, true);
                return edit;
            }
        }
        return null;
    }

    @Override
    public boolean shouldPathTo(BlockPos last, BlockPos next, Direction dir)
    {
        if (super.shouldPathTo(last, next, dir))
        {
            if (last.isAirBlock(oldWorld) && next.isAirBlock(oldWorld))
            {
                return dir != Direction.DOWN.UP;
            }
            return true;
        }
        return false;
    }

    @Override
    public void doStartDisplay()
    {
        //Mainly just to disable default effects
    }

    @Override
    public void doEndDisplay()
    {
        //Mainly just to disable default effects
    }

    @Override
    public void displayEffectForEdit(IWorldEdit blocks)
    {
        if (!oldWorld.isRemote)
        {
            world.spawnParticle("lava", blocks.x(), blocks.y(), blocks.z(), 0.0D, 0.0D, 0.0D);
        }
    }

    @Override
    public void playAudioForEdit(IWorldEdit blocks)
    {
        if (!oldWorld.isRemote)
        {
            oldWorld.playSoundEffect(blocks.x(), blocks.y(), blocks.z(), "liquid.lavapop", 0.2F + oldWorld.rand.nextFloat() * 0.2F, 0.9F + oldWorld.rand.nextFloat() * 0.15F);
        }
    }
}
