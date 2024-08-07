package com.poscodx.odc.excan013.store.repository;

import com.poscodx.odc.excan013.store.jpo.ExcanAccessTokenJpo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExcanAccessTokenRepository
    extends JpaRepository<ExcanAccessTokenJpo, Integer>
{
    List<ExcanAccessTokenJpo> findByToken(String id);
}
