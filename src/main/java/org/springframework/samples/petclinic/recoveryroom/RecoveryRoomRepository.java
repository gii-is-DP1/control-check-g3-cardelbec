package org.springframework.samples.petclinic.recoveryroom;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface RecoveryRoomRepository extends CrudRepository<RecoveryRoom, Integer>{
	@Query("SELECT r FROM RecoveryRoom r")
    List<RecoveryRoom> findAll();
    @Query("SELECT t FROM RecoveryRoomType t")
    List<RecoveryRoomType> findAllRecoveryRoomTypes();
    @Query("SELECT t FROM RecoveryRoomType t WHERE t.name=:name")
    RecoveryRoomType getRecoveryRoomType(String name);
    @Query("SELECT r FROM RecoveryRoom r WHERE r.size>:size")
    List<RecoveryRoom> findBySizeMoreThan(double size);
}
