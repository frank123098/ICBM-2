package com.builtbroken.icbm.content.blast.fragment;

import com.builtbroken.icbm.content.fragments.EntityFragment;
import com.builtbroken.icbm.content.fragments.FragmentType;
import com.builtbroken.mc.api.explosive.IExplosiveHandler;
import com.builtbroken.mc.core.Engine;
import com.builtbroken.mc.core.network.packet.callback.PacketBlast;
import com.builtbroken.mc.imp.transform.rotation.EulerAngle;
import com.builtbroken.mc.imp.transform.vector.Pos;
import com.builtbroken.mc.prefab.explosive.blast.BlastBasic;
import net.minecraft.item.Item;
import net.minecraft.world.World;

/**
 * @see <a href="https://github.com/BuiltBrokenModding/VoltzEngine/blob/development/license.md">License</a> for what you can and can't do with the code.
 * Created by Dark(DarkGuardsman, Robert) on 12/17/2016.
 */
public class BlastFragments extends BlastBasic<BlastFragments>
{
    public static final double START_VELOCITY = 2.0;
    public FragBlastType blastType;

    public BlastFragments(IExplosiveHandler handler, FragBlastType type)
    {
        super(handler);
        this.blastType = type;
        eUnitPerBlock = 2F;
    }

    @Override
    public void doEffectOther(boolean beforeBlocksPlaced)
    {
        super.doEffectOther(beforeBlocksPlaced);
        if (!beforeBlocksPlaced)
        {
            final Pos center = new Pos(x, y, z);
            spawnFragments(oldWorld, center, (int) size, blastType.fragmentType, blastType.material);
        }
    }

    /**
     * Spawns fragments from a location in all directions
     *
     * @param world
     * @param center
     * @param size
     * @param type
     */
    public static void spawnFragments(final World world, final Pos center, int size, FragmentType type, Item item)
    {
        //TODO implement version with restricted directions
        //If this is changed update item description so users are not confused
        int rotations = Math.min(256, size); //Default of 1 = 5 rotations or 25 fragments
        double degrees = 360 / rotations;
        for (int yaw = 0; yaw < rotations; yaw++)
        {
            for (int pitch = 0; pitch < rotations; pitch++)
            {
                //Reduces number of fragments and provides a small random in distributions
                if (world.rand.nextBoolean())
                {
                    EulerAngle rotation = new EulerAngle(yaw * degrees + (world.rand.nextFloat() * 2), pitch * degrees + (world.rand.nextFloat() * 2));
                    Pos velocity = rotation.toPos().multiply(START_VELOCITY + world.rand.nextFloat());
                    Pos pos = center.add(rotation.toPos()).addRandom(world.rand, 0.2);
                    if (pos.isAirBlock(world)) //TODO add proper collision check
                    {
                        EntityFragment fragment = new EntityFragment(world, type, item);
                        fragment.setPosition(pos.x(), pos.y(), pos.z());

                        //Random chance for fragment to be on fire
                        if (fragment.getFragmentType() == FragmentType.BLOCK && world.rand.nextBoolean())
                        {
                            //TODO introduce better chance for some material types
                            //TODO disable burning on some materials like obsidian
                            fragment.setFire(3 + world.rand.nextInt(60));
                        }

                        //Motion
                        fragment.motionX = velocity.x();
                        fragment.motionY = velocity.y();
                        fragment.motionZ = velocity.z();

                        world.spawnEntityInWorld(fragment);
                    }
                }
            }
        }
    }

    @Override
    public void doStartDisplay()
    {
        Engine.packetHandler.sendToAllAround(new PacketBlast(this, PacketBlast.BlastPacketType.PRE_BLAST_DISPLAY), this, 400);
    }

    @Override
    public void doEndDisplay()
    {
        Engine.packetHandler.sendToAllAround(new PacketBlast(this, PacketBlast.BlastPacketType.POST_BLAST_DISPLAY), this, 400);
    }
}

