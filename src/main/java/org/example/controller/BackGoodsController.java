package org.example.controller;

import org.example.model.BackGoods;
import org.example.model.SubOrder;
import org.example.service.BackGoodsService;
import org.example.service.SubService;
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
@RequestMapping("/api/backgoods")
public class BackGoodsController {

    @Autowired
    private BackGoodsService backgoodsService;
    @GetMapping("/checkCompany")
    public ResponseEntity<?> checkCompany() {
        Map<String, Object> result = new HashMap<>();
        try{
            List<BackGoods> backGoods = backgoodsService.checkCompany();
            if(backGoods != null){
                result.put("status", 200);
                result.put("message", "查询成功");
                result.put("data", backGoods);
                return new ResponseEntity<>(result, HttpStatus.OK);
            }else{
                result.put("status", 500);
                result.put("message", "查询失败");
                return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }catch (Exception e){
            result.put("status", 500);
            result.put("message", "查询失败");
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/commitOrders")
    public ResponseEntity<?> commitOrders(@RequestBody List<BackGoods> backGoodsList) {
        Map<String, Object> result = new HashMap<>();
        try{
            boolean res = backgoodsService.commitOrders(backGoodsList);
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
    @GetMapping("/getOrdersList")
    public ResponseEntity<?> getOrdersList() {
        Map<String, Object> result = new HashMap<>();
        try{
            List<BackGoods> backGoods = backgoodsService.getOrdersList();
            if(backGoods != null){
                result.put("status", 200);
                result.put("message", "查询成功");
                result.put("data", backGoods);
                return new ResponseEntity<>(result, HttpStatus.OK);
            }else{
                result.put("status", 500);
                result.put("message", "查询失败");
                return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }catch (Exception e){
            result.put("status", 500);
            result.put("message", "查询失败");
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("getOrderDetail")
    public ResponseEntity<?> getOrderDetail(@RequestBody BackGoods backGoods) {
        Map<String, Object> result = new HashMap<>();
        try{
            List<BackGoods> backGoodsList = backgoodsService.getOrderDetail(backGoods);
            if(backGoodsList != null){
                result.put("status", 200);
                result.put("message", "查询成功");
                result.put("data", backGoodsList);
                return new ResponseEntity<>(result, HttpStatus.OK);
            }else{
                result.put("status", 500);
                result.put("message", "查询失败");
                return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }catch (Exception e){
            result.put("status", 500);
            result.put("message", "查询失败");
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/verify")
    public ResponseEntity<?> verify(@RequestBody BackGoods backGoods) {
        Map<String, Object> result = new HashMap<>();
        try{
            boolean res = backgoodsService.verify(backGoods);
            if(res){
                result.put("code", 200);
                result.put("message", "审核成功");
                return new ResponseEntity<>(result, HttpStatus.OK);
            }else{
                result.put("code", 500);
                result.put("message", "审核失败");
                return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }catch (Exception e) {
            result.put("code", 500);
            result.put("message", "服务器错误");
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }








}
