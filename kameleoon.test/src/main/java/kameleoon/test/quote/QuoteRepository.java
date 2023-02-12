package kameleoon.test.quote;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface QuoteRepository extends JpaRepository<Quote, Long> {

    @Modifying
    @Query("select * from Quote r set r.status = ?1 where r.event = ?2 and r.status = ?3")
    @Transactional
    List<Quote> findTopBy(Integer top);
}
