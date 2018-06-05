/**
 * Copyright 2014 Hash Engineering Solutions
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
 */
package org.bitcoinj.governance;

import org.bitcoinj.core.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;

public class GovernanceObjectVote extends Message implements Serializable {

    private static final Logger log = LoggerFactory.getLogger(GovernanceObjectVote.class);

    public GovernanceObjectVote(NetworkParameters params, byte[] payload)
    {
        super(params, payload, 0);
    }

    protected static int calcLength(byte[] buf, int offset) {
        int cursor = offset;// + 4;

        return cursor - offset;
    }
    @Override
    protected void parse() throws ProtocolException {


        length = cursor - offset;
    }

    @Override
    protected void bitcoinSerializeToStream(OutputStream stream) throws IOException {

    }

    public String toString() {
        return "";
    }


    public Sha256Hash getHash() {
        UnsafeByteArrayOutputStream bos = new UnsafeByteArrayOutputStream();

        return Sha256Hash.twiceOf(bos.toByteArray());
    }
}