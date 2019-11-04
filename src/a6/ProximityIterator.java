package a6;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ProximityIterator implements Iterator<Driver> {

	Iterator<Driver> all_Drivers;
	Iterable<Driver> driver_pool;
	Position client_position;
	int proximity_limit;
	Driver nextDriver;

	public ProximityIterator(Iterable<Driver> driver_pool, Position client_position, int proximity_limit) {

//	In your constructor, use the iterator method of the provided driver_pool to create an iterator for
//	all of the Driver objects in the collection.
//	Store this iterator in an instance field.

		if (driver_pool == null) {
			throw new IllegalArgumentException("Null");
		}
		
		if (client_position == null) {
			throw new IllegalArgumentException("Null Position");
		}
		
		this.all_Drivers = driver_pool.iterator();

//Use an instance field to store the next driver that matches the proximity limit.
//Initialize this to null in your constructor

		this.nextDriver = null;

		this.driver_pool = driver_pool;
		this.client_position = client_position;
		this.proximity_limit = proximity_limit;

	}

	@Override
	public boolean hasNext() {


		if (this.nextDriver != null) {
			return true;
		} else {
			
			while (all_Drivers.hasNext() == true) {
				
				nextDriver = all_Drivers.next();
				
				if (nextDriver.getVehicle().getPosition().getManhattanDistanceTo(client_position) <= proximity_limit) {
					
					return true;
					
				} 
					
				}
			
			return false;
				
			}
			
		}

	

//	To implement hasNext(), first see if you already found the next appropriate driver by 
//	checking your next driver field (i.e., testing to see if it is not null). 
//	If you already found the next driver, just return true. If not, retrieve drivers from your driver pool
//	iterator until you either find the next appropriate driver or you run out of drivers.
//	If you find an appropriate driver,
//	store it in your next driver instance field and return true.
//	If the pool runs out of drivers, then return false.

	

	@Override
	public Driver next() {
// If next() is called but there is no next eligible driver, throw a NoSuchElementException. 

		Driver local_nextDriver = null;

		if (this.hasNext() == false) {
			throw new NoSuchElementException("No Such Element");
		} else {
			
			local_nextDriver = nextDriver;
			nextDriver = null;

			return local_nextDriver;
		}

//      To implement next(), first call hasNext(). If false, throw a NoSuchElementException.

//	If true, then you know that the next eligible driver must be in your next driver instance field.
//	Copy this to a local variable. Reset your next driver field to be null so that you don't keep
//	returning the same driver.
//	Now return the value of the local variable that you copied the next driver to.

	}
	


}