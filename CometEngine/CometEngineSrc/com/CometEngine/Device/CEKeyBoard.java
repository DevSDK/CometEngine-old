package com.CometEngine.Device;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.CometEngine.Event.CEEventDispatcher;

public class CEKeyBoard extends CEDevice {

	public static final int CE_KEY_RELEASE = 0;
	public static final int CE_KEY_PRESS = 1;
	public static final int CE_KEY_REPEAT = 2;
	public static final int CE_KEY_UNKNOWN = -1;
	public static final int CE_KEY_SPACE = 32;
	public static final int CE_KEY_APOSTROPHE = 39;
	public static final int CE_KEY_COMMA = 44;
	public static final int CE_KEY_MINUS = 45;
	public static final int CE_KEY_PERIOD = 46;
	public static final int CE_KEY_SLASH = 47;
	public static final int CE_KEY_0 = 48;
	public static final int CE_KEY_1 = 49;
	public static final int CE_KEY_2 = 50;
	public static final int CE_KEY_3 = 51;
	public static final int CE_KEY_4 = 52;
	public static final int CE_KEY_5 = 53;
	public static final int CE_KEY_6 = 54;
	public static final int CE_KEY_7 = 55;
	public static final int CE_KEY_8 = 56;
	public static final int CE_KEY_9 = 57;
	public static final int CE_KEY_SEMICOLON = 59;
	public static final int CE_KEY_EQUAL = 61;
	public static final int CE_KEY_A = 65;
	public static final int CE_KEY_B = 66;
	public static final int CE_KEY_C = 67;
	public static final int CE_KEY_D = 68;
	public static final int CE_KEY_E = 69;
	public static final int CE_KEY_F = 70;
	public static final int CE_KEY_G = 71;
	public static final int CE_KEY_H = 72;
	public static final int CE_KEY_I = 73;
	public static final int CE_KEY_J = 74;
	public static final int CE_KEY_K = 75;
	public static final int CE_KEY_L = 76;
	public static final int CE_KEY_M = 77;
	public static final int CE_KEY_N = 78;
	public static final int CE_KEY_O = 79;
	public static final int CE_KEY_P = 80;
	public static final int CE_KEY_Q = 81;
	public static final int CE_KEY_R = 82;
	public static final int CE_KEY_S = 83;
	public static final int CE_KEY_T = 84;
	public static final int CE_KEY_U = 85;
	public static final int CE_KEY_V = 86;
	public static final int CE_KEY_W = 87;
	public static final int CE_KEY_X = 88;
	public static final int CE_KEY_Y = 89;
	public static final int CE_KEY_Z = 90;
	public static final int CE_KEY_LEFT_BRACKET = 91;
	public static final int CE_KEY_BACKSLASH = 92;
	public static final int CE_KEY_RIGHT_BRACKET = 93;
	public static final int CE_KEY_GRAVE_ACCENT = 96;
	public static final int CE_KEY_WORLD_1 = 161;
	public static final int CE_KEY_WORLD_2 = 162;
	public static final int CE_KEY_ESCAPE = 256;
	public static final int CE_KEY_ENTER = 257;
	public static final int CE_KEY_TAB = 258;
	public static final int CE_KEY_BACKSPACE = 259;
	public static final int CE_KEY_INSERT = 260;
	public static final int CE_KEY_DELETE = 261;
	public static final int CE_KEY_RIGHT = 262;
	public static final int CE_KEY_LEFT = 263;
	public static final int CE_KEY_DOWN = 264;
	public static final int CE_KEY_UP = 265;
	public static final int CE_KEY_PAGE_UP = 266;
	public static final int CE_KEY_PAGE_DOWN = 267;
	public static final int CE_KEY_HOME = 268;
	public static final int CE_KEY_END = 269;
	public static final int CE_KEY_CAPS_LOCK = 280;
	public static final int CE_KEY_SCROLL_LOCK = 281;
	public static final int CE_KEY_NUM_LOCK = 282;
	public static final int CE_KEY_PRINT_SCREEN = 283;
	public static final int CE_KEY_PAUSE = 284;
	public static final int CE_KEY_F1 = 290;
	public static final int CE_KEY_F2 = 291;
	public static final int CE_KEY_F3 = 292;
	public static final int CE_KEY_F4 = 293;
	public static final int CE_KEY_F5 = 294;
	public static final int CE_KEY_F6 = 295;
	public static final int CE_KEY_F7 = 296;
	public static final int CE_KEY_F8 = 297;
	public static final int CE_KEY_F9 = 298;
	public static final int CE_KEY_F10 = 299;
	public static final int CE_KEY_F11 = 300;
	public static final int CE_KEY_F12 = 301;
	public static final int CE_KEY_F13 = 302;
	public static final int CE_KEY_F14 = 303;
	public static final int CE_KEY_F15 = 304;
	public static final int CE_KEY_F16 = 305;
	public static final int CE_KEY_F17 = 306;
	public static final int CE_KEY_F18 = 307;
	public static final int CE_KEY_F19 = 308;
	public static final int CE_KEY_F20 = 309;
	public static final int CE_KEY_F21 = 310;
	public static final int CE_KEY_F22 = 311;
	public static final int CE_KEY_F23 = 312;
	public static final int CE_KEY_F24 = 313;
	public static final int CE_KEY_F25 = 314;
	public static final int CE_KEY_KP_0 = 320;
	public static final int CE_KEY_KP_1 = 321;
	public static final int CE_KEY_KP_2 = 322;
	public static final int CE_KEY_KP_3 = 323;
	public static final int CE_KEY_KP_4 = 324;
	public static final int CE_KEY_KP_5 = 325;
	public static final int CE_KEY_KP_6 = 326;
	public static final int CE_KEY_KP_7 = 327;
	public static final int CE_KEY_KP_8 = 328;
	public static final int CE_KEY_KP_9 = 329;
	public static final int CE_KEY_KP_DECIMAL = 330;
	public static final int CE_KEY_KP_DIVIDE = 331;
	public static final int CE_KEY_KP_MULTIPLY = 332;
	public static final int CE_KEY_KP_SUBTRACT = 333;
	public static final int CE_KEY_KP_ADD = 334;
	public static final int CE_KEY_KP_ENTER = 335;
	public static final int CE_KEY_KP_EQUAL = 336;
	public static final int CE_KEY_LEFT_SHIFT = 340;
	public static final int CE_KEY_LEFT_CONTROL = 341;
	public static final int CE_KEY_LEFT_ALT = 342;
	public static final int CE_KEY_LEFT_SUPER = 343;
	public static final int CE_KEY_RIGHT_SHIFT = 344;
	public static final int CE_KEY_RIGHT_CONTROL = 345;
	public static final int CE_KEY_RIGHT_ALT = 346;
	public static final int CE_KEY_RIGHT_SUPER = 347;
	public static final int CE_KEY_MENU = 348;
	public static final int CE_KEY_LAST = 348;
	public static final int CE_MOD_SHIFT = 1;
	public static final int CE_MOD_CONTROL = 2;
	public static final int CE_MOD_ALT = 4;
	public static final int CE_MOD_SUPER = 8;

