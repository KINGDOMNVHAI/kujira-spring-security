package com.codewithproject.springsecurity.repository;

import com.codewithproject.springsecurity.entities.Question;
import com.codewithproject.springsecurity.entities.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Modifying
    @Transactional
    @Query(value = "TRUNCATE TABLE questions", nativeQuery = true)
    void truncateTable();

    @Query(value = "SELECT q.* " +
            " FROM questions q " +
            " WHERE q.id_question = :idQuestion", nativeQuery = true)
    Question getQuestionById(Integer idQuestion);

    @Query(value = "SELECT q.* " +
            " FROM questions q " +
            " LEFT JOIN test_question tq ON tq.id_question = q.id_question " +
            " WHERE tq.id_test = :idTest", nativeQuery = true)
    List<Question> getQuestionByIdTest(Integer idTest);
}
