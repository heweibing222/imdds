package edu.uestc.imdds.service;


import edu.uestc.imdds.entitiy.RsSubImage;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public interface RsSubImageService {



    List<RsSubImage> getAll();

    List<RsSubImage> getByFatherId(Integer id);

    RsSubImage getById(Integer id);

    boolean add(RsSubImage subImage);

    boolean deleteByFatherId(Integer id);

    void download(Integer id, HttpServletResponse response) throws IOException;

    boolean publish(Integer id);

}
