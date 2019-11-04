package a6;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ProximityIterator implements Iterator<Driver> {

	Iterator<Driver> _all_Drivers;
	Iterable<Driver> _driver_pool;
	Position _client_position;
	int _proximity_limit;
	Driver _nextDriver;

	public ProximityIterator(Iterable<Driver> driver_pool, Position client_position, int proximity_limit) {

		if (driver_pool == null) {
			throw new IllegalArgumentException("Null");
		}
		
		if (client_position == null) {
			throw new IllegalArgumentException("Null Position");
		}
		
		this._all_Drivers = driver_pool.iterator();


		this._nextDriver = null;

		this._driver_pool = driver_pool;
		this._client_position = client_position;
		this._proximity_limit = proximity_limit;

	}

	@Override
	public boolean hasNext() {


		if (this._nextDriver != null) {
			return true;
		} else {
			
			while (_all_Drivers.hasNext() == true) {
				
				_nextDriver = _all_Drivers.next();
				
				if (_nextDriver.getVehicle().getPosition().getManhattanDistanceTo(_client_position) <= _proximity_limit) {
					
					return true;
					
				} 
					
				}
			
			_nextDriver = null;
			return false;
				
			}
			
		}


	

	@Override
	public Driver next() {

		Driver local_nextDriver = null;

		if (this.hasNext() == false) {
			throw new NoSuchElementException("No Such Element");
		} else {
			
			local_nextDriver = _nextDriver;
			_nextDriver = null;

			return local_nextDriver;
		}

	}
	


}