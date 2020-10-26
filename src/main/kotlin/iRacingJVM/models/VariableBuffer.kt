package iRacingJVM.models

import java.nio.ByteBuffer
import java.nio.ByteOrder

class VariableBuffer {
    val tickCount: Int
    internal val bufOffset: Int
    constructor(rawBytes: ByteArray) {
        val byteBuffer = ByteBuffer.wrap(rawBytes).order(ByteOrder.LITTLE_ENDIAN)
        tickCount = byteBuffer.int
        bufOffset = byteBuffer.int
    }

}
