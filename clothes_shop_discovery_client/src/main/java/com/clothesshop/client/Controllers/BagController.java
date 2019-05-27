//package com.clothesshop.client.Controllers;
//
//import com.restapi.lab.DAL.BagRepository;
//import com.restapi.lab.Models.Bag;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.servlet.ModelAndView;
//
//
//@Controller
//@RequestMapping(path="bags")
//public class BagController {
//    @Autowired
//    private BagRepository bagRepository;
//
//    @RequestMapping(method = RequestMethod.GET)
//    public String all(Model model, String error, String logout) {
//
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        String name = auth.getName();
//        model.addAttribute("name", name);
//
//        model.addAttribute("bags", bagRepository.findAll());
//        return "bagsList";
//    }
//
//    @RequestMapping(value="/{id}",method = RequestMethod.GET)
//    public ModelAndView detail(@PathVariable("id") Integer id) {
//
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        String name = auth.getName();
//
//        ModelAndView model = new ModelAndView("bagsDetail");
//
//        model.addObject("name", name);
//
//        model.addObject("bag", bagRepository.findById(id).get());
//        return model;
//    }
//
//    @RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
//    public ModelAndView delete(@PathVariable("id") Integer id) {
//        bagRepository.deleteById(id);
//        return new ModelAndView("redirect:/bags");
//    }
//
//    @RequestMapping(value="/create", method=RequestMethod.POST)
//    public ModelAndView create(@ModelAttribute Bag bag) {
//        bagRepository.save(bag);
//        return new ModelAndView("redirect:/bags");
//    }
//
//    @RequestMapping(value="/update/{id}", method=RequestMethod.POST)
//    public ModelAndView update(@PathVariable("id") Integer id,@ModelAttribute Bag bag) {
//        bag.setId(id);
//        bagRepository.save(bag);
//        return new ModelAndView("redirect:/bags");
//    }
//
////    @RequestMapping(method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
////    public ResponseEntity<?> create(@RequestBody Bag bag){
////        try{
////            bagRepository.save(bag);
////        }
////        catch (Exception e) {
////            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
////        }
////        return new ResponseEntity(HttpStatus.OK);
////    }
////
////    @RequestMapping(value="/{id}",method = RequestMethod.PUT,produces = {MediaType.APPLICATION_JSON_VALUE})
////    public ResponseEntity<?> update(@PathVariable("id")Integer id, @RequestBody Bag bag){
////        try{
////            bag.setId(id);
////            bagRepository.save(bag);
////        }
////        catch (Exception e) {
////            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
////        }
////        return new ResponseEntity(HttpStatus.OK);
////    }
////
////    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
////    public ResponseEntity<?> delete(@PathVariable("id") Integer id){
////        try{
////            bagRepository.deleteById(id);
////        }
////        catch (Exception e) {
////            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
////        }
////        return new ResponseEntity(HttpStatus.OK);
////    }
////
////    @RequestMapping(value="/{id}",method = RequestMethod.GET)
////    public @ResponseBody
////    Optional<Bag> getbag(@PathVariable("id") Integer id) {
////        return bagRepository.findById(id);
////    }
////
////    @RequestMapping(method = RequestMethod.GET)
////    public @ResponseBody
////    Iterable<Bag> getAllbags() {
////        return bagRepository.findAll();
////    }
//
//
//}