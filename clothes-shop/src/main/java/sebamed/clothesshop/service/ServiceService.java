package sebamed.clothesshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sebamed.clothesshop.repository.ServiceRepository;
import sebamed.clothesshop.domain.Services;

@Service
public class ServiceService {

	@Autowired
	ServiceRepository serviceRepository;
	
	public Services addService(Services service) {
		return this.serviceRepository.save(service);
	}
	
	public Services updateService(Services service) {
		return this.serviceRepository.findOneById(service.getId());
	}
	
	public Services save(Services service) {
		return this.serviceRepository.save(service);
	}
	
}
