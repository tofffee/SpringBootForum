package com.example.springforumapp.users.controllers.api;

import com.example.springforumapp.common.api.ResponseApi;
import com.example.springforumapp.common.api.ResponseStatusApi;
import com.example.springforumapp.common.api.ResponseSuccessApi;
import com.example.springforumapp.users.services.UsersService;
import com.example.springforumapp.users.services.UsersServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminControllerApi {
    private final UsersService usersService;

    @PatchMapping("/grant/users/{id}")
    public ResponseEntity<ResponseApi> grantAdminRole(@PathVariable("id") long id){
        usersService.grantAdminRole(id);
        return ResponseEntity.ok(new ResponseSuccessApi(ResponseStatusApi.SUCCESS, "Admin rights were granted"));
    }

    @PatchMapping("/ungrant/users/{id}")
    public ResponseEntity<ResponseApi> ungrantAdminRole(@PathVariable("id") long id){
        usersService.ungrantAdminRole(id);
        return ResponseEntity.ok(new ResponseSuccessApi(ResponseStatusApi.SUCCESS, "Admin rights were deleted"));
    }

    @DeleteMapping ("/delete/users/{id}")
    public ResponseEntity<ResponseApi> deleteUser(@PathVariable("id") long id){
        usersService.deleteUser(id);
        return ResponseEntity.ok(new ResponseSuccessApi(ResponseStatusApi.SUCCESS, "User was deleted"));
    }
}
