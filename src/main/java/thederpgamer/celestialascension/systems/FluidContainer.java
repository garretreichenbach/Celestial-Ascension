package thederpgamer.celestialascension.systems;

import org.json.JSONObject;

/**
 * [Description]
 * 
 * @author TheDerpGamer
 */
public class FluidContainer implements IElementContainer {

	private static final byte VERSION = 0;
	private boolean flagUpdate;
	private ContainerType containerType;
	protected float capacity;
	protected float stored;

	public FluidContainer(ContainerType containerType, float capacity) {
		this.containerType = containerType;
		this.capacity = capacity;
	}

	public FluidContainer(JSONObject json) {
		fromJSON(json);
	}

	@Override
	public ContainerType getContainerType() {
		return containerType;
	}

	@Override
	public boolean needsUpdate() {
		return flagUpdate;
	}

	@Override
	public void flagUpdate() {
		flagUpdate = true;
	}

	@Override
	public JSONObject toJSON() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("version", VERSION);
		jsonObject.put("flag_update", flagUpdate);
		jsonObject.put("container_type", containerType.toString());
		jsonObject.put("capacity", capacity);
		jsonObject.put("stored", stored);
		return jsonObject;
	}

	@Override
	public void fromJSON(JSONObject jsonObject) {
		byte version = (byte) jsonObject.getInt("version");
		flagUpdate = jsonObject.getBoolean("flag_update");
		containerType = ContainerType.valueOf(jsonObject.getString("container_type"));
		capacity = (float) jsonObject.getDouble("capacity");
		stored = (float) jsonObject.getDouble("stored");
	}

	public float getCapacity() {
		return capacity;
	}

	public void setCapacity(float capacity) {
		this.capacity = capacity;
	}

	public float getStored() {
		return stored;
	}

	public void setStored(float stored) {
		this.stored = stored;
	}
}
