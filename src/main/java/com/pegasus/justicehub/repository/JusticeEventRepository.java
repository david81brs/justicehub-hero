package com.pegasus.justicehub.repository;

import com.pegasus.justicehub.models.JusticeEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JusticeEventRepository extends JpaRepository<JusticeEvent, Long> {

}
