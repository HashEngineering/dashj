/*
 * Copyright 2018 Dash Core Group
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * This file was generated by SWIG (http://www.swig.org) and modified.
 * Version 3.0.12
 */

package org.dashj.bls;


import com.google.common.base.Preconditions;

public class InsecureSignatureVector extends java.util.AbstractList<InsecureSignature> {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected InsecureSignatureVector(long cPtr, boolean cMemoryOwn) {
    Preconditions.checkArgument(cPtr != 0);
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(InsecureSignatureVector obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        JNI.delete_InsecureSignatureVector(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  InsecureSignatureVector(java.util.Collection<InsecureSignature> e) {
    this.reserve(e.size());
    for(InsecureSignature value: e) {
      this.push_back(value);
    }
  }

  public InsecureSignatureVector() {
    this(JNI.new_InsecureSignatureVector__SWIG_0(), true);
  }

  public InsecureSignatureVector(InsecureSignatureVector o) {
    this(JNI.new_InsecureSignatureVector__SWIG_2(InsecureSignatureVector.getCPtr(o), o), true);
  }

  public long capacity() {
    return JNI.InsecureSignatureVector_capacity(swigCPtr, this);
  }

  public void reserve(long n) {
    JNI.InsecureSignatureVector_reserve(swigCPtr, this, n);
  }

  public boolean isEmpty() {
    return JNI.InsecureSignatureVector_isEmpty(swigCPtr, this);
  }

  public void clear() {
    JNI.InsecureSignatureVector_clear(swigCPtr, this);
  }

  public void push_back(InsecureSignature x) {
    Preconditions.checkNotNull(x);
    JNI.InsecureSignatureVector_push_back(swigCPtr, this, InsecureSignature.getCPtr(x), x);
  }

  public InsecureSignature get(int i) {
    return new InsecureSignature(JNI.InsecureSignatureVector_get(swigCPtr, this, i), false);
  }

  public InsecureSignature set(int i, InsecureSignature VECTOR_VALUE_IN) {
    Preconditions.checkNotNull(VECTOR_VALUE_IN);
    return new InsecureSignature(JNI.InsecureSignatureVector_set(swigCPtr, this, i, InsecureSignature.getCPtr(VECTOR_VALUE_IN), VECTOR_VALUE_IN), true);
  }

  public int size() {
    return JNI.InsecureSignatureVector_size(swigCPtr, this);
  }

  public void removeRange(int from, int to) {
    JNI.InsecureSignatureVector_removeRange(swigCPtr, this, from, to);
  }

}
