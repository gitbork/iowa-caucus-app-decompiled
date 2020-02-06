package com.oblador.keychain.cipherStorage;

import androidx.annotation.NonNull;
import com.facebook.android.crypto.keychain.AndroidConceal;
import com.facebook.android.crypto.keychain.SharedPrefsBackedKeyChain;
import com.facebook.crypto.Crypto;
import com.facebook.crypto.CryptoConfig;
import com.facebook.crypto.Entity;
import com.facebook.react.bridge.ReactApplicationContext;
import com.oblador.keychain.SecurityLevel;
import com.oblador.keychain.cipherStorage.CipherStorage.DecryptionResult;
import com.oblador.keychain.cipherStorage.CipherStorage.EncryptionResult;
import com.oblador.keychain.exceptions.CryptoFailedException;
import java.nio.charset.Charset;

public class CipherStorageFacebookConceal implements CipherStorage {
    public static final String CIPHER_STORAGE_NAME = "FacebookConceal";
    public static final String KEYCHAIN_DATA = "RN_KEYCHAIN";
    private final Crypto crypto;

    public String getCipherStorageName() {
        return CIPHER_STORAGE_NAME;
    }

    public int getMinSupportedApiLevel() {
        return 16;
    }

    public void removeKey(@NonNull String str) {
    }

    public boolean supportsSecureHardware() {
        return false;
    }

    public CipherStorageFacebookConceal(ReactApplicationContext reactApplicationContext) {
        this.crypto = AndroidConceal.get().createDefaultCrypto(new SharedPrefsBackedKeyChain(reactApplicationContext, CryptoConfig.KEY_256));
    }

    public SecurityLevel securityLevel() {
        return SecurityLevel.ANY;
    }

    public EncryptionResult encrypt(@NonNull String str, @NonNull String str2, @NonNull String str3, SecurityLevel securityLevel) throws CryptoFailedException {
        String str4 = "UTF-8";
        if (!securityLevel().satisfiesSafetyThreshold(securityLevel)) {
            throw new CryptoFailedException(String.format("Insufficient security level (wants %s; got %s)", new Object[]{securityLevel, securityLevel()}));
        } else if (this.crypto.isAvailable()) {
            try {
                return new EncryptionResult(this.crypto.encrypt(str2.getBytes(Charset.forName(str4)), createUsernameEntity(str)), this.crypto.encrypt(str3.getBytes(Charset.forName(str4)), createPasswordEntity(str)), this);
            } catch (Exception e) {
                StringBuilder sb = new StringBuilder();
                sb.append("Encryption failed for service ");
                sb.append(str);
                throw new CryptoFailedException(sb.toString(), e);
            }
        } else {
            throw new CryptoFailedException("Crypto is missing");
        }
    }

    public DecryptionResult decrypt(@NonNull String str, @NonNull byte[] bArr, @NonNull byte[] bArr2) throws CryptoFailedException {
        String str2 = "UTF-8";
        if (this.crypto.isAvailable()) {
            Entity createUsernameEntity = createUsernameEntity(str);
            Entity createPasswordEntity = createPasswordEntity(str);
            try {
                return new DecryptionResult(new String(this.crypto.decrypt(bArr, createUsernameEntity), Charset.forName(str2)), new String(this.crypto.decrypt(bArr2, createPasswordEntity), Charset.forName(str2)), SecurityLevel.ANY);
            } catch (Exception e) {
                StringBuilder sb = new StringBuilder();
                sb.append("Decryption failed for service ");
                sb.append(str);
                throw new CryptoFailedException(sb.toString(), e);
            }
        } else {
            throw new CryptoFailedException("Crypto is missing");
        }
    }

    private Entity createUsernameEntity(String str) {
        String entityPrefix = getEntityPrefix(str);
        StringBuilder sb = new StringBuilder();
        sb.append(entityPrefix);
        sb.append("user");
        return Entity.create(sb.toString());
    }

    private Entity createPasswordEntity(String str) {
        String entityPrefix = getEntityPrefix(str);
        StringBuilder sb = new StringBuilder();
        sb.append(entityPrefix);
        sb.append("pass");
        return Entity.create(sb.toString());
    }

    private String getEntityPrefix(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("RN_KEYCHAIN:");
        sb.append(str);
        return sb.toString();
    }
}
