package kr.co.broadwave.homeservice.exitGo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExitGoService {

    private final ExitGoRepository exitGoRepository;

    @Autowired
    public ExitGoService(ExitGoRepository exitGoRepository) {
        this.exitGoRepository = exitGoRepository;
    }

    public Optional<ExitGo> findByIdLeave(String id) {
        return exitGoRepository.findByIdLeave(id);
    }

    public ExitGo save(ExitGo exitGo) {
        return exitGoRepository.save(exitGo);
    }

}
