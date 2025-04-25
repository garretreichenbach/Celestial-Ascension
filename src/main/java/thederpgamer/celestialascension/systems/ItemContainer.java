package thederpgamer.celestialascension.systems;

import org.json.JSONObject;

/**
 * [Description]
 *
 * @author TheDerpGamer
 */
public class ItemContainer implements IElementContainer {

	private static final byte VERSION = 0;
	private boolean flagUpdate;
	private ContainerType containerType;
	protected short storedType;
	protected int storedCount;

	public ItemContainer(ContainerType containerType) {
		this.containerType = containerType;
	}

	public ItemContainer(JSONObject json) {
		fromJSON(json);
	}

	@Override
	public ContainerType getContainerType() {
		return null;
	}

	@Override
	public boolean needsUpdate() {
		return false;
	}

	@Override
	public void flagUpdate() {

	}

	@Override
	public JSONObject toJSON() {
		return null;
	}

	@Override
	public void fromJSON(JSONObject jsonObject) {

	}
}
