package com.poscodx.odc.excan013.store;

import com.poscdx.odc.excan013.domain.entity.ExcanAccessToken;
import com.poscdx.odc.excan013.domain.store.ExcanAccessTokenStore;
import com.poscodx.odc.excan013.store.jpo.ExcanAccessTokenJpo;
import com.poscodx.odc.excan013.store.repository.ExcanAccessTokenRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ExcanAccessTokenJpaStore
    implements ExcanAccessTokenStore
{
    private final ExcanAccessTokenRepository repository;

    public ExcanAccessTokenJpaStore(ExcanAccessTokenRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ExcanAccessToken> findByToken(String token) {

        return this.repository.findByToken(token).stream().map(ExcanAccessTokenJpo::toDomain).collect(Collectors.toList());
    }

    @Override
    public ExcanAccessToken register(ExcanAccessToken entity) {
        ExcanAccessTokenJpo excanAccessTokenJpo = new ExcanAccessTokenJpo(entity);
        ExcanAccessTokenJpo savedJpo = this.repository.save(excanAccessTokenJpo);
        return savedJpo.toDomain();
    }
}
