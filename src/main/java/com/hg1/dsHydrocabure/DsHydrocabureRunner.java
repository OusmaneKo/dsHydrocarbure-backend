package com.hg1.dsHydrocabure;

import com.hg1.dsHydrocabure.services.params.InitParamsService;
import com.hg1.dsHydrocabure.services.params.InitRolesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DsHydrocabureRunner implements CommandLineRunner {

    @Autowired
    private InitRolesService initRolesService;
    @Autowired
    private InitParamsService initParamsService;

    @Override
    public void run(String... args) throws Exception {
        this.initRolesService.run();
        initParamsService.create();
        log.info("Application started");
    }

}
