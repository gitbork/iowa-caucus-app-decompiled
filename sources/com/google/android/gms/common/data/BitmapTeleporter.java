package com.google.android.gms.common.data;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.ParcelFileDescriptor.AutoCloseInputStream;
import android.os.Parcelable.Creator;
import android.util.Log;
import com.facebook.cache.disk.DefaultDiskStorage.FileType;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

@ShowFirstParty
@KeepForSdk
@Class(creator = "BitmapTeleporterCreator")
public class BitmapTeleporter extends AbstractSafeParcelable implements ReflectedParcelable {
    @KeepForSdk
    public static final Creator<BitmapTeleporter> CREATOR = new zaa();
    @Field(mo15127id = 3)
    private final int mType;
    @VersionField(mo15133id = 1)
    private final int zalf;
    @Field(mo15127id = 2)
    private ParcelFileDescriptor zalg;
    private Bitmap zalh;
    private boolean zali;
    private File zalj;

    @Constructor
    BitmapTeleporter(@Param(mo15130id = 1) int i, @Param(mo15130id = 2) ParcelFileDescriptor parcelFileDescriptor, @Param(mo15130id = 3) int i2) {
        this.zalf = i;
        this.zalg = parcelFileDescriptor;
        this.mType = i2;
        this.zalh = null;
        this.zali = false;
    }

    @KeepForSdk
    public BitmapTeleporter(Bitmap bitmap) {
        this.zalf = 1;
        this.zalg = null;
        this.mType = 0;
        this.zalh = bitmap;
        this.zali = true;
    }

    @KeepForSdk
    public Bitmap get() {
        if (!this.zali) {
            DataInputStream dataInputStream = new DataInputStream(new AutoCloseInputStream(this.zalg));
            try {
                byte[] bArr = new byte[dataInputStream.readInt()];
                int readInt = dataInputStream.readInt();
                int readInt2 = dataInputStream.readInt();
                Config valueOf = Config.valueOf(dataInputStream.readUTF());
                dataInputStream.read(bArr);
                zaa(dataInputStream);
                ByteBuffer wrap = ByteBuffer.wrap(bArr);
                Bitmap createBitmap = Bitmap.createBitmap(readInt, readInt2, valueOf);
                createBitmap.copyPixelsFromBuffer(wrap);
                this.zalh = createBitmap;
                this.zali = true;
            } catch (IOException e) {
                throw new IllegalStateException("Could not read from parcel file descriptor", e);
            } catch (Throwable th) {
                zaa(dataInputStream);
                throw th;
            }
        }
        return this.zalh;
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (this.zalg == null) {
            Bitmap bitmap = this.zalh;
            ByteBuffer allocate = ByteBuffer.allocate(bitmap.getRowBytes() * bitmap.getHeight());
            bitmap.copyPixelsToBuffer(allocate);
            byte[] array = allocate.array();
            DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(zabz()));
            try {
                dataOutputStream.writeInt(array.length);
                dataOutputStream.writeInt(bitmap.getWidth());
                dataOutputStream.writeInt(bitmap.getHeight());
                dataOutputStream.writeUTF(bitmap.getConfig().toString());
                dataOutputStream.write(array);
                zaa(dataOutputStream);
            } catch (IOException e) {
                throw new IllegalStateException("Could not write into unlinked file", e);
            } catch (Throwable th) {
                zaa(dataOutputStream);
                throw th;
            }
        }
        int i2 = i | 1;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zalf);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zalg, i2, false);
        SafeParcelWriter.writeInt(parcel, 3, this.mType);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
        this.zalg = null;
    }

    @KeepForSdk
    public void release() {
        if (!this.zali) {
            try {
                this.zalg.close();
            } catch (IOException e) {
                Log.w("BitmapTeleporter", "Could not close PFD", e);
            }
        }
    }

    @KeepForSdk
    public void setTempDir(File file) {
        if (file != null) {
            this.zalj = file;
            return;
        }
        throw new NullPointerException("Cannot set null temp directory");
    }

    private final FileOutputStream zabz() {
        File file = this.zalj;
        if (file != null) {
            try {
                File createTempFile = File.createTempFile("teleporter", FileType.TEMP, file);
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(createTempFile);
                    this.zalg = ParcelFileDescriptor.open(createTempFile, 268435456);
                    createTempFile.delete();
                    return fileOutputStream;
                } catch (FileNotFoundException unused) {
                    throw new IllegalStateException("Temporary file is somehow already deleted");
                }
            } catch (IOException e) {
                throw new IllegalStateException("Could not create temporary file", e);
            }
        } else {
            throw new IllegalStateException("setTempDir() must be called before writing this object to a parcel");
        }
    }

    private static void zaa(Closeable closeable) {
        try {
            closeable.close();
        } catch (IOException e) {
            Log.w("BitmapTeleporter", "Could not close stream", e);
        }
    }
}
