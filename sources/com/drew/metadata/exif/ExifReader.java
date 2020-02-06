package com.drew.metadata.exif;

import com.drew.imaging.jpeg.JpegSegmentMetadataReader;
import com.drew.imaging.jpeg.JpegSegmentType;
import com.drew.imaging.tiff.TiffProcessingException;
import com.drew.imaging.tiff.TiffReader;
import com.drew.lang.ByteArrayReader;
import com.drew.lang.RandomAccessReader;
import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import java.io.IOException;
import java.util.Collections;

public class ExifReader implements JpegSegmentMetadataReader {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final String JPEG_SEGMENT_PREAMBLE = "Exif\u0000\u0000";

    @NotNull
    public Iterable<JpegSegmentType> getSegmentTypes() {
        return Collections.singletonList(JpegSegmentType.APP1);
    }

    public void readJpegSegments(@NotNull Iterable<byte[]> iterable, @NotNull Metadata metadata, @NotNull JpegSegmentType jpegSegmentType) {
        for (byte[] bArr : iterable) {
            if (bArr.length >= 6 && new String(bArr, 0, 6).equals(JPEG_SEGMENT_PREAMBLE)) {
                extract(new ByteArrayReader(bArr), metadata, 6);
            }
        }
    }

    public void extract(@NotNull RandomAccessReader randomAccessReader, @NotNull Metadata metadata) {
        extract(randomAccessReader, metadata, 0);
    }

    public void extract(@NotNull RandomAccessReader randomAccessReader, @NotNull Metadata metadata, int i) {
        extract(randomAccessReader, metadata, i, null);
    }

    public void extract(@NotNull RandomAccessReader randomAccessReader, @NotNull Metadata metadata, int i, @Nullable Directory directory) {
        String str = "Exception processing TIFF data: ";
        ExifTiffHandler exifTiffHandler = new ExifTiffHandler(metadata, directory);
        try {
            new TiffReader().processTiff(randomAccessReader, exifTiffHandler, i);
        } catch (TiffProcessingException e) {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(e.getMessage());
            exifTiffHandler.error(sb.toString());
            e.printStackTrace(System.err);
        } catch (IOException e2) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(str);
            sb2.append(e2.getMessage());
            exifTiffHandler.error(sb2.toString());
            e2.printStackTrace(System.err);
        }
    }
}
