package kr.co.broadwave.homeservice.lock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LockService {

    private final LockRepository lockRepository;

    @Autowired
    public LockService(LockRepository lockRepository) {
        this.lockRepository = lockRepository;
    }

    public Optional<Lock> findById(String id) {
        return lockRepository.findById(id);
    }

    public Lock save(Lock lock) {
        return lockRepository.save(lock);
    }

}
