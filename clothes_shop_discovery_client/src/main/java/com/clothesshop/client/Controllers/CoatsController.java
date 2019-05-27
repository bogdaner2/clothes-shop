//package com.clothesshop.client.Controllers;
//
//
//import com.restapi.lab.DAL.CoatRepository;
//import com.restapi.lab.Models.Coat;
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
//@RequestMapping(path="coats")
//public class CoatsController {
//    @Autowired
//   private CoatRepository coatRepository;
//
//    @RequestMapping(method = RequestMethod.GET)
//    public String all(Model model, String error, String logout) {
//
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        String name = auth.getName();
//        model.addAttribute("name", name);
//
//        model.addAttribute("coats", coatRepository.findAll());
//        return "coatsList";
//    }
//
//    @RequestMapping(value="/{id}",method = RequestMethod.GET)
//    public ModelAndView detail(@PathVariable("id") Integer id) {
//
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        String name = auth.getName();
//
//        ModelAndView model = new ModelAndView("coatDetail");
//
//        model.addObject("name", name);
//
//        model.addObject("coat", coatRepository.findById(id).get());
//        return model;
//    }
//
//    @RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
//    public ModelAndView delete(@PathVariable("id") Integer id) {
//        coatRepository.deleteById(id);
//        return new ModelAndView("redirect:/coats");
//    }
//
//    @RequestMapping(value="/create", method=RequestMethod.POST)
//    public ModelAndView create(@ModelAttribute Coat coat) {
//        coatRepository.save(coat);
//        return new ModelAndView("redirect:/coats");
//    }
//
//    @RequestMapping(value="/update/{id}", method=RequestMethod.POST)
//    public ModelAndView update(@PathVariable("id") Integer id,@ModelAttribute Coat coat) {
//        coat.setId(id);
//        coatRepository.save(coat);
//        return new ModelAndView("redirect:/coats");
//    }
//
////    @RequestMapping(method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
////    public ResponseEntity<?> create(@RequestBody Coat coat){
////        try{
////        coatRepository.save(coat);
////        }
////        catch (Exception e) {
////            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
////        }
////        return new ResponseEntity(HttpStatus.OK);
////    }
////
////    @RequestMapping(value="/{id}",method = RequestMethod.PUT,produces = {MediaType.APPLICATION_JSON_VALUE})
////    public ResponseEntity<?> update(@PathVariable("id")Integer id, @RequestBody Coat coat){
////        try{
////            coat.setId(id);
////            coatRepository.save(coat);
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
////            coatRepository.deleteById(id);
////        }
////        catch (Exception e) {
////            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
////        }
////        return new ResponseEntity(HttpStatus.OK);
////    }
////
////    @RequestMapping(value="/{id}",method = RequestMethod.GET)
////   public @ResponseBody
////    Optional<Coat> getCoat(@PathVariable("id") Integer id) {
////        return coatRepository.findById(id);
////   }
//
//
//}