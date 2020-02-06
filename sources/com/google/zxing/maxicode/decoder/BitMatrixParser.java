package com.google.zxing.maxicode.decoder;

import androidx.core.app.FrameMetricsAggregator;
import com.adobe.xmp.XMPError;
import com.drew.metadata.exif.ExifDirectoryBase;
import com.drew.metadata.exif.makernotes.CanonMakernoteDirectory;
import com.drew.metadata.exif.makernotes.LeicaMakernoteDirectory;
import com.drew.metadata.exif.makernotes.NikonType2MakernoteDirectory;
import com.drew.metadata.exif.makernotes.OlympusCameraSettingsMakernoteDirectory;
import com.drew.metadata.exif.makernotes.OlympusFocusInfoMakernoteDirectory;
import com.drew.metadata.exif.makernotes.OlympusImageProcessingMakernoteDirectory;
import com.drew.metadata.exif.makernotes.OlympusMakernoteDirectory;
import com.drew.metadata.exif.makernotes.OlympusRawInfoMakernoteDirectory;
import com.drew.metadata.exif.makernotes.PanasonicMakernoteDirectory;
import com.drew.metadata.exif.makernotes.SanyoMakernoteDirectory;
import com.drew.metadata.iptc.IptcDirectory;
import com.facebook.imagepipeline.memory.BitmapCounterConfig;
import com.facebook.imageutils.JfifUtil;
import com.google.firebase.storage.internal.ExponentialBackoffSender;
import com.google.logging.type.LogSeverity;
import com.google.zxing.common.BitMatrix;
import p006io.grpc.internal.GrpcUtil;
import p006io.sentry.connection.HttpConnection;

