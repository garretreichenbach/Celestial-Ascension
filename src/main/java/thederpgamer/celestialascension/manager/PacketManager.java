package thederpgamer.celestialascension.manager;

import api.network.packets.PacketUtil;
import thederpgamer.celestialascension.network.client.ExampleClientPacket;

public class PacketManager {

	public static void initialize() {
		PacketUtil.registerPacket(ExampleClientPacket.class);
	}
}