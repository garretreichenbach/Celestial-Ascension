package thederpgamer.celestialascension.element.block.containers;

import api.utils.element.Blocks;
import org.schema.game.common.data.SegmentPiece;
import thederpgamer.celestialascension.element.block.ISensorOutput;
import thederpgamer.celestialascension.element.block.systems.SystemBlock;

import java.util.Set;

/**
 * [Description]
 *
 * @author TheDerpGamer
 */
public class FluidTank extends SystemBlock implements IFluidContainer, ISensorOutput {

	protected FluidTank() {
		super("Fluid Tank", Blocks.STORAGE.getInfo().type);
	}

	@Override
	public void initialize() {
		blockInfo.setDescription("A versatile tank capable of holding a large amount of fluids.\n" +
				"Placing multiple tanks next to each other will combine them, increasing the total storage capacity.\n" +
				"Using pipes, you can configure fluids to be pumped in and out of these tanks.\n\nFluid Tanks can only hold one type of fluid per group.");
	}

	@Override
	public void createTextures() {

	}

	@Override
	public float getSensorOutput(SegmentPiece block) {
		return 0;
	}

	@Override
	public Set<IFluidHolder> getFluidHolders(SegmentPiece containerBlock) {
		assert containerBlock.getType() == blockInfo.id : new IllegalArgumentException("Invalid block type, expected: " + blockInfo.id + ", found: " + containerBlock.getType());

	}
}
