package thederpgamer.celestialascension.utils;

import org.json.JSONObject;
import thederpgamer.celestialascension.misc.JSONSerializable;

/**
 * [Description]
 *
 * @author TheDerpGamer
 */
public class ItemStack implements JSONSerializable {

	private final byte VERSION = 0;
	private short id;
	private int amount;
	private int metaId;
	private String multiSlot;

	public ItemStack(short id, int amount) {
		this.id = id;
		this.amount = amount;
	}

	public ItemStack(JSONObject jsonObject) {
		fromJSON(jsonObject);
	}

	@Override
	public JSONObject toJSON() {
		JSONObject json = new JSONObject();
		json.put("version", VERSION);
		json.put("id", id);
		json.put("amount", amount);
		json.put("meta_id", metaId);
		json.put("multi_slot", multiSlot);
		return json;
	}

	@Override
	public void fromJSON(JSONObject jsonObject) {
		byte version = (byte) jsonObject.getInt("version");
		id = (short) jsonObject.getInt("id");
		amount = jsonObject.getInt("amount");
		metaId = jsonObject.getInt("meta_id");
		multiSlot = jsonObject.getString("multi_slot");
	}
}
