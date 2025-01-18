package com.himanshu.modulith_demo.module1;

import com.himanshu.modulith_demo.module1.internal.InternalData1;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Module1Api {

    private final InternalData1 internalData1;

    public Module1Api(InternalData1 internalData1) {
        this.internalData1 = internalData1;
    }

    @PostMapping("/sendEvent")
    public void sendEvent() {
        internalData1.sendEvent();
    }
}
