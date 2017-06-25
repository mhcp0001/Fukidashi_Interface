package com.example.fukidashi_interface;

import java.util.EventListener;

/**
 * Created by koki on 2017/06/25.
 */

public interface SpeechRecognitionListenerInterface extends EventListener {

    /*音声認識がされていない状態を通知する*/
    public void noSpeechRecognition();

    /*音声認識がされている状態を通知する*/
    public void SpeechRecognition();

}
