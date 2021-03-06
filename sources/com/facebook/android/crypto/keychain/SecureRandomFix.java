package com.facebook.android.crypto.keychain;

import android.os.Build;
import android.os.Build.VERSION;
import android.os.Process;
import androidx.exifinterface.media.ExifInterface;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.SecureRandomSpi;
import java.security.Security;

public class SecureRandomFix {
    private static final String DEV_URANDOM = "/dev/urandom";
    private static final int VERSION_CODE_JELLY_BEAN = 17;
    private static final int VERSION_CODE_JELLY_BEAN_MR2 = 18;
    private static boolean sFixApplied;

    public static class FixException extends RuntimeException {
        public FixException(Throwable th) {
            super("Error fixing the Android's SecureRandom", th);
        }
    }

    public static class LinuxPRNGSecureRandom extends SecureRandomSpi {
        private static final File URANDOM_FILE = new File(SecureRandomFix.DEV_URANDOM);
        private static final Object sLock = new Object();
        private static DataInputStream sUrandomIn;
        private static OutputStream sUrandomOut;
        private boolean mSeedAttempted;

        /* access modifiers changed from: protected */
        public void engineSetSeed(byte[] bArr) {
            OutputStream urandomOutputStream;
            try {
                synchronized (sLock) {
                    urandomOutputStream = getUrandomOutputStream();
                }
                urandomOutputStream.write(bArr);
                urandomOutputStream.flush();
            } catch (Throwable th) {
                this.mSeedAttempted = true;
                throw th;
            }
            this.mSeedAttempted = true;
        }

        /* access modifiers changed from: protected */
        public void engineNextBytes(byte[] bArr) {
            DataInputStream urandomInputStream;
            if (!this.mSeedAttempted) {
                engineSetSeed(SecureRandomFix.generateSeed());
            }
            try {
                synchronized (sLock) {
                    urandomInputStream = getUrandomInputStream();
                }
                synchronized (urandomInputStream) {
                    urandomInputStream.readFully(bArr);
                }
            } catch (IOException e) {
                StringBuilder sb = new StringBuilder();
                sb.append("Failed to read from ");
                sb.append(URANDOM_FILE);
                throw new SecurityException(sb.toString(), e);
            }
        }

        /* access modifiers changed from: protected */
        public byte[] engineGenerateSeed(int i) {
            byte[] bArr = new byte[i];
            engineNextBytes(bArr);
            return bArr;
        }

        private DataInputStream getUrandomInputStream() {
            DataInputStream dataInputStream;
            synchronized (sLock) {
                if (sUrandomIn == null) {
                    try {
                        sUrandomIn = new DataInputStream(new FileInputStream(URANDOM_FILE));
                    } catch (IOException e) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("Failed to open ");
                        sb.append(URANDOM_FILE);
                        sb.append(" for reading");
                        throw new SecurityException(sb.toString(), e);
                    }
                }
                dataInputStream = sUrandomIn;
            }
            return dataInputStream;
        }

