package com.sdk.hopeplayer.musicplayer.data.bean;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;


/**
 * Created with Android Studio.
 * User: ryan.hoo.j@gmail.com
 * Date: 9/5/16
 * Time: 5:53 PM
 * Desc: PlayList
 */
public class RemotePlayList implements Parcelable {
    public static final int NO_POSITION = -1;

    private int id;

    private String name;

    private int type;

    private boolean scene = false;

    private List<RemoteSong> songs = new ArrayList<>();

    private int playingIndex = -1;

    private boolean isMainList = false;

    private PlayMode playMode = PlayMode.LOOP;

    public RemotePlayList() {
        // EMPTY
    }

    public RemotePlayList(RemoteSong song) {
        songs.add(song);
    }

    public RemotePlayList(Parcel in) {
        readFromParcel(in);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isScene() {
        return scene;
    }

    public void setScene(boolean scene) {
        this.scene = scene;
    }

    public int getNumOfSongs() {
        return songs.size();
    }

    public boolean isMainList() {
        return isMainList;
    }

    public void setMainList(boolean mainList) {
        isMainList = mainList;
    }

    @NonNull
    public List<RemoteSong> getSongs() {
        if (songs == null) {
            songs = new ArrayList<>();
        }
        return songs;
    }

    public void setRemoteSongs(@Nullable List<RemoteSong> songs) {
        if (songs == null) {
            songs = new ArrayList<>();
        }
        this.songs = songs;
    }

    public int getPlayingIndex() {
        return playingIndex;
    }

    public void setPlayingIndex(int playingIndex) {
        this.playingIndex = playingIndex;
    }

    public PlayMode getPlayMode() {
        return playMode;
    }

    public void setPlayMode(PlayMode playMode) {
        this.playMode = playMode;
    }

    // Parcelable

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeInt(this.type);
        dest.writeByte(this.scene ? (byte) 1 : (byte) 0);
        dest.writeTypedList(this.songs);
        dest.writeInt(this.playingIndex);
        dest.writeInt(this.playMode == null ? -1 : this.playMode.ordinal());
    }

    public void readFromParcel(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.type = in.readInt();
        this.scene = in.readByte() != 0;
        this.songs = in.createTypedArrayList(RemoteSong.CREATOR);
        this.playingIndex = in.readInt();
        int tmpPlayMode = in.readInt();
        this.playMode = tmpPlayMode == -1 ? null : PlayMode.values()[tmpPlayMode];
    }

    public static final Creator<RemotePlayList> CREATOR = new Creator<RemotePlayList>() {
        @Override
        public RemotePlayList createFromParcel(Parcel source) {
            return new RemotePlayList(source);
        }

        @Override
        public RemotePlayList[] newArray(int size) {
            return new RemotePlayList[size];
        }
    };

    // Utils

    public int getItemCount() {
        return songs == null ? 0 : songs.size();
    }

    public void addSong(@Nullable RemoteSong song) {
        if (song == null) return;
        if (contains(song))return;
        songs.add(song);
    }

    public void addRecentSong(@Nullable RemoteSong song) {
        if (song == null) return;
        removeSong(song);
        songs.add(0,song);
        if (songs.size() > 20){
            songs.remove(songs.size()-1);
        }
    }

    public void addSong(@Nullable RemoteSong song, int index) {
        if (song == null) return;
        if (contains(song))return;
        songs.add(index, song);
    }

    public void addSongList(@Nullable List<RemoteSong> songs) {
        if (songs == null || songs.isEmpty()) return;
        for (RemoteSong song:songs) {
            if (!contains(song))
                addSong(song,0);
        }
    }

    public boolean removeSong(RemoteSong song) {
        if (song == null) return false;

        int index;
        if ((index = songs.indexOf(song)) != -1) {
            if (songs.remove(index) != null) {
                return true;
            }
        } else {
            for (Iterator<RemoteSong> iterator = songs.iterator(); iterator.hasNext(); ) {
                RemoteSong item = iterator.next();
                if (song.getId() == item.getId()) {
                    iterator.remove();
                    return true;
                }
            }
        }
        return false;
    }

