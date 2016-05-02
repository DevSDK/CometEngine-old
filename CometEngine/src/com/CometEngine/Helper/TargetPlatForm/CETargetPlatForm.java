package com.CometEngine.Helper.TargetPlatForm;

public class CETargetPlatForm {
	private enum PLATFORM
	{
		CE_NULL, CE_WIN32, CE_ANDROID, CE_IOS, CE_MAC
	}
	
	private static boolean bTargeted = false;
	private static PLATFORM TargetPlatforms = PLATFORM.CE_NULL;
	
	
	public static boolean initTargetPlatform(PLATFORM platform)
	{
		if(bTargeted)
			return false;
		bTargeted = true;
		TargetPlatforms = platform;
		return true;
	}
	public static PLATFORM getPlatform()
	{
		return TargetPlatforms;
	}
	
	
}
