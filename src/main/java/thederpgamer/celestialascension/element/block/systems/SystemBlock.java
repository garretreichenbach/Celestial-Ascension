package thederpgamer.celestialascension.element.block.systems;

import org.schema.game.common.data.element.ElementCategory;
import org.schema.game.common.data.element.ElementKeyMap;
import thederpgamer.celestialascension.element.block.Block;

/**
 * [Description]
 *
 * @author TheDerpGamer
 */
public abstract class SystemBlock extends Block {

	protected SystemBlock(String name, ElementCategory category) {
		super(name, category);
	}

	public void addControlledBy(short id) {
		blockInfo.getControlledBy().add(id);
		ElementKeyMap.getInfo(id).getControlling().add(blockInfo.getId());
	}

	public void removeControlledBy(short id) {
		blockInfo.getControlledBy().remove(id);
		ElementKeyMap.getInfo(id).getControlling().remove(blockInfo.getId());
	}

	public void addControlling(short id) {
		blockInfo.getControlling().add(id);
		ElementKeyMap.getInfo(id).getControlledBy().add(blockInfo.getId());
	}

	public void removeControlling(short id) {
		blockInfo.getControlling().remove(id);
		ElementKeyMap.getInfo(id).getControlledBy().remove(blockInfo.getId());
	}
}
