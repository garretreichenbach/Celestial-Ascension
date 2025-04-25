package thederpgamer.celestialascension.element.block.systems.chambers;

import org.schema.game.common.data.element.ElementCategory;
import thederpgamer.celestialascension.element.block.Block;

public abstract class ChamberBlock extends Block {

	protected ChamberBlock(String name, ElementCategory category) {
		super(name, category);
	}

	public void addChildren(ChamberBlock... children) {
		for(ChamberBlock child : children) {
			child.blockInfo.chamberParent = getId();
			child.blockInfo.chamberPrerequisites.add(getId());
			blockInfo.chamberChildren.add(child.getId());
		}
	}

	public void setUpgrade(ChamberBlock upgrade) {
		addChildren(upgrade);
		upgrade.blockInfo.chamberMutuallyExclusive.addAll(blockInfo.chamberMutuallyExclusive);
		blockInfo.chamberUpgradesTo = upgrade.getId();
	}

	public void addExclusives(ChamberBlock... exclusives) {
		for(ChamberBlock exclusive : exclusives) {
			exclusive.blockInfo.chamberMutuallyExclusive.add(getId());
			blockInfo.chamberMutuallyExclusive.add(exclusive.getId());
		}
	}
}
