package com.wordpress.carledwinti.newcarshop.batchapi.repository;

import org.springframework.batch.item.database.orm.JpaQueryProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wordpress.carledwinti.newcarshop.batchapi.model.Carro;

@Repository
public interface CarroRepository extends JpaRepository<Carro,Long> {

}
