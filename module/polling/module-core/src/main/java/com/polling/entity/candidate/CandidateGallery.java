package com.polling.entity.candidate;

import com.polling.entity.common.BaseTimeEntity;
import com.querydsl.core.annotations.QueryEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@QueryEntity
public class CandidateGallery extends BaseTimeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "image_id")
    private Long id;

    String imagePath;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;

    public CandidateGallery(String imagePath) {
        this.imagePath = imagePath;
    }

    public void changeCandidate(Candidate candidate) {
        this.candidate = candidate;
    }
}
