package com.example.demo.repo;



import com.example.demo.DTO.Topic.TopicWithMostFollowers;
import com.example.demo.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface TopicRepo extends JpaRepository<Topic, Long> {

    @Query(value = "select t.topic_name from topic_tbl t where t.id = :id", nativeQuery = true)
    String getTopicname(@Param("id") Long id);

    @Transactional
    @Modifying
    @Query("update Topic t set t.isDeleted = true where t.id = :id")
    void softdeletetopic(@Param("id") Long id);

    @Query("select t from Topic t where t.isDeleted = false order by modifyDate desc")
    List<Topic> getAllTopic();


    Optional<Topic> findByIdAndIdeasIsDeletedFalse(Long id);

    @Query("select t from Topic t where t.topic_closure_date between :today and :date")
    List<Topic> getclosureTopic(@Param("date") String date, @Param("today") String today);

    @Query("select t from Topic t where t.final_closure_date between :today and :date ")
    List<Topic> getFinalClosureTopic(@Param("date") String date, @Param("today") String today);


    @Query(nativeQuery = true)
    List<TopicWithMostFollowers> top7followers();

}
