package com.sdk.hopeplayer.musicplayer.player;

import com.sdk.hopeplayer.musicplayer.data.bean.PlayMode;

import com.sdk.hopeplayer.musicplayer.data.bean.RemotePlayList;

import com.sdk.hopeplayer.musicplayer.data.bean.RemoteSong;

import com.sdk.hopeplayer.musicplayer.player.IPlayCallback;

import com.sdk.hopeplayer.musicplayer.player.ISongRemote;

import com.sdk.hopeplayer.socket.bean.MediaSource;


// Declare any non-default types here with import statements

interface PlayerManager {
    void setPlayList(in RemotePlayList list);

    boolean play();

    boolean playList(in RemotePlayList list);

    boolean playLast();

    boolean playNext();

    boolean pause();

    boolean isPlaying();

    int getProgress();

    RemoteSong getPlayingSong();

    RemotePlayList getPlayList();

    void updataPlayList(in RemotePlayList list);

    boolean seekTo(int progress);

    void setPlayMode(in PlayMode playMode);

    void registerCallback(IPlayCallback callback);

    void unregisterCallback(IPlayCallback callback);

    void removeCallbacks();

    void registerRemoteSong(ISongRemote songRemote);

    void unregisterRemoteSong(ISongRemote songRemote);

    void removeRemoteSong();

    void releasePlayer();

    void OnSoundEffectChange(int effect);

    MediaSource getMediaSource();

    void setMediaSource(in MediaSource source);

    void queryAllDeviceTunnelInfo();

    void setTunnelVolume(int tunnel,int volume);

    void setTunnelSwitch(int tunnel,boolean on);

    int getSoundEffect();

    void resetUser();

    void reconn();

        int getTunnelVolume(int tunnel);

        boolean getTunnelSwitch(int tunnel);

        int getTunnelCount();

    /** ---------------------------------Remote歌单操作------------------------------------**/

    RemotePlayList getFavouiteList();
    RemotePlayList getLocalList();
    List<RemotePlayList> getSongList();
    List<RemotePlayList> getAddPlayList();
    RemotePlayList createPlayList(String name);
    boolean deletePlayList(int playId);
    RemotePlayList getPlayListById(int playId);
    boolean addSongsToPlayList(in List<String> ids,int playId);
    boolean deleteSongsToPlayList(in List<String> ids,int playId);

    /** ---------------------------------特殊歌曲播放------------------------------------**/
    boolean playSpecialSong(String songName);
    boolean pauseSpecialSong();
    List<String> getSpecialSongs();
}
