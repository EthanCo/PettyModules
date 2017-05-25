// ISongRemote.aidl
package com.sdk.hopeplayer.musicplayer.player;

// Declare any non-default types here with import statements

interface ISongRemote {
    void notifyPlayListOption();
    void notifyPlayListUpdated();
    void notifyFavoriteChange();
}
