package kr.co.broadwave.homeservice.homedata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HomeDataService {

    private final HomedataRepository homedataRepository;

    @Autowired
    public HomeDataService(HomedataRepository homedataRepository) {
        this.homedataRepository = homedataRepository;
    }

    public Optional<HomeData> findByIdData(String s_type) {
        return homedataRepository.findByIdData(s_type);
    }

}
