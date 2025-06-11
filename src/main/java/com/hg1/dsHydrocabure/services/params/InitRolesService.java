package com.hg1.dsHydrocabure.services.params;


import com.hg1.dsHydrocabure.common.services.CommonService;
import com.hg1.dsHydrocabure.models.params.*;
import com.hg1.dsHydrocabure.models.pojos.Personnes;
import com.hg1.dsHydrocabure.repositories.params.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
@Slf4j
public class InitRolesService {

    private final CommonService commonService;
    private final ModulesRepository modulesRepository;
    private final FonctionsRepository fonctionsRepository;
    private final UsersRepository usersRepository;
    private final UsersGroupsRepository usersGroupsRepository;
    private final ConfigsRepository configsRepository;

    public InitRolesService(CommonService commonService, ModulesRepository modulesRepository, FonctionsRepository fonctionsRepository, UsersRepository usersRepository, UsersGroupsRepository usersGroupsRepository, ConfigsRepository configsRepository) {
        this.commonService = commonService;
        this.modulesRepository = modulesRepository;
        this.fonctionsRepository = fonctionsRepository;
        this.usersRepository = usersRepository;
        this.usersGroupsRepository = usersGroupsRepository;
        this.configsRepository = configsRepository;
    }

    public void run() {
        this.createDossier();
        this.createUser();
        this.createModule();
        this.createFonction();
    }

    public void createDossier() {
        Configs cf = configsRepository.findById("00").orElse(null);
        if (cf == null) {
            this.configsRepository.saveAndFlush(Configs.builder().code("00")
                    .raisonSocial("SIH").build());
        }

    }

    public void createUser() {
        UsersGroups groupeUser = usersGroupsRepository.findById(1L).orElse(null);
        if (groupeUser == null) {
            groupeUser = UsersGroups.builder()
                    .nom("Administrator")
                    .nature("A")
                    .type("S")
                    .build();
            groupeUser = usersGroupsRepository.saveAndFlush(groupeUser);
        }


        Users user = usersRepository.findByLogin("admin").orElse(null);
        if (user == null) {
            user = Users.builder()
                    .login("admin")
                    .pwd("p@$$w0rd".toLowerCase())
                    .personnes(Personnes.builder().nom("Admin").prenom("").build())
                    .groupeId(groupeUser.getId())
                    .build();

            usersRepository.saveAndFlush(user);
        }

    }

    private void createModule() {
        String[] tabLignes = this.commonService.getFromInputStream("static/modules");

        Stream.of(tabLignes).forEach(l -> {
            String ligne = l.toString().replaceAll("\\r\\n|\\r|\\n", "").trim();
            String[] tab = ligne.split("#");

            if (tab.length < 3) {
                log.warn("Ligne de module invalide ignorée : '{}'", ligne);
                return;
            }

            try {
                Modules m = Modules.builder()
                        .code(tab[0])
                        .libelle(tab[1])
                        .ordre(Integer.parseInt(tab[2]))
                        .etat(true)
                        .build();
                this.modulesRepository.saveAndFlush(m);
            } catch (NumberFormatException e) {
                log.error("Erreur de format de nombre dans la ligne de module : '{}'", ligne, e);
            }
        });
    }

    public void createFonction() {
        String[] tlignes = this.commonService.getFromInputStream("static/fonctions");
        Stream.of(tlignes).forEach(s -> {
            String ligne = s.toString().replaceAll("\\r\\n|\\r|\\n", "").trim();
            String[] t = ligne.split("##");

            if (t.length < 4) {
                log.warn("Ligne de fonction invalide ignorée : '{}'", ligne);
                return;
            }

            try {
                Fonctions f = Fonctions.builder()
                        .Code(t[0])
                        .libelle(t[1])
                        .module(t[2])
                        .ordre(Integer.parseInt(t[3]))
                        .etat(true)
                        .build();
                this.fonctionsRepository.saveAndFlush(f);
            } catch (NumberFormatException e) {
                log.error("Format de nombre invalide dans la ligne de fonction : '{}'", ligne, e);
            }
        });
    }
    }
