/**
 * Created by Alireza Khodakarami on 9/17/2014.
 */

package com.gollumteam.morepistons.client.gui;

import com.gollumteam.morepistons.handler.ConfigurationHandler;
import com.gollumteam.morepistons.reference.Reference;
import com.gollumteam.morepistons.utility.Lang;
import cpw.mods.fml.client.config.GuiConfig;
import cpw.mods.fml.client.config.IConfigElement;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.ConfigElement;

import java.util.ArrayList;
import java.util.List;

public class ModGuiConfig extends GuiConfig
{
    public ModGuiConfig(GuiScreen guiScreenScreen)
    {
        super(guiScreenScreen, getConfigElements(guiScreenScreen),
                Reference.MOD_ID,
                false,
                false,
                GuiConfig.getAbridgedConfigPath(ConfigurationHandler.configuration.toString()));
    }

    private static List<IConfigElement> getConfigElements(GuiScreen parent)
    {
        List<IConfigElement> list = new ArrayList<IConfigElement>();
        String prefix = Lang.prefix + "config.";
        for (ConfigurationHandler.Section section : ConfigurationHandler.Sections)
        {
            list.add(new ConfigElement<ConfigCategory>
                    (ConfigurationHandler.configuration.getCategory(section.lc())
                            .setLanguageKey(prefix+section.lang)));
        }
        return list;
    }
}
