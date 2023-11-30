package com.siseth.roi.entity;

import com.siseth.roi.component.entity.BaseEntity;
import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
@AllArgsConstructor
@Setter
@Builder
@Entity
@Table(schema = "public", name = "`Roi2Source`")
@Where(clause = "`isActive`")
public class Roi2Source extends BaseEntity {

    @Column(name="`desc`")
    private String desc;

    @Column(name="`sourceId`")
    private Long sourceId;

    @Column(name="`fileId`")
    private Long fileId;

    @Column(name="`dateFrom`")
    private LocalDate dateFrom;

    @Column(name="`dateTo`")
    private LocalDate dateTo;

    @Column(name="`isActive`")
    private Boolean isActive;

    @Column(name="`userId`")
    private String userId;

    @Column(name="`realm`")
    private String realm;

    @Column(name="`type`")
    @Enumerated(EnumType.STRING)
    private RoiType type;

    public enum RoiType {
        IMAGE, //roi do zdjęcia

        IMAGE_ANALYSIS, //roi do analizy
        RESULT // roi wynikowe (np z analizy)
    }

    public Roi2Source(Long sourceId,
                      LocalDate from,
                      LocalDate to,
                      RoiType type,
                      String userId,
                      String realm) {
        this.sourceId = sourceId;
        this.dateFrom = from;
        this.dateTo = to;
        this.userId = userId;
        this.realm = realm;
        this.type = type;
        this.isActive = true;
    }

    public Roi2Source addFile(Long fileId) {
        this.fileId = fileId;
        valid();
        return this;
    }

    public void delete() {
        this.isActive = false;
    }

    private void valid() {
        if(this.realm == null || this.sourceId == null )
            throw new RuntimeException("Roi2Source not valid!");
    }
}
