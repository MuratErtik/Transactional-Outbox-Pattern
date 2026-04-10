package org.payment;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "payments", path = "payments")
public interface PaymentRepository  extends JpaRepository<Payment, Long> {
    Optional<Payment> findByOrderId(Long orderId);
}
