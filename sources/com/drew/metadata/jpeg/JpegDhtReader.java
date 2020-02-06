package com.drew.metadata.jpeg;

import com.drew.imaging.jpeg.JpegSegmentMetadataReader;
import com.drew.imaging.jpeg.JpegSegmentType;
import com.drew.lang.SequentialByteArrayReader;
import com.drew.lang.SequentialReader;
import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Metadata;
import com.drew.metadata.jpeg.HuffmanTablesDirectory.HuffmanTable;
import com.drew.metadata.jpeg.HuffmanTablesDirectory.HuffmanTable.HuffmanTableClass;
import com.google.common.base.Ascii;
import com.google.common.primitives.UnsignedBytes;
import java.io.IOException;
import java.util.Collections;

public class JpegDhtReader implements JpegSegmentMetadataReader {
    @NotNull
    public Iterable<JpegSegmentType> getSegmentTypes() {
        return Collections.singletonList(JpegSegmentType.DHT);
    }

    public void readJpegSegments(@NotNull Iterable<byte[]> iterable, @NotNull Metadata metadata, @NotNull JpegSegmentType jpegSegmentType) {
        for (byte[] sequentialByteArrayReader : iterable) {
            extract(new SequentialByteArrayReader(sequentialByteArrayReader), metadata);
        }
    }

    public void extract(@NotNull SequentialReader sequentialReader, @NotNull Metadata metadata) {
        HuffmanTablesDirectory huffmanTablesDirectory = (HuffmanTablesDirectory) metadata.getFirstDirectoryOfType(HuffmanTablesDirectory.class);
        if (huffmanTablesDirectory == null) {
            huffmanTablesDirectory = new HuffmanTablesDirectory();
            metadata.addDirectory(huffmanTablesDirectory);
        }
        while (sequentialReader.available() > 0) {
            try {
                byte b = sequentialReader.getByte();
                HuffmanTableClass typeOf = HuffmanTableClass.typeOf((b & 240) >> 4);
                byte b2 = b & Ascii.f158SI;
                byte[] bytes = getBytes(sequentialReader, 16);
                int i = 0;
                for (byte b3 : bytes) {
                    i += b3 & UnsignedBytes.MAX_VALUE;
                }
                huffmanTablesDirectory.getTables().add(new HuffmanTable(typeOf, b2, bytes, getBytes(sequentialReader, i)));
            } catch (IOException e) {
                huffmanTablesDirectory.addError(e.getMessage());
            }
        }
        huffmanTablesDirectory.setInt(1, huffmanTablesDirectory.getTables().size());
    }

    private byte[] getBytes(@NotNull SequentialReader sequentialReader, int i) throws IOException {
        byte[] bArr = new byte[i];
        for (int i2 = 0; i2 < i; i2++) {
            byte b = sequentialReader.getByte();
            if ((b & UnsignedBytes.MAX_VALUE) == 255) {
                byte b2 = sequentialReader.getByte();
                if (b2 != 0) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Marker ");
                    sb.append(JpegSegmentType.fromByte(b2));
                    sb.append(" found inside DHT segment");
                    throw new IOException(sb.toString());
                }
            }
            bArr[i2] = b;
        }
        return bArr;
    }
}
