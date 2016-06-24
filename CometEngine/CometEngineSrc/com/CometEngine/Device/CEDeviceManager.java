package com.CometEngine.Device;

import java.util.ArrayList;
import java.util.Hashtable;

public class CEDeviceManager {
	private static final Hashtable<Object, CEDevice> DeviceList = new Hashtable<Object, CEDevice>();
	private static CEDeviceManager Instance = new CEDeviceManager();
	
	public static CEDeviceManager getInstance()
	{
		return Instance;
	}
	public void addDevice(Object key, CEDevice device)
	{
		if(DeviceList.containsKey(key))
			return;
		DeviceList.put(key, device);
	}
	public CEDevice getDevice(Object key)
	{
		if(DeviceList.containsKey(key))
			return DeviceList.get(key);
		return null;
	}
	
}
