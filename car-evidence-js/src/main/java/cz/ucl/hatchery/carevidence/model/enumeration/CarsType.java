/**
 * 
 */
package cz.ucl.hatchery.carevidence.model.enumeration;

/**
 * Type of cars
 * 
 * @author DZCJS9F
 *
 */
public enum CarsType {
	BULLDOZER, TRACTOR, DREDGING, EXCAVATOR, RECLAIMER, SKIDDER, LOADER, FORKLIFT, DUMP_TRUCK, ROAD_ROLLER, TRACKED_LOADER;

	public static CarsType parseType(final String type) {
		for (final CarsType t : CarsType.values()) {
			if (t.name().equalsIgnoreCase(type)) {
				return t;
			}
		}

		return null;
	}

}
