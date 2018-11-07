package com.zhou.medical.manager.client.account;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "ACCOUNT-SERVICE")
@RequestMapping("/doctorsLoginInfo")
public interface DoctorsLoginInfoFeignClient {
}
