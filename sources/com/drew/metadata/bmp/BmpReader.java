package com.drew.metadata.bmp;

import com.drew.lang.ByteArrayReader;
import com.drew.lang.Charsets;
import com.drew.lang.SequentialReader;
import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Directory;
import com.drew.metadata.ErrorDirectory;
import com.drew.metadata.Metadata;
import com.drew.metadata.MetadataException;
import com.drew.metadata.bmp.BmpHeaderDirectory.ColorSpaceType;
import com.drew.metadata.icc.IccReader;
import java.io.IOException;

public class BmpReader {
    public static final int BITMAP = 19778;
    public static final int OS2_BITMAP_ARRAY = 16706;
    public static final int OS2_COLOR_ICON = 18755;
    public static final int OS2_COLOR_POINTER = 20547;
    public static final int OS2_ICON = 17225;
    public static final int OS2_POINTER = 21584;

    public void extract(@NotNull SequentialReader sequentialReader, @NotNull Metadata metadata) {
        sequentialReader.setMotorolaByteOrder(false);
        readFileHeader(sequentialReader, metadata, true);
    }

    /* access modifiers changed from: protected */
    public void readFileHeader(@NotNull SequentialReader sequentialReader, @NotNull Metadata metadata, boolean z) {
        try {
            int uInt16 = sequentialReader.getUInt16();
            Directory directory = null;
            if (uInt16 != 16706) {
                if (uInt16 == 17225 || uInt16 == 18755 || uInt16 == 19778 || uInt16 == 20547 || uInt16 == 21584) {
                    BmpHeaderDirectory bmpHeaderDirectory = new BmpHeaderDirectory();
                    try {
                        metadata.addDirectory(bmpHeaderDirectory);
                        bmpHeaderDirectory.setInt(-2, uInt16);
                        sequentialReader.skip(12);
                        readBitmapHeader(sequentialReader, bmpHeaderDirectory, metadata);
                    } catch (IOException unused) {
                        directory = bmpHeaderDirectory;
                    }
                } else {
                    try {
                        StringBuilder sb = new StringBuilder();
                        sb.append("Invalid BMP magic number 0x");
                        sb.append(Integer.toHexString(uInt16));
                        metadata.addDirectory(new ErrorDirectory(sb.toString()));
                    } catch (IOException unused2) {
                        String str = "Unable to read BMP file header";
                        if (directory == null) {
                            addError(str, metadata);
                        } else {
                            directory.addError(str);
                        }
                    }
                }
            } else if (!z) {
                addError("Invalid bitmap file - nested arrays not allowed", metadata);
            } else {
                sequentialReader.skip(4);
                long uInt32 = sequentialReader.getUInt32();
                sequentialReader.skip(4);
                readFileHeader(sequentialReader, metadata, false);
                if (uInt32 != 0) {
                    if (sequentialReader.getPosition() > uInt32) {
                        addError("Invalid next header offset", metadata);
                    } else {
                        sequentialReader.skip(uInt32 - sequentialReader.getPosition());
                        readFileHeader(sequentialReader, metadata, true);
                    }
                }
            }
        } catch (IOException e) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Couldn't determine bitmap type: ");
            sb2.append(e.getMessage());
            metadata.addDirectory(new ErrorDirectory(sb2.toString()));
        }
    }

    /* access modifiers changed from: protected */
    public void readBitmapHeader(@NotNull SequentialReader sequentialReader, @NotNull BmpHeaderDirectory bmpHeaderDirectory, @NotNull Metadata metadata) {
        SequentialReader sequentialReader2 = sequentialReader;
        BmpHeaderDirectory bmpHeaderDirectory2 = bmpHeaderDirectory;
        try {
            int i = bmpHeaderDirectory2.getInt(-2);
            long position = sequentialReader.getPosition();
            int int32 = sequentialReader.getInt32();
            bmpHeaderDirectory2.setInt(-1, int32);
            if (int32 == 12 && i == 19778) {
                bmpHeaderDirectory2.setInt(2, sequentialReader.getInt16());
                bmpHeaderDirectory2.setInt(1, sequentialReader.getInt16());
                bmpHeaderDirectory2.setInt(3, sequentialReader.getUInt16());
                bmpHeaderDirectory2.setInt(4, sequentialReader.getUInt16());
            }
            if (int32 == 12) {
                bmpHeaderDirectory2.setInt(2, sequentialReader.getUInt16());
                bmpHeaderDirectory2.setInt(1, sequentialReader.getUInt16());
                bmpHeaderDirectory2.setInt(3, sequentialReader.getUInt16());
                bmpHeaderDirectory2.setInt(4, sequentialReader.getUInt16());
            } else {
                if (int32 != 16) {
                    if (int32 != 64) {
                        if (!(int32 == 40 || int32 == 52 || int32 == 56 || int32 == 108)) {
                            if (int32 != 124) {
                                StringBuilder sb = new StringBuilder();
                                sb.append("Unexpected DIB header size: ");
                                sb.append(int32);
                                bmpHeaderDirectory2.addError(sb.toString());
                            }
                        }
                        bmpHeaderDirectory2.setInt(2, sequentialReader.getInt32());
                        bmpHeaderDirectory2.setInt(1, sequentialReader.getInt32());
                        bmpHeaderDirectory2.setInt(3, sequentialReader.getUInt16());
                        bmpHeaderDirectory2.setInt(4, sequentialReader.getUInt16());
                        bmpHeaderDirectory2.setInt(5, sequentialReader.getInt32());
                        sequentialReader2.skip(4);
                        bmpHeaderDirectory2.setInt(6, sequentialReader.getInt32());
                        bmpHeaderDirectory2.setInt(7, sequentialReader.getInt32());
                        bmpHeaderDirectory2.setInt(8, sequentialReader.getInt32());
                        bmpHeaderDirectory2.setInt(9, sequentialReader.getInt32());
                        if (int32 != 40) {
                            bmpHeaderDirectory2.setLong(12, sequentialReader.getUInt32());
                            bmpHeaderDirectory2.setLong(13, sequentialReader.getUInt32());
                            bmpHeaderDirectory2.setLong(14, sequentialReader.getUInt32());
                            if (int32 != 52) {
                                bmpHeaderDirectory2.setLong(15, sequentialReader.getUInt32());
                                if (int32 != 56) {
                                    long uInt32 = sequentialReader.getUInt32();
                                    bmpHeaderDirectory2.setLong(16, uInt32);
                                    sequentialReader2.skip(36);
                                    bmpHeaderDirectory2.setLong(17, sequentialReader.getUInt32());
                                    bmpHeaderDirectory2.setLong(18, sequentialReader.getUInt32());
                                    bmpHeaderDirectory2.setLong(19, sequentialReader.getUInt32());
                                    if (int32 != 108) {
                                        bmpHeaderDirectory2.setInt(20, sequentialReader.getInt32());
                                        if (uInt32 != ColorSpaceType.PROFILE_EMBEDDED.getValue()) {
                                            if (uInt32 != ColorSpaceType.PROFILE_LINKED.getValue()) {
                                                sequentialReader2.skip(12);
                                            }
                                        }
                                        long uInt322 = sequentialReader.getUInt32();
                                        int int322 = sequentialReader.getInt32();
                                        long j = position + uInt322;
                                        if (sequentialReader.getPosition() > j) {
                                            StringBuilder sb2 = new StringBuilder();
                                            sb2.append("Invalid profile data offset 0x");
                                            sb2.append(Long.toHexString(j));
                                            bmpHeaderDirectory2.addError(sb2.toString());
                                            return;
                                        }
                                        sequentialReader2.skip(j - sequentialReader.getPosition());
                                        if (uInt32 == ColorSpaceType.PROFILE_LINKED.getValue()) {
                                            bmpHeaderDirectory2.setString(21, sequentialReader2.getNullTerminatedString(int322, Charsets.WINDOWS_1252));
                                        } else {
                                            new IccReader().extract(new ByteArrayReader(sequentialReader2.getBytes(int322)), metadata, bmpHeaderDirectory2);
                                        }
                                    } else {
                                        return;
                                    }
                                } else {
                                    return;
                                }
                            } else {
                                return;
                            }
                        } else {
                            return;
                        }
                    }
                }
                bmpHeaderDirectory2.setInt(2, sequentialReader.getInt32());
                bmpHeaderDirectory2.setInt(1, sequentialReader.getInt32());
                bmpHeaderDirectory2.setInt(3, sequentialReader.getUInt16());
                bmpHeaderDirectory2.setInt(4, sequentialReader.getUInt16());
                if (int32 > 16) {
                    bmpHeaderDirectory2.setInt(5, sequentialReader.getInt32());
                    sequentialReader2.skip(4);
                    bmpHeaderDirectory2.setInt(6, sequentialReader.getInt32());
                    bmpHeaderDirectory2.setInt(7, sequentialReader.getInt32());
                    bmpHeaderDirectory2.setInt(8, sequentialReader.getInt32());
                    bmpHeaderDirectory2.setInt(9, sequentialReader.getInt32());
                    sequentialReader2.skip(6);
                    bmpHeaderDirectory2.setInt(10, sequentialReader.getUInt16());
                    sequentialReader2.skip(8);
                    bmpHeaderDirectory2.setInt(11, sequentialReader.getInt32());
                    sequentialReader2.skip(4);
                }
            }
        } catch (IOException unused) {
            bmpHeaderDirectory2.addError("Unable to read BMP header");
        } catch (MetadataException unused2) {
            bmpHeaderDirectory2.addError("Internal error");
        }
    }

    /* access modifiers changed from: protected */
    public void addError(@NotNull String str, @NotNull Metadata metadata) {
        ErrorDirectory errorDirectory = (ErrorDirectory) metadata.getFirstDirectoryOfType(ErrorDirectory.class);
        if (errorDirectory == null) {
            metadata.addDirectory(new ErrorDirectory(str));
        } else {
            errorDirectory.addError(str);
        }
    }
}