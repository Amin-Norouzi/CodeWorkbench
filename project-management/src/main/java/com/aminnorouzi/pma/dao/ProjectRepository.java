package com.aminnorouzi.pma.dao;

import com.aminnorouzi.pma.dto.ChartData;
import com.aminnorouzi.pma.dto.TimeChartData;
import com.aminnorouzi.pma.entities.Project;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProjectRepository extends CrudRepository<Project, Long> {

    @Override
    List<Project> findAll();

    @Query(nativeQuery = true, value = "SELECT stage as label, COUNT(*) as value " +
            "FROM project " +
            "GROUP BY stage")
    List<ChartData> getProjectStatus();

    @Query(nativeQuery = true, value = "SELECT name as projectName, start_date as startDate, end_date as endDate"
            + " FROM project WHERE start_date is not null")
    List<TimeChartData> getTimeData();
}
