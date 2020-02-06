package com.adobe.xmp.impl;

import com.adobe.xmp.XMPException;

class ParseState {
    private int pos = 0;
    private String str;

    public ParseState(String str2) {
        this.str = str2;
    }

    /* renamed from: ch */
    public char mo7090ch() {
        if (this.pos < this.str.length()) {
            return this.str.charAt(this.pos);
        }
        return 0;
    }

    /* renamed from: ch */
    public char mo7091ch(int i) {
        if (i < this.str.length()) {
            return this.str.charAt(i);
        }
        return 0;
    }

    public int gatherInt(String str2, int i) throws XMPException {
        char ch = mo7091ch(this.pos);
        int i2 = 0;
        boolean z = false;
        while ('0' <= ch && ch <= '9') {
            i2 = (i2 * 10) + (ch - '0');
            this.pos++;
            ch = mo7091ch(this.pos);
            z = true;
        }
        if (!z) {
            throw new XMPException(str2, 5);
        } else if (i2 > i) {
            return i;
        } else {
            if (i2 < 0) {
                return 0;
            }
            return i2;
        }
    }

    public boolean hasNext() {
        return this.pos < this.str.length();
    }

    public int length() {
        return this.str.length();
    }

    public int pos() {
        return this.pos;
    }

    public void skip() {
        this.pos++;
    }
}
