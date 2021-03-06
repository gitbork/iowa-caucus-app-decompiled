package com.drew.metadata.wav;

import androidx.exifinterface.media.ExifInterface;
import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Directory;
import com.drew.metadata.exif.makernotes.CanonMakernoteDirectory;
import com.drew.metadata.exif.makernotes.FujifilmMakernoteDirectory;
import com.drew.metadata.exif.makernotes.NikonType2MakernoteDirectory;
import com.drew.metadata.exif.makernotes.OlympusCameraSettingsMakernoteDirectory;
import com.drew.metadata.exif.makernotes.OlympusFocusInfoMakernoteDirectory;
import com.drew.metadata.exif.makernotes.OlympusImageProcessingMakernoteDirectory;
import com.drew.metadata.exif.makernotes.OlympusMakernoteDirectory;
import com.drew.metadata.exif.makernotes.OlympusRawInfoMakernoteDirectory;
import com.drew.metadata.exif.makernotes.PanasonicMakernoteDirectory;
import com.drew.metadata.exif.makernotes.SonyType1MakernoteDirectory;
import com.drew.metadata.iptc.IptcDirectory;
import com.drew.metadata.photoshop.PhotoshopDirectory;
import com.facebook.imagepipeline.memory.BitmapCounterConfig;
import java.util.HashMap;

public class WavDirectory extends Directory {
    public static final String CHUNK_DATA = "data";
    public static final String CHUNK_FORMAT = "fmt ";
    public static final String FORMAT = "WAVE";
    public static final String LIST_INFO = "INFO";
    public static final int TAG_ARTIST = 7;
    public static final int TAG_BITS_PER_SAMPLE = 6;
    public static final int TAG_BLOCK_ALIGNMENT = 5;
    public static final int TAG_BYTES_PER_SEC = 4;
    public static final int TAG_CHANNELS = 2;
    public static final int TAG_COMMENTS = 13;
    public static final int TAG_COPYRIGHT = 14;
    public static final int TAG_DATE_CREATED = 11;
    public static final int TAG_DURATION = 16;
    public static final int TAG_FORMAT = 1;
    public static final int TAG_GENRE = 12;
    public static final int TAG_PRODUCT = 9;
    public static final int TAG_SAMPLES_PER_SEC = 3;
    public static final int TAG_SOFTWARE = 15;
    public static final int TAG_TITLE = 8;
    public static final int TAG_TRACK_NUMBER = 10;
    @NotNull
    protected static final transient HashMap<Integer, String> _audioEncodingMap = new HashMap<>();
    @NotNull
    protected static final transient HashMap<String, Integer> _tagIntegerMap = new HashMap<>();
    @NotNull
    protected static final HashMap<Integer, String> _tagNameMap = new HashMap<>();

    @NotNull
    public String getName() {
        return "WAV";
    }

