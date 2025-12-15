package org.example.controller;

import org.example.model.BackGoods;
import org.example.model.Sku;
import org.example.service.BackGoodsService;
import org.example.service.HCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(
        origins = "*",
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS},
        allowedHeaders = "*",
        allowCredentials = "false",
        maxAge = 3600
)
@RestController
@RequestMapping("/api/HC")
public class HCController {

    @Autowired
    private HCService hcService;

    @PostMapping("/commit")
    public ResponseEntity<?> commitOrders(@RequestBody Sku sku) {
        Map<String, Object> result = new HashMap<>();
        try{
            boolean res = hcService.commit(sku);
            if(res){
                result.put("code", 200);
                result.put("message", "提交成功");
                return new ResponseEntity<>(result, HttpStatus.OK);
            }else{
                result.put("code", 500);
                result.put("message", "提交失败1");
                return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }catch (Exception e) {
            result.put("code", 500);
            result.put("message", "服务器错误");
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/check")
    public ResponseEntity<?> checkOrders(@RequestBody Sku sku) {
        Map<String, Object> result = new HashMap<>();
        try{
            List<Sku> res = hcService.check(sku);
            if(res != null){
                result.put("code", 200);
                result.put("message", "复核成功");
                result.put("data", res);
                return new ResponseEntity<>(result, HttpStatus.OK);
            }else{
                result.put("code", 500);
                result.put("message", "复核失败1");
                return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }catch (Exception e) {
            result.put("code", 500);
            result.put("message", "服务器错误");
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/delete")
    public ResponseEntity<?> deleteOrders(@RequestBody Sku sku) {
        Map<String, Object> result = new HashMap<>();
        try {
            boolean res = hcService.delete(sku);
            if (res) {
                result.put("code", 200);
                result.put("message", "删除成功");
                return new ResponseEntity<>(result, HttpStatus.OK);
            } else {
                result.put("code", 500);
                result.put("message", "删除失败1");
                return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            result.put("code", 500);
            result.put("message", "服务器错误");
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/deleteAll")
    public ResponseEntity<?> deleteAllOrders() {
        Map<String, Object> result = new HashMap<>();
        try {
            boolean res = hcService.deleteAll();
            if (res) {
                result.put("code", 200);
                result.put("message", "删除成功");
                return new ResponseEntity<>(result, HttpStatus.OK);
            } else {
                result.put("code", 500);
                result.put("message", "删除失败1");
                return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            result.put("code", 500);
            result.put("message", "服务器错误");
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }
    @GetMapping("/deleteChaos")
    public ResponseEntity<?> chaosAllOrders() {
        Map<String, Object> result = new HashMap<>();
        try {
            boolean res = hcService.deleteChaos();
            if (res) {
                result.put("code", 200);
                result.put("message", "删除成功");
                return new ResponseEntity<>(result, HttpStatus.OK);
            } else {
                result.put("code", 500);
                result.put("message", "删除失败1");
                return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            result.put("code", 500);
            result.put("message", "服务器错误");
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }










}
