package thederpgamer.celestialascension.systems;

/**
 * [Description]
 *
 * @author TheDerpGamer
 */
public enum MachineStatus {

	IDLE("Machine is idle"),
	PROCESSING("Machine is processing"),
	WAITING("Machine is waiting for inputs"),
	ERROR("Machine has encountered an error"),
	UNPOWERED("Machine needs more power"),
	PAUSED("Machine is paused");

	private final String description;

	MachineStatus(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}