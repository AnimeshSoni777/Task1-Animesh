package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.service.AIService;
import com.example.demo.util.MathUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class Controller {

    @Value("${official.email}")
    private String email;

    private final AIService aiService;

    public Controller(AIService aiService) {
        this.aiService = aiService;
    }

    @PostMapping("/bfhl")
    public Object bfhl(@RequestBody Map<String, Object> body) {

        if (body.size() != 1) {
            return new ApiResponse<>(false, email, "Exactly one key required");
        }

        String key = body.keySet().iterator().next();
        Object value = body.get(key);

        try {
            switch (key) {

                case "fibonacci":
                    int n = (int) value;
                    return new ApiResponse<>(true, email, MathUtil.fibonacci(n));

                case "prime":
                    List<Integer> nums = (List<Integer>) value;
                    List<Integer> primes = nums.stream()
                            .filter(MathUtil::isPrime)
                            .toList();
                    return new ApiResponse<>(true, email, primes);

                case "lcm":
                    List<Integer> lcmList = (List<Integer>) value;
                    int lcm = lcmList.stream().reduce(MathUtil::lcm).get();
                    return new ApiResponse<>(true, email, lcm);

                case "hcf":
                    List<Integer> hcfList = (List<Integer>) value;
                    int hcf = hcfList.stream().reduce(MathUtil::gcd).get();
                    return new ApiResponse<>(true, email, hcf);

                case "AI":
                    String question = value.toString();
                    return new ApiResponse<>(true, email, aiService.askAI(question));

                default:
                    return new ApiResponse<>(false, email, "Invalid key");
            }
        } catch (Exception e) {
            return new ApiResponse<>(false, email, "Bad Request");
        }
    }
}
