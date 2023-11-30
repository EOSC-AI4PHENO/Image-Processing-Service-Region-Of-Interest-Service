package com.siseth.roi.repository;

import com.siseth.roi.entity.Roi2Source;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface Roi2SourceRepository extends JpaRepository<Roi2Source, Long> {

    public List<Roi2Source> findAllBySourceIdAndRealm(Long sourceId, String realm);

    public List<Roi2Source> findAllBySourceIdAndUserIdAndRealm(Long sourceId, String userId,  String realm);
    List<Roi2Source> findAllBySourceIdAndUserIdAndRealmAndType(Long sourceId, String userId, String realm, Roi2Source.RoiType type);

    List<Roi2Source> findAllBySourceIdAndUserIdAndRealmAndTypeOrderByCreatedAt(Long sourceId, String userId, String realm, Roi2Source.RoiType type);
    @Query("SELECT r2s " +
            "FROM Roi2Source r2s " +
            "WHERE r2s.sourceId = :sourceId AND " +
            "      (r2s.dateFrom IS NULL OR r2s.dateFrom <= CAST(:dateTime as LocalDate) ) AND " +
            "      (r2s.dateTo IS NULL OR r2s.dateTo >= CAST(:dateTime as LocalDate) ) AND " +
            "       r2s.realm = :realm AND " +
            "       r2s.userId = :userId AND " +
            "       r2s.type = :type " +
            "ORDER BY r2s.createdAt DESC ")
    List<Roi2Source> getRoiToSources(@Param("sourceId") Long sourceId,
                                     @Param("dateTime")LocalDateTime dateTime,
                                     @Param("userId") String userId,
                                     @Param("type")  Roi2Source.RoiType type,
                                     @Param("realm")String realm);

}
