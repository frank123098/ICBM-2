package com.builtbroken.icbm.api;

import com.builtbroken.icbm.content.crafting.AbstractModule;
import net.minecraft.item.ItemStack;
import resonant.lib.mod.AbstractMod;

/**
 * Created by robert on 12/28/2014.
 */
public interface IModuleItem
{
    /** Gets the module that this item represents */
    public AbstractModule getModule(ItemStack stack);
}