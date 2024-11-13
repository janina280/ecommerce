package com.ecommerce.topit.bean;

import com.ecommerce.topit.services.LuceneService;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

@Component
public class LuceneServiceCleanup {

    private final LuceneService luceneService;

    public LuceneServiceCleanup(LuceneService luceneService) {
        this.luceneService = luceneService;
    }

    @PreDestroy
    public void cleanup() {
        luceneService.closeIndexWriter();
        luceneService.closeIndex();
    }
}
