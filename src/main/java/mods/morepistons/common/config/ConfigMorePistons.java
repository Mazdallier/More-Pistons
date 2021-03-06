package mods.morepistons.common.config;

import mods.gollum.core.common.config.Config;
import mods.gollum.core.common.config.ConfigProp;

public class ConfigMorePistons extends Config {

	@ConfigProp(mcRestart = true) public boolean overrideVanillaPiston = true;
	@ConfigProp(mcRestart = true) public boolean overrideVanillaStickPiston = true;
	
	@ConfigProp(minValue="0", mcRestart = true) public int numberMovableBlockWithDefaultPiston = 12;
	@ConfigProp(minValue="0", mcRestart = true) public int numberMovableBlockWithSuperPiston   = 41;
	
	@ConfigProp(minValue="0.1", mcRestart = true) public double powerGravitationalPistons = 1.5D;
	
}
