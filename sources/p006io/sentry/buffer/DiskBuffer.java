package p006io.sentry.buffer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.Iterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import p006io.sentry.event.Event;

/* renamed from: io.sentry.buffer.DiskBuffer */
public class DiskBuffer implements Buffer {
    public static final String FILE_SUFFIX = ".sentry-event";
    private static final Logger logger = LoggerFactory.getLogger(DiskBuffer.class);
    private final File bufferDir;
    private int maxEvents;

    public DiskBuffer(File file, int i) {
        this.bufferDir = file;
        this.maxEvents = i;
        StringBuilder sb = new StringBuilder();
        sb.append("Could not create or write to disk buffer dir: ");
        sb.append(file.getAbsolutePath());
        String sb2 = sb.toString();
        try {
            file.mkdirs();
            if (!file.isDirectory() || !file.canWrite()) {
                throw new RuntimeException(sb2);
            }
            Logger logger2 = logger;
            StringBuilder sb3 = new StringBuilder();
            sb3.append(Integer.toString(getNumStoredEvents()));
            sb3.append(" stored events found in dir: ");
            sb3.append(file.getAbsolutePath());
            logger2.debug(sb3.toString());
        } catch (Exception e) {
            throw new RuntimeException(sb2, e);
        }
    }

    public void add(Event event) {
        FileOutputStream fileOutputStream;
        ObjectOutputStream objectOutputStream;
        Throwable th;
        Throwable th2;
        if (getNumStoredEvents() >= this.maxEvents) {
            Logger logger2 = logger;
            StringBuilder sb = new StringBuilder();
            sb.append("Not adding Event because at least ");
            sb.append(Integer.toString(this.maxEvents));
            sb.append(" events are already stored: ");
            sb.append(event.getId());
            logger2.warn(sb.toString());
            return;
        }
        String absolutePath = this.bufferDir.getAbsolutePath();
        StringBuilder sb2 = new StringBuilder();
        sb2.append(event.getId().toString());
        sb2.append(FILE_SUFFIX);
        File file = new File(absolutePath, sb2.toString());
        if (file.exists()) {
            Logger logger3 = logger;
            StringBuilder sb3 = new StringBuilder();
            sb3.append("Not adding Event to offline storage because it already exists: ");
            sb3.append(file.getAbsolutePath());
            logger3.trace(sb3.toString());
            return;
        }
        Logger logger4 = logger;
        StringBuilder sb4 = new StringBuilder();
        sb4.append("Adding Event to offline storage: ");
        sb4.append(file.getAbsolutePath());
        logger4.debug(sb4.toString());
        try {
            fileOutputStream = new FileOutputStream(file);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            try {
                objectOutputStream.writeObject(event);
                objectOutputStream.close();
                fileOutputStream.close();
            } catch (Throwable th3) {
                Throwable th4 = th3;
                th = r3;
                th2 = th4;
            }
        } catch (Exception e) {
            Logger logger5 = logger;
            StringBuilder sb5 = new StringBuilder();
            sb5.append("Error writing Event to offline storage: ");
            sb5.append(event.getId());
            logger5.error(sb5.toString(), (Throwable) e);
        } catch (Throwable unused) {
        }
        Logger logger6 = logger;
        StringBuilder sb6 = new StringBuilder();
        sb6.append(Integer.toString(getNumStoredEvents()));
        sb6.append(" stored events are now in dir: ");
        sb6.append(this.bufferDir.getAbsolutePath());
        logger6.debug(sb6.toString());
        return;
        throw th;
        throw th2;
        if (th != null) {
            try {
                objectOutputStream.close();
            } catch (Throwable unused2) {
            }
        } else {
            objectOutputStream.close();
        }
        throw th2;
    }

