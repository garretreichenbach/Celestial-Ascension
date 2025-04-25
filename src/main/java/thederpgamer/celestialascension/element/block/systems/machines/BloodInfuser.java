package thederpgamer.celestialascension.element.block.systems.machines;

import org.schema.game.common.data.SegmentPiece;
import org.schema.game.common.data.element.ElementCategory;
import thederpgamer.celestialascension.element.block.ISensorOutput;
import thederpgamer.celestialascension.element.block.systems.SystemBlock;

/**
 * [Description]
 *
 * @author TheDerpGamer
 */
public class BloodInfuser extends SystemBlock implements ISensorOutput {

	protected BloodInfuser(String name, ElementCategory category) {
		super(name, category);
	}

	@Override
	public void initialize() {

	}

	@Override
	public void createTextures() {

	}

	@Override
	public float getSensorOutput(SegmentPiece block) {
		return 0;
	}
}
