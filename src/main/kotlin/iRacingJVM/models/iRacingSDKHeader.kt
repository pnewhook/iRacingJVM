package iRacingJVM.models

import iRacingJVM.iRacingSDK
import java.nio.ByteBuffer
import java.nio.ByteOrder

/**
 * @param numVars Length of array pointed to by varHeaderOffset
 * @param numBuf IRSDK_MAX_BUFS (3 for now)
 * @param bufLength Length in bytes for one line
 */
class iRacingSDKHeader{
    val apiVersion: Int
    val sdkStatus: Int
    val tickRate: Int
    val sessionInfoUpdate: Int
    internal val sessionInfoLength: Int
    internal val sessionInfoOffset: Int
    val numVars: Int
    internal val varHeaderOffset: Int
    internal val numBuf: Int
    internal val bufLength: Int
    val varBuf: MutableCollection<VariableBuffer> = ArrayList(iRacingSDK.IRSDK_MAX_BUFS)

    constructor(byteArray: ByteArray) {
        val byteBuffer = ByteBuffer.wrap(byteArray).order(ByteOrder.LITTLE_ENDIAN)
        this.apiVersion = byteBuffer.int
        this.sdkStatus = byteBuffer.int
        this.tickRate = byteBuffer.int
        this.sessionInfoUpdate = byteBuffer.int
        this.sessionInfoLength = byteBuffer.int
        this.sessionInfoOffset = byteBuffer.int
        this.numVars = byteBuffer.int
        this.varHeaderOffset = byteBuffer.int
        this.numBuf = byteBuffer.int
        this.bufLength = byteBuffer.int
        // advance buffer 2 integers because SDK pads for 16 byte alignment
        byteBuffer.position(byteBuffer.position() + Int.SIZE_BYTES * 2)

        // Each VariableBuffer is 2 integers + 2 padding integers
        val varBufHeaderSize = Int.SIZE_BYTES * (2 + 2)
        val variableBufferDefinitionBytes = ByteArray(varBufHeaderSize)
        for (i in 0 until iRacingSDK.IRSDK_MAX_BUFS) {
            byteBuffer.get(variableBufferDefinitionBytes, 0, varBufHeaderSize)
            this.varBuf.add(VariableBuffer(variableBufferDefinitionBytes))
        }
    }
}