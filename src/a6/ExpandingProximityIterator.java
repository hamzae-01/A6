package a6;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ExpandingProximityIterator implements Iterator<Driver> {
	private Iterator<Driver> driver_iterator;
	private Iterable<Driver> _driver_pool;
	private Position _client_position;
	private int _expansion_step;
	private Driver _next_driver;
	private int count;
	private int count_2;
	private int c_ring;

	public ExpandingProximityIterator(Iterable<Driver> driver_pool, Position client_position, int expansion_step) {
		driver_iterator = driver_pool.iterator();
		_client_position = client_position;
		_expansion_step = expansion_step;
		_driver_pool = driver_pool;
		_next_driver = null;
		c_ring = 0;

		while (driver_iterator.hasNext()) {
			count += 1;
			driver_iterator.next();

		}

		driver_iterator = driver_pool.iterator();
	}

	@Override
	public boolean hasNext() {
		if (_next_driver != null) {
			return true;
		}

		if (count_2 >= count) {
			return false;
		}

		while (true) {
			while (driver_iterator.hasNext()) {
				Driver iterator_next_driver = driver_iterator.next();
				if (c_ring == 0) {
					if ((_client_position.getManhattanDistanceTo(iterator_next_driver.getVehicle().getPosition())) >= 0
							&& _client_position
									.getManhattanDistanceTo(iterator_next_driver.getVehicle().getPosition()) <= 1) {
						_next_driver = iterator_next_driver;
						return true;
					}
				} else if ((_client_position.getManhattanDistanceTo(
						iterator_next_driver.getVehicle().getPosition())) > 1 + (c_ring - 1) * _expansion_step
						&& _client_position.getManhattanDistanceTo(iterator_next_driver.getVehicle().getPosition()) <= 1
								+ c_ring * _expansion_step) {
					_next_driver = iterator_next_driver;
					return true;
				}
			}
			c_ring += 1;
			driver_iterator = _driver_pool.iterator();
		}
	}
//return false;
	//returns next iterator
	@Override
	public Driver next() {

		if (!hasNext()) {
			throw new NoSuchElementException("No next driver in range");
		}
		Driver result = _next_driver;
		_next_driver = null;
		count_2 += 1;
		return result;
	}
}