package com.polling.core.entity.candidate;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@NoArgsConstructor
@Embeddable
public class CandidateInfo {
    @Column
    private String profilePath;

    @Column(name = "media_path_1")
    private String mediaPath1;

    @Column(name = "media_path_2")
    private String mediaPath2;

    @Column(name = "media_path_3")
    private String mediaPath3;

    public void setProfilePath(String profilePath){
        this.profilePath = profilePath;
    }

    public void setMediaPath1(String mediaPath1){
        this.mediaPath1 = mediaPath1;
    }

    public void setMediaPath2(String mediaPath2){
        this.mediaPath2 = mediaPath2;
    }

    public void setMediaPath3(String mediaPath3){
        this.mediaPath3 = mediaPath3;
    }
}
