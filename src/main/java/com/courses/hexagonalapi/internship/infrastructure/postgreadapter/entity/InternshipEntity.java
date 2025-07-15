package com.courses.hexagonalapi.internship.infrastructure.postgreadapter.entity;

import com.courses.hexagonalapi.internship.domain.internships.InternshipStatus;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "internship")
public class InternshipEntity {

    @Id
    @Column(columnDefinition = "uuid", unique = true, updatable = false)
    private UUID internshipId;

    private String title;

    private LocalDate startDate;

    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    private InternshipStatus status;

    @OneToOne(mappedBy = "internship", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private DefenseEntity defense;

    public InternshipEntity(UUID internshipId, String title, LocalDate startDate, LocalDate endDate, InternshipStatus status) {
        this.internshipId = internshipId;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    public InternshipEntity() {
    }

    public UUID getInternshipId() {
        return internshipId;
    }

    public void setInternshipId(UUID internshipId) {
        this.internshipId = internshipId;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public InternshipStatus getStatus() {
        return status;
    }

    public DefenseEntity getDefense() {
        return defense;
    }
}
