package com.hg1.dsHydrocabure.services.params;


import com.hg1.dsHydrocabure.AppConstants;
import com.hg1.dsHydrocabure.common.tools.FilesUtils;
import com.hg1.dsHydrocabure.common.tools.OsUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class InitParamsService {

    public InitParamsService() {
    }

    public void create() throws Exception {
        String path = "";

        path = OsUtils.getOsHomeDir();
        if (!FilesUtils.Exist(path)) {
            FilesUtils.Mkdir(path);
        }

        if (FilesUtils.Exist(path)) {
            if (!FilesUtils.Exist(path + "/" + AppConstants.DIR_TEMP)) {
                FilesUtils.Mkdir(path + "/" + AppConstants.DIR_TEMP);
            }
            if (!FilesUtils.Exist(path + "/" + AppConstants.DIR_LOG)) {
                FilesUtils.Mkdir(path + "/" + AppConstants.DIR_LOG);
            }
        }
    }


}
