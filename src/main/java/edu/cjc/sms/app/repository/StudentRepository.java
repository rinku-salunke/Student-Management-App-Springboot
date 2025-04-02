package edu.cjc.sms.app.repository;
import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import edu.cjc.sms.app.model.*;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer>
{

public List<Student> findAllByBatchNumber(String batchNumber);


}
