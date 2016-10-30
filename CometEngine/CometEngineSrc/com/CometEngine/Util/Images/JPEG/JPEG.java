package com.CometEngine.Util.Images.JPEG;


class JPEG
{
	final static int APP0  = 0xFFE0;   // JFIF APP0 segment marker
	final static int APP1  = 0xFFE1;
	final static int APP2  = 0xFFE2;
	final static int APP3  = 0xFFE3;
	final static int APP4  = 0xFFE4;
	final static int APP5  = 0xFFE5;
	final static int APP6  = 0xFFE6;
	final static int APP7  = 0xFFE7;
	final static int APP8  = 0xFFE8;
	final static int APP9  = 0xFFE9;
	final static int APP10 = 0xFFEA;
	final static int APP11 = 0xFFEB;
	final static int APP12 = 0xFFEC;
	final static int APP13 = 0xFFED;
	final static int APP14 = 0xFFEE;
	final static int APP15 = 0xFFEF;
	final static int COM   = 0xFFFE;   // Comment
	final static int DAC   = 0xFFCC;   // Define Arithmetic Table, usually unsupported
	final static int DHP   = 0xFFDE;   // Reserved, fatal error
	final static int DHT   = 0xFFC4;   // Define Huffman Table
	final static int DNL   = 0xFFDC;
	final static int DQT   = 0xFFDB;   // Define Quantization Table
	final static int DRI   = 0xFFDD;   // Define Restart Interval
	final static int EOI   = 0xFFD9;   // End Of Image
	final static int EXP   = 0xFFDF;   // Reserved, fatal error
	final static int JPG   = 0xFFC8;   // Reserved, fatal error
	final static int JPG0  = 0xFFF0;   // Reserved, fatal error
	final static int JPG13 = 0xFFFD;   // Reserved, fatal error
	final static int RST0  = 0xFFD0;   // RSTn are used for resync
	final static int RST1  = 0xFFD1;
	final static int RST2  = 0xFFD2;
	final static int RST3  = 0xFFD3;
	final static int RST4  = 0xFFD4;
	final static int RST5  = 0xFFD5;
	final static int RST6  = 0xFFD6;
	final static int RST7  = 0xFFD7;
	final static int SOF0  = 0xFFC0;   // Baseline
	final static int SOF1  = 0xFFC1;   // Extended sequential, Huffman
	final static int SOF2  = 0xFFC2;   // Progressive, Huffman
	final static int SOF3  = 0xFFC3;   // Unsupported; Lossless, Huffman
	final static int SOF5  = 0xFFC5;   // Unsupported; Differential sequential, Huffman
	final static int SOF6  = 0xFFC6;   // Unsupported; Differential progressive, Huffman
	final static int SOF7  = 0xFFC7;   // Unsupported; Differential lossless, Huffman
	final static int SOF9  = 0xFFC9;   // Extended sequential, arithmetic
	final static int SOF10 = 0xFFCA;   // Progressive, arithmetic
	final static int SOF11 = 0xFFCB;   // Unsupported; Lossless, Unsupported; arithmetic
	final static int SOF13 = 0xFFCD;   // Unsupported; Differential sequential, arithmetic
	final static int SOF14 = 0xFFCE;   // Unsupported; Differential progressive, arithmetic
	final static int SOF15 = 0xFFCF;   // Unsupported; Differential lossless, arithmetic
	final static int SOI   = 0xFFD8;   // Start Of Image
	final static int SOS   = 0xFFDA;   // Start Of Scan


	static String getSOFDescription(int aMarker)
	{
		switch (aMarker)
		{
			case SOF0:	return "Baseline";
			case SOF1:	return "Extended sequential, Huffman";
			case SOF2:	return "Progressive, Huffman";
			case SOF3:	return "Lossless, Huffman";
			case SOF5:	return "Differential sequential, Huffman";
			case SOF6:	return "Differential progressive, Huffman";
			case SOF7:	return "Differential lossless, Huffman";
			case SOF9:	return "Extended sequential, arithmetic coding";
			case SOF10:	return "Progressive, arithmetic coding";
			case SOF11:	return "Lossless, arithmetic coding";
			case SOF13:	return "Differential sequential, arithmetic coding";
			case SOF14:	return "Differential progressive, arithmetic coding";
			case SOF15:	return "Differential lossless, arithmetic coding";
			case APP0:	return "APP0";
			case APP1:	return "APP1";
			case APP2:	return "APP2";
			case APP3:	return "APP3";
			case APP4:	return "APP4";
			case APP5:	return "APP5";
			case APP6:	return "APP6";
			case APP7:	return "APP7";
			case APP8:	return "APP8";
			case APP9:	return "APP9";
			case APP10:	return "APP10";
			case APP11:	return "APP11";
			case APP12:	return "APP12";
			case APP13:	return "APP13";
			case APP14:	return "APP14";
			case APP15:	return "APP15";
			case COM:	return "Comment";
			case DAC:	return "Define Arithmetic Table";
			case DHP:	return "DHP";
			case DHT:	return "Define Huffman Table";
			case DNL:	return "DNL";
			case DQT:	return "Define Quantization Table";
			case DRI:	return "Define Restart Interval";
			case EOI:	return "End Of Image";
			case EXP:	return "EXP";
			case JPG:	return "JPG";
			case JPG0:	return "JPG0";
			case JPG13:	return "JPG13";
			case RST0:	return "RST0";
			case RST1:	return "RST1";
			case RST2:	return "RST2";
			case RST3:	return "RST3";
			case RST4:	return "RST4";
			case RST5:	return "RST5";
			case RST6:	return "RST6";
			case RST7:	return "RST7";
			case SOI:	return "Start Of Image";
			case SOS:	return "Start Of Scan";
			default:	return "Unknown";
		}
	}
}