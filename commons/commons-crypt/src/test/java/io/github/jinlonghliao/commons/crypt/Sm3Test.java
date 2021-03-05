/**
 * Copyright 2020-2021 JinlongLiao
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.jinlonghliao.commons.crypt;

import io.github.jinlonghliao.common.hex.HexUtils;
import io.github.jinlonghliao.commons.crypt.digest.DigestUtils;
import org.bouncycastle.crypto.digests.SM3Digest;
import org.bouncycastle.util.encoders.Hex;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

public class Sm3Test {
    public static final String digest = "abc";

    @Test
    public void sm3() throws UnsupportedEncodingException {
        final DigestUtils sm3 = DigestUtils.SM3;
        final MessageDigest messageDigest = sm3.getMessageDigest();
        String sm3Str = sm3.getDigest(digest);
        System.out.println(sm3Str);
    }
}
