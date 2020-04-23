package pl.offer.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.offer.jpa.*;
import pl.offer.repository.*;

import java.util.List;

@Service
public class OffersService {
    private final OfferRepository offerRepository;
    private final CarModelRepository carModelRepository;
    private final CarManufacturerRepository carManufacturerRepository;
    private final BodyStyleRepository bodyStyleRepository;
    private final FuelTypeRepository fuelTypeRepository;

    public OffersService(OfferRepository offerRepository, CarModelRepository carModelRepository, CarManufacturerRepository carManufacturerRepository, BodyStyleRepository bodyStyleRepository, FuelTypeRepository fuelTypeRepository) {
        this.offerRepository = offerRepository;
        this.carModelRepository = carModelRepository;
        this.carManufacturerRepository = carManufacturerRepository;
        this.bodyStyleRepository = bodyStyleRepository;
        this.fuelTypeRepository = fuelTypeRepository;
    }

    public CarManufacturer getCarManufacturer(int id) {
        return carManufacturerRepository.findOfferById(id).orElseThrow(IllegalStateException::new);
    }

    public List<Offer> getAllOffer() {
        return offerRepository.findAll();
    }

    public Offer getOffer(int id) {
        return offerRepository.findOfferById(id).orElseThrow(IllegalStateException::new);
    }

    public CarModel getModel(int id) {
        return carModelRepository.findOfferById(id).orElseThrow(IllegalStateException::new);
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

    public List<Offer> getOffersByModel(int modelId) {
        return offerRepository.findOfferByModelId(modelId);
    }


    public List<Offer> getOffersByManufacturer(int manufacturerId) {
        return offerRepository.findOffersByManufacturerId(manufacturerId);
    }

    @Transactional
    public Offer createOffer(Offer offer) {
        return offerRepository.save(offer);
    }

    @Transactional
    public Offer deleteOffer(Integer id) {
        Offer offer = offerRepository.findOfferById(id).orElseThrow(() -> new IllegalStateException("There is no such advertisement\n"));
        offerRepository.delete(offer);
        return offer;
    }

    @Transactional
    public Offer saveOffer(Offer offer) {
        return offerRepository.save(offer);
    }

}
