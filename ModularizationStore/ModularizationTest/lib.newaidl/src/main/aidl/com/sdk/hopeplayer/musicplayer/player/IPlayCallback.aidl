// IPlayCallback.aidl
package com.sdk.hopeplayer.musicplayer.player;

import com.sdk.hopeplayer.musicplayer.data.bean.RemoteSong;

import com.sdk.hopeplayer.musicplayer.data.bean.RemotePlayList;

import com.sdk.hopeplayer.socket.bean.MediaSource;

import com.sdk.hopeplayer.musicplayer.data.bean.PlayMode;

// Declare any non-default types here with import statements

interface IPlayCallback {
    void onSwitchLast(in RemoteSong last);

    void onSwitchNext(in RemoteSong next);

    void onComplete(in RemoteSong next);

    void onPlayStatusChanged(boolean isPlaying);

    void OnSoundEffectChange(int effect);

    void notifyMediaSourceSwitch(in MediaSource source);

    void notifyTunnelState(int tunnel,int volume,boolean on);

    void notifyTunnelVolume(int tunnel,int volume);

    void notifyTunnelSwitch(int tunnel,boolean on);
//    //当音源改变
//    void onMediaSourceChange();
//
    //当播放模式改变
    void onPlayModeChange(in PlayMode playMode);
    //App播放指定歌曲
    void notifyRemotePlay(in RemotePlayList playlist);
//    //App播放指定歌单
    void notifyRemotePlayList(int playListId, int songId);
}