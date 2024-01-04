package com.example.breno.appbus.Appbus.repository;

import com.example.breno.appbus.Appbus.dto.AppbusRecords;
import com.example.breno.appbus.Appbus.model.AppbusModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AppbusRepository extends JpaRepository <AppbusModel, UUID>  {

}
