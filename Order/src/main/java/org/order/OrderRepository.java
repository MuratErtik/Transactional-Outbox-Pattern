package org.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//that annotation creates REST Methods automatically
@RepositoryRestResource(collectionResourceRel = "orders",path = "orders ")
public interface OrderRepository extends JpaRepository<Order, Long> {
}
