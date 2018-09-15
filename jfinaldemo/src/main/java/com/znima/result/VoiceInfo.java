/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.znima.result;

/**
 *
 * @author Administrator
 */
public class VoiceInfo {

    private String artist;
    private String avatarURL;
    private String musicAlbum;
    private String musicName;
    private String musicTime;
    private String musicURL;

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAvatarURL() {
        return avatarURL;
    }

    public void setAvatarURL(String avatarURL) {
        this.avatarURL = avatarURL;
    }

    public String getMusicAlbum() {
        return musicAlbum;
    }

    public void setMusicAlbum(String musicAlbum) {
        this.musicAlbum = musicAlbum;
    }

    public String getMusicName() {
        return musicName;
    }

    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }

    public String getMusicTime() {
        return musicTime;
    }

    public void setMusicTime(String musicTime) {
        this.musicTime = musicTime;
    }

    public String getMusicURL() {
        return musicURL;
    }

    public void setMusicURL(String musicURL) {
        this.musicURL = musicURL;
    }

    @Override
    public String toString() {
        return "VoiceInfo{" + "artist=" + artist + ", avatarURL=" + avatarURL + ", musicAlbum=" + musicAlbum + ", musicName=" + musicName + ", musicTime=" + musicTime + ", musicURL=" + musicURL + '}';
    }

}
