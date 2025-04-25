package thederpgamer.celestialascension.element.block;

import api.config.BlockConfig;
import org.schema.game.common.data.element.ElementCategory;
import org.schema.game.common.data.element.ElementInformation;
import thederpgamer.celestialascension.CelestialAscension;

public abstract class Block {

	protected ElementInformation blockInfo;

	protected Block(String name, ElementCategory category) {
		blockInfo = BlockConfig.newElement(CelestialAscension.getInstance(), name, new short[6]);
		BlockConfig.setElementCategory(blockInfo, category);
	}

	public final ElementInformation getBlockInfo() {
		return blockInfo;
	}

	public final short getId() {
		return blockInfo.getId();
	}

	public abstract void initialize();

	public abstract void createTextures();
}