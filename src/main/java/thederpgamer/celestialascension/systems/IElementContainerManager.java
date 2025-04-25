package thederpgamer.celestialascension.systems;

import thederpgamer.celestialascension.misc.JSONSerializable;

import java.util.Set;

/**
 * [Description]
 *
 * @author TheDerpGamer
 */
public interface IElementContainerManager extends JSONSerializable {

	Set<IElementContainer> getElementContainers();
}
