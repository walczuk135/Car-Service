package pl.ogloszenia.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.ogloszenia.jpa.*;
import pl.ogloszenia.repository.*;

import java.util.List;

@Service
@Transactional
public class OffersService {
    OfferRepository offerRepository;
    CarModelRepository carModelRepository;
    CarManufacturerRepository carManufacturerRepository;
    BodyStyleRepository bodyStyleRepository;
    FuelTypeRepository fuelTypeRepository;

    public OffersService(OfferRepository offerRepository, CarModelRepository carModelRepository, CarManufacturerRepository carManufacturerRepository, BodyStyleRepository bodyStyleRepository, FuelTypeRepository fuelTypeRepository) {
        this.offerRepository = offerRepository;
        this.carModelRepository = carModelRepository;
        this.carManufacturerRepository = carManufacturerRepository;
        this.bodyStyleRepository = bodyStyleRepository;
        this.fuelTypeRepository = fuelTypeRepository;
    }

    public CarManufacturer getCarManufacturer(int id) {
        return carManufacturerRepository.findOfferById(id);
    }

    public Offer getOffer(int id) {
        return offerRepository.findOfferById(id);
    }

    public CarModel getModel(int id) {
        return carModelRepository.findOfferById(id);
    }

    public List<CarManufacturer> getCarManufacturers() {
        return carManufacturerRepository.findAll();
    }

    public List<BodyStyle> getBodyStyles() {
        return bodyStyleRepository.findAll();
    }

    public List<FuelType> getFuelTypes() {
        return fuelTypeRepository.findAll();
    }

    public List<CarModel> getCarModels() {
        return carModelRepository.findAll();
    }

    public List<CarModel> getCarModels(int manufacturerId) {
        return carModelRepository.findByManufacturerId(manufacturerId);
    }

    public List<Offer> getOffers() {
        return offerRepository.findAll();
    }

    public List<Offer> getOffersByModel(int modelId) {
        return offerRepository.findOfferByModelId(modelId);
    }


    public List<Offer> getOffersByManufacturer(int manufacturerId) {
        return offerRepository.findOffersByManufacturerId(manufacturerId);
    }
    public Page<Offer> page(Pageable pageable){
        return offerRepository.findAll(pageable);
    }

    public Offer createOffer(Offer offer) {
        offerRepository.save(offer);
        return offer;
    }

    public Offer deleteOffer(Integer id) {
        Offer offer = offerRepository.findOfferById(id);
        offerRepository.delete(offer);
        return offer;
    }

    public Offer saveOffer(Offer offer) {

        return offerRepository.save(offer);
    }

}
