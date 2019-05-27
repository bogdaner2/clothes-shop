//package com.clothesshop.client.Controllers;
//
//import com.restapi.lab.DAL.EmployeeRepository;
//import com.restapi.lab.Models.Employee;
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
//@RequestMapping(path="employees")
//public class EmployeeController {
//    @Autowired
//    private EmployeeRepository employeeRepository;
//
//    @RequestMapping(method = RequestMethod.GET)
//    public String all(Model model, String error, String logout) {
//
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        String name = auth.getName();
//        model.addAttribute("name", name);
//
//        model.addAttribute("employees", employeeRepository.findAll());
//        return "employeesList";
//    }
//
//    @RequestMapping(value="/{id}",method = RequestMethod.GET)
//    public ModelAndView detail(@PathVariable("id") Integer id) {
//
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        String name = auth.getName();
//
//        ModelAndView model = new ModelAndView("employeesDetail");
//
//        model.addObject("name", name);
//
//        model.addObject("employee", employeeRepository.findById(id).get());
//        return model;
//    }
//
//    @RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
//    public ModelAndView delete(@PathVariable("id") Integer id) {
//        employeeRepository.deleteById(id);
//        return new ModelAndView("redirect:/employees");
//    }
//
//    @RequestMapping(value="/create", method=RequestMethod.POST)
//    public ModelAndView create(@ModelAttribute Employee employee) {
//        employeeRepository.save(employee);
//        return new ModelAndView("redirect:/employees");
//    }
//
//    @RequestMapping(value="/update/{id}", method=RequestMethod.POST)
//    public ModelAndView update(@PathVariable("id") Integer id,@ModelAttribute Employee employee) {
//        employee.setId(id);
//        employeeRepository.save(employee);
//        return new ModelAndView("redirect:/employees");
//    }
//
////    @RequestMapping(method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
////    public ResponseEntity<?> create(@RequestBody Employee employee){
////        try{
////            employeeRepository.save(employee);
////        }
////        catch (Exception e) {
////            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
////        }
////        return new ResponseEntity(HttpStatus.OK);
////    }
////
////    @RequestMapping(value="/{id}",method = RequestMethod.PUT,produces = {MediaType.APPLICATION_JSON_VALUE})
////    public ResponseEntity<?> update(@PathVariable("id")Integer id, @RequestBody Employee employee){
////        try{
////            employee.setId(id);
////            employeeRepository.save(employee);
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
////            employeeRepository.deleteById(id);
////        }
////        catch (Exception e) {
////            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
////        }
////        return new ResponseEntity(HttpStatus.OK);
////    }
////
////    @RequestMapping(value="/{id}",method = RequestMethod.GET)
////    public @ResponseBody
////    Optional<Employee> getemployee(@PathVariable("id") Integer id) {
////        return employeeRepository.findById(id);
////    }
////
////    @RequestMapping(method = RequestMethod.GET)
////    public @ResponseBody
////    Iterable<Employee> getAllemployees() {
////        return employeeRepository.findAll();
////    }
//}