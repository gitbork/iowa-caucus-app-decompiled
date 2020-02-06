package com.drew.metadata.mov.atoms;

import com.drew.lang.SequentialReader;
import com.drew.metadata.mov.media.QuickTimeMusicDirectory;
import java.io.IOException;

public class MusicSampleDescriptionAtom extends SampleDescriptionAtom<MusicSampleDescription> {

    class MusicSampleDescription extends SampleDescription {
        long flags;

        public MusicSampleDescription(SequentialReader sequentialReader) throws IOException {
            super(sequentialReader);
            this.flags = sequentialReader.getUInt32();
        }
    }

    public void addMetadata(QuickTimeMusicDirectory quickTimeMusicDirectory) {
    }

    public MusicSampleDescriptionAtom(SequentialReader sequentialReader, Atom atom) throws IOException {
        super(sequentialReader, atom);
    }

    /* access modifiers changed from: 0000 */
    public MusicSampleDescription getSampleDescription(SequentialReader sequentialReader) throws IOException {
        return new MusicSampleDescription(sequentialReader);
    }
}
