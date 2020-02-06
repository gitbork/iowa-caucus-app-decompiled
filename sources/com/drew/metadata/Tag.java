package com.drew.metadata;

import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;

public class Tag {
    @NotNull
    private final Directory _directory;
    private final int _tagType;

    public Tag(int i, @NotNull Directory directory) {
        this._tagType = i;
        this._directory = directory;
    }

    public int getTagType() {
        return this._tagType;
    }

    @NotNull
    public String getTagTypeHex() {
        return String.format("0x%04x", new Object[]{Integer.valueOf(this._tagType)});
    }

    @Nullable
    public String getDescription() {
        return this._directory.getDescription(this._tagType);
    }

    public boolean hasTagName() {
        return this._directory.hasTagName(this._tagType);
    }

    @NotNull
    public String getTagName() {
        return this._directory.getTagName(this._tagType);
    }

    @NotNull
    public String getDirectoryName() {
        return this._directory.getName();
    }

    @NotNull
    public String toString() {
        String description = getDescription();
        if (description == null) {
            StringBuilder sb = new StringBuilder();
            sb.append(this._directory.getString(getTagType()));
            sb.append(" (unable to formulate description)");
            description = sb.toString();
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("[");
        sb2.append(this._directory.getName());
        sb2.append("] ");
        sb2.append(getTagName());
        sb2.append(" - ");
        sb2.append(description);
        return sb2.toString();
    }
}
