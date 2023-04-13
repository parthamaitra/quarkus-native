package co.partha.repository;

import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;

import co.partha.entities.UserEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import lombok.extern.slf4j.Slf4j;

@ApplicationScoped
@Slf4j
public class UserRepository implements PanacheRepository<UserEntity>{

   
    

    public Optional<UserEntity> findUsingNativeQuery(String query, String name, String phone) {
        log.info("findUsingNativeQuery is called using native query <{}> for name <{}> and phone <{}>", query, name, phone);
        
        List<UserEntity> resultList = getEntityManager().createNativeQuery(query, UserEntity.class)
        .setParameter(1, name)
        .setParameter(2, phone).getResultList();
        log.info("resultList is <{}>", resultList);
        return resultList.stream().findFirst();

    }

    
}
