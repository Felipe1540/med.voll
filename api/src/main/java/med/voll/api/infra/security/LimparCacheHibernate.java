package med.voll.api.infra.security;

import org.hibernate.Cache;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LimparCacheHibernate {

    @Autowired
    private SessionFactory sessionFactory;

    public void limparCache() {
        Cache cache = sessionFactory.getCache();
        cache.evictAllRegions();
    }
}

