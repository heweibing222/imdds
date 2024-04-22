package edu.uestc.imdds.mapper;

import edu.uestc.imdds.entitiy.RsSubImage;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RsSubImageMapper {
    RsSubImage getById(Integer id);

    List<RsSubImage> getAll();

    List<RsSubImage> getByFatherId(Integer id);

    int addRsImage(RsSubImage rsSubImage);

    int deleteById(Integer id);

    int updateRsSubImage(RsSubImage rsImage);

}
