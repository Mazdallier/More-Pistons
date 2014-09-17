/**
 * Created by Alireza Khodakarami on 9/17/2014.
 */

package com.gollumteam.morepistons;

import com.gollumteam.morepistons.handler.ConfigurationHandler;
import com.gollumteam.morepistons.reference.Reference;
import com.gollumteam.morepistons.utility.Log;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.init.Blocks;

@Mod(modid = Reference.MOD_ID,name = Reference.MOD_NAME, version = Reference.VERSION, guiFactory = Reference.GUI_FACTORY_CLASS)
public class MorePistons 
{
    //public static final GenericCreativeTab MorePistonsTab = new GenericCreativeTab(Reference.MOD_ID);

    @Mod.Instance(Reference.MOD_ID)
    public static MorePistons Instance;

    //@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    //public static IProxy proxy;

    @Mod.EventHandler
    public void PreInit(FMLPreInitializationEvent event)
    {
        ConfigurationHandler.init(event.getSuggestedConfigurationFile());
        FMLCommonHandler.instance().bus().register(new ConfigurationHandler());
        //ModBlocks.init();
        Log.info("Pre Initialization Complete");
    }

    @Mod.EventHandler
    public void Init(FMLInitializationEvent event)
    {
        //MorePistonsTab.setIcon(Blocks.piston);
        //ModEntities.init();
        //ModRecipes.init();
        //proxy.registerRenderers();
        Log.info("Initialization Complete");
    }

    @Mod.EventHandler
    public void PostInit(FMLPostInitializationEvent event)
    {
        //if (Loader.isModLoaded("NotEnoughItems"))
            //NEICompat.load();
        Log.info("Post Initialization Complete");
    }
}
