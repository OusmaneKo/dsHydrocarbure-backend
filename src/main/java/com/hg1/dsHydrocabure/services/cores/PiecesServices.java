package com.hg1.dsHydrocabure.services.cores;


import com.hg1.dsHydrocabure.common.wrappers.ResponseWrapper;
import com.hg1.dsHydrocabure.models.cores.Pieces;
import com.hg1.dsHydrocabure.repositories.cores.PiecesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class PiecesServices {
    private final PiecesRepository piecesRepository;

    public PiecesServices(PiecesRepository piecesRepository) {
        this.piecesRepository = piecesRepository;
    }

    public ResponseWrapper<Pieces> create(Pieces pc) {
        try {
            piecesRepository.saveAndFlush(pc);
            return ResponseWrapper.ok(pc);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseWrapper.ko(e.getMessage());
        }
    }

    private ResponseWrapper<Pieces> updateOld(Pieces oldPiece, Pieces pc) {
        try {
            piecesRepository.saveAndFlush(pc);
            return ResponseWrapper.ok(pc);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseWrapper.ko(e.getMessage());
        }
    }

    public ResponseWrapper<Pieces> update(Long id, Pieces pc) {
        try {
            return piecesRepository
                    .findById(id)
                    .map(oldPiece -> updateOld(oldPiece, pc))
                    .orElseGet(() -> ResponseWrapper.ko("Mise a jour impossible"));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseWrapper.ko(e.getMessage());
        }
    }

    private Optional<Pieces> findById(Long id) {
        try {
            Optional<Pieces> opti = Optional.ofNullable(id).flatMap(piecesRepository::findById);
            return opti;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Optional.empty();
        }
    }

    public Pieces getPiece(Long id) {
        try {
            return findById(id).orElse(null);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    public List<Pieces> liste() {
        try {
            return piecesRepository.findAll();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    public ResponseWrapper delete(Long id) {
        try {
            Optional<Pieces> opt = piecesRepository.findById(id);
            if (!opt.isPresent()) {
                return ResponseWrapper.ok("suppression reussie");
            }
            Pieces p = opt.get();
            piecesRepository.delete(p);
            return ResponseWrapper.ok(p);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseWrapper.ko(e.getMessage());
        }
    }

}
