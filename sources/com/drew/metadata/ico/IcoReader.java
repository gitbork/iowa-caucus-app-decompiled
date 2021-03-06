package com.drew.metadata.ico;

import com.drew.lang.SequentialReader;
import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Metadata;
import java.io.IOException;

public class IcoReader {
    public void extract(@NotNull SequentialReader sequentialReader, @NotNull Metadata metadata) {
        String str = "Exception reading ICO file metadata: ";
        sequentialReader.setMotorolaByteOrder(false);
        try {
            if (sequentialReader.getUInt16() != 0) {
                IcoDirectory icoDirectory = new IcoDirectory();
                icoDirectory.addError("Invalid header bytes");
                metadata.addDirectory(icoDirectory);
                return;
            }
            int uInt16 = sequentialReader.getUInt16();
            if (uInt16 == 1 || uInt16 == 2) {
                int uInt162 = sequentialReader.getUInt16();
                if (uInt162 == 0) {
                    IcoDirectory icoDirectory2 = new IcoDirectory();
                    icoDirectory2.addError("Image count cannot be zero");
                    metadata.addDirectory(icoDirectory2);
                    return;
                }
                for (int i = 0; i < uInt162; i++) {
                    IcoDirectory icoDirectory3 = new IcoDirectory();
                    try {
                        icoDirectory3.setInt(1, uInt16);
                        icoDirectory3.setInt(2, sequentialReader.getUInt8());
                        icoDirectory3.setInt(3, sequentialReader.getUInt8());
                        icoDirectory3.setInt(4, sequentialReader.getUInt8());
                        sequentialReader.getUInt8();
                        if (uInt16 == 1) {
                            icoDirectory3.setInt(5, sequentialReader.getUInt16());
                            icoDirectory3.setInt(7, sequentialReader.getUInt16());
                        } else {
                            icoDirectory3.setInt(6, sequentialReader.getUInt16());
                            icoDirectory3.setInt(8, sequentialReader.getUInt16());
                        }
                        icoDirectory3.setLong(9, sequentialReader.getUInt32());
                        icoDirectory3.setLong(10, sequentialReader.getUInt32());
                    } catch (IOException e) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(str);
                        sb.append(e.getMessage());
                        icoDirectory3.addError(sb.toString());
                    }
                    metadata.addDirectory(icoDirectory3);
                }
                return;
            }
            IcoDirectory icoDirectory4 = new IcoDirectory();
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Invalid type ");
            sb2.append(uInt16);
            sb2.append(" -- expecting 1 or 2");
            icoDirectory4.addError(sb2.toString());
            metadata.addDirectory(icoDirectory4);
        } catch (IOException e2) {
            IcoDirectory icoDirectory5 = new IcoDirectory();
            StringBuilder sb3 = new StringBuilder();
            sb3.append(str);
            sb3.append(e2.getMessage());
            icoDirectory5.addError(sb3.toString());
            metadata.addDirectory(icoDirectory5);
        }
    }
}
