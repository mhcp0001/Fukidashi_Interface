package com.example.fukidashi_interface;

/**
 * Created by koki on 2017/06/25. Reference page=ttps://techbooster.org/android/9054/
 */

public class SpeechRecognitionNotify {
    private String et = null;
    private SpeechRecognitionListenerInterface listener = null;

    public void SpeechRecognitionNot(String et) {
        this.et = et;
    }

    /**
     * etに文字が入力されているかどうかを判定する
     */
    public void checkString(){
        if(this.listener != null){
            if(this.et.equals("")){
                // 文字が入力されていない場合の通知を行う
                listener.noSpeechRecognition();
            }else{
                // 文字が入力されている場合の通知を行う
                listener.SpeechRecognition();
            }
        }
    }

    /**
     * リスナーを追加する
     * @param listener
     */
    public void setListener(SpeechRecognitionListenerInterface listener){
        this.listener = listener;
    }

    /**
     * リスナーを削除する
     */
    public void removeListener(){
        this.listener = null;
    }
}
