package net.ossrs.sea.rtmp;

import java.io.IOException;

/**
 * Simple RTMP publisher, using vanilla Java networking (no NIO)
 * This was created primarily to address a NIO bug in Android 2.2 when
 * used with Apache Mina, but also to provide an easy-to-use way to access
 * RTMP streams
 * 
 * @author francois, leo
 */
public interface RtmpPublisher {
    
    void connect(String url) throws IOException;
    
    /**
     * Issues an RTMP "publish" command and write the media content stream packets (audio and video). 
     * 
     * @param publishType specify the way to publish raw RTMP packets among "live", "record" and "append"
     * @return An outputStream allowing you to write the incoming media content data
     * @throws IllegalStateException if the client is not connected to a RTMP server
     * @throws IOException if a network/IO error occurs
     */
    void publish(String publishType) throws IllegalStateException, IOException;
     
    /**
     * Stops and closes the current RTMP stream
     */
    void closeStream() throws IllegalStateException;
    
    /**
     * Shuts down the RTMP client and stops all threads associated with it
     */
    void shutdown();

    /**
     * publish a video content packet to server
     */
    void publishVideoData(byte[] data) throws IllegalStateException;

    /**
     * publish an audio content packet to server
     */
    void publishAudioData(byte[] data) throws IllegalStateException;

    /**
     * RTMP event handler.
     */
    interface EventHandler {

        void onRtmpConnecting(String msg);

        void onRtmpConnected(String msg);

        void onRtmpVideoStreaming(String msg);

        void onRtmpAudioStreaming(String msg);

        void onRtmpStopped(String msg);

        void onRtmpDisconnected(String msg);
    }
}
