package com.flyex.mapper;

import com.flyex.pojo.Bike;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BikeMapper {
    public void save(Bike bike);
}
