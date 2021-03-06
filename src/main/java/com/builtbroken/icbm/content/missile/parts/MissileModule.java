package com.builtbroken.icbm.content.missile.parts;

import com.builtbroken.icbm.api.modules.IMissileModule;
import com.builtbroken.icbm.content.missile.entity.EntityMissile;
import com.builtbroken.mc.prefab.module.AbstractModule;
import net.minecraft.item.ItemStack;

/**
 * Prefab for any module that can fit on a missile
 * Created by robert on 12/28/2014.
 */
public class MissileModule extends AbstractModule implements IMissileModule
{
    /**
     * Default constructor
     *
     * @param item - item the module is create out of
     * @param name - name of the module
     */
    public MissileModule(ItemStack item, String name)
    {
        super(item, name);
    }

    @Override
    public String getSaveID()
    {
        return MissileModuleBuilder.INSTANCE.getID(this);
    }

    @Override
    public void update(EntityMissile missile)
    {

    }

    @Override
    public int getMissileSize()
    {
        return -1;
    }

    @Override
    public String getUnlocalizedName()
    {
        return "module.icbm:" + getName();
    }
}
