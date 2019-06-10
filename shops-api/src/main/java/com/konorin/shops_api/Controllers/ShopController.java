package com.konorin.shops_api.Controllers;


import com.konorin.shops_api.Models.Shop;
import com.konorin.shops_api.Services.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/shops")
public class ShopController {

    @Autowired
    private ShopService service;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<?> getAll() {

        try {
            return new ResponseEntity(service.GetAll(), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<?> get(@PathVariable("id") Integer id) {
        if(id <= 0) return new ResponseEntity("Id have to be more than 0", HttpStatus.BAD_REQUEST);

        try {
            return new ResponseEntity(service.Get(id), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> create(@RequestBody Shop shop){
        try{
            return new ResponseEntity(service.Create(shop), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value="/{id}",method = RequestMethod.PUT,produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> update(@PathVariable("id")Integer id, @RequestBody Shop shop){
        if(id <= 0) return new ResponseEntity("Id have to be more than 0", HttpStatus.BAD_REQUEST);

        try{
            return new ResponseEntity(service.Update(id, shop), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable("id") Integer id){
        if(id <= 0) return new ResponseEntity("Id have to be more than 0", HttpStatus.BAD_REQUEST);

        try{
            return new ResponseEntity(service.Delete(id), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
