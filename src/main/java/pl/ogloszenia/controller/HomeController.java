package pl.ogloszenia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.ogloszenia.jpa.*;
import pl.ogloszenia.repository.OfferRepository;
import pl.ogloszenia.service.OffersService;
import pl.ogloszenia.web.OfferFilter;

import javax.validation.Valid;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    OffersService offersService;

    @GetMapping("/")
    public String home(Model model, OfferFilter offerFilter,@PageableDefault(size = 10) Pageable pageable) {
        List<CarManufacturer> carManufacturers = offersService.getCarManufacturers();
        List<CarModel> carModels = offersService.getCarModels();
        List<Offer> offers;
        Page<Offer> page = offersService.page(pageable);


        if (offerFilter.getManufacturerId() != null) {
            if ( offerFilter.getModelId() != null) {
                page = (Page<Offer>) offersService.getOffersByModel(offerFilter.getModelId());
                carModels = offersService.getCarModels(offerFilter.getManufacturerId());
            } else page = (Page<Offer>) offersService.getOffersByManufacturer(offerFilter.getManufacturerId());

        } else {
        //    offers = offersService.getOffers();
            page=offersService.page(pageable);
        }

        model.addAttribute("carManufacturers", carManufacturers);
        model.addAttribute("carModels", carModels);
        model.addAttribute("page", page);
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
        model.addAttribute("header", "Nowe ogłoszenie");
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
        model.addAttribute("header", "Nowe ogłoszenie");
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
        model.addAttribute("header", "Edycja ogłoszenia");
        model.addAttribute("action", "/editoffer/" + id);
        return "offerForm";
    }

    @PostMapping("/editoffer/{id}")
    public String saveEditedOffer(Model model, @PathVariable("id") Integer id, @Valid Offer offer, BindingResult binding) {
        if (binding.hasErrors()) {
            model.addAttribute("header", "Edycja ogłoszenia");
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