	private void INITKEYSET() {
		int[] KEYSETS = new int[] { CE_KEY_RELEASE, CE_KEY_PRESS, CE_KEY_REPEAT, CE_KEY_UNKNOWN, CE_KEY_SPACE, CE_KEY_APOSTROPHE,
				CE_KEY_COMMA, CE_KEY_MINUS, CE_KEY_PERIOD, CE_KEY_SLASH, CE_KEY_0, CE_KEY_1, CE_KEY_2, CE_KEY_3,
				CE_KEY_4, CE_KEY_5, CE_KEY_6, CE_KEY_7, CE_KEY_8, CE_KEY_9, CE_KEY_SEMICOLON, CE_KEY_EQUAL, CE_KEY_A,
				CE_KEY_B, CE_KEY_C, CE_KEY_D, CE_KEY_E, CE_KEY_F, CE_KEY_G, CE_KEY_H, CE_KEY_I, CE_KEY_J, CE_KEY_K,
				CE_KEY_L, CE_KEY_M, CE_KEY_N, CE_KEY_O, CE_KEY_P, CE_KEY_Q, CE_KEY_R, CE_KEY_S, CE_KEY_T, CE_KEY_U,
				CE_KEY_V, CE_KEY_W, CE_KEY_X, CE_KEY_Y, CE_KEY_Z, CE_KEY_LEFT_BRACKET, CE_KEY_BACKSLASH,
				CE_KEY_RIGHT_BRACKET, CE_KEY_GRAVE_ACCENT, CE_KEY_WORLD_1, CE_KEY_WORLD_2, CE_KEY_ESCAPE, CE_KEY_ENTER,
				CE_KEY_TAB, CE_KEY_BACKSPACE, CE_KEY_INSERT, CE_KEY_DELETE, CE_KEY_RIGHT, CE_KEY_LEFT, CE_KEY_DOWN,
				CE_KEY_UP, CE_KEY_PAGE_UP, CE_KEY_PAGE_DOWN, CE_KEY_HOME, CE_KEY_END, CE_KEY_CAPS_LOCK,
				CE_KEY_SCROLL_LOCK, CE_KEY_NUM_LOCK, CE_KEY_PRINT_SCREEN, CE_KEY_PAUSE, CE_KEY_F1, CE_KEY_F2, CE_KEY_F3,
				CE_KEY_F4, CE_KEY_F5, CE_KEY_F6, CE_KEY_F7, CE_KEY_F8, CE_KEY_F9, CE_KEY_F10, CE_KEY_F11, CE_KEY_F12,
				CE_KEY_F13, CE_KEY_F14, CE_KEY_F15, CE_KEY_F16, CE_KEY_F17, CE_KEY_F18, CE_KEY_F19, CE_KEY_F20,
				CE_KEY_F21, CE_KEY_F22, CE_KEY_F23, CE_KEY_F24, CE_KEY_F25, CE_KEY_KP_0, CE_KEY_KP_1, CE_KEY_KP_2,
				CE_KEY_KP_3, CE_KEY_KP_4, CE_KEY_KP_5, CE_KEY_KP_6, CE_KEY_KP_7, CE_KEY_KP_8, CE_KEY_KP_9,
				CE_KEY_KP_DECIMAL, CE_KEY_KP_DIVIDE, CE_KEY_KP_MULTIPLY, CE_KEY_KP_SUBTRACT, CE_KEY_KP_ADD,
				CE_KEY_KP_ENTER, CE_KEY_KP_EQUAL, CE_KEY_LEFT_SHIFT, CE_KEY_LEFT_CONTROL, CE_KEY_LEFT_ALT,
				CE_KEY_LEFT_SUPER, CE_KEY_RIGHT_SHIFT, CE_KEY_RIGHT_CONTROL, CE_KEY_RIGHT_ALT, CE_KEY_RIGHT_SUPER,
				CE_KEY_MENU, CE_KEY_LAST, CE_MOD_SHIFT, CE_MOD_CONTROL, CE_MOD_ALT, CE_MOD_SUPER };

		for (int i = 0; i < KEYSETS.length; i++) {
			KeyTable.put(KEYSETS[i], new InputStatus(0, false));
		}

	}

