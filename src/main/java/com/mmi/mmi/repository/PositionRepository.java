package com.mmi.mmi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.mmi.mmi.model.Position;

public interface PositionRepository extends PagingAndSortingRepository<Position, Integer> {
	Position findByCode(String code);
	Page<Position> findByIsDelete(Pageable pageable,int status);
}
