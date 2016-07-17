package com.CometEngine.Event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import com.CometEngine.CometEngine;
import com.CometEngine.CELib.Object.CEObject;
import com.CometEngine.CELib.Scene.CEScene;
import com.CometEngine.CELib.Scene.CESceneManager;
import com.CometEngine.Device.CEDeviceManager;
import com.CometEngine.Device.CEKeyBoard;
import com.CometEngine.Device.CEMouse;
import com.CometEngine.Device.CETouchPad;
import com.CometEngine.Event.CEEventMouse.EVENT_TYPE;

public class CEEventDispatcher {
	private static final CEEventDispatcher Instance = new CEEventDispatcher();
	private static final HashMap<String, ArrayList<CEEventListenerCustom>> CustomEnventListenerTable = new HashMap<String, ArrayList<CEEventListenerCustom>>();
	private static final HashMap<String, ArrayList<CEEventListener>> EventListenerTable = new HashMap<String, ArrayList<CEEventListener>>();

	private static final String KEYBOARD_LISTENER = "KeyBoard";
	private static final String DEFAULT_MOUSE_LISTENER = "Mouse";
	private static final String TOUCH_PAD_LISTENER = "TouchPad";

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

	public void addEventListener(CEEventListener event, CEScene target) {
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

			EventListenerShortingAndAdd(event, target);
		}
	}

	public void addEventListener(String ListenerKey, CEEventListener event, CEScene target) {
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
			EventListenerShortingAndAdd(ListenerKey, event, target);
		}
	}

	private void EventListenerShortingAndAdd(String ListenerKey, CEEventListener listener, CEScene scene) {
		if (listener instanceof CEEventListenerKeyboard) {
			CEEventListenerKeyboard keyboard = (CEEventListenerKeyboard) listener;
			keyboard.TargetScene = scene;
			StoreEventListener(ListenerKey, listener);
		}
		if (listener instanceof CEEventListenerMouse) {
			CEEventListenerMouse mouselistener = (CEEventListenerMouse) listener;
			mouselistener.TargetScene = scene;
			StoreEventListener(ListenerKey, mouselistener);
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

	private void EventListenerShortingAndAdd(CEEventListener listener, CEScene scene) {
		if (listener instanceof CEEventListenerKeyboard) {
			CEEventListenerKeyboard keyboard = (CEEventListenerKeyboard) listener;
			keyboard.TargetScene = scene;
			StoreEventListener(KEYBOARD_LISTENER, listener);
		}
		if (listener instanceof CEEventListenerMouse) {
			CEEventListenerMouse mouselistener = (CEEventListenerMouse) listener;
			mouselistener.TargetScene = scene;
			StoreEventListener(DEFAULT_MOUSE_LISTENER, mouselistener);
		}
		if (listener instanceof CEEventListenerTouch) {
			CEEventListenerTouch toucheventlistener = (CEEventListenerTouch) listener;
			toucheventlistener.TargetScene = scene;
			StoreEventListener(TOUCH_PAD_LISTENER, toucheventlistener);
		}
	}

	private static final CEEventKeyboard KeyBoardEvent = new CEEventKeyboard();

	private void TalkKeyBoardEvent(CEEventKeyboard event) {
		if (EventListenerTable.containsKey(KEYBOARD_LISTENER)) {
			ArrayList<CEEventListener> listener = EventListenerTable.get(KEYBOARD_LISTENER);
			for (int i = 0; i < listener.size(); i++) {
				if (listener.get(i) instanceof CEEventListenerKeyboard) {
					CEEventListenerKeyboard t = (CEEventListenerKeyboard) listener.get(i);
					if (CESceneManager.getInstance().getCurrentScene().isExit() == false) {

						if (t.getTargetScene() == CESceneManager.getInstance().getCurrentScene()) {
							KeyBoardEvent.TargetObject = t.TargetObject;
							t.ListenKeyBoardEvent(KeyBoardEvent);
						}
					}
				}
			}
		}
	}

	private void PutEvent(CEEvent event) {
		if (event instanceof CEEventKeyboard) {
			TalkKeyBoardEvent((CEEventKeyboard) event);
		}
		if (event instanceof CEEventMouse) {
			TalkMouseEvent((CEEventMouse) event);
		}
		if (event instanceof CEEventTouch) {
			TalkTouchEvent((CEEventTouch) event);
		}
	}

	private void CEEventDispatcher() {
	}

	private void UpdateKeyPushData() {
		ArrayList<Integer> PushedKeys = ((CEKeyBoard) CEDeviceManager.getInstance().getDevice("keyboard"))
				.getPushedKeys();

		for (int i = 0; i < PushedKeys.size(); i++) {
			if (KeyBoardEvent.KEYDATA.get(PushedKeys.get(i)).getStat() == 1) {
				KeyBoardEvent.KEYDATA.get(PushedKeys.get(i)).OnceStatUpper();

			}

		}
	}

	private void SetKeyboardEvents() {
		if (CEDeviceManager.getInstance().getDevice("Keyboard") == null)
			return;

		if (((CEKeyBoard) CEDeviceManager.getInstance().getDevice("keyboard")).getGetPushedKeyCounter() > 0) {
			CEKeyBoard device = (CEKeyBoard) CEDeviceManager.getInstance().getDevice("keyboard");
			KeyBoardEvent.KEYDATA = device.getKeyMap();
			if (KeyBoardEvent.KEYDATA == null)
				KeyBoardEvent.Mode = device.getMode();

			PutEvent(KeyBoardEvent);
			UpdateKeyPushData();
		}
	}

	public void RelaseKeyBoardEvents() {

		KeyBoardEvent.KEYDATA = ((CEKeyBoard) CEDeviceManager.getInstance().getDevice("keyboard")).getKeyMap();
		KeyBoardEvent.Mode = ((CEKeyBoard) CEDeviceManager.getInstance().getDevice("keyboard")).getMode();
		PutEvent(KeyBoardEvent);
		UpdateKeyPushData();
	}

	private final CEEventMouse mouse = new CEEventMouse();

	private void TalkMouseEvent(CEEventMouse mouse) {
		if (EventListenerTable.containsKey(DEFAULT_MOUSE_LISTENER)) {
			ArrayList<CEEventListener> listener = EventListenerTable.get(DEFAULT_MOUSE_LISTENER);
			for (int i = 0; i < listener.size(); i++) {
				if (listener.get(i) instanceof CEEventListenerMouse) {
					CEEventListenerMouse t = (CEEventListenerMouse) listener.get(i);
					if (CESceneManager.getInstance().getCurrentScene().isExit() == false) {
						if (t.TargetScene == CESceneManager.getInstance().getCurrentScene()) {

							mouse.TargetObject = t.TargetObject;
							if (mouse.type == EVENT_TYPE.MOUSE_CLICK) {
								t.ListenClickEvent(mouse);
							} else if (mouse.type == EVENT_TYPE.MOUSE_MOVE) {
								t.ListenMoveEvent(mouse);
							} else if (mouse.type == EVENT_TYPE.MOUSE_SCROLL) {
								t.ListenScrollEvent(mouse);
							}

						}
					}
				}
			}
		}
	}

	public void PullEvents() {
		SetKeyboardEvents();
	}

	public void MouseEventActive(CEEventMouse.EVENT_TYPE Type) {
		CEMouse mousedevice = (CEMouse) CEDeviceManager.getInstance().getDevice(DEFAULT_MOUSE_LISTENER);
		if (Type == EVENT_TYPE.MOUSE_CLICK) {
			mouse.type = EVENT_TYPE.MOUSE_CLICK;
			mouse.ActiveButton = mousedevice.getActiveButton();
			mouse.Status = mousedevice.getStatus();
			mouse.XPos = mousedevice.getXPos();
			mouse.YPos = mousedevice.getYPos();
		} else if (Type == EVENT_TYPE.MOUSE_MOVE) {
			mouse.type = EVENT_TYPE.MOUSE_MOVE;
			mouse.ActiveButton = -1;
			mouse.Status = -1;
			mouse.XPos = mousedevice.getXPos();
			mouse.YPos = mousedevice.getYPos();
		} else if (Type == EVENT_TYPE.MOUSE_SCROLL) {
			mouse.type = EVENT_TYPE.MOUSE_SCROLL;
			mouse.ActiveButton = -1;
			mouse.Status = -1;
			mouse.XPos = -1;
			mouse.YPos = -1;
			mouse.scroll = mousedevice.getScroll();
		}
		PutEvent(mouse);

	}

	private final CEEventTouch TouchEvent = new CEEventTouch();

	public void UpdateTouchEvent(int index) {

		CETouchPad device = (CETouchPad) CEDeviceManager.getInstance().getDevice(TOUCH_PAD_LISTENER);
		TouchEvent.setData(index, device.getXpos(), device.getYpos(), device.getStatus());

		PutEvent(TouchEvent);
	}

	private void TalkTouchEvent(CEEventTouch event) {
		if (EventListenerTable.containsKey(TOUCH_PAD_LISTENER)) {
			ArrayList<CEEventListener> listener = EventListenerTable.get(TOUCH_PAD_LISTENER);
			for (int i = 0; i < listener.size(); i++) {
				if (listener.get(i) instanceof CEEventListenerTouch) {
					CEEventListenerTouch t = (CEEventListenerTouch) listener.get(i);
					if (CESceneManager.getInstance().getCurrentScene().isExit() == false) {

						if (t.TargetScene == CESceneManager.getInstance().getCurrentScene()) {
							event.TargetObject = t.TargetObject;
							t.ListenEvent(event);

						}
					}
				}
			}
			for (int i = 0; i < event.dataList.size(); i++) {
				if (event.dataList.get(i).status == CETouchPad.CE_TOUCH_UP) {
					System.out.println("Im Remove that Index: " + event.dataList.get(i).ID + " Status : "
							+ event.dataList.get(i).status);
					event.dataList.remove(i);

				}
			}
		}
	}

	public static CEEventDispatcher getInstance() {
		return Instance;
	}

}
