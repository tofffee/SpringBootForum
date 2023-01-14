package com.example.springforumapp.users.controllers.api;

import com.example.springforumapp.common.api.ResponseApi;
import com.example.springforumapp.common.api.ResponseStatusApi;
import com.example.springforumapp.common.api.ResponseSuccessApi;
import com.example.springforumapp.users.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/admin")
public class AdminControllerApi {
    private final UsersService usersService;

    @Autowired
    public AdminControllerApi(UsersService usersService) {
        this.usersService = usersService;
    }

    @PatchMapping("/grant/users/{id}")
    public ResponseEntity<ResponseApi> grantAdminRole(@PathVariable("id") int id){
        usersService.grantAdminRole(id);
        return ResponseEntity.ok(new ResponseSuccessApi(ResponseStatusApi.SUCCESS, HttpStatus.OK.value(), "Admin rights were granted"));
    }

    @PatchMapping("/ungrant/users/{id}")
    public ResponseEntity<ResponseApi> ungrantAdminRole(@PathVariable("id") int id){
        usersService.ungrantAdminRole(id);
        return ResponseEntity.ok(new ResponseSuccessApi(ResponseStatusApi.SUCCESS, HttpStatus.OK.value(), "Admin rights were deleted"));
    }

    @DeleteMapping ("/delete/users/{id}")
    public ResponseEntity<ResponseApi> deleteUser(@PathVariable("id") int id){
        usersService.deleteUser(id);
        return ResponseEntity.ok(new ResponseSuccessApi(ResponseStatusApi.SUCCESS, HttpStatus.OK.value(), "User was deleted"));
    }
}
