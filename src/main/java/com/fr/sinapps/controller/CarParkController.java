package com.fr.sinapps.controller;

import com.fr.sinapps.dao.CarParkDao;
import com.fr.sinapps.model.CarPark;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/carParks")
public class CarParkController {

    @Autowired
    private CarParkDao carParkDao;

    @GetMapping(value = "/", produces = "application/json")
    public List<CarPark> carPark() {
        return carParkDao.getAllCarParks();
    }

}
