package com.courses.hexagonalapi.internship.infrastructure.postgreadapter.entity;

import com.courses.hexagonalapi.internship.domain.defenses.DefenseStatus;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "defense")
public class DefenseEntity {

    @Id
    @Column(columnDefinition = "uuid", updatable = false, unique = true)
    public UUID defenseId;

    private LocalDate preferredDate;

    private String preferredTime;

    private String presentationTitle;

    @Column(columnDefinition = "TEXT")
    private String comments;

    @Enumerated(EnumType.STRING)
    private DefenseStatus status;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "internship_id", nullable = false, unique = true)
    private InternshipEntity internship;

    public DefenseEntity() {}

    public DefenseEntity(UUID defenseId, LocalDate preferredDate, String preferredTime,
                         String presentationTitle, String comments, DefenseStatus status,
                         InternshipEntity internship) {
        this.defenseId = defenseId;
        this.preferredDate = preferredDate;
        this.preferredTime = preferredTime;
        this.presentationTitle = presentationTitle;
        this.comments = comments;
        this.status = status;
        this.internship = internship;
    }

    public UUID getDefenseId() {
        return defenseId;
    }

    public void setDefenseId(UUID defenseId) {
        this.defenseId = defenseId;
    }

    public LocalDate getPreferredDate() {
        return preferredDate;
    }

    public void setPreferredDate(LocalDate preferredDate) {
        this.preferredDate = preferredDate;
    }

    public String getPreferredTime() {
        return preferredTime;
    }

    public void setPreferredTime(String preferredTime) {
        this.preferredTime = preferredTime;
    }

    public String getPresentationTitle() {
        return presentationTitle;
    }

    public void setPresentationTitle(String presentationTitle) {
        this.presentationTitle = presentationTitle;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public DefenseStatus getStatus() {
        return status;
    }

    public void setStatus(DefenseStatus status) {
        this.status = status;
    }

    public InternshipEntity getInternship() {
        return internship;
    }

    public void setInternship(InternshipEntity internship) {
        this.internship = internship;
    }
}