        private OutputStream getUrandomOutputStream() {
            OutputStream outputStream;
            synchronized (sLock) {
                if (sUrandomOut == null) {
                    try {
                        sUrandomOut = new FileOutputStream(URANDOM_FILE);
                    } catch (IOException e) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("Failed to open ");
                        sb.append(URANDOM_FILE);
                        sb.append(" for writing");
                        throw new SecurityException(sb.toString(), e);
                    }
                }
                outputStream = sUrandomOut;
            }
            return outputStream;
        }
    }

    private static class LinuxPRNGSecureRandomProvider extends Provider {
        public LinuxPRNGSecureRandomProvider() {
            super("LinuxPRNG", 1.0d, "A Linux-specific random number provider that uses /dev/urandom");
            put("SecureRandom.SHA1PRNG", LinuxPRNGSecureRandom.class.getName());
            put("SecureRandom.SHA1PRNG ImplementedIn", ExifInterface.TAG_SOFTWARE);
        }
    }

    public static synchronized void tryApplyFixes() throws FixException {
        synchronized (SecureRandomFix.class) {
            if (!sFixApplied) {
                try {
                    tryApplyOpenSSLFix();
                    tryInstallLinuxPRNGSecureRandom();
                    sFixApplied = true;
                } catch (Throwable th) {
                    throw new FixException(th);
                }
            }
        }
    }

    private static void tryApplyOpenSSLFix() {
        String str = "org.apache.harmony.xnet.provider.jsse.NativeCrypto";
        if (VERSION.SDK_INT >= 17 && VERSION.SDK_INT <= 18) {
            try {
                Class.forName(str).getMethod("RAND_seed", new Class[]{byte[].class}).invoke(null, new Object[]{generateSeed()});
                int intValue = ((Integer) Class.forName(str).getMethod("RAND_load_file", new Class[]{String.class, Long.TYPE}).invoke(null, new Object[]{DEV_URANDOM, Integer.valueOf(1024)})).intValue();
                if (intValue != 1024) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Unexpected number of bytes read from Linux PRNG: ");
                    sb.append(intValue);
                    throw new IOException(sb.toString());
                }
            } catch (Exception e) {
                throw new SecurityException("Failed to seed OpenSSL PRNG", e);
            }
        }
    }

    /* access modifiers changed from: private */
    public static byte[] generateSeed() {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            dataOutputStream.writeLong(System.currentTimeMillis());
            dataOutputStream.writeLong(System.nanoTime());
            dataOutputStream.writeInt(Process.myPid());
            dataOutputStream.writeInt(Process.myUid());
            dataOutputStream.write(getBuildFingerprintAndDeviceSerial());
            dataOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new SecurityException("Failed to generate seed", e);
        }
    }

    private static byte[] getBuildFingerprintAndDeviceSerial() {
        StringBuilder sb = new StringBuilder();
        String str = Build.FINGERPRINT;
        if (str != null) {
            sb.append(str);
        }
        String deviceSerialNumber = getDeviceSerialNumber();
        if (deviceSerialNumber != null) {
            sb.append(deviceSerialNumber);
        }
        try {
            return sb.toString().getBytes("UTF-8");
        } catch (UnsupportedEncodingException unused) {
            throw new RuntimeException("UTF-8 encoding not supported");
        }
    }

    private static String getDeviceSerialNumber() {
        try {
            return (String) Build.class.getField("SERIAL").get(null);
        } catch (Exception unused) {
            return null;
        }
    }

    private static void tryInstallLinuxPRNGSecureRandom() throws SecurityException {
        if (VERSION.SDK_INT <= 18) {
            Provider[] providers = Security.getProviders("SecureRandom.SHA1PRNG");
            if (providers == null || providers.length < 1 || !LinuxPRNGSecureRandomProvider.class.equals(providers[0].getClass())) {
                Security.insertProviderAt(new LinuxPRNGSecureRandomProvider(), 1);
            }
            SecureRandom secureRandom = new SecureRandom();
            if (LinuxPRNGSecureRandomProvider.class.equals(secureRandom.getProvider().getClass())) {
                try {
                    SecureRandom instance = SecureRandom.getInstance("SHA1PRNG");
                    if (!LinuxPRNGSecureRandomProvider.class.equals(instance.getProvider().getClass())) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("SecureRandom.getInstance(\"SHA1PRNG\") backed by wrong Provider: ");
                        sb.append(instance.getProvider().getClass());
                        throw new SecurityException(sb.toString());
                    }
                } catch (NoSuchAlgorithmException e) {
                    throw new SecurityException("SHA1PRNG not available", e);
                }
            } else {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("new SecureRandom() backed by wrong Provider: ");
                sb2.append(secureRandom.getProvider().getClass());
                throw new SecurityException(sb2.toString());
            }
        }
    }
}
