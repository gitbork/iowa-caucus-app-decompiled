package com.drew.tools;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.imaging.jpeg.JpegProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import java.io.IOException;
import java.net.URL;

public class ProcessUrlUtility {
    public static void main(String[] strArr) throws IOException, JpegProcessingException {
        if (strArr.length == 0) {
            System.err.println("Expects one or more URLs as arguments.");
            System.exit(1);
        }
        for (String url : strArr) {
            processUrl(new URL(url));
        }
        System.out.println("Completed.");
    }

    private static void processUrl(URL url) throws IOException {
        try {
            Metadata readMetadata = ImageMetadataReader.readMetadata(url.openConnection().getInputStream());
            if (readMetadata.hasErrors()) {
                System.err.println(url);
                for (Directory directory : readMetadata.getDirectories()) {
                    if (directory.hasErrors()) {
                        for (String str : directory.getErrors()) {
                            System.err.printf("\t[%s] %s%n", new Object[]{directory.getName(), str});
                        }
                    }
                }
            }
            for (Directory directory2 : readMetadata.getDirectories()) {
                for (Tag tag : directory2.getTags()) {
                    String tagName = tag.getTagName();
                    String name = directory2.getName();
                    String description = tag.getDescription();
                    if (description != null && description.length() > 1024) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(description.substring(0, 1024));
                        sb.append("...");
                        description = sb.toString();
                    }
                    System.out.printf("[%s] %s = %s%n", new Object[]{name, tagName, description});
                }
            }
        } catch (ImageProcessingException e) {
            System.err.printf("%s: %s [Error Extracting Metadata]%n\t%s%n", new Object[]{e.getClass().getName(), url, e.getMessage()});
        } catch (Throwable th) {
            System.err.printf("%s: %s [Error Extracting Metadata]%n", new Object[]{th.getClass().getName(), url});
            th.printStackTrace(System.err);
        }
    }
}
