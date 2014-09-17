/**
 * Created by Alireza Khodakarami on 9/17/2014.
 */

package com.gollumteam.morepistons.handler;

import com.gollumteam.morepistons.reference.Configurations;
import com.gollumteam.morepistons.reference.Reference;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.config.Configuration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ConfigurationHandler
{
    public static class Section
    {
        public final String name;
        public final String lang;

        public Section(String name, String lang)
        {
            this.name = name;
            this.lang = lang;
            register();
        }

        private void register()
        {
            Sections.add (this);
        }

        public String lc()
        {
            return name.toLowerCase();
        }
    }

    public static final List<Section> Sections;

    static
    {
        Sections = new ArrayList<Section>();
    }

    public static Configuration configuration;

    public static final Section SectionGlobalPistons = new Section ("Global","global");
    public static final Section SectionNormalPistons = new Section("Normal Pistons","normal");
    public static final Section SectionNormalStickyPistons = new Section ("Normal Sticky Pistons","normalsticky");
    public static final Section SectionSuperPistons = new Section ("Super Pistons","super");
    public static final Section SectionSuperStickyPistons = new Section ("Super Sticky Pistons","supersticky");
    public static final Section SectionGraviPistons = new Section ("Gravitational Pistons","gravitational");


    public static void init(File configFile)
    {
        // Create the configuration object from the given configuration file
        if (configuration == null)
        {
            configuration = new Configuration(configFile);
            loadConfiguration();
        }
    }

    @SubscribeEvent
    public void OnConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event )
    {
        if (event.modID.equalsIgnoreCase(Reference.MOD_ID))
        {
            loadConfiguration();
        }
    }

    private static void loadConfiguration()
    {
        // Read in properties from configuration file
        // Values
        Configurations.GRAVI_POWER = configuration.get(SectionGlobalPistons.name,
                "4 -Gravitationa Piston Power",
                Configurations.GRAVI_POWER,
                "This is the power that gravitational piston uses to push things upward, default is 1.5")
                    .getDouble(Configurations.GRAVI_POWER);
        Configurations.SUPER_PISTON_ROW = configuration.get(SectionGlobalPistons.name,
                "5 -Super Max Block Move",
                Configurations.SUPER_PISTON_ROW,
                "This is the number of blocks in front of super pistons that it would move when powered, default is 24 (not the distance but n number of blocks will be moved).")
                    .getInt(Configurations.SUPER_PISTON_ROW);

        // Normal Pistons
        Configurations.NORMAL_PISTONS = configuration.get(SectionGlobalPistons.name,
                "1 -Enable Normal Pistons",
                Configurations.NORMAL_PISTONS,
                "Set to false to disable all normal other than vanilla pistons in game, this will override any settings you have bellow.")
                    .getBoolean(Configurations.NORMAL_PISTONS);
        Configurations.DOUBLE_PISTON = configuration.get(SectionNormalPistons.name,
                "1 - Enable Double Pistons",
                Configurations.DOUBLE_PISTON,
                "Set to false to disable the double pistons in game")
                    .getBoolean(Configurations.DOUBLE_PISTON);
        Configurations.TRIPLE_PISTON = configuration.get(SectionNormalPistons.name,
                "2 - Enable Triple Pistons",
                Configurations.TRIPLE_PISTON,
                "Set to false to disable the triple pistons in game")
                    .getBoolean(Configurations.TRIPLE_PISTON);
        Configurations.QUADRUPLE_PISTON = configuration.get(SectionNormalPistons.name,
                "3 - Enable Quadruple Pistons",
                Configurations.QUADRUPLE_PISTON,
                "Set to false to disable the quadruple pistons in game")
                    .getBoolean(Configurations.QUADRUPLE_PISTON);
        Configurations.QUINTUPLE_PISTON = configuration.get(SectionNormalPistons.name,
                "4 - Enable Quintuple Pistons",
                Configurations.QUINTUPLE_PISTON,
                "Set to false to disable the quintuple pistons in game")
                    .getBoolean(Configurations.QUINTUPLE_PISTON);
        Configurations.SEXTUPLE_PISTON = configuration.get(SectionNormalPistons.name,
                "5 - Enable Sextuple Pistons",
                Configurations.SEXTUPLE_PISTON,
                "Set to false to disable the sextuple pistons in game")
                    .getBoolean(Configurations.SEXTUPLE_PISTON);
        Configurations.SEPTUPLE_PISTON = configuration.get(SectionNormalPistons.name,
                "6 - Enable Septuple Pistons",
                Configurations.SEPTUPLE_PISTON,
                "Set to false to disable the septuple pistons in game")
                    .getBoolean(Configurations.SEPTUPLE_PISTON);
        Configurations.OCTUPLE_PISTON = configuration.get(SectionNormalPistons.name,
                "7 - Enable Octuple Pistons",
                Configurations.OCTUPLE_PISTON,
                "Set to false to disable the octuple pistons in game")
                    .getBoolean(Configurations.OCTUPLE_PISTON);
        // Normal Sticky Pistons
        Configurations.DOUBLE_STICKY_PISTON = configuration.get(SectionNormalStickyPistons.name,
                "1 - Enable Double Sticky Pistons",
                Configurations.DOUBLE_STICKY_PISTON,
                "Set to false to disable the double sticky pistons in game")
                    .getBoolean(Configurations.DOUBLE_STICKY_PISTON);
        Configurations.TRIPLE_STICKY_PISTON = configuration.get(SectionNormalStickyPistons.name,
                "2 - Enable Triple Sticky Pistons",
                Configurations.TRIPLE_STICKY_PISTON,
                "Set to false to disable the triple sticky pistons in game")
                    .getBoolean(Configurations.TRIPLE_STICKY_PISTON);
        Configurations.QUADRUPLE_STICKY_PISTON = configuration.get(SectionNormalStickyPistons.name,
                "3 - Enable Quadruple Sticky Pistons",
                Configurations.QUADRUPLE_STICKY_PISTON,
                "Set to false to disable the quadruple sticky pistons in game")
                    .getBoolean(Configurations.QUADRUPLE_STICKY_PISTON);
        Configurations.QUINTUPLE_STICKY_PISTON = configuration.get(SectionNormalStickyPistons.name,
                "4 - Enable Quintuple Sticky Pistons",
                Configurations.QUINTUPLE_STICKY_PISTON,
                "Set to false to disable the quintuple sticky pistons in game")
                    .getBoolean(Configurations.QUINTUPLE_STICKY_PISTON);
        Configurations.SEXTUPLE_STICKY_PISTON = configuration.get(SectionNormalStickyPistons.name,
                "5 - Enable Sextuple Sticky Pistons",
                Configurations.SEXTUPLE_STICKY_PISTON,
                "Set to false to disable the sextuple sticky pistons in game")
                    .getBoolean(Configurations.SEXTUPLE_STICKY_PISTON);
        Configurations.SEPTUPLE_STICKY_PISTON = configuration.get(SectionNormalStickyPistons.name,
                "6 - Enable Septuple Sticky Pistons",
                Configurations.SEPTUPLE_STICKY_PISTON,
                "Set to false to disable the septuple sticky pistons in game")
                    .getBoolean(Configurations.SEPTUPLE_STICKY_PISTON);
        Configurations.OCTUPLE_STICKY_PISTON = configuration.get(SectionNormalStickyPistons.name,
                "7 - Enable Octuple Sticky Pistons",
                Configurations.OCTUPLE_STICKY_PISTON,
                "Set to false to disable the octuple sticky pistons in game")
                    .getBoolean(Configurations.OCTUPLE_STICKY_PISTON);
        // Super Pistons
        Configurations.SUPER_PISTONS = configuration.get(SectionGlobalPistons.name,
                "2 - Enable Super Pistons",
                Configurations.SUPER_PISTONS,
                "Set to false to disable all super pistons in game, this will override any settings you have bellow.")
                    .getBoolean(Configurations.SUPER_PISTONS);
        Configurations.SUPER_PISTON = configuration.get(SectionSuperPistons.name,
                "1 - Enable Super Piston",
                Configurations.SUPER_PISTON,
                "Set to false to disable the super pistons (not the whole category) in game")
                    .getBoolean(Configurations.SUPER_PISTON);
        Configurations.DOUBLE_SUPER_PISTON = configuration.get(SectionSuperPistons.name,
                "2 - Enable Double Super Pistons",
                Configurations.DOUBLE_SUPER_PISTON,
                "Set to false to disable the double super pistons in game")
                    .getBoolean(Configurations.DOUBLE_SUPER_PISTON);
        Configurations.TRIPLE_SUPER_PISTON = configuration.get(SectionSuperPistons.name,
                "3 - Enable Triple Super Pistons",
                Configurations.TRIPLE_SUPER_PISTON,
                "Set to false to disable the triple super pistons in game")
                    .getBoolean(Configurations.TRIPLE_SUPER_PISTON);
        Configurations.QUADRUPLE_SUPER_PISTON = configuration.get(SectionSuperPistons.name,
                "4 - Enable Quadruple Super Pistons",
                Configurations.QUADRUPLE_SUPER_PISTON,
                "Set to false to disable the quadruple super pistons in game")
                    .getBoolean(Configurations.QUADRUPLE_SUPER_PISTON);
        Configurations.QUINTUPLE_SUPER_PISTON = configuration.get(SectionSuperPistons.name,
                "5 - Enable Quintuple Super Pistons",
                Configurations.QUINTUPLE_SUPER_PISTON,
                "Set to false to disable the quintuple super pistons in game")
                    .getBoolean(Configurations.QUINTUPLE_SUPER_PISTON);
        Configurations.SEXTUPLE_SUPER_PISTON = configuration.get(SectionSuperPistons.name,
                "6 - Enable Sextuple Super Pistons",
                Configurations.SEXTUPLE_SUPER_PISTON,
                "Set to false to disable the sextuple super pistons in game")
                    .getBoolean(Configurations.SEXTUPLE_SUPER_PISTON);
        Configurations.SEPTUPLE_SUPER_PISTON = configuration.get(SectionSuperPistons.name,
                "7 - Enable Septuple Super Pistons",
                Configurations.SEPTUPLE_SUPER_PISTON,
                "Set to false to disable the septuple super pistons in game")
                    .getBoolean(Configurations.SEPTUPLE_SUPER_PISTON);
        Configurations.OCTUPLE_SUPER_PISTON = configuration.get(SectionSuperPistons.name,
                "8 - Enable Octuple Super Pistons",
                Configurations.OCTUPLE_SUPER_PISTON,
                "Set to false to disable the octuple super pistons in game")
                    .getBoolean(Configurations.OCTUPLE_SUPER_PISTON);
        // Super Sticky Pistons
        Configurations.SUPER_STICKY_PISTON = configuration.get(SectionSuperStickyPistons.name,
                "1 - Enable Super Sticky Piston",
                Configurations.SUPER_STICKY_PISTON,
                "Set to false to disable the super sticky pistons (not the whole category) in game")
                    .getBoolean(Configurations.SUPER_STICKY_PISTON);
        Configurations.DOUBLE_SUPER_STICKY_PISTON = configuration.get(SectionSuperStickyPistons.name,
                "2 - Enable Double Super Sticky Pistons",
                Configurations.DOUBLE_SUPER_STICKY_PISTON,
                "Set to false to disable the double super sticky pistons in game")
                    .getBoolean(Configurations.DOUBLE_SUPER_STICKY_PISTON);
        Configurations.TRIPLE_SUPER_STICKY_PISTON = configuration.get(SectionSuperStickyPistons.name,
                "3 - Enable Triple Super Sticky Pistons",
                Configurations.TRIPLE_SUPER_STICKY_PISTON,
                "Set to false to disable the triple super sticky pistons in game")
                    .getBoolean(Configurations.TRIPLE_SUPER_STICKY_PISTON);
        Configurations.QUADRUPLE_SUPER_STICKY_PISTON = configuration.get(SectionSuperStickyPistons.name,
                "4 - Enable Quadruple Super Sticky Pistons",
                Configurations.QUADRUPLE_SUPER_STICKY_PISTON,
                "Set to false to disable the super sticky quadruple pistons in game")
                    .getBoolean(Configurations.QUADRUPLE_SUPER_STICKY_PISTON);
        Configurations.QUINTUPLE_SUPER_STICKY_PISTON = configuration.get(SectionSuperStickyPistons.name,
                "5 - Enable Quintuple Super Sticky Pistons",
                Configurations.QUINTUPLE_SUPER_STICKY_PISTON,
                "Set to false to disable the super sticky quintuple pistons in game")
                    .getBoolean(Configurations.QUINTUPLE_SUPER_STICKY_PISTON);
        Configurations.SEXTUPLE_SUPER_STICKY_PISTON = configuration.get(SectionSuperStickyPistons.name,
                "6 - Enable Sextuple Super Sticky Pistons",
                Configurations.SEXTUPLE_SUPER_STICKY_PISTON,
                "Set to false to disable the super sticky sextuple pistons in game")
                    .getBoolean(Configurations.SEXTUPLE_SUPER_STICKY_PISTON);
        Configurations.SEPTUPLE_SUPER_STICKY_PISTON = configuration.get(SectionSuperStickyPistons.name,
                "7 - Enable Septuple Super Sticky Pistons",
                Configurations.SEPTUPLE_SUPER_STICKY_PISTON,
                "Set to false to disable the super sticky septuple pistons in game")
                    .getBoolean(Configurations.SEPTUPLE_SUPER_STICKY_PISTON);
        Configurations.OCTUPLE_SUPER_STICKY_PISTON = configuration.get(SectionSuperStickyPistons.name,
                "8 - Enable Octuple Super Sticky Pistons",
                Configurations.OCTUPLE_SUPER_STICKY_PISTON,
                "Set to false to disable the super sticky octuple pistons in game")
                    .getBoolean(Configurations.OCTUPLE_SUPER_STICKY_PISTON);

        // Gravitation Pistons
        Configurations.GRAVITATION_PISTON = configuration.get(SectionGraviPistons.name,
                "1 - Enable Gravitational Pistons",
                Configurations.GRAVITATION_PISTON,
                "Set to false to disable the gravitational pistons in game")
                    .getBoolean(Configurations.GRAVITATION_PISTON);
        Configurations.GRAVITATION_STICKY_PISTON = configuration.get(SectionGraviPistons.name,
                "2 - Enable Gravitational Sticky Pistons",
                Configurations.GRAVITATION_STICKY_PISTON,
                "Set to false to disable the sticky gravitational pistons in game")
                    .getBoolean(Configurations.GRAVITATION_STICKY_PISTON);
        // Save the configuration file
        if (configuration.hasChanged())
            configuration.save();
    }
}
