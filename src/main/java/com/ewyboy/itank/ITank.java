package com.ewyboy.itank;

import com.ewyboy.itank.common.compatibilities.CompatibilityHandler;
import com.ewyboy.itank.common.loaders.BlockLoader;
import com.ewyboy.itank.common.loaders.ConfigLoader;
import com.ewyboy.itank.common.loaders.RecipeLoader;
import com.ewyboy.itank.common.utility.Logger;
import com.ewyboy.itank.common.utility.Reference;
import com.ewyboy.itank.proxy.CommonProxy;
import com.google.common.base.Stopwatch;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.util.concurrent.TimeUnit;

/** Created by EwyBoy **/
@Mod(modid = Reference.ModInfo.MODID, name = Reference.ModInfo.MODID, version = Reference.ModInfo.BUILD_VERSION)
public class ITank {

    @Mod.Instance(Reference.ModInfo.MODID)
    public static ITank instance;

    @SidedProxy(modId = Reference.ModInfo.MODID, clientSide = Reference.Path.clientProxyPath, serverSide = Reference.Path.commonProxyPath)
    public static CommonProxy proxy;

    public static Configuration configuration;

    private long launchTime;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        Stopwatch watch = Stopwatch.createStarted();
            Logger.info("Pre-Initialization started");
                CompatibilityHandler.registerWaila();
                ConfigLoader.init(event.getSuggestedConfigurationFile());
                BlockLoader.loadBlocks();
                proxy.loadModels();
                proxy.loadItemModels();
                launchTime += watch.elapsed(TimeUnit.MILLISECONDS);
            Logger.info("Pre-Initialization finished after " + watch.elapsed(TimeUnit.MILLISECONDS) + "ms");
        Logger.info("Pre-Initialization process successfully done");
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        Stopwatch watch = Stopwatch.createStarted();
            Logger.info("Initialization started");
                RecipeLoader.registerRecipes();
                launchTime += watch.elapsed(TimeUnit.MILLISECONDS);
            Logger.info("Initialization finished after " + watch.elapsed(TimeUnit.MILLISECONDS) + "ms");
        Logger.info("Initialization process successfully done");
    }

    @Mod.EventHandler
    public void PostInit(FMLPostInitializationEvent event) {
        Stopwatch watch = Stopwatch.createStarted();
            Logger.info("Post-Initialization started");
                launchTime += watch.elapsed(TimeUnit.MILLISECONDS);
            Logger.info("Post-Initialization finished after " + watch.elapsed(TimeUnit.MILLISECONDS) + "ms");
            Logger.info("Post-Initialization process successfully done");
        Logger.info("Total Initialization time was " + launchTime);
    }
}