package org.i18ntest.i18ntest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {
    private final MessageAccessor messageAccessor;

    @GetMapping("/hello")
    public String hello() {
        return messageAccessor.getMessage("say.hello");
    }
}
