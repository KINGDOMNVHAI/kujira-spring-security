package com.codewithproject.springsecurity.logic;

import com.codewithproject.springsecurity.config.Constants;
import com.codewithproject.springsecurity.dto.entitydto.VideoDto;
import com.codewithproject.springsecurity.dto.logic.ChannelVideoLogicStore;
import com.codewithproject.springsecurity.entities.Video;
import com.codewithproject.springsecurity.repository.VideoRepository;
import com.codewithproject.springsecurity.store.VideoStore;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CompoundSelection;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class VideoLogic {

    @Autowired
    private VideoStore store;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private VideoRepository videoRepo;

    public long getLineCount() {
        return videoRepo.count();
    }

//    public List<Line> getListLine() {
//        return lineRepo.getListBrand();
//    }

//    public Optional<Line> getBrandByCode(String brandCD) {
//        return lineRepo.get(brandCD);
//    }

    public VideoDto save(VideoDto req) {
        Video jpoToSave = new Video();
        jpoToSave = req.convertToEntity(Constants.LANG_VI);
        Video savedJpo = videoRepo.save(jpoToSave);
        return savedJpo.toDomain();
    }

    public TypedQuery<VideoDto> retrieveLineFindVideo(ChannelVideoLogicStore req) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<VideoDto> lineEntityQuery = builder.createQuery(VideoDto.class);
        Root<Video> videoRoot = lineEntityQuery.from(Video.class);

        // build all the selected column
        CompoundSelection<VideoDto> compoundSelection = builder.construct(VideoDto.class,
                videoRoot.get("idVideo").alias("idVideo"),
                videoRoot.get("thumbnailVideo").alias("thumbnailVideo"),
                videoRoot.get("idChannel").alias("idChannel")
        );
        lineEntityQuery.select(compoundSelection);
        lineEntityQuery.distinct(true);

        List<Predicate> listPredicates = store.buildPredicateFindVideo(builder, videoRoot, req);
        lineEntityQuery.where(listPredicates.toArray(new Predicate[0]));

        return entityManager.createQuery(lineEntityQuery);
//        queryCustom.setMaxResults(10000);
    }
}
