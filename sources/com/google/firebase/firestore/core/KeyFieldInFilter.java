package com.google.firebase.firestore.core;

import com.google.firebase.firestore.core.Filter.Operator;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.FieldPath;
import com.google.firebase.firestore.model.value.ArrayValue;
import com.google.firebase.firestore.model.value.FieldValue;
import com.google.firebase.firestore.model.value.ReferenceValue;
import com.google.firebase.firestore.util.Assert;

/* compiled from: com.google.firebase:firebase-firestore@@20.2.0 */
public class KeyFieldInFilter extends FieldFilter {
    KeyFieldInFilter(FieldPath fieldPath, ArrayValue arrayValue) {
        super(fieldPath, Operator.IN, arrayValue);
        for (FieldValue fieldValue : ((ArrayValue) getValue()).getInternalValue()) {
            Assert.hardAssert(fieldValue instanceof ReferenceValue, "Comparing on key with IN, but an array value was not a ReferenceValue", new Object[0]);
        }
    }

    public boolean matches(Document document) {
        for (FieldValue fieldValue : ((ArrayValue) getValue()).getInternalValue()) {
            if (document.getKey().equals(((ReferenceValue) fieldValue).value())) {
                return true;
            }
        }
        return false;
    }
}
