package com.fr.sinapps.controller;

import com.fr.sinapps.model.CarPark;
import com.fr.sinapps.repository.CarParkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Math.abs;

@RestController
@RequestMapping(path = "/carParks")
public class CarParkController {

    @Autowired
    private CarParkRepository carParkRepository;

    @GetMapping(path = "/", produces = "application/json")
    public List<CarPark> carPark() {
        return carParkRepository.findAll();
    }

    // The front will need to deal with the response = null
    @GetMapping(path = "/carPark/{id}", produces = "application/json")
    public CarPark getOneCarParkWithId(@PathVariable long id) {
        return carParkRepository.findById(id).orElse(null);
    }

    @GetMapping(path = "/carParkWithName/{name}", produces = "application/json")
    public List<CarPark> getCarParksWithName(@PathVariable String name) {
        return carParkRepository.findAll().stream().filter(cp -> cp.getName().equals(name)).collect(Collectors.toList());
    }

    @GetMapping(path = "/fiveClosestParks/{lat},{lon}", produces = "application/json")
    public List<CarPark> getFiveCarParksClosestToPosition(@PathVariable double lat, @PathVariable double lon) {

        List<CarPark> carParks = carParkRepository.findAll();

        carParks.sort((cp1, cp2) -> {
            double distFirstCarPark = abs(cp1.getPosLat() - lat) + abs(cp1.getPosLon() - lon);
            double distSecondCarpark = abs(cp2.getPosLat() - lat) + abs(cp2.getPosLon() - lon);

            return Double.compare(distFirstCarPark, distSecondCarpark);
        });

        List<CarPark> result = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            result.add(carParks.get(i));
        }

        return result;
    }

    @GetMapping(path = "/fiveOpenAndClosestParks/{lat},{lon}", produces = "application/json")
    public List<CarPark> getFiveOpenCarParksClosestToPosition(@PathVariable double lat, @PathVariable double lon) {

        List<CarPark> carParks = carParkRepository.findAll().stream().filter(
                currentCarPark -> currentCarPark.getStatus().equals("COMPLET") || currentCarPark.getStatus().equals("FERMER")
        ).collect(Collectors.toList());
        List<CarPark> result = new ArrayList<>();

        carParks.sort((cp1, cp2) -> {
            double distFirstCarPark = abs(cp1.getPosLat() - lat) + abs(cp1.getPosLat() - lat);
            double distSecondCarpark = abs(cp2.getPosLat() - lon) + abs(cp2.getPosLon() - lon);
            return Double.compare(distFirstCarPark, distSecondCarpark);
        });

        for (int i = 0; i < 5; i++) {
            result.add(carParks.get(i));
        }

        return result;
    }

    @GetMapping(path = "/carParksWithBikeSpot/", produces = "application/json")
    public List<CarPark> getAllCarParksWithBikeSpot() {

        return carParkRepository.findAll().stream().sorted(Comparator.comparing(CarPark::getBikeSpot).reversed()).filter(
                currentCarPark -> currentCarPark.getBikeSpot() > 0
        ).collect(Collectors.toList());
    }

    @GetMapping(path = "/carParksWithCarpoolingSpot/", produces = "application/json")
    public List<CarPark> getAllCarParksWithCarpoolingSpot() {

        return carParkRepository.findAll().stream().sorted(Comparator.comparing(CarPark::getCarpollingSpot).reversed()).filter(
                currentCarPark -> currentCarPark.getCarpollingSpot() > 0
        ).collect(Collectors.toList());

    }

    @GetMapping(path = "/fiveOpenAndClosestBikeParks/{lat},{lon}", produces = "application/json")
    public List<CarPark> getFiveOpenBikeParksClosestToPosition(@PathVariable double lat, @PathVariable double lon) {

        List<CarPark> carParks = carParkRepository.findAll().stream().filter(
                currentCarPark -> currentCarPark.getStatus().equals("COMPLET") || currentCarPark.getStatus().equals("FERMER") && currentCarPark.getBikeSpot() > 0
        ).collect(Collectors.toList());
        List<CarPark> result = new ArrayList<>();

        carParks.sort((cp1, cp2) -> {
            double distFirstCarPark = abs(cp1.getPosLat() - lat) + abs(cp1.getPosLat() - lat);
            double distSecondCarpark = abs(cp2.getPosLat() - lon) + abs(cp2.getPosLon() - lon);
            return Double.compare(distFirstCarPark, distSecondCarpark);
        });

        for (int i = 0; i < 5; i++) {
            result.add(carParks.get(i));
        }

        return result;
    }

    @GetMapping(path = "/fiveOpenAndClosestCarpoolingParks/{lat},{lon}", produces = "application/json")
    public List<CarPark> getFiveOpenCarpoolingParksClosestToPosition(@PathVariable double lat, @PathVariable double lon) {

        List<CarPark> carParks = carParkRepository.findAll().stream().filter(
                currentCarPark -> currentCarPark.getStatus().equals("COMPLET") || currentCarPark.getStatus().equals("FERMER") && currentCarPark.getCarpollingSpot() > 0
        ).collect(Collectors.toList());
        List<CarPark> result = new ArrayList<>();

        carParks.sort((cp1, cp2) -> {
            double distFirstCarPark = abs(cp1.getPosLat() - lat) + abs(cp1.getPosLat() - lat);
            double distSecondCarpark = abs(cp2.getPosLat() - lon) + abs(cp2.getPosLon() - lon);
            return Double.compare(distFirstCarPark, distSecondCarpark);
        });

        for (int i = 0; i < 5; i++) {
            result.add(carParks.get(i));
        }

        return result;
    }

    // We can't replace id or ident don't seem to be appropriate
    @PutMapping(path = "/carPark/")
    public CarPark addOrUpdateCarPark(@RequestBody CarPark carPark) {
        return carParkRepository.findById(carPark.getId()).map(currentCarPark -> {
            currentCarPark.updateCarPark(carPark);
            return carParkRepository.save(currentCarPark);
        }).orElseGet(() -> carParkRepository.save(carPark));
    }

    @DeleteMapping(path = "/carPark/{id}")
    String deleteCarPark(@PathVariable long id) {
        carParkRepository.deleteById(id);
        return carParkRepository.existsById(id) ? "Error on delete" : "Object with id " + id + " has been deleted";
    }

}