    public void removeSongList(List<RemoteSong> songList) {
        for (RemoteSong song: songList) {
            removeSong(song);
        }
    }

    public boolean contains(RemoteSong song){
        for (Iterator<RemoteSong> iterator = songs.iterator(); iterator.hasNext(); ) {
            RemoteSong item = iterator.next();
            if (song.getId() == item.getId()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Prepare to play
     */
    public boolean prepare() {
        if (songs.isEmpty()) return false;
        if (playingIndex == NO_POSITION) {
            playingIndex = 0;
        }
        return true;
    }

    /**
     * The current song being played or is playing based on the {@link #playingIndex}
     */
    public RemoteSong getCurrentSong() {
        if (!songs.isEmpty() && playingIndex != NO_POSITION) {
            return songs.get(playingIndex);
        }
        return null;
    }

    public Integer indexOf(RemoteSong song){
        for (RemoteSong item : songs) {
            if (song.getId() == item.getId()) {
                return songs.indexOf(item);
            }
        }
        return -1;
    }

    public Integer indexOf(int songId){
        for (RemoteSong item : songs) {
            if (songId == item.getId()) {
                return songs.indexOf(item);
            }
        }
        return -1;
    }

    public boolean hasLast() {
        return songs != null && songs.size() != 0;
    }

    public RemoteSong last() {
        switch (playMode) {
            case SINGLE:
            case LOOP:
            case LIST:
                int newIndex = playingIndex - 1;
                if (newIndex < 0) {
                    newIndex = songs.size() - 1;
                }
                playingIndex = newIndex;
                break;
            case SHUFFLE:
                playingIndex = randomPlayIndex();
                break;
        }
        return songs.get(playingIndex);
    }

    /**
     * @return Whether has next song to play.
     * <p/>
     * If this query satisfies these conditions
     * - comes from media player's complete listener
     * - current play mode is PlayMode.LIST (the only limited play mode)
     * - current song is already in the end of the list
     * then there shouldn't be a next song to play, for this condition, it returns false.
     * <p/>
     * If this query is from user's action, such as from play controls, there should always
     * has a next song to play, for this condition, it returns true.
     */
    public boolean hasNext(boolean fromComplete) {
        if (songs.isEmpty()) return false;
        if (fromComplete) {
            if (playMode == PlayMode.LIST && playingIndex + 1 >= songs.size()) return false;
        }
        return true;
    }

    /**
     * Move the playingIndex forward depends on the play mode
     *
     * @return The next song to play
     */
    public RemoteSong next() {
        switch (playMode) {
            case SINGLE:
            case LIST:
            case LOOP:
                int newIndex = playingIndex + 1;
                if (newIndex >= songs.size()) {
                    newIndex = 0;
                }
                playingIndex = newIndex;
                break;
            case SHUFFLE:
                playingIndex = randomPlayIndex();
                break;
        }
        return songs.get(playingIndex);
    }

    public RemoteSong complete() {
        switch (playMode) {
            case LIST:
            case LOOP:
                int newIndex = playingIndex + 1;
                if (newIndex >= songs.size()) {
                    newIndex = 0;
                }
                playingIndex = newIndex;
                break;
            case SINGLE:
                break;
            case SHUFFLE:
                playingIndex = randomPlayIndex();
                break;
        }
        return songs.get(playingIndex);
    }

    private int randomPlayIndex() {
        int randomIndex = new Random().nextInt(songs.size());
        // Make sure not play the same song twice if there are at least 2 songs
        if (songs.size() > 1 && randomIndex == playingIndex) {
            randomPlayIndex();
        }
        return randomIndex;
    }


    @Override
    public String toString() {
        return "RemotePlayList{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", songs=" + songs +
                ", playingIndex=" + playingIndex +
                ", isMainList=" + isMainList +
                ", playMode=" + playMode +
                '}';
    }
}
