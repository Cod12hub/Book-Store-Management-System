package com.mains.repositry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mains.bin.Cart;
@Repository

public interface MyBookRepository extends JpaRepository<Cart,Integer> {

}

