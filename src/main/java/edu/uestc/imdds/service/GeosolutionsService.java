package edu.uestc.imdds.service;

import edu.uestc.imdds.entitiy.RsSubImage;

public interface GeosolutionsService {

    boolean publishGeoTIFF(RsSubImage rsSubImage);

    boolean createWorkspace(String workSpaceName);

    boolean setStyle(String workspace, String coverageName, String style);
}
