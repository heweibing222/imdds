package edu.uestc.imdds.service;

public interface GeosolutionsService {

    boolean publishGeoTIFF(Integer id);

    boolean createWorkspace(String workSpaceName);

    boolean setStyle(String workspace, String coverageName, String style);
}
