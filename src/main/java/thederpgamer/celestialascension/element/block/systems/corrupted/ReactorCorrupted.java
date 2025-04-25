package thederpgamer.celestialascension.element.block.systems.corrupted;

import api.utils.element.Blocks;
import org.schema.game.common.data.element.ElementCategory;
import thederpgamer.celestialascension.element.block.systems.SystemBlock;
import thederpgamer.celestialascension.manager.ConfigManager;

/**
 * [Description]
 *
 * @author TheDerpGamer
 */
public class ReactorCorrupted extends SystemBlock {

	public ReactorCorrupted() {
		super("Reactor Power (Corrupted)", new ElementCategory("Corrupted Systems", Blocks.REACTOR_POWER.getInfo().type));
	}

	@Override
	public void initialize() {
		blockInfo.setDescription("A corrupted reactor block that has been tainted by dark and otherworldly energies.\n" +
				"Compatible with regular reactor blocks, but provides extra energy output at the cost of consuming Stellarium fuel.\n\n" +
				"+" + (ConfigManager.getSystemsConfig().getDouble("corrupted_reactor_extra_output") * 100.0f) + "% extra power output per block\n" +
				"+" + ConfigManager.getSystemsConfig().getDouble("corrupted_reactor_fuel_consumption") + "u/s fuel consumption\n" +
				"+" + (ConfigManager.getSystemsConfig().getDouble("corrupted_reactor_corruption") * 100.0f) + "% corruption\n\n" +
				"Using too many of these can lead to dangerous and unpredictable consequences...");
		blockInfo.setInRecipe(false);
		blockInfo.setShoppable(false);
		blockInfo.setPlacable(true);
		blockInfo.volume = Blocks.REACTOR_POWER.getInfo().volume;
		blockInfo.mass = Blocks.REACTOR_POWER.getInfo().mass;
	}

	@Override
	public void createTextures() {
	}
}