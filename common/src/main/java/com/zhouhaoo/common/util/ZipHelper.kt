/*
 * Copyright (c) 2018  zhouhaoo
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.zhouhaoo.common.util

import java.io.ByteArrayInputStream
import java.io.Closeable
import java.io.IOException
import java.io.UnsupportedEncodingException
import java.nio.charset.Charset
import java.util.*
import java.util.zip.DataFormatException
import java.util.zip.GZIPInputStream
import java.util.zip.Inflater

/**
 * Created by zhou on 18/2/7.
 */
class ZipHelper {
    companion object {
        fun decompressForGzip(compressed: ByteArray, charset: Charset): String? {
            val bufferSize = compressed.size
            var gis: GZIPInputStream? = null
            var inputStream: ByteArrayInputStream? = null
            try {
                inputStream = ByteArrayInputStream(compressed)
                gis = GZIPInputStream(inputStream, bufferSize)
                var string = StringBuilder()
                var data = ByteArray(bufferSize)
                var bytesRead: Int
                while (true) {
                    bytesRead = gis.read(data)
                    if (bytesRead == -1) {
                        break
                    }
                    string.append(String(data, 0, bytesRead, charset))
                }
                return string.toString()
            } catch (e: IOException) {
                e.printStackTrace()
            } finally {
                closeQuietly(gis)
                closeQuietly(inputStream)
            }
            return null
        }

        fun decompressToStringForZlib(bytesToDecompress: ByteArray, charset: Charset): String? {
            val bytesDecompressed = decompressForZlib(bytesToDecompress)
            var returnValue: String? = null
            try {
                returnValue = String(bytesDecompressed!!, 0, bytesDecompressed.size,
                        charset)
            } catch (uee: UnsupportedEncodingException) {
                uee.printStackTrace()
            }
            return returnValue
        }

        private fun closeQuietly(closeable: Closeable?) {
            if (closeable != null) {
                try {
                    closeable.close()
                } catch (rethrown: RuntimeException) {
                    throw rethrown
                } catch (ignored: Exception) {
                }

            }
        }

        private fun decompressForZlib(bytesToDecompress: ByteArray): ByteArray? {
            var returnValues: ByteArray? = null
            val inflater = Inflater()
            val numberOfBytesToDecompress = bytesToDecompress.size
            inflater.setInput(
                    bytesToDecompress,
                    0,
                    numberOfBytesToDecompress
            )
            var numberOfBytesDecompressedSoFar = 0
            val bytesDecompressedSoFar = ArrayList<Byte>()
            try {
                while (!inflater.needsInput()) {
                    val bytesDecompressedBuffer = ByteArray(numberOfBytesToDecompress)
                    val numberOfBytesDecompressedThisTime = inflater.inflate(
                            bytesDecompressedBuffer
                    )
                    numberOfBytesDecompressedSoFar += numberOfBytesDecompressedThisTime
                    (0 until numberOfBytesDecompressedThisTime).mapTo(bytesDecompressedSoFar) { bytesDecompressedBuffer[it] }
                }
                returnValues = ByteArray(bytesDecompressedSoFar.size)
                for (b in returnValues.indices) {
                    returnValues[b] = bytesDecompressedSoFar[b]
                }
            } catch (dfe: DataFormatException) {
                dfe.printStackTrace()
            }
            inflater.end()
            return returnValues
        }
    }

}