package cz.ucl.hatchery.carevidence.util.collections;

import java.util.HashMap;
import java.util.Map;

import org.springframework.util.CollectionUtils;

/**
 * Implementation of two dimensional map.
 *
 * @author DZCAVMB
 */
public class DoubleKeyMap<K1, K2, V> {

	private final Map<K1, Map<K2, V>> map = new HashMap<K1, Map<K2, V>>();

	/**
	 * Get inner map by first key.
	 *
	 * @param key1
	 * @return
	 */
	public Map<K2, V> get(final K1 key1) {
		return map.get(key1);
	}

	public V get(final K1 key1, final K2 key2) {
		final Map<K2, V> insideMap = map.get(key1);
		if (CollectionUtils.isEmpty(insideMap)) {
			return null;
		}

		final V result = insideMap.get(key2);
		return result;
	}

	public void put(final K1 key1, final K2 key2, final V value) {
		if (map.containsKey(key1)) {
			final Map<K2, V> insider = map.get(key1);
			insider.put(key2, value);
		} else {
			final Map<K2, V> insider = new HashMap<K2, V>();
			insider.put(key2, value);
			map.put(key1, insider);
		}
	}

	public void clear() {
		map.clear();
	}

	public boolean isEmpty() {
		return map.isEmpty();
	}

}
