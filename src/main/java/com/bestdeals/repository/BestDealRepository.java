package com.bestdeals.repository;

import com.bestdeals.domain.Deal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by arif on 18/06/2017.
 */
@Repository
public interface BestDealRepository extends JpaRepository<Deal, Integer>{

}
