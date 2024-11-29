package com.example.repository;

import com.example.entity.Setting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SettingRepository extends JpaRepository<Setting, Long>
{
    List<Setting> findByKeyVal(String key);
}
