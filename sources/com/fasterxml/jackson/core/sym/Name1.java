package com.fasterxml.jackson.core.sym;

public final class Name1 extends Name {
    private static final Name1 EMPTY = new Name1("", 0, 0);

    /* renamed from: q */
    private final int f71q;

    public boolean equals(int i, int i2, int i3) {
        return false;
    }

    Name1(String str, int i, int i2) {
        super(str, i);
        this.f71q = i2;
    }

    public static Name1 getEmptyName() {
        return EMPTY;
    }

    public boolean equals(int i) {
        return i == this.f71q;
    }

    public boolean equals(int i, int i2) {
        return i == this.f71q && i2 == 0;
    }

    public boolean equals(int[] iArr, int i) {
        return i == 1 && iArr[0] == this.f71q;
    }
}
