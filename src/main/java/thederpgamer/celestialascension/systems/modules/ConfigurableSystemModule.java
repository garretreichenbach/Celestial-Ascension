package thederpgamer.celestialascension.systems.modules;

import api.mod.StarMod;
import api.utils.game.module.ModManagerContainerModule;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.controller.elements.ManagerContainer;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import thederpgamer.celestialascension.CelestialAscension;
import thederpgamer.celestialascension.manager.ConfigManager;
import thederpgamer.celestialascension.misc.IConfigurableSystem;

/**
 * [Description]
 *
 * @author TheDerpGamer
 */
public abstract class ConfigurableSystemModule extends ModManagerContainerModule implements IConfigurableSystem {

	protected ConfigurableSystemModule(SegmentController ship, ManagerContainer<?> managerContainer, StarMod mod, short blockId) {
		super(ship, managerContainer, mod, blockId);
		ConfigManager.parseSystemConfig(this);
	}

	public void parse(Document document) {
		Node rootNode = document.getDocumentElement();
		String systemName = getSystemName();
		Node systemNode = rootNode.getAttributes().getNamedItem(systemName);
		for(Node node = systemNode.getFirstChild(); node != null; node = node.getNextSibling()) {
			if(node.getNodeType() == Node.ELEMENT_NODE) {
				String name = node.getNodeName();
				String value = node.getTextContent();
				try {
					if(getClass().getDeclaredField(name).getType() == float.class) {
						getClass().getDeclaredField(name).setFloat(this, Float.parseFloat(value));
					} else if(getClass().getDeclaredField(name).getType() == int.class) {
						getClass().getDeclaredField(name).setInt(this, Integer.parseInt(value));
					} else if(getClass().getDeclaredField(name).getType() == boolean.class) {
						getClass().getDeclaredField(name).setBoolean(this, Boolean.parseBoolean(value));
					} else if(getClass().getDeclaredField(name).getType() == String.class) {
						getClass().getDeclaredField(name).set(this, value);
					}
				} catch(Exception exception) {
					CelestialAscension.getInstance().logFatal("Failed to parse system config value: " + name + " for system: " + systemName, exception);
				}
			}
		}
	}

	protected abstract String getSystemName();
}
