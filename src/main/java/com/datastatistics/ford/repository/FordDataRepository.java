package com.datastatistics.ford.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import com.datastatistics.ford.entity.FordStockData;

/**
 * Repository class for fetching data from database
 * @author Monika Tantia
 *
 */
@Component
public interface FordDataRepository extends JpaRepository<FordStockData, Long> {

	List<FordStockData> findOpenCloseDateByOrderByDateAsc();
	
	
	List<FordStockData> findByYear(Integer year);

	List<FordStockData> findByYearAndMonth(Integer year, Integer month);

	List<FordStockData> findByYearAndMonthAndDay(Integer year, Integer month, Integer day);
}
