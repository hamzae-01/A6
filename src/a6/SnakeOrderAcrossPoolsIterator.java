package a6;

import java.util.*;

public class SnakeOrderAcrossPoolsIterator implements Iterator<Driver> {
	
	//fields below
	private List<Iterable<Driver>> _driver_pools;
	private List<Iterator<Driver>> _driver_pools_iterators;
	private int c_Index;
	private int _next_Index;
	private int _list_size;
	private Driver _next_Driver;
	private boolean _count_Up;
	
	public SnakeOrderAcrossPoolsIterator(List<Iterable<Driver>> driver_pools) {
		this._driver_pools = driver_pools;
		this._driver_pools_iterators = new ArrayList<Iterator<Driver>>();
		for (Iterable<Driver> i : driver_pools) {
		
			//exception handling
				if (driver_pools == null) {
					throw new IllegalArgumentException("Null");
				}
			
				this._driver_pools_iterators.add(i.iterator());
				//if (driver_pools_iterators != null) {
		}
		
		if (driver_pools == null) {
			throw new IllegalArgumentException("Null");
		}
		
		
		this.c_Index = 0;
		this._list_size = _driver_pools_iterators.size();
		this._next_Driver = null;
		//this.driver_pools_iterators.add(driver_pools.get(4).iterator());
		this._count_Up = true;
		
	}
	//has next method checks if theres a next driver
	@Override
	public boolean hasNext() {
		boolean flag = true;
		boolean reachedTwice = false;
		
		if (_next_Driver != null) {
			return true;
		}
		int flag_index = c_Index;
		
		while (flag) {//final jedi test
			if (_driver_pools_iterators.isEmpty()) {
				return false;
			}
			while (_count_Up) {
				while (c_Index < _list_size) {
					if (_driver_pools_iterators.get(c_Index).hasNext()) {
						_next_Driver = _driver_pools_iterators.get(c_Index).next();
						c_Index++;
						return true;
					}
					c_Index++;
					if(c_Index == flag_index) {
						if (reachedTwice) {
							flag = false;
						}
						reachedTwice = true;
					}
				}
				_count_Up = false;
				c_Index --;
			}
			
			while (!(_count_Up)) {
				while (c_Index >= 0) {
					if (_driver_pools_iterators.get(c_Index).hasNext()) {
						_next_Driver = _driver_pools_iterators.get(c_Index).next();
						c_Index--;
						return true;
					}
					c_Index--;
					if(c_Index == flag_index) {
						if (reachedTwice) {
							flag = false;
						}
						reachedTwice = true;
					}
				}
				_count_Up = true;
				c_Index ++;
			}
		}
		
		return false;
	}
	
	
	//returns next iterator
	@Override
	public Driver next() {
		if (!(hasNext())) {
			throw new NoSuchElementException ("No drivers left");
		} 
		Driver d = _next_Driver;
		_next_Driver = null;
		return d;
	}
}