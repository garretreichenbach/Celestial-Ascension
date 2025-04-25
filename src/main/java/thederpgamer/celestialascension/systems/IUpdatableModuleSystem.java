package thederpgamer.celestialascension.systems;

import thederpgamer.celestialascension.misc.JSONSerializable;

/**
 * [Description]
 *
 * @author TheDerpGamer
 */
public interface IUpdatableModuleSystem extends JSONSerializable {

	boolean needsUpdate();

	void flagUpdate();
}
