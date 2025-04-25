package thederpgamer.celestialascension.utils;

import api.common.GameCommon;
import org.w3c.dom.Document;
import thederpgamer.celestialascension.CelestialAscension;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class DataUtils {

	public static String getWorldDataPath() {
		return getResourcesPath() + "/data/" + GameCommon.getUniqueContextId();
	}

	public static String getResourcesPath() {
		return CelestialAscension.getInstance().getSkeleton().getResourcesFolder().getPath().replace('\\', '/');
	}

	public static Document parseXML(File configFile) {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			Document document = dbf.newDocumentBuilder().parse(configFile);
			document.getDocumentElement().normalize();
			return document;
		} catch(Exception exception) {
			CelestialAscension.getInstance().logFatal("Failed to parse XML file: " + configFile.getName(), exception);
			return null;
		}
	}
}
