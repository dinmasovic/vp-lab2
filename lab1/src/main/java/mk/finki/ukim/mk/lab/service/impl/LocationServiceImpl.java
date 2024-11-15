package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.Location;
import mk.finki.ukim.mk.lab.repository.LocationRepository;
import mk.finki.ukim.mk.lab.service.LocationService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class LocationServiceImpl implements LocationService {
    @Override
    public List<Location> findAll() {
        LocationRepository obj=new LocationRepository();
        return obj.findAll();
    }

    @Override
    public Location findByID(Long id) {
        LocationRepository obj=new LocationRepository();
        return obj.findByID(id);
    }

}
