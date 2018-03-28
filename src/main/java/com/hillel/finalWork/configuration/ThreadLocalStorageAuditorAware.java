// Copyright (c) Philipp Wagner. All rights reserved.
// Licensed under the MIT license. See LICENSE file in the project root for full license information.

package com.hillel.finalWork.configuration;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

@Component
public class ThreadLocalStorageAuditorAware implements AuditorAware<String> {

    @Override
    public String getCurrentAuditor() {
        return "admin";
//        return (String) RequestContextHolder
//                .currentRequestAttributes()
//                .getAttribute("Username", SCOPE_REQUEST);
    }

}
