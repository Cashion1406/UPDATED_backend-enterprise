package com.example.demo.repo;


import com.example.demo.DTO.Idea.IdeasPerCate;
import com.example.demo.DTO.Idea.IdeasPerDepartment;
import com.example.demo.DTO.Topic.IdeaAnalytics;
import com.example.demo.model.Idea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface IdeaRepo extends JpaRepository<Idea, Long> {

    @Query("select i from Idea i where i.id = :id  ")
    Idea getideabyid(@Param("id") long id);

    @Query(value = "select i.cate_id from idea_cate_tbl i where i.idea_id =:id", nativeQuery = true)
    List<Long> getIdeaCate(@Param("id") Long id);

    @Query("select i from Idea i where i.topic.id=:id order by i.modify_date asc")
    List<Idea> getIdeaWithTopic(@Param("id") Long id);

    @Query(value = "select c.cate_name from cate_tbl c inner join idea_cate_tbl ic on ic.cate_id=c.id inner join idea_tbl i on i.id = ic.idea_id where i.id=:id", nativeQuery = true)
    List<String> getCateName(@Param("id") Long id);

    @Query(value = "select t.topic_name from idea_tbl i inner join topic_tbl t on t.id=i.topic_id where i.id=:id", nativeQuery = true)
    String getTopicNameEachIdea(@Param("id") Long id);

    @Query("select i from Idea i where i.client.id = :id  ")
    List<Idea> getideabyclientid(@Param("id") String id);

    @Query(value = "select i.id, i.attached_path, i.idea_body, i.date,i.is_anonymous,i.is_deleted,i.modify_date,i.idea_title,i.client_id,i.topic_id from idea_tbl i inner join reaction_tbl r on r.idea_id = i.id group by i.id order by count(r.idea_id) desc", nativeQuery = true)
    List<Idea> top5Ideas();


    @Query(value = "select i.id,id.attached_path, i.idea_body, i.client_id, i.topic_id, r.id, r.client_id, r.idea_id from idea_tbl i inner join reaction_tbl r on r.client_id = i.client_id where r.reaction = true", nativeQuery = true)
    List<Idea> getupvote();

    @Query("select i.name from Idea i where i.id=:id")
    String getideaname(@Param("id") Long id);


    @Query(value = "select i.id,id.attached_path, i.idea_body, i.client_id, i.topic_id, r.id, r.client_id, r.idea_id from idea_tbl i inner join reaction_tbl r on r.client_id = i.client_id where r.reaction = false", nativeQuery = true)
    List<Idea> getdownvote();


    @Transactional
    @Modifying
    @Query(value = "insert into idea_cate_tbl (cate_id,idea_id) values (:cate_id,:idea_id)", nativeQuery = true)
    void insertideacatev2(@Param("cate_id") long cate_id, @Param("idea_id") long idea_id);


    @Transactional
    @Modifying
    @Query(value = "delete from idea_cate_tbl i where i.cate_id = :cate_id and i.idea_id = :idea_id ", nativeQuery = true)
    void deleteIdeaCate(@Param("cate_id") Long cate_id, @Param("idea_id") Long idea_id);

    @Query(value = "insert into comment_tbl (comment, client_id, idea_id) values (:cate_id,:idea_id)", nativeQuery = true)
    void insertcomment(@Param("cate_id") long cate_id, @Param("idea_id") long idea_id);


    @Query(nativeQuery = true)
    List<IdeasPerDepartment> ideasPerDepartment();

    @Query(nativeQuery = true)
    List<IdeasPerCate> top7ideas();

    @Query("select i from Idea i where i.isDeleted = false order by modify_date desc ")
    List<Idea> getIdeas();

    @Transactional
    @Modifying
    @Query("update Idea i set i.isDeleted = true where i.id = :id")
    void softdeleteidea(@Param("id") Long id);

    @Query(nativeQuery = true)
    List<IdeaAnalytics> ideasAnalytics();
}