	public CEKeyBoard() {
		INITKEYSET();
	}

	private int PushedKeyCounter = 0;

	public class InputStatus {
		public InputStatus(int stat, boolean psuhed) {
			status = stat;
			this.Pushed = psuhed;
		}

		int status = 0;
		boolean Pushed;

		public int getStat() {
			return status;
		}

		public void OnceStatUpper() {
				if (status == 1)
					status = 2;
		}
	}	
	

	private final Map<Integer, InputStatus> KeyTable = new HashMap<Integer, InputStatus>();
	private final ArrayList<Integer> PushedKeyArray = new ArrayList<Integer>();

	private int NowMode = 0;

	public void PushKey(int key, int status, int mode) {
		this.NowMode = mode;

		if (KeyTable.containsKey(key)) {
			if (KeyTable.get(key).Pushed == false) {
				KeyTable.get(key).status = 1;
				PushedKeyCounter++;
				PushedKeyArray.add(key);
				KeyTable.get(key).Pushed = true;
			}
		}
	}

	public int getMode() {
		return NowMode;
	}

	public Map<Integer, InputStatus> getKeyMap() {
		return KeyTable;
	}

	public ArrayList<Integer> getPushedKeys() {
		return PushedKeyArray;
	}

	public void ResetKeySet() {
		for (InputStatus input : KeyTable.values()) {
			input.Pushed = false;

		}
		PushedKeyCounter = 0;
	}

	public int getGetPushedKeyCounter() {
		return PushedKeyCounter;
	}

	public void RelaseKey(int key, int mode) {
		this.NowMode = mode;
		if (KeyTable.containsKey(key)) {
			KeyTable.get(key).status = 0;
			if (KeyTable.get(key).Pushed) {
				CEEventDispatcher.getInstance().RelaseKeyBoardEvents();
				PushedKeyCounter--;
				PushedKeyArray.remove((Integer) key);
				KeyTable.get(key).Pushed = false;
			}
		}
	}

	CEEventDispatcher keybord = new CEEventDispatcher();

	public CEEventDispatcher getKeyBoardEventObject() {

		return keybord;
	}

	}
