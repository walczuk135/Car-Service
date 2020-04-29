package pl.offer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.offer.model.*;
import pl.offer.service.OffersService;

import javax.validation.Valid;
import java.util.List;

@Controller
public class OfferController {


    private final OffersService offersService;

    OfferController(OffersService offersService) {
        this.offersService = offersService;
    }

    @GetMapping("/")
    public String home(Model model, OfferFilter offerFilter) {
        List<CarManufacturer> carManufacturers = offersService.getCarManufacturers();
        List<CarModel> carModels = offersService.getCarModels();
        List<Offer> offers =offersService.getAllOffer();
        //Page<Offer> page = offersService.page(pageable);


        if (offerFilter.getManufacturerId() != null) {
            if(offerFilter.getModelId() != null) {
                offers = offersService.getOffersByModel(offerFilter.getModelId());
                carModels = offersService.getCarModels(offerFilter.getManufacturerId());
            } else offers = offersService.getOffersByManufacturer(offerFilter.getManufacturerId());
        }


        model.addAttribute("carManufacturers", carManufacturers);
        model.addAttribute("carModels", carModels);
        model.addAttribute("offers", offers);
        return "offerList";
    }

    @GetMapping("/offer/{id}")
    public String offerDetails(Model model, @PathVariable("id") Integer id) {
        Offer offer = offersService.getOffer(id);
        model.addAttribute("offer", offer);
        return "offerDetails";
    }

    @GetMapping("/newoffer")
    public String newOfferForm(Model model, Offer offer) {
        addListForm(model);
        model.addAttribute("header", "New offer");
        model.addAttribute("action", "/newoffer");
        return "offerForm";
    }

    private void addListForm(Model model) {
        defaultModel(model);
    }

    @PostMapping("/newoffer")
    public String saveNewOffer(Model model, @Valid Offer offer, BindingResult binding) {
        if (binding.hasErrors()) {
            addListForm(model);
            return "offerForm";
        }
        model.addAttribute("header", "New offer");
        model.addAttribute("action", "/newoffer");
        offer = offersService.createOffer(offer);
        return "redirect:/offer/" + offer.getId();
    }


    @GetMapping("/deleteoffer/{id}")
    public String deleteOffer(Model model, @PathVariable("id") Integer id) {
        Offer offer = offersService.deleteOffer(id);
        model.addAttribute("offer", offer);
        return "deleteOffer";
    }

    @GetMapping("/editoffer/{id}")
    public String editOffer(Model model, @PathVariable("id") Integer id) {

        defaultModel(model);

        Offer offer = offersService.getOffer(id);
        model.addAttribute("offer", offer);
        model.addAttribute("header", "Editing an offer");
        model.addAttribute("action", "/editoffer/" + id);
        return "offerForm";
    }

    @PostMapping("/editoffer/{id}")
    public String saveEditedOffer(Model model, @PathVariable("id") Integer id, @Valid Offer offer, BindingResult binding) {
        if (binding.hasErrors()) {
            model.addAttribute("header", "Editing an offer");
            model.addAttribute("action", "/editoffer/" + id);

            defaultModel(model);

            return "offerForm";
        }
        offersService.saveOffer(offer);

        return "redirect:/offer/" + offer.getId();

    }

    private void defaultModel(Model model) {
        List<CarModel> carModels = offersService.getCarModels();
        List<BodyStyle> bodyStyles = offersService.getBodyStyles();
        List<FuelType> fuelTypes = offersService.getFuelTypes();

        model.addAttribute("carModels", carModels);
        model.addAttribute("bodyStyles", bodyStyles);
        model.addAttribute("fuelTypes", fuelTypes);
    }

}