final class BitMatrixParser {
    private static final int[][] BITNR = {new int[]{PanasonicMakernoteDirectory.TAG_INTELLIGENT_D_RANGE, 120, 127, 126, NikonType2MakernoteDirectory.TAG_MANUAL_FOCUS_DISTANCE, NikonType2MakernoteDirectory.TAG_LENS, NikonType2MakernoteDirectory.TAG_LENS_STOPS, 138, 145, 144, 151, 150, 157, NikonType2MakernoteDirectory.TAG_SCENE_ASSIST, 163, 162, 169, NikonType2MakernoteDirectory.TAG_FLASH_INFO, NikonType2MakernoteDirectory.TAG_UNKNOWN_30, 174, NikonType2MakernoteDirectory.TAG_UNKNOWN_48, 180, NikonType2MakernoteDirectory.TAG_UNKNOWN_49, 186, 193, JfifUtil.MARKER_SOFn, 199, 198, -2, -2}, new int[]{123, 122, 129, 128, NikonType2MakernoteDirectory.TAG_FLASH_USED, NikonType2MakernoteDirectory.TAG_DIGITAL_ZOOM, 141, 140, 147, 146, 153, 152, 159, 158, NikonType2MakernoteDirectory.TAG_IMAGE_COUNT, 164, 171, 170, 177, 176, NikonType2MakernoteDirectory.TAG_AF_INFO_2, 182, NikonType2MakernoteDirectory.TAG_UNKNOWN_50, 188, 195, 194, XMPError.BADXML, LogSeverity.INFO_VALUE, LeicaMakernoteDirectory.TAG_CCD_VERSION, -3}, new int[]{125, PanasonicMakernoteDirectory.TAG_CLEAR_RETOUCH, 131, NikonType2MakernoteDirectory.TAG_ADAPTER, 137, 136, 143, 142, 149, 148, NikonType2MakernoteDirectory.TAG_UNKNOWN_10, 154, CanonMakernoteDirectory.TAG_TONE_CURVE_TABLE, 160, NikonType2MakernoteDirectory.TAG_EXPOSURE_SEQUENCE_NUMBER, NikonType2MakernoteDirectory.TAG_DELETED_IMAGE_COUNT, NikonType2MakernoteDirectory.TAG_AF_RESPONSE, NikonType2MakernoteDirectory.TAG_IMAGE_STABILISATION, 179, 178, NikonType2MakernoteDirectory.TAG_AF_TUNE, NikonType2MakernoteDirectory.TAG_FILE_INFO, 191, 190, 197, 196, XMPError.BADXMP, XMPError.BADRDF, LeicaMakernoteDirectory.TAG_CONTROLLER_BOARD_VERSION, LeicaMakernoteDirectory.TAG_CCD_BOARD_VERSION}, new int[]{283, 282, 277, 276, 271, 270, 265, 264, 259, 258, 253, 252, 247, 246, 241, 240, 235, 234, 229, 228, 223, 222, JfifUtil.MARKER_EOI, JfifUtil.MARKER_SOI, 211, 210, 205, XMPError.BADSTREAM, LeicaMakernoteDirectory.TAG_M16_C_VERSION, -3}, new int[]{285, 284, 279, 278, 273, 272, 267, 266, 261, 260, 255, ExifDirectoryBase.TAG_NEW_SUBFILE_TYPE, 249, 248, 243, 242, 237, 236, 231, 230, JfifUtil.MARKER_APP1, CanonMakernoteDirectory.TAG_SENSOR_INFO_ARRAY, 219, JfifUtil.MARKER_SOS, 213, 212, 207, 206, 821, 820}, new int[]{OlympusImageProcessingMakernoteDirectory.TagWbGLevel, 286, 281, 280, 275, 274, 269, 268, 263, 262, 257, 256, 251, ExponentialBackoffSender.RND_MAX, 245, 244, 239, 238, 233, 232, 227, 226, 221, 220, JfifUtil.MARKER_RST7, 214, 209, 208, 822, -3}, new int[]{289, 288, 295, 294, ExifDirectoryBase.TAG_TRANSFER_FUNCTION, 300, 307, 306, 313, 312, ExifDirectoryBase.TAG_PRIMARY_CHROMATICITIES, ExifDirectoryBase.TAG_WHITE_POINT, ExifDirectoryBase.TAG_TILE_BYTE_COUNTS, ExifDirectoryBase.TAG_TILE_OFFSETS, 331, ExifDirectoryBase.TAG_SUB_IFD_OFFSET, 337, IptcDirectory.TAG_TIME_SENT, 343, ExifDirectoryBase.TAG_TRANSFER_RANGE, 349, 348, 355, 354, 361, 360, 367, 366, 824, 823}, new int[]{291, OlympusRawInfoMakernoteDirectory.TagWbRbLevelsFineWeather, ExifDirectoryBase.TAG_PAGE_NUMBER, 296, 303, 302, 309, 308, ExifDirectoryBase.TAG_ARTIST, 314, 321, 320, 327, IptcDirectory.TAG_DATE_SENT, 333, 332, 339, 338, 345, 344, 351, 350, 357, IptcDirectory.TAG_UNIQUE_OBJECT_NAME, 363, 362, 369, 368, 825, -3}, new int[]{293, OlympusRawInfoMakernoteDirectory.TagWbRbLevelsEveningSunlight, 299, 298, 305, OlympusRawInfoMakernoteDirectory.TagWbRbLevelsDaylightFluor, 311, 310, ExifDirectoryBase.TAG_PREDICTOR, 316, ExifDirectoryBase.TAG_TILE_LENGTH, ExifDirectoryBase.TAG_TILE_WIDTH, 329, 328, 335, 334, 341, 340, ExifDirectoryBase.TAG_JPEG_TABLES, IptcDirectory.TAG_CODED_CHARACTER_SET, 353, 352, 359, 358, 365, 364, 371, 370, 827, 826}, new int[]{409, 408, 403, 402, 397, 396, 391, 390, 79, 78, -2, -2, 13, 12, 37, 36, 2, -1, 44, 43, 109, 108, 385, BitmapCounterConfig.DEFAULT_MAX_BITMAP_COUNT, 379, IptcDirectory.TAG_ARM_VERSION, 373, 372, 828, -3}, new int[]{411, 410, 405, 404, 399, 398, 393, 392, 81, 80, 40, -2, 15, 14, 39, 38, 3, -1, -1, 45, 111, 110, 387, 386, 381, 380, 375, 374, 830, 829}, new int[]{413, 412, 407, 406, 401, 400, 395, 394, 83, 82, 41, -3, -3, -3, -3, -3, 5, 4, 47, 46, 113, 112, 389, 388, 383, 382, 377, IptcDirectory.TAG_ARM_IDENTIFIER, 831, -3}, new int[]{415, 414, 421, 420, 427, 426, 103, 102, 55, 54, 16, -3, -3, -3, -3, -3, -3, -3, 20, 19, 85, 84, 433, 432, 439, 438, 445, 444, 833, LeicaMakernoteDirectory.TAG_IMAGE_ID_NUMBER}, new int[]{417, 416, 423, 422, HttpConnection.HTTP_TOO_MANY_REQUESTS, 428, 105, 104, 57, 56, -3, -3, -3, -3, -3, -3, -3, -3, 22, 21, 87, 86, 435, 434, 441, 440, 447, 446, 834, -3}, new int[]{419, 418, 425, 424, 431, 430, 107, 106, 59, 58, -3, -3, -3, -3, -3, -3, -3, -3, -3, 23, 89, 88, 437, 436, GrpcUtil.DEFAULT_PORT_SSL, 442, 449, 448, 836, 835}, new int[]{481, 480, 475, 474, 469, 468, 48, -2, 30, -3, -3, -3, -3, -3, -3, -3, -3, -3, -3, 0, 53, 52, 463, 462, 457, 456, 451, 450, 837, -3}, new int[]{483, 482, 477, 476, 471, 470, 49, -1, -2, -3, -3, -3, -3, -3, -3, -3, -3, -3, -3, -3, -2, -1, 465, 464, 459, 458, 453, 452, 839, 838}, new int[]{485, 484, 479, 478, 473, 472, 51, 50, 31, -3, -3, -3, -3, -3, -3, -3, -3, -3, -3, 1, -2, 42, 467, 466, 461, 460, 455, 454, 840, -3}, new int[]{487, 486, 493, 492, 499, 498, 97, 96, 61, 60, -3, -3, -3, -3, -3, -3, -3, -3, -3, 26, 91, 90, 505, 504, FrameMetricsAggregator.EVERY_DURATION, 510, 517, 516, 842, 841}, new int[]{489, 488, 495, 494, 501, 500, 99, 98, 63, 62, -3, -3, -3, -3, -3, -3, -3, -3, 28, 27, 93, 92, 507, 506, 513, 512, 519, 518, 843, -3}, new int[]{491, 490, 497, 496, 503, 502, 101, 100, 65, 64, 17, -3, -3, -3, -3, -3, -3, -3, 18, 29, 95, 94, 509, 508, 515, 514, 521, 520, 845, 844}, new int[]{559, 558, 553, IptcDirectory.TAG_SPECIAL_INSTRUCTIONS, 547, 546, SanyoMakernoteDirectory.TAG_LIGHT_SOURCE_SPECIAL, 540, 73, 72, 32, -3, -3, -3, -3, -3, -3, 10, 67, 66, 115, 114, SanyoMakernoteDirectory.TAG_RECORD_SHUTTER_RELEASE, 534, 529, 528, 523, 522, 846, -3}, new int[]{561, 560, 555, IptcDirectory.TAG_ACTION_ADVISED, 549, SanyoMakernoteDirectory.TAG_SEQUENCE_SHOT_INTERVAL, SanyoMakernoteDirectory.TAG_SCENE_SELECT, 542, 75, 74, -2, -1, 7, 6, 35, 34, 11, -2, 69, 68, 117, 116, 537, SanyoMakernoteDirectory.TAG_FLICKER_REDUCE, 531, 530, OlympusMakernoteDirectory.TAG_ORIGINAL_MANUFACTURER_MODEL, 524, 848, 847}, new int[]{563, IptcDirectory.TAG_REFERENCE_NUMBER, IptcDirectory.TAG_REFERENCE_SERVICE, 556, 551, IptcDirectory.TAG_EXPIRATION_TIME, 545, 544, 77, 76, -2, 33, 9, 8, 25, 24, -1, -2, 71, 70, 119, 118, 539, IptcDirectory.TAG_CONTENT_LOCATION_CODE, 533, 532, 527, SanyoMakernoteDirectory.TAG_SEQUENTIAL_SHOT, 849, -3}, new int[]{565, 564, 571, 570, IptcDirectory.TAG_ORIGINATING_PROGRAM, 576, 583, IptcDirectory.TAG_PROGRAM_VERSION, 589, 588, 595, 594, 601, LogSeverity.CRITICAL_VALUE, IptcDirectory.TAG_PROVINCE_OR_STATE, 606, IptcDirectory.TAG_COUNTRY_OR_PRIMARY_LOCATION_NAME, IptcDirectory.TAG_COUNTRY_OR_PRIMARY_LOCATION_CODE, 619, 618, 625, 624, 631, IptcDirectory.TAG_CONTACT, IptcDirectory.TAG_RASTERIZED_CAPTION, 636, IptcDirectory.TAG_IMAGE_ORIENTATION, IptcDirectory.TAG_IMAGE_TYPE, 851, 850}, new int[]{IptcDirectory.TAG_DATE_CREATED, 566, 573, IptcDirectory.TAG_TIME_CREATED, 579, 578, 585, 584, 591, 590, IptcDirectory.TAG_BY_LINE_TITLE, 596, 603, IptcDirectory.TAG_CITY, 609, 608, IptcDirectory.TAG_ORIGINAL_TRANSMISSION_REFERENCE, 614, 621, 620, IptcDirectory.TAG_SOURCE, 626, IptcDirectory.TAG_LOCAL_CAPTION, IptcDirectory.TAG_CAPTION, 639, 638, 645, 644, 852, -3}, new int[]{569, 568, IptcDirectory.TAG_DIGITAL_TIME_CREATED, IptcDirectory.TAG_DIGITAL_DATE_CREATED, 581, 580, IptcDirectory.TAG_OBJECT_CYCLE, 586, 593, IptcDirectory.TAG_BY_LINE, 599, 598, 605, IptcDirectory.TAG_SUB_LOCATION, 611, 610, IptcDirectory.TAG_HEADLINE, 616, 623, IptcDirectory.TAG_CREDIT, 629, IptcDirectory.TAG_COPYRIGHT_NOTICE, 635, IptcDirectory.TAG_CAPTION_WRITER, 641, OlympusMakernoteDirectory.TAG_PREVIEW_IMAGE, IptcDirectory.TAG_LANGUAGE_IDENTIFIER, 646, 854, 853}, new int[]{727, 726, 721, 720, 715, IptcDirectory.TAG_OBJECT_PREVIEW_DATA, 709, 708, 703, 702, IptcDirectory.TAG_MASTER_DOCUMENT_ID, IptcDirectory.TAG_JOB_ID, 691, 690, 685, 684, 679, 678, 673, 672, 667, IptcDirectory.TAG_AUDIO_OUTCUE, 661, 660, 655, 654, 649, 648, 855, -3}, new int[]{729, 728, 723, 722, 717, 716, 711, 710, 705, 704, IptcDirectory.TAG_UNIQUE_DOCUMENT_ID, IptcDirectory.TAG_SHORT_DOCUMENT_ID, 693, 692, 687, 686, 681, 680, 675, 674, 669, 668, IptcDirectory.TAG_AUDIO_SAMPLING_RATE, IptcDirectory.TAG_AUDIO_TYPE, 657, 656, 651, 650, 857, 856}, new int[]{731, 730, 725, 724, 719, 718, IptcDirectory.TAG_OBJECT_PREVIEW_FILE_FORMAT_VERSION, IptcDirectory.TAG_OBJECT_PREVIEW_FILE_FORMAT, 707, 706, 701, 700, 695, 694, 689, 688, 683, 682, 677, 676, 671, 670, IptcDirectory.TAG_AUDIO_DURATION, IptcDirectory.TAG_AUDIO_SAMPLING_RESOLUTION, 659, 658, 653, 652, 858, -3}, new int[]{733, 732, 739, 738, 745, 744, 751, 750, 757, 756, 763, 762, 769, 768, OlympusCameraSettingsMakernoteDirectory.TagAfFineTuneAdj, 774, 781, 780, LeicaMakernoteDirectory.TAG_APPROXIMATE_F_NUMBER, LeicaMakernoteDirectory.TAG_MEASURED_LV, 793, 792, 799, 798, 805, LeicaMakernoteDirectory.TAG_WB_BLUE_LEVEL, 811, 810, 860, 859}, new int[]{735, 734, 741, 740, 747, 746, 753, 752, 759, 758, 765, 764, 771, 770, 777, OlympusFocusInfoMakernoteDirectory.TagAfPoint, 783, 782, 789, 788, 795, 794, LeicaMakernoteDirectory.TAG_COLOR_TEMPERATURE, 800, 807, 806, 813, 812, 861, -3}, new int[]{737, 736, 743, 742, 749, 748, 755, 754, 761, 760, 767, 766, 773, 772, 779, 778, 785, 784, 791, 790, 797, 796, LeicaMakernoteDirectory.TAG_WB_GREEN_LEVEL, LeicaMakernoteDirectory.TAG_WB_RED_LEVEL, 809, OlympusFocusInfoMakernoteDirectory.TagAfInfo, 815, 814, 863, 862}};
    private final BitMatrix bitMatrix;

    BitMatrixParser(BitMatrix bitMatrix2) {
        this.bitMatrix = bitMatrix2;
    }

    /* access modifiers changed from: 0000 */
    public byte[] readCodewords() {
        byte[] bArr = new byte[144];
        int height = this.bitMatrix.getHeight();
        int width = this.bitMatrix.getWidth();
        for (int i = 0; i < height; i++) {
            int[] iArr = BITNR[i];
            for (int i2 = 0; i2 < width; i2++) {
                int i3 = iArr[i2];
                if (i3 >= 0 && this.bitMatrix.get(i2, i)) {
                    int i4 = i3 / 6;
                    bArr[i4] = (byte) (((byte) (1 << (5 - (i3 % 6)))) | bArr[i4]);
                }
            }
        }
        return bArr;
    }
}