    static {
        HashMap<String, Integer> hashMap = _tagIntegerMap;
        Integer valueOf = Integer.valueOf(7);
        hashMap.put("IART", valueOf);
        HashMap<String, Integer> hashMap2 = _tagIntegerMap;
        Integer valueOf2 = Integer.valueOf(8);
        hashMap2.put("INAM", valueOf2);
        HashMap<String, Integer> hashMap3 = _tagIntegerMap;
        Integer valueOf3 = Integer.valueOf(9);
        hashMap3.put("IPRD", valueOf3);
        HashMap<String, Integer> hashMap4 = _tagIntegerMap;
        Integer valueOf4 = Integer.valueOf(10);
        hashMap4.put("ITRK", valueOf4);
        HashMap<String, Integer> hashMap5 = _tagIntegerMap;
        Integer valueOf5 = Integer.valueOf(11);
        hashMap5.put("ICRD", valueOf5);
        HashMap<String, Integer> hashMap6 = _tagIntegerMap;
        Integer valueOf6 = Integer.valueOf(12);
        hashMap6.put("IGNR", valueOf6);
        HashMap<String, Integer> hashMap7 = _tagIntegerMap;
        Integer valueOf7 = Integer.valueOf(13);
        hashMap7.put("ICMT", valueOf7);
        _tagIntegerMap.put("ICOP", Integer.valueOf(14));
        _tagIntegerMap.put("ISFT", Integer.valueOf(15));
        _tagNameMap.put(Integer.valueOf(1), "Format");
        _tagNameMap.put(Integer.valueOf(2), "Channels");
        _tagNameMap.put(Integer.valueOf(3), "Samples Per Second");
        _tagNameMap.put(Integer.valueOf(4), "Bytes Per Second");
        _tagNameMap.put(Integer.valueOf(5), "Block Alignment");
        _tagNameMap.put(Integer.valueOf(6), "Bits Per Sample");
        _tagNameMap.put(valueOf, ExifInterface.TAG_ARTIST);
        _tagNameMap.put(valueOf2, "Title");
        _tagNameMap.put(valueOf3, "Product");
        _tagNameMap.put(valueOf4, "Track Number");
        _tagNameMap.put(valueOf5, "Date Created");
        _tagNameMap.put(valueOf6, "Genre");
        _tagNameMap.put(valueOf7, "Comments");
        _tagNameMap.put(Integer.valueOf(14), ExifInterface.TAG_COPYRIGHT);
        _tagNameMap.put(Integer.valueOf(15), ExifInterface.TAG_SOFTWARE);
        _tagNameMap.put(Integer.valueOf(16), "Duration");
        _audioEncodingMap.put(Integer.valueOf(1), "Microsoft PCM");
        _audioEncodingMap.put(Integer.valueOf(2), "Microsoft ADPCM");
        _audioEncodingMap.put(Integer.valueOf(3), "Microsoft IEEE float");
        _audioEncodingMap.put(Integer.valueOf(4), "Compaq VSELP");
        _audioEncodingMap.put(Integer.valueOf(5), "IBM CVSD");
        _audioEncodingMap.put(Integer.valueOf(6), "Microsoft a-Law");
        _audioEncodingMap.put(valueOf, "Microsoft u-Law");
        _audioEncodingMap.put(valueOf2, "Microsoft DTS");
        _audioEncodingMap.put(valueOf3, "DRM");
        _audioEncodingMap.put(valueOf4, "WMA 9 Speech");
        _audioEncodingMap.put(valueOf5, "Microsoft Windows Media RT Voice");
        _audioEncodingMap.put(Integer.valueOf(16), "OKI-ADPCM");
        _audioEncodingMap.put(Integer.valueOf(17), "Intel IMA/DVI-ADPCM");
        _audioEncodingMap.put(Integer.valueOf(18), "Videologic Mediaspace ADPCM");
        _audioEncodingMap.put(Integer.valueOf(19), "Sierra ADPCM");
        _audioEncodingMap.put(Integer.valueOf(20), "Antex G.723 ADPCM");
        _audioEncodingMap.put(Integer.valueOf(21), "DSP Solutions DIGISTD");
        _audioEncodingMap.put(Integer.valueOf(22), "DSP Solutions DIGIFIX");
        _audioEncodingMap.put(Integer.valueOf(23), "Dialoic OKI ADPCM");
        _audioEncodingMap.put(Integer.valueOf(24), "Media Vision ADPCM");
        _audioEncodingMap.put(Integer.valueOf(25), "HP CU");
        _audioEncodingMap.put(Integer.valueOf(26), "HP Dynamic Voice");
        _audioEncodingMap.put(Integer.valueOf(32), "Yamaha ADPCM");
        _audioEncodingMap.put(Integer.valueOf(33), "SONARC Speech Compression");
        _audioEncodingMap.put(Integer.valueOf(34), "DSP Group True Speech");
        _audioEncodingMap.put(Integer.valueOf(35), "Echo Speech Corp.");
        _audioEncodingMap.put(Integer.valueOf(36), "Virtual Music Audiofile AF36");
        _audioEncodingMap.put(Integer.valueOf(37), "Audio Processing Tech.");
        _audioEncodingMap.put(Integer.valueOf(38), "Virtual Music Audiofile AF10");
        _audioEncodingMap.put(Integer.valueOf(39), "Aculab Prosody 1612");
        _audioEncodingMap.put(Integer.valueOf(40), "Merging Tech. LRC");
        _audioEncodingMap.put(Integer.valueOf(48), "Dolby AC2");
        _audioEncodingMap.put(Integer.valueOf(49), "Microsoft GSM610");
        _audioEncodingMap.put(Integer.valueOf(50), "MSN Audio");
        _audioEncodingMap.put(Integer.valueOf(51), "Antex ADPCME");
        _audioEncodingMap.put(Integer.valueOf(52), "Control Resources VQLPC");
        _audioEncodingMap.put(Integer.valueOf(53), "DSP Solutions DIGIREAL");
        _audioEncodingMap.put(Integer.valueOf(54), "DSP Solutions DIGIADPCM");
        _audioEncodingMap.put(Integer.valueOf(55), "Control Resources CR10");
        _audioEncodingMap.put(Integer.valueOf(56), "Natural MicroSystems VBX ADPCM");
        _audioEncodingMap.put(Integer.valueOf(57), "Crystal Semiconductor IMA ADPCM");
        _audioEncodingMap.put(Integer.valueOf(58), "Echo Speech ECHOSC3");
        _audioEncodingMap.put(Integer.valueOf(59), "Rockwell ADPCM");
        _audioEncodingMap.put(Integer.valueOf(60), "Rockwell DIGITALK");
        _audioEncodingMap.put(Integer.valueOf(61), "Xebec Multimedia");
        _audioEncodingMap.put(Integer.valueOf(64), "Antex G.721 ADPCM");
        _audioEncodingMap.put(Integer.valueOf(65), "Antex G.728 CELP");
        _audioEncodingMap.put(Integer.valueOf(66), "Microsoft MSG723");
        _audioEncodingMap.put(Integer.valueOf(67), "IBM AVC ADPCM");
        _audioEncodingMap.put(Integer.valueOf(69), "ITU-T G.726");
        _audioEncodingMap.put(Integer.valueOf(80), "Microsoft MPEG");
        _audioEncodingMap.put(Integer.valueOf(81), "RT23 or PAC");
        _audioEncodingMap.put(Integer.valueOf(82), "InSoft RT24");
        _audioEncodingMap.put(Integer.valueOf(83), "InSoft PAC");
        _audioEncodingMap.put(Integer.valueOf(85), "MP3");
        _audioEncodingMap.put(Integer.valueOf(89), "Cirrus");
        _audioEncodingMap.put(Integer.valueOf(96), "Cirrus Logic");
        _audioEncodingMap.put(Integer.valueOf(97), "ESS Tech. PCM");
        _audioEncodingMap.put(Integer.valueOf(98), "Voxware Inc.");
        _audioEncodingMap.put(Integer.valueOf(99), "Canopus ATRAC");
        _audioEncodingMap.put(Integer.valueOf(100), "APICOM G.726 ADPCM");
        _audioEncodingMap.put(Integer.valueOf(101), "APICOM G.722 ADPCM");
        _audioEncodingMap.put(Integer.valueOf(102), "Microsoft DSAT");
        _audioEncodingMap.put(Integer.valueOf(103), "Micorsoft DSAT DISPLAY");
        _audioEncodingMap.put(Integer.valueOf(105), "Voxware Byte Aligned");
        _audioEncodingMap.put(Integer.valueOf(112), "Voxware AC8");
        _audioEncodingMap.put(Integer.valueOf(113), "Voxware AC10");
        _audioEncodingMap.put(Integer.valueOf(114), "Voxware AC16");
        _audioEncodingMap.put(Integer.valueOf(115), "Voxware AC20");
        _audioEncodingMap.put(Integer.valueOf(116), "Voxware MetaVoice");
        _audioEncodingMap.put(Integer.valueOf(117), "Voxware MetaSound");
        _audioEncodingMap.put(Integer.valueOf(118), "Voxware RT29HW");
        _audioEncodingMap.put(Integer.valueOf(119), "Voxware VR12");
        _audioEncodingMap.put(Integer.valueOf(120), "Voxware VR18");
        _audioEncodingMap.put(Integer.valueOf(PanasonicMakernoteDirectory.TAG_INTELLIGENT_D_RANGE), "Voxware TQ40");
        _audioEncodingMap.put(Integer.valueOf(122), "Voxware SC3");
        _audioEncodingMap.put(Integer.valueOf(123), "Voxware SC3");
        _audioEncodingMap.put(Integer.valueOf(128), "Soundsoft");
        _audioEncodingMap.put(Integer.valueOf(129), "Voxware TQ60");
        _audioEncodingMap.put(Integer.valueOf(NikonType2MakernoteDirectory.TAG_ADAPTER), "Microsoft MSRT24");
        _audioEncodingMap.put(Integer.valueOf(131), "AT&T G.729A");
        _audioEncodingMap.put(Integer.valueOf(NikonType2MakernoteDirectory.TAG_LENS), "Motion Pixels MVI MV12");
        _audioEncodingMap.put(Integer.valueOf(NikonType2MakernoteDirectory.TAG_MANUAL_FOCUS_DISTANCE), "DataFusion G.726");
        _audioEncodingMap.put(Integer.valueOf(NikonType2MakernoteDirectory.TAG_DIGITAL_ZOOM), "DataFusion GSM610");
        _audioEncodingMap.put(Integer.valueOf(136), "Iterated Systems Audio");
        _audioEncodingMap.put(Integer.valueOf(137), "Onlive");
        _audioEncodingMap.put(Integer.valueOf(138), "Multitude, Inc. FT SX20");
        _audioEncodingMap.put(Integer.valueOf(NikonType2MakernoteDirectory.TAG_LENS_STOPS), "Infocom ITS A/S G.721 ADPCM");
        _audioEncodingMap.put(Integer.valueOf(140), "Convedia G729");
        _audioEncodingMap.put(Integer.valueOf(141), "Not specified congruency, Inc.");
        _audioEncodingMap.put(Integer.valueOf(145), "Siemens SBC24");
        _audioEncodingMap.put(Integer.valueOf(146), "Sonic Foundry Dolby AC3 APDIF");
        _audioEncodingMap.put(Integer.valueOf(147), "MediaSonic G.723");
        _audioEncodingMap.put(Integer.valueOf(148), "Aculab Prosody 8kbps");
        _audioEncodingMap.put(Integer.valueOf(151), "ZyXEL ADPCM");
        _audioEncodingMap.put(Integer.valueOf(152), "Philips LPCBB");
        _audioEncodingMap.put(Integer.valueOf(153), "Studer Professional Audio Packed");
        _audioEncodingMap.put(Integer.valueOf(160), "Malden PhonyTalk");
        _audioEncodingMap.put(Integer.valueOf(CanonMakernoteDirectory.TAG_TONE_CURVE_TABLE), "Racal Recorder GSM");
        _audioEncodingMap.put(Integer.valueOf(162), "Racal Recorder G720.a");
        _audioEncodingMap.put(Integer.valueOf(163), "Racal G723.1");
        _audioEncodingMap.put(Integer.valueOf(164), "Racal Tetra ACELP");
        _audioEncodingMap.put(Integer.valueOf(176), "NEC AAC NEC Corporation");
        _audioEncodingMap.put(Integer.valueOf(255), "AAC");
        _audioEncodingMap.put(Integer.valueOf(256), "Rhetorex ADPCM");
        _audioEncodingMap.put(Integer.valueOf(257), "IBM u-Law");
        _audioEncodingMap.put(Integer.valueOf(258), "IBM a-Law");
        _audioEncodingMap.put(Integer.valueOf(259), "IBM ADPCM");
        _audioEncodingMap.put(Integer.valueOf(273), "Vivo G.723");
        _audioEncodingMap.put(Integer.valueOf(274), "Vivo Siren");
        _audioEncodingMap.put(Integer.valueOf(288), "Philips Speech Processing CELP");
        _audioEncodingMap.put(Integer.valueOf(289), "Philips Speech Processing GRUNDIG");
        _audioEncodingMap.put(Integer.valueOf(291), "Digital G.723");
        _audioEncodingMap.put(Integer.valueOf(293), "Sanyo LD ADPCM");
        _audioEncodingMap.put(Integer.valueOf(OlympusRawInfoMakernoteDirectory.TagWbRbLevelsDaylightFluor), "Sipro Lab ACEPLNET");
        _audioEncodingMap.put(Integer.valueOf(305), "Sipro Lab ACELP4800");
        _audioEncodingMap.put(Integer.valueOf(306), "Sipro Lab ACELP8V3");
        _audioEncodingMap.put(Integer.valueOf(307), "Sipro Lab G.729");
        _audioEncodingMap.put(Integer.valueOf(308), "Sipro Lab G.729A");
        _audioEncodingMap.put(Integer.valueOf(309), "Sipro Lab Kelvin");
        _audioEncodingMap.put(Integer.valueOf(310), "VoiceAge AMR");
        _audioEncodingMap.put(Integer.valueOf(320), "Dictaphone G.726 ADPCM");
        _audioEncodingMap.put(Integer.valueOf(IptcDirectory.TAG_TIME_SENT), "Qualcomm PureVoice");
        _audioEncodingMap.put(Integer.valueOf(337), "Qualcomm HalfRate");
        _audioEncodingMap.put(Integer.valueOf(341), "Ring Zero Systems TUBGSM");
        _audioEncodingMap.put(Integer.valueOf(352), "Microsoft Audio1");
        _audioEncodingMap.put(Integer.valueOf(353), "Windows Media Audio V2 V7 V8 V9 / DivX audio (WMA) / Alex AC3 Audio");
        _audioEncodingMap.put(Integer.valueOf(354), "Windows Media Audio Professional V9");
        _audioEncodingMap.put(Integer.valueOf(355), "Windows Media Audio Lossless V9");
        _audioEncodingMap.put(Integer.valueOf(IptcDirectory.TAG_UNIQUE_OBJECT_NAME), "WMA Pro over S/PDIF");
        _audioEncodingMap.put(Integer.valueOf(368), "UNISYS NAP ADPCM");
        _audioEncodingMap.put(Integer.valueOf(369), "UNISYS NAP ULAW");
        _audioEncodingMap.put(Integer.valueOf(370), "UNISYS NAP ALAW");
        _audioEncodingMap.put(Integer.valueOf(371), "UNISYS NAP 16K");
        _audioEncodingMap.put(Integer.valueOf(372), "MM SYCOM ACM SYC008 SyCom Technologies");
        _audioEncodingMap.put(Integer.valueOf(373), "MM SYCOM ACM SYC701 G726L SyCom Technologies");
        _audioEncodingMap.put(Integer.valueOf(374), "MM SYCOM ACM SYC701 CELP54 SyCom Technologies");
        _audioEncodingMap.put(Integer.valueOf(375), "MM SYCOM ACM SYC701 CELP68 SyCom Technologies");
        _audioEncodingMap.put(Integer.valueOf(IptcDirectory.TAG_ARM_IDENTIFIER), "Knowledge Adventure ADPCM");
        _audioEncodingMap.put(Integer.valueOf(BitmapCounterConfig.DEFAULT_MAX_BITMAP_COUNT), "Fraunhofer IIS MPEG2AAC");
        _audioEncodingMap.put(Integer.valueOf(400), "Digital Theater Systems DTS DS");
        _audioEncodingMap.put(Integer.valueOf(512), "Creative Labs ADPCM");
        _audioEncodingMap.put(Integer.valueOf(514), "Creative Labs FASTSPEECH8");
        _audioEncodingMap.put(Integer.valueOf(515), "Creative Labs FASTSPEECH10");
        _audioEncodingMap.put(Integer.valueOf(528), "UHER ADPCM");
        _audioEncodingMap.put(Integer.valueOf(533), "Ulead DV ACM");
        _audioEncodingMap.put(Integer.valueOf(534), "Ulead DV ACM");
        _audioEncodingMap.put(Integer.valueOf(544), "Quarterdeck Corp.");
        _audioEncodingMap.put(Integer.valueOf(560), "I-Link VC");
        _audioEncodingMap.put(Integer.valueOf(576), "Aureal Semiconductor Raw Sport");
        _audioEncodingMap.put(Integer.valueOf(IptcDirectory.TAG_ORIGINATING_PROGRAM), "ESST AC3");
        _audioEncodingMap.put(Integer.valueOf(IptcDirectory.TAG_BY_LINE), "Interactive Products HSX");
        _audioEncodingMap.put(Integer.valueOf(593), "Interactive Products RPELP");
        _audioEncodingMap.put(Integer.valueOf(608), "Consistent CS2");
        _audioEncodingMap.put(Integer.valueOf(624), "Sony SCX");
        _audioEncodingMap.put(Integer.valueOf(625), "Sony SCY");
        _audioEncodingMap.put(Integer.valueOf(626), "Sony ATRAC3");
        _audioEncodingMap.put(Integer.valueOf(IptcDirectory.TAG_SOURCE), "Sony SPC");
        _audioEncodingMap.put(Integer.valueOf(OlympusMakernoteDirectory.TAG_PREVIEW_IMAGE), "TELUM Telum Inc.");
        _audioEncodingMap.put(Integer.valueOf(641), "TELUMIA Telum Inc.");
        _audioEncodingMap.put(Integer.valueOf(645), "Norcom Voice Systems ADPCM");
        _audioEncodingMap.put(Integer.valueOf(768), "Fujitsu FM TOWNS SND");
        String str = "Fujitsu (not specified)";
        _audioEncodingMap.put(Integer.valueOf(769), str);
        _audioEncodingMap.put(Integer.valueOf(770), str);
        _audioEncodingMap.put(Integer.valueOf(771), str);
        _audioEncodingMap.put(Integer.valueOf(772), str);
        _audioEncodingMap.put(Integer.valueOf(773), str);
        _audioEncodingMap.put(Integer.valueOf(774), str);
        _audioEncodingMap.put(Integer.valueOf(OlympusCameraSettingsMakernoteDirectory.TagAfFineTuneAdj), str);
        _audioEncodingMap.put(Integer.valueOf(OlympusFocusInfoMakernoteDirectory.TagAfPoint), str);
        _audioEncodingMap.put(Integer.valueOf(848), "Micronas Semiconductors, Inc. Development");
        _audioEncodingMap.put(Integer.valueOf(849), "Micronas Semiconductors, Inc. CELP833");
        _audioEncodingMap.put(Integer.valueOf(1024), "Brooktree Digital");
        _audioEncodingMap.put(Integer.valueOf(1025), "Intel Music Coder (IMC)");
        _audioEncodingMap.put(Integer.valueOf(PhotoshopDirectory.TAG_LAYERS_GROUP_INFORMATION), "Ligos Indeo Audio");
        _audioEncodingMap.put(Integer.valueOf(1104), "QDesign Music");
        _audioEncodingMap.put(Integer.valueOf(OlympusCameraSettingsMakernoteDirectory.TagWhiteBalance2), "On2 VP7 On2 Technologies");
        _audioEncodingMap.put(Integer.valueOf(OlympusCameraSettingsMakernoteDirectory.TagWhiteBalanceTemperature), "On2 VP6 On2 Technologies");
        _audioEncodingMap.put(Integer.valueOf(1664), "AT&T VME VMPCM");
        _audioEncodingMap.put(Integer.valueOf(1665), "AT&T TCP");
        _audioEncodingMap.put(Integer.valueOf(1792), "YMPEG Alpha (dummy for MPEG-2 compressor)");
        _audioEncodingMap.put(Integer.valueOf(2222), "ClearJump LiteWave (lossless)");
        _audioEncodingMap.put(Integer.valueOf(4096), "Olivetti GSM");
        _audioEncodingMap.put(Integer.valueOf(4097), "Olivetti ADPCM");
        _audioEncodingMap.put(Integer.valueOf(4098), "Olivetti CELP");
        _audioEncodingMap.put(Integer.valueOf(4099), "Olivetti SBC");
        _audioEncodingMap.put(Integer.valueOf(4100), "Olivetti OPR");
        _audioEncodingMap.put(Integer.valueOf(FujifilmMakernoteDirectory.TAG_AUTO_BRACKETING), "Lernout & Hauspie");
        _audioEncodingMap.put(Integer.valueOf(FujifilmMakernoteDirectory.TAG_SEQUENCE_NUMBER), "Lernout & Hauspie CELP codec");
        String str2 = "Lernout & Hauspie SBC codec";
        _audioEncodingMap.put(Integer.valueOf(4354), str2);
        _audioEncodingMap.put(Integer.valueOf(OlympusImageProcessingMakernoteDirectory.TagUnknownBlock3), str2);
        _audioEncodingMap.put(Integer.valueOf(OlympusImageProcessingMakernoteDirectory.TagUnknownBlock4), str2);
        _audioEncodingMap.put(Integer.valueOf(FujifilmMakernoteDirectory.TAG_DYNAMIC_RANGE), "Norris Comm. Inc.");
        _audioEncodingMap.put(Integer.valueOf(FujifilmMakernoteDirectory.TAG_FILM_MODE), "ISIAudio");
        _audioEncodingMap.put(Integer.valueOf(OlympusFocusInfoMakernoteDirectory.TagSensorTemperature), "AT&T Soundspace Music Compression");
        _audioEncodingMap.put(Integer.valueOf(6172), "VoxWare RT24 speech codec");
        _audioEncodingMap.put(Integer.valueOf(6174), "Lucent elemedia AX24000P Music codec");
        _audioEncodingMap.put(Integer.valueOf(6513), "Sonic Foundry LOSSLESS");
        _audioEncodingMap.put(Integer.valueOf(6521), "Innings Telecom Inc. ADPCM");
        _audioEncodingMap.put(Integer.valueOf(7175), "Lucent SX8300P speech codec");
        _audioEncodingMap.put(Integer.valueOf(7180), "Lucent SX5363S G.723 compliant codec");
        _audioEncodingMap.put(Integer.valueOf(7939), "CUseeMe DigiTalk (ex-Rocwell)");
        _audioEncodingMap.put(Integer.valueOf(8132), "NCT Soft ALF2CD ACM");
        _audioEncodingMap.put(Integer.valueOf(8192), "FAST Multimedia DVM");
        _audioEncodingMap.put(Integer.valueOf(8193), "Dolby DTS (Digital Theater System)");
        _audioEncodingMap.put(Integer.valueOf(8194), "RealAudio 1 / 2 14.4");
        _audioEncodingMap.put(Integer.valueOf(8195), "RealAudio 1 / 2 28.8");
        _audioEncodingMap.put(Integer.valueOf(SonyType1MakernoteDirectory.TAG_CONTRAST), "RealAudio G2 / 8 Cook (low bitrate)");
        _audioEncodingMap.put(Integer.valueOf(SonyType1MakernoteDirectory.TAG_SATURATION), "RealAudio 3 / 4 / 5 Music (DNET)");
        _audioEncodingMap.put(Integer.valueOf(SonyType1MakernoteDirectory.TAG_SHARPNESS), "RealAudio 10 AAC (RAAC)");
        _audioEncodingMap.put(Integer.valueOf(SonyType1MakernoteDirectory.TAG_BRIGHTNESS), "RealAudio 10 AAC+ (RACP)");
        _audioEncodingMap.put(Integer.valueOf(9472), "Reserved range to 0x2600 Microsoft");
        _audioEncodingMap.put(Integer.valueOf(13075), "makeAVIS (ffvfw fake AVI sound from AviSynth scripts)");
        _audioEncodingMap.put(Integer.valueOf(16707), "Divio MPEG-4 AAC audio");
        _audioEncodingMap.put(Integer.valueOf(16897), "Nokia adaptive multirate");
        _audioEncodingMap.put(Integer.valueOf(16963), "Divio G726 Divio, Inc.");
        _audioEncodingMap.put(Integer.valueOf(17228), "LEAD Speech");
        _audioEncodingMap.put(Integer.valueOf(22092), "LEAD Vorbis");
        _audioEncodingMap.put(Integer.valueOf(22358), "WavPack Audio");
        _audioEncodingMap.put(Integer.valueOf(26447), "Ogg Vorbis (mode 1)");
        _audioEncodingMap.put(Integer.valueOf(26448), "Ogg Vorbis (mode 2)");
        _audioEncodingMap.put(Integer.valueOf(26449), "Ogg Vorbis (mode 3)");
        _audioEncodingMap.put(Integer.valueOf(26479), "Ogg Vorbis (mode 1+)");
        _audioEncodingMap.put(Integer.valueOf(26480), "Ogg Vorbis (mode 2+)");
        _audioEncodingMap.put(Integer.valueOf(26481), "Ogg Vorbis (mode 3+)");
        _audioEncodingMap.put(Integer.valueOf(28672), "3COM NBX 3Com Corporation");
        _audioEncodingMap.put(Integer.valueOf(28781), "FAAD AAC");
        _audioEncodingMap.put(Integer.valueOf(31265), "GSM-AMR (CBR, no SID)");
        _audioEncodingMap.put(Integer.valueOf(31266), "GSM-AMR (VBR, including SID)");
        _audioEncodingMap.put(Integer.valueOf(41216), "Comverse Infosys Ltd. G723 1");
        _audioEncodingMap.put(Integer.valueOf(41217), "Comverse Infosys Ltd. AVQSBC");
        _audioEncodingMap.put(Integer.valueOf(41218), "Comverse Infosys Ltd. OLDSBC");
        _audioEncodingMap.put(Integer.valueOf(41219), "Symbol Technologies G729A");
        _audioEncodingMap.put(Integer.valueOf(41220), "VoiceAge AMR WB VoiceAge Corporation");
        _audioEncodingMap.put(Integer.valueOf(41221), "Ingenient Technologies Inc. G726");
        _audioEncodingMap.put(Integer.valueOf(41222), "ISO/MPEG-4 advanced audio Coding");
        _audioEncodingMap.put(Integer.valueOf(41223), "Encore Software Ltd G726");
        _audioEncodingMap.put(Integer.valueOf(41225), "Speex ACM Codec xiph.org");
        _audioEncodingMap.put(Integer.valueOf(57260), "DebugMode SonicFoundry Vegas FrameServer ACM Codec");
        _audioEncodingMap.put(Integer.valueOf(59144), "Unknown -");
        _audioEncodingMap.put(Integer.valueOf(61868), "Free Lossless Audio Codec FLAC");
        _audioEncodingMap.put(Integer.valueOf(65534), "Extensible");
        _audioEncodingMap.put(Integer.valueOf(65535), "Development");
    }

    public WavDirectory() {
        setDescriptor(new WavDescriptor(this));
    }

    /* access modifiers changed from: protected */
    @NotNull
    public HashMap<Integer, String> getTagNameMap() {
        return _tagNameMap;
    }
}
