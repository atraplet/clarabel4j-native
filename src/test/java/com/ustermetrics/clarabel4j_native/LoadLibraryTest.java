// SPDX-FileCopyrightText: Uster Metrics GmbH
// SPDX-License-Identifier: Apache-2.0
package com.ustermetrics.clarabel4j_native;

import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.scijava.nativelib.NativeLoader;

class LoadLibraryTest {

    @Test
    void loadLibrary() throws IOException {
        NativeLoader.loadLibrary("clarabel_c");
    }
}
