package com.ramy.whytespyderproject.repository;

import com.ramy.whytespyderproject.entity.StoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StoreRepo extends JpaRepository<StoreEntity,Integer> {

   StoreEntity findByStore(int store);
   List<StoreEntity> findAllByState(String state);

}