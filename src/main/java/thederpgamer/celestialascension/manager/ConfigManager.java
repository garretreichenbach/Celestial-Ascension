package thederpgamer.celestialascension.manager;

import api.mod.config.FileConfiguration;
import thederpgamer.celestialascension.CelestialAscension;
import thederpgamer.celestialascension.systems.modules.ConfigurableSystemModule;
import thederpgamer.celestialascension.utils.DataUtils;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;

public class ConfigManager {

	private static FileConfiguration mainConfig;
	private static final String[] defaultMainConfig = {
			"debug_mode: false"
	};

	public static void initialize(CelestialAscension instance) {
		mainConfig = instance.getConfig("config");
		mainConfig.saveDefault(defaultMainConfig);
		initSystemsConfig();
	}

	public static FileConfiguration getMainConfig() {
		return mainConfig;
	}

	private static void initSystemsConfig() {
		try {
			File configFile = new File(DataUtils.getResourcesPath() + "systems_config.xml");
			if(!configFile.exists()){
				configFile.createNewFile();
				createDefaultSystemsConfig(configFile);
			}
		} catch(Exception exception) {
			CelestialAscension.getInstance().logFatal("Failed to initialize systems config file!", exception);
		}
	}

	private static void createDefaultSystemsConfig(File configFile) {
		try {
			InputStream inputStream = CelestialAscension.getInstance().getJarResource("systems_config.xml");
			if(inputStream != null) {
				Files.copy(inputStream, configFile.toPath());
				inputStream.close();
			} else throw new NullPointerException("Default systems config file not found in jar! This implies the mod jar is invalid or corrupt!");
		} catch(Exception exception) {
			CelestialAscension.getInstance().logFatal("Failed to create default systems config file!", exception);
		}
	}

	public static void parseSystemConfig(ConfigurableSystemModule configurableSystemModule) {
		try {
			File configFile = new File(DataUtils.getResourcesPath() + "systems_config.xml");
			if(configFile.exists()) configurableSystemModule.parse(DataUtils.parseXML(configFile));
			else throw new Exception("Config file does not exist!");
		} catch(Exception exception) {
			CelestialAscension.getInstance().logFatal("Failed to parse system config file!", exception);
		}
	}
}