    public void discard(Event event) {
        File file = this.bufferDir;
        StringBuilder sb = new StringBuilder();
        sb.append(event.getId().toString());
        sb.append(FILE_SUFFIX);
        File file2 = new File(file, sb.toString());
        if (file2.exists()) {
            Logger logger2 = logger;
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Discarding Event from offline storage: ");
            sb2.append(file2.getAbsolutePath());
            logger2.debug(sb2.toString());
            if (!file2.delete()) {
                Logger logger3 = logger;
                StringBuilder sb3 = new StringBuilder();
                sb3.append("Failed to delete Event: ");
                sb3.append(file2.getAbsolutePath());
                logger3.warn(sb3.toString());
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0070, code lost:
        r3 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0071, code lost:
        r4 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0070 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:3:0x0011] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x007b A[SYNTHETIC, Splitter:B:41:0x007b] */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x007f A[SYNTHETIC, Splitter:B:43:0x007f] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private p006io.sentry.event.Event fileToEvent(java.io.File r8) {
        /*
            r7 = this;
            java.lang.String r0 = "Failed to delete Event: "
            r1 = 0
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ FileNotFoundException -> 0x00bc, Exception -> 0x0083 }
            java.io.File r3 = new java.io.File     // Catch:{ FileNotFoundException -> 0x00bc, Exception -> 0x0083 }
            java.lang.String r4 = r8.getAbsolutePath()     // Catch:{ FileNotFoundException -> 0x00bc, Exception -> 0x0083 }
            r3.<init>(r4)     // Catch:{ FileNotFoundException -> 0x00bc, Exception -> 0x0083 }
            r2.<init>(r3)     // Catch:{ FileNotFoundException -> 0x00bc, Exception -> 0x0083 }
            java.io.ObjectInputStream r3 = new java.io.ObjectInputStream     // Catch:{ Throwable -> 0x0073, all -> 0x0070 }
            r3.<init>(r2)     // Catch:{ Throwable -> 0x0073, all -> 0x0070 }
            java.lang.Object r4 = r3.readObject()     // Catch:{ Throwable -> 0x0060, all -> 0x005d }
            r3.close()     // Catch:{ Throwable -> 0x0073, all -> 0x0070 }
            r2.close()     // Catch:{ FileNotFoundException -> 0x00bc, Exception -> 0x0083 }
            io.sentry.event.Event r4 = (p006io.sentry.event.Event) r4     // Catch:{ Exception -> 0x0023 }
            return r4
        L_0x0023:
            r2 = move-exception
            org.slf4j.Logger r3 = logger
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "Error casting Object to Event: "
            r4.append(r5)
            java.lang.String r5 = r8.getAbsolutePath()
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            r3.error(r4, r2)
            boolean r2 = r8.delete()
            if (r2 != 0) goto L_0x005c
            org.slf4j.Logger r2 = logger
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r0)
            java.lang.String r8 = r8.getAbsolutePath()
            r3.append(r8)
            java.lang.String r8 = r3.toString()
            r2.warn(r8)
        L_0x005c:
            return r1
        L_0x005d:
            r4 = move-exception
            r5 = r1
            goto L_0x0066
        L_0x0060:
            r4 = move-exception
            throw r4     // Catch:{ all -> 0x0062 }
        L_0x0062:
            r5 = move-exception
            r6 = r5
            r5 = r4
            r4 = r6
        L_0x0066:
            if (r5 == 0) goto L_0x006c
            r3.close()     // Catch:{ Throwable -> 0x006f, all -> 0x0070 }
            goto L_0x006f
        L_0x006c:
            r3.close()     // Catch:{ Throwable -> 0x0073, all -> 0x0070 }
        L_0x006f:
            throw r4     // Catch:{ Throwable -> 0x0073, all -> 0x0070 }
        L_0x0070:
            r3 = move-exception
            r4 = r1
            goto L_0x0079
        L_0x0073:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x0075 }
        L_0x0075:
            r4 = move-exception
            r6 = r4
            r4 = r3
            r3 = r6
        L_0x0079:
            if (r4 == 0) goto L_0x007f
            r2.close()     // Catch:{ Throwable -> 0x0082 }
            goto L_0x0082
        L_0x007f:
            r2.close()     // Catch:{ FileNotFoundException -> 0x00bc, Exception -> 0x0083 }
        L_0x0082:
            throw r3     // Catch:{ FileNotFoundException -> 0x00bc, Exception -> 0x0083 }
        L_0x0083:
            r2 = move-exception
            org.slf4j.Logger r3 = logger
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "Error reading Event file: "
            r4.append(r5)
            java.lang.String r5 = r8.getAbsolutePath()
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            r3.error(r4, r2)
            boolean r2 = r8.delete()
            if (r2 != 0) goto L_0x00bc
            org.slf4j.Logger r2 = logger
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r0)
            java.lang.String r8 = r8.getAbsolutePath()
            r3.append(r8)
            java.lang.String r8 = r3.toString()
            r2.warn(r8)
        L_0x00bc:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: p006io.sentry.buffer.DiskBuffer.fileToEvent(java.io.File):io.sentry.event.Event");
    }

    /* access modifiers changed from: private */
    public Event getNextEvent(Iterator<File> it) {
        while (it.hasNext()) {
            File file = (File) it.next();
            if (file.getAbsolutePath().endsWith(FILE_SUFFIX)) {
                Event fileToEvent = fileToEvent(file);
                if (fileToEvent != null) {
                    return fileToEvent;
                }
            }
        }
        return null;
    }

    public Iterator<Event> getEvents() {
        final Iterator it = Arrays.asList(this.bufferDir.listFiles()).iterator();
        return new Iterator<Event>() {
            private Event next = DiskBuffer.this.getNextEvent(it);

            public boolean hasNext() {
                return this.next != null;
            }

            public Event next() {
                Event event = this.next;
                this.next = DiskBuffer.this.getNextEvent(it);
                return event;
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    private int getNumStoredEvents() {
        int i = 0;
        for (File absolutePath : this.bufferDir.listFiles()) {
            if (absolutePath.getAbsolutePath().endsWith(FILE_SUFFIX)) {
                i++;
            }
        }
        return i;
    }
}
