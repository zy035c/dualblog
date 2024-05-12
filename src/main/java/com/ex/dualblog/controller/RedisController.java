// package com.ex.dualblog.controller;

// import com.ex.dualblog.utils.Result;
// import org.springframework.data.redis.core.RedisTemplate;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;


// @RestController 
// @RequestMapping("redis")
// public class RedisController {

//     private final RedisTemplate redisTemplate;

//     public RedisController(RedisTemplate redisTemplate) {
//         this.redisTemplate = redisTemplate;
//     }

//     @SuppressWarnings("unchecked")
//     @GetMapping("save")
//     public Result save(){
//         redisTemplate.opsForValue().set("key", "value");
//         return Result.success();
//     }
//     @GetMapping("get")
//     public Result get(){
//         String data = (String) redisTemplate.opsForValue().get("k");
//         return Result.success(data);
//     }

// }
