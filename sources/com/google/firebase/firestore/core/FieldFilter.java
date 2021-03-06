package com.google.firebase.firestore.core;

import com.google.firebase.firestore.core.Filter.Operator;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.FieldPath;
import com.google.firebase.firestore.model.value.ArrayValue;
import com.google.firebase.firestore.model.value.DoubleValue;
import com.google.firebase.firestore.model.value.FieldValue;
import com.google.firebase.firestore.model.value.NullValue;
import com.google.firebase.firestore.model.value.ReferenceValue;
import com.google.firebase.firestore.util.Assert;
import java.util.Arrays;

/* compiled from: com.google.firebase:firebase-firestore@@20.2.0 */
public class FieldFilter extends Filter {
    private final FieldPath field;
    private final Operator operator;
    private final FieldValue value;

    /* renamed from: com.google.firebase.firestore.core.FieldFilter$1 */
    /* compiled from: com.google.firebase:firebase-firestore@@20.2.0 */
    static /* synthetic */ class C15221 {
        static final /* synthetic */ int[] $SwitchMap$com$google$firebase$firestore$core$Filter$Operator = new int[Operator.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0035 */
        static {
            /*
                com.google.firebase.firestore.core.Filter$Operator[] r0 = com.google.firebase.firestore.core.Filter.Operator.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$google$firebase$firestore$core$Filter$Operator = r0
                int[] r0 = $SwitchMap$com$google$firebase$firestore$core$Filter$Operator     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.google.firebase.firestore.core.Filter$Operator r1 = com.google.firebase.firestore.core.Filter.Operator.LESS_THAN     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = $SwitchMap$com$google$firebase$firestore$core$Filter$Operator     // Catch:{ NoSuchFieldError -> 0x001f }
                com.google.firebase.firestore.core.Filter$Operator r1 = com.google.firebase.firestore.core.Filter.Operator.LESS_THAN_OR_EQUAL     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = $SwitchMap$com$google$firebase$firestore$core$Filter$Operator     // Catch:{ NoSuchFieldError -> 0x002a }
                com.google.firebase.firestore.core.Filter$Operator r1 = com.google.firebase.firestore.core.Filter.Operator.EQUAL     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = $SwitchMap$com$google$firebase$firestore$core$Filter$Operator     // Catch:{ NoSuchFieldError -> 0x0035 }
                com.google.firebase.firestore.core.Filter$Operator r1 = com.google.firebase.firestore.core.Filter.Operator.GREATER_THAN     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                int[] r0 = $SwitchMap$com$google$firebase$firestore$core$Filter$Operator     // Catch:{ NoSuchFieldError -> 0x0040 }
                com.google.firebase.firestore.core.Filter$Operator r1 = com.google.firebase.firestore.core.Filter.Operator.GREATER_THAN_OR_EQUAL     // Catch:{ NoSuchFieldError -> 0x0040 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0040 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0040 }
            L_0x0040:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.firestore.core.FieldFilter.C15221.<clinit>():void");
        }
    }

    protected FieldFilter(FieldPath fieldPath, Operator operator2, FieldValue fieldValue) {
        this.field = fieldPath;
        this.operator = operator2;
        this.value = fieldValue;
    }

    public Operator getOperator() {
        return this.operator;
    }

    public FieldPath getField() {
        return this.field;
    }

    public FieldValue getValue() {
        return this.value;
    }

    public static FieldFilter create(FieldPath fieldPath, Operator operator2, FieldValue fieldValue) {
        if (fieldPath.isKeyField()) {
            if (operator2 == Operator.IN) {
                Assert.hardAssert(fieldValue instanceof ArrayValue, "Comparing on key with IN, but an array value was not a RefValue", new Object[0]);
                return new KeyFieldInFilter(fieldPath, (ArrayValue) fieldValue);
            }
            Assert.hardAssert(fieldValue instanceof ReferenceValue, "Comparing on key, but filter value not a ReferenceValue", new Object[0]);
            boolean z = (operator2 == Operator.ARRAY_CONTAINS || operator2 == Operator.ARRAY_CONTAINS_ANY) ? false : true;
            StringBuilder sb = new StringBuilder();
            sb.append(operator2.toString());
            sb.append("queries don't make sense on document keys");
            Assert.hardAssert(z, sb.toString(), new Object[0]);
            return new KeyFieldFilter(fieldPath, operator2, (ReferenceValue) fieldValue);
        } else if (fieldValue.equals(NullValue.nullValue())) {
            if (operator2 == Operator.EQUAL) {
                return new FieldFilter(fieldPath, operator2, fieldValue);
            }
            throw new IllegalArgumentException("Invalid Query. You can only perform equality comparisons on null (via whereEqualTo()).");
        } else if (fieldValue.equals(DoubleValue.NaN)) {
            if (operator2 == Operator.EQUAL) {
                return new FieldFilter(fieldPath, operator2, fieldValue);
            }
            throw new IllegalArgumentException("Invalid Query. You can only perform equality comparisons on NaN (via whereEqualTo()).");
        } else if (operator2 == Operator.ARRAY_CONTAINS) {
            return new ArrayContainsFilter(fieldPath, fieldValue);
        } else {
            if (operator2 == Operator.IN) {
                boolean z2 = fieldValue instanceof ArrayValue;
                StringBuilder sb2 = new StringBuilder();
                sb2.append("IN filter has invalid value: ");
                sb2.append(fieldValue.toString());
                Assert.hardAssert(z2, sb2.toString(), new Object[0]);
                return new InFilter(fieldPath, (ArrayValue) fieldValue);
            } else if (operator2 != Operator.ARRAY_CONTAINS_ANY) {
                return new FieldFilter(fieldPath, operator2, fieldValue);
            } else {
                boolean z3 = fieldValue instanceof ArrayValue;
                StringBuilder sb3 = new StringBuilder();
                sb3.append("ARRAY_CONTAINS_ANY filter has invalid value: ");
                sb3.append(fieldValue.toString());
                Assert.hardAssert(z3, sb3.toString(), new Object[0]);
                return new ArrayContainsAnyFilter(fieldPath, (ArrayValue) fieldValue);
            }
        }
    }

    public boolean matches(Document document) {
        FieldValue field2 = document.getField(this.field);
        return field2 != null && this.value.typeOrder() == field2.typeOrder() && matchesComparison(field2.compareTo(this.value));
    }

    /* access modifiers changed from: protected */
    public boolean matchesComparison(int i) {
        int i2 = C15221.$SwitchMap$com$google$firebase$firestore$core$Filter$Operator[this.operator.ordinal()];
        boolean z = false;
        if (i2 == 1) {
            if (i < 0) {
                z = true;
            }
            return z;
        } else if (i2 == 2) {
            if (i <= 0) {
                z = true;
            }
            return z;
        } else if (i2 == 3) {
            if (i == 0) {
                z = true;
            }
            return z;
        } else if (i2 == 4) {
            if (i > 0) {
                z = true;
            }
            return z;
        } else if (i2 == 5) {
            if (i >= 0) {
                z = true;
            }
            return z;
        } else {
            throw Assert.fail("Unknown FieldFilter operator: %s", this.operator);
        }
    }

    public boolean isInequality() {
        return Arrays.asList(new Operator[]{Operator.LESS_THAN, Operator.LESS_THAN_OR_EQUAL, Operator.GREATER_THAN, Operator.GREATER_THAN_OR_EQUAL}).contains(this.operator);
    }

    public String getCanonicalId() {
        StringBuilder sb = new StringBuilder();
        sb.append(getField().canonicalString());
        sb.append(getOperator().toString());
        sb.append(getValue().toString());
        return sb.toString();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.field.canonicalString());
        String str = " ";
        sb.append(str);
        sb.append(this.operator);
        sb.append(str);
        sb.append(this.value);
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof FieldFilter)) {
            return false;
        }
        FieldFilter fieldFilter = (FieldFilter) obj;
        if (this.operator != fieldFilter.operator || !this.field.equals(fieldFilter.field) || !this.value.equals(fieldFilter.value)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((((1147 + this.operator.hashCode()) * 31) + this.field.hashCode()) * 31) + this.value.hashCode();
    }
}
