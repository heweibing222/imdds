package edu.uestc.imdds.mapper;

import edu.uestc.imdds.entitiy.RsImage;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RsImageMapper {
    RsImage getById(Integer id);

    RsImage getByName(String Name);

    int addRsImage(RsImage rsImage);

    int deleteById(Integer id);

    int updateRsImage(RsImage rsImage);

    List<RsImage> getAll();
}
