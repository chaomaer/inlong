/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.inlong.sort.base.util;

import org.apache.flink.table.data.binary.BinaryRowData;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * calculate tool for object
 */
public class CalculateObjectSizeUtils {

    /**
     * {@link BinaryRowData} don't implement the {@link Object#toString} method
     * So, we need use {@link BinaryRowData#getSizeInBytes} to get byte size.
     */
    public static long getDataSize(Object object) {
        if (object == null) {
            return 0L;
        }
        long size;
        if (object instanceof BinaryRowData) {
            BinaryRowData binaryRowData = (BinaryRowData) object;
            size = binaryRowData.getSizeInBytes();
        } else {
            size = object.toString().getBytes(StandardCharsets.UTF_8).length;
        }
        return size;
    }

    public static long getDataArraySize(Object[] objects) {
        return Arrays.stream(objects)
                .mapToLong(CalculateObjectSizeUtils::getDataSize)
                .sum();
    }

}
