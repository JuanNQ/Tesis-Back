package com.proyect.sistvehiculosapi.Controller;

import com.proyect.sistvehiculosapi.DTO.InternmentDTO;
import com.proyect.sistvehiculosapi.Entity.InternmentEntity;
import com.proyect.sistvehiculosapi.Service.InternmentService;
import com.proyect.sistvehiculosapi.Service.InternmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/internment")
public class InternmentController {

    @Autowired
    private InternmentService internmentService;

    @PostMapping("/saveInternment")
    public ResponseEntity<?> saveInternment(@RequestBody InternmentDTO internmentDTO){
        try {
            System.out.println("-------"+internmentDTO);
            return new ResponseEntity<>(internmentService.SaveInterment(internmentDTO), HttpStatus.OK);
        } catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }


    @GetMapping("/getInternment/{id}")
    public ResponseEntity<?> getInternment(@PathVariable(name = "id") int id){
        try{
            return new ResponseEntity<>(internmentService.getInternment(id),HttpStatus.OK);
        } catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/getListPageInternment")
    public ResponseEntity<?> getListPageInternment(@RequestParam(name = "size") int size,
                                                   @RequestParam(name = "page") int page,
                                                   @RequestParam(name = "search") String search){
        try{
            return new ResponseEntity<>(internmentService.getListPageInternment(PageRequest.of(page,size),search),HttpStatus.OK);
        } catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

//    @DeleteMapping("/deleteInternment/{id)")
    @RequestMapping(value = "/deleteInternment/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteInternment(@PathVariable("id") int id){
        try{
            System.out.println("-----------");
            internmentService.deleteInterment(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
}
