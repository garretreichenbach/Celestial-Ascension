package thederpgamer.celestialascension.systems.modules;

import api.network.PacketReadBuffer;
import api.network.PacketWriteBuffer;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import org.json.JSONArray;
import org.json.JSONObject;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.controller.elements.ManagerContainer;
import org.schema.game.common.data.ManagedSegmentController;
import org.schema.schine.graphicsengine.core.Timer;
import thederpgamer.celestialascension.CelestialAscension;
import thederpgamer.celestialascension.element.ElementManager;
import thederpgamer.celestialascension.misc.ConfigurationElement;
import thederpgamer.celestialascension.systems.*;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * [Description]
 *
 * @author TheDerpGamer
 */
public class BloodInfuserModule extends ConfigurableSystemModule {

	@ConfigurationElement(name = "power_consumption_resting")
	public static float POWER_CONSUMPTION_RESTING = 0.0f;

	@ConfigurationElement(name = "power_consumption_active")
	public static float POWER_CONSUMPTION_ACTIVE = 666.0f;

	@ConfigurationElement(name = "fuel_capacity")
	public static float FUEL_CAPACITY = 1000.0f;

	@ConfigurationElement(name = "input_tank_capacity")
	public static float INPUT_TANK_CAPACITY = 1000.0f;

	@ConfigurationElement(name = "output_tank_capacity")
	public static float OUTPUT_TANK_CAPACITY = 1000.0f;

	private final Long2ObjectOpenHashMap<BloodInfuserModuleData> bloodInfuserDataMap = new Long2ObjectOpenHashMap<>();

	public BloodInfuserModule(SegmentController ship, ManagerContainer<?> managerContainer) {
		super(ship, managerContainer, CelestialAscension.getInstance(), ElementManager.getBlock("blood_infuser").getId());
	}

	public static BloodInfuserModule getModule(ManagedSegmentController<?> controller) {
		Object module = controller.getManagerContainer().getModMCModule(ElementManager.getBlock("blood_infuser").getId());
		return module instanceof BloodInfuserModule ? (BloodInfuserModule) module : null;
	}

	@Override
	public void handle(Timer timer) {

	}

	@Override
	public void onTagSerialize(PacketWriteBuffer packetWriteBuffer) throws IOException {

	}

	@Override
	public void onTagDeserialize(PacketReadBuffer packetReadBuffer) throws IOException {

	}

	@Override
	public double getPowerConsumedPerSecondResting() {
		return POWER_CONSUMPTION_RESTING;
	}

	@Override
	public double getPowerConsumedPerSecondCharging() {
		return POWER_CONSUMPTION_ACTIVE;
	}

	@Override
	public String getName() {
		return "Blood Infuser Module";
	}

	public BloodInfuserModuleData getModule(long index) {
		return bloodInfuserDataMap.get(index);
	}

	public BloodInfuserModuleData addModule(long index) {
		BloodInfuserModuleData data = new BloodInfuserModuleData(index);
		bloodInfuserDataMap.put(index, data);
		return data;
	}

	public BloodInfuserModuleData removeModule(long index) {
		return bloodInfuserDataMap.remove(index);
	}

	@Override
	protected String getSystemName() {
		return "blood_infuser";
	}

	public static class BloodInfuserModuleData implements IElementContainerManager {

		private static final byte VERSION = 0;
		private boolean flagUpdate;
		private long index;
		private FluidContainer fuelContainer;
		private FluidContainer inputFluidContainer;
		private FluidContainer outputFluidContainer;
		private Set<ItemContainer> inputElementContainers = new HashSet<>();
		private Set<ItemContainer> outputElementContainers = new HashSet<>();
		private MachineStatus status;

		public BloodInfuserModuleData(long index) {
			this.index = index;
			fuelContainer = new FluidContainer(ContainerType.FUEL_FLUIDS, FUEL_CAPACITY);
			inputFluidContainer = new FluidContainer(ContainerType.INPUT_FLUIDS, INPUT_TANK_CAPACITY);
			outputFluidContainer = new FluidContainer(ContainerType.OUTPUT_FLUIDS, OUTPUT_TANK_CAPACITY);
			inputElementContainers.add(new ItemContainer(ContainerType.INPUT_ITEMS));
			outputElementContainers.add(new ItemContainer(ContainerType.OUTPUT_ITEMS));
			status = MachineStatus.IDLE;
		}

		public BloodInfuserModuleData(JSONObject jsonObject) {
			fromJSON(jsonObject);
		}

		@Override
		public JSONObject toJSON() {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("version", VERSION);
			jsonObject.put("index", index);
			jsonObject.put("flag_update", flagUpdate);
			jsonObject.put("fuel_container", fuelContainer.toJSON());
			jsonObject.put("input_fluid_container", inputFluidContainer.toJSON());
			jsonObject.put("output_fluid_container", outputFluidContainer.toJSON());
			JSONArray inputElementContainersArray = new JSONArray();
			for(ItemContainer container : inputElementContainers) inputElementContainersArray.put(container.toJSON());
			jsonObject.put("input_element_containers", inputElementContainersArray);
			JSONArray outputElementContainersArray = new JSONArray();
			for(ItemContainer container : outputElementContainers) outputElementContainersArray.put(container.toJSON());
			jsonObject.put("output_element_containers", outputElementContainersArray);
			jsonObject.put("status", status.toString());
			return jsonObject;
		}

		@Override
		public void fromJSON(JSONObject jsonObject) {
			byte version = (byte) jsonObject.getInt("version");
			index = jsonObject.getLong("index");
			flagUpdate = jsonObject.getBoolean("flag_update");
			fuelContainer = new FluidContainer(jsonObject.getJSONObject("fuel_container"));
			inputFluidContainer = new FluidContainer(jsonObject.getJSONObject("input_fluid_container"));
			outputFluidContainer = new FluidContainer(jsonObject.getJSONObject("output_fluid_container"));
			JSONArray inputElementContainersArray = jsonObject.getJSONArray("input_element_containers");
			for(int i = 0; i < inputElementContainersArray.length(); i++) inputElementContainers.add(new ItemContainer(inputElementContainersArray.getJSONObject(i)));
			JSONArray outputElementContainersArray = jsonObject.getJSONArray("output_element_containers");
			for(int i = 0; i < outputElementContainersArray.length(); i++) outputElementContainers.add(new ItemContainer(outputElementContainersArray.getJSONObject(i)));
			status = MachineStatus.valueOf(jsonObject.getString("status"));
		}

		@Override
		public int hashCode() {
			return (int) index;
		}

		@Override
		public Set<IElementContainer> getElementContainers() {
			Set<IElementContainer> containers = new HashSet<>();
			containers.add(fuelContainer);
			containers.add(inputFluidContainer);
			containers.add(outputFluidContainer);
			containers.addAll(inputElementContainers);
			containers.addAll(outputElementContainers);
			return containers;
		}
	}
}
