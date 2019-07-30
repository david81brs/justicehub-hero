package com.pegasus.justicehub.repository;

import com.pegasus.justicehub.models.JusticeEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface JusticeEventRepository extends JpaRepository<JusticeEvent, Long> {
    JusticeEvent findById(long id);

    @Query(value = "SELECT SUM(je.people_attended) FROM justice_events je", nativeQuery = true)
    int getSumPeopleAttended();
}
