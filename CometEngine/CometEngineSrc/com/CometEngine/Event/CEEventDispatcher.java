package com.CometEngine.Event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import com.CometEngine.CometEngine;
import com.CometEngine.CELib.Node.CEObject;
import com.CometEngine.CELib.Scene.CESceneManager;
import com.CometEngine.Device.CEDeviceManager;
import com.CometEngine.Device.CEKeyBoard;

public class CEEventDispatcher {
	private static final CEEventDispatcher Instance = new CEEventDispatcher();
	private static final HashMap<String, ArrayList<CEEventListenerCustom>> CustomEnventListenerTable = new HashMap<String, ArrayList<CEEventListenerCustom>>();
	private static final HashMap<String, ArrayList<CEEventListener>> EventListenerTable = new HashMap<String, ArrayList<CEEventListener>>();

	private static final String KEYBOARD_LISTENER = "KeyBoard";

	private ArrayList<CEEventListenerCustom> getCustomEventContainer(CEEventListenerCustom listener) {
		if (CustomEnventListenerTable.containsKey(listener.getEventName())) {
			return CustomEnventListenerTable.get(listener.getEventName());
		} else {
			ArrayList<CEEventListenerCustom> list = new ArrayList<CEEventListenerCustom>();
			CustomEnventListenerTable.put(listener.getEventName(), list);
			return list;
		}
	}

	private ArrayList<CEEventListener> getEventListenerContainer(String key) {
		if (EventListenerTable.containsKey(key)) {
			return EventListenerTable.get(key);
		} else {
			ArrayList<CEEventListener> list = new ArrayList<CEEventListener>();
			EventListenerTable.put(key, list);
			return list;
		}
	}

	private boolean StoreCustomEventListener(CEEventListenerCustom listener) {
		ArrayList<CEEventListenerCustom> array = getCustomEventContainer(listener);
		if (array.contains(listener)) {
			return false;
		} else {
			array.add(listener);
			return true;
		}

	}

	public void addEventListener(CEEventListener event) {
		if (event instanceof CEEventListenerCustom) {
			CEEventListenerCustom listener = (CEEventListenerCustom) event;
			if (StoreCustomEventListener(listener)) {

				return;
			} else {
				System.err
						.println("Custom EVENT Listener \' " + listener + " \' was Registered." + " It didn't Allow! ");
				return;
			}
		} else {
			EventListenerShortingAndAdd(event);
		}
	}

	private boolean StoreEventListener(final String key, final CEEventListener eventlistener) {
		ArrayList<CEEventListener> listener = getEventListenerContainer(key);
		if (listener.contains(eventlistener) == false) {
			listener.add(eventlistener);
			return true;
		} else {
			System.err.println("EventListener \' " + listener + " \' was Registered.");
			return false;
		}
	}

	private void EventListenerShortingAndAdd(CEEventListener listener) {
		if (listener instanceof CEEventListenerKeyboard) {
			StoreEventListener(KEYBOARD_LISTENER, listener);
		}
	}

	private static final CEEventKeyboard KeyBoardEvent = new CEEventKeyboard();

	private void TalkKeyBoardEvent(CEEventKeyboard event) {
		if (EventListenerTable.containsKey(KEYBOARD_LISTENER)) {
			ArrayList<CEEventListener> listener = EventListenerTable.get(KEYBOARD_LISTENER);
			for (int i = 0; i < listener.size(); i++) {
				if (listener.get(i) instanceof CEEventListenerKeyboard) {
					CEEventListenerKeyboard t = (CEEventListenerKeyboard) listener.get(i);
					if (t.getTargetScene() == CESceneManager.getInstance().getScene())
						t.ListenKeyBoardEvent(KeyBoardEvent);
				}
			}
		}
	}

	private void PutEvent(CEEvent event) {
		if (event instanceof CEEventKeyboard) {
			TalkKeyBoardEvent((CEEventKeyboard) event);
		}
	}

	private void CEEventDispatcher() {
	}

	private void UpdateKeyPushData() {
		ArrayList<Integer> PushedKeys = ((CEKeyBoard) CEDeviceManager.getInstance().getDevice("keyboard"))
				.getPushedKeys();

		for (int i = 0; i < PushedKeys.size(); i++) {
			KeyBoardEvent.KEYDATA.get(PushedKeys.get(i)).OnceStatUpper();
		}
	}

	private void SetKeyboardEvents() {
		if(CEDeviceManager.getInstance().getDevice("Keyboard") == null)
			return;
		
		if ( ((CEKeyBoard) CEDeviceManager.getInstance().getDevice("keyboard")).getGetPushedKeyCounter() > 0) {
			KeyBoardEvent.KEYDATA = ((CEKeyBoard) CEDeviceManager.getInstance().getDevice("keyboard")).getKeyMap();
			if(KeyBoardEvent.KEYDATA == null)
			KeyBoardEvent.Mode = ((CEKeyBoard) CEDeviceManager.getInstance().getDevice("keyboard")).getMode();
			PutEvent(KeyBoardEvent);
			UpdateKeyPushData();
		}
	}

	public void RelaseKeyBoardEvents() {

		KeyBoardEvent.Mode = ((CEKeyBoard) CEDeviceManager.getInstance().getDevice("keyboard")).getMode();
		KeyBoardEvent.KEYDATA = ((CEKeyBoard) CEDeviceManager.getInstance().getDevice("keyboard")).getKeyMap();
		PutEvent(KeyBoardEvent);
		UpdateKeyPushData();
	}

	public void PullEvents() {
		SetKeyboardEvents();
	}

	public static CEEventDispatcher getInstance() {
		return Instance;
	}

}
